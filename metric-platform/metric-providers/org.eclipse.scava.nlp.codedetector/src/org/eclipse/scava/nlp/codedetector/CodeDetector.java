/*******************************************************************************
 * Copyright (C) 2018 Edge Hill University
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.scava.nlp.codedetector;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import cc.fasttext.FastText;
import cc.fasttext.Vector;
import org.eclipse.scava.nlp.predictionmanager.Prediction;
import org.eclipse.scava.nlp.preprocessor.normalizer.Normalizer;

public class CodeDetector
{
//Singleton not working outside
	private static FastText codeDetector;
	private static CodeDetectorFormater formatter;
	
	/** The {@code defaultLabel} is the label that it is used when a string, after formatting it for the Code Detector, is empty.
	 * Thus, the Code Detector will be unable to determine a label. The default value is <i>__label__English</i>, as many of the cases may
	 * occur in English contexts. In theory, if the pre-processing tool was used previously, the only case were it is going to be used is a string composed
	 * uniquely of a comma or a set of it, e.g. "," or ",,,,,,,".
	 */
	private static String defaultLabel="__label__English";

	static
	{
		CodeDetectorSingleton singleton=CodeDetectorSingleton.getInstance();
		formatter = singleton.getFormatter();
		codeDetector = singleton.getCodeDetector();
	}
	
	private static String formatter(String input)
	{
		input=Normalizer.normalize(input);
		return formatter.apply(input);
	}
	
	public static void printWordVector(String input)
	{
		input=formatter(input);
		Vector vec = codeDetector.getWordVector(input);
        System.out.println(input + " " + vec);
	}
	
	public static void printSentenceVector(String input)
	{
		Vector vec;
		try
		{
			input=formatter(input);
			vec = codeDetector.getSentenceVector(input);
			System.out.println(input + " " + vec);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Predicts whether a text is Code or English.
	 * @param text Input to analyze. To have better results, text must have been normalized with the text normalizer.
     * @return {@link Prediction}, where it is kept the input text, 
     * its label (<i>__label__Code</i> or <i>__label__English</i>), and label's probabilities (float).
     * 
     * <br><b>Note:</b> In the case that {@code text} is an empty string after being formatted for the Code Detector,
     * it will return a {@link CodeDetector#defaultLabel}. The {@code Prediction}, beside having the default label,
     * can be recognized as well for having a probability of -1.0.
	 */
	public static Prediction predict(String text)
	{
		//System.out.println(input);
		String formattedText=formatter(text);
		if(formattedText.isEmpty())
		{
			Prediction defaultPrediction= new Prediction();
			defaultPrediction.set(text, defaultLabel, (float) -1.0);
			return defaultPrediction;
		}
		//System.out.println(formattedText);
		//The new line is added in order to have the same output that the C++ version. In fact the new line character is used to predict unseen words.
		formattedText += "\n";
		InputStream formattedTextAsIStream = new ByteArrayInputStream(formattedText.getBytes());
		Stream<Map<String, Float>> prediction = codeDetector.predict(formattedTextAsIStream, 1);
		return PredictionConsumeStream(text, prediction);
	}
	
	
	/**
	 * Predicts whether a text is Code or English.
	 * @param text Input to analyze. To have better results, text must have been normalized with the text normalizer.
	 * @param defaultLabel In the case that {@code text} is an empty string, this method returns a {@link Prediction} with
	 * the label defined in {@code defaultLabel} and a probability of -1.0.
	 * @return {@link Prediction}, where it is kept the input text, 
     * its label (<i>__label__Code</i> or <i>__label__English</i>), and label's probabilities (float).
     */
	public static Prediction predict(String text, String defaultLabel)
	{
		//System.out.println(input);
		String formattedText=formatter(text);
		if(formattedText.isEmpty())
		{
			Prediction defaultPrediction= new Prediction();
			defaultPrediction.set(text, defaultLabel, (float) -1.0);
			return defaultPrediction;
		}
		//System.out.println(formattedText);
		//The new line is added in order to have the same output that the C++ version. In fact the new line character is used to predict unseen words.
		formattedText += "\n";
		InputStream formattedTextAsIStream = new ByteArrayInputStream(formattedText.getBytes());
		Stream<Map<String, Float>> prediction = codeDetector.predict(formattedTextAsIStream, 1);
		return PredictionConsumeStream(text, prediction);
	}
	
	/**
	 * Predicts for each element of a list of texts whether it is Code or English.
	 * @param textList Input to analyze. To have better results, textList must have been normalized with the text normalizer.
     * @return {@code List<Prediction>}, where it is kept, for each entry of <b>textList</b>, the input text, 
     * its label (<i>__label__Code</i> or <i>__label__English</i>), and label's probabilities (float).
     * 
     * * <br><b>Note:</b> In the case that and element of {@code textList} is an empty string after being formatted for the Code Detector,
     * it will return a {@link CodeDetector#defaultLabel}. The {@code Prediction}, beside having the default label,
     * can be recognized as well for having a probability of -1.0.
     * 
     * @see Prediction
	 */
	public static List<Prediction> predict(List <String> textList)
	{
		List<Prediction> predictionList = new ArrayList<Prediction>();
		//It must be forEachOrdered otherwise, the output may not keep the same input order
		textList.stream().forEachOrdered(text->predictionList.add(predict(text)));
		return(predictionList);
	}
	
	/**
	 * Predicts for each element of a list of texts whether it is Code or English.
	 * @param textList Input to analyze. To have better results, textList must have been normalized with the text normalizer.
	 * @param defaultLabel In the case an element of {@code textList} is an empty string, this method returns a {@link Prediction} with
	 * the label defined in {@code defaultLabel} and a probability of -1.0.
	 * @return {@code List<Prediction>}, where it is kept, for each entry of <b>textList</b>, the input text, 
     * its label (<i>__label__Code</i> or <i>__label__English</i>), and label's probabilities (float).
	 */
	public static List<Prediction> predict(List <String> textList, String defaultLabel)
	{
		List<Prediction> predictionList = new ArrayList<Prediction>();
		//It must be forEachOrdered otherwise, the output may not keep the same input order
		textList.stream().forEachOrdered(text->predictionList.add(predict(text, defaultLabel)));
		return(predictionList);
	}
	
	private static Prediction PredictionConsumeStream(String originalInput, Stream<Map<String, Float>> output)
	{
		Prediction prediction= new Prediction();
		output.map(map->map.entrySet().stream().findFirst().get()).forEachOrdered(e->prediction.set(originalInput, e.getKey(),e.getValue()));
		return prediction;
	}
}
