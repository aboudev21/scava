import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment.prod';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LocalStorageService } from '../authentication/local-storage.service';
import { Observable } from 'rxjs';
import { ExecutionTask, MetricProvider } from '../../../layout/project/components/configure-project/execution-task.model';

@Injectable({
  providedIn: 'root'
})
export class AnalysisTaskService {

  private resourceUrl: string = environment.SERVER_API_URL + '/administration';
  private analysis: string = 'analysis';
  private task: string = 'task';
  private create: string = 'create';
  private update: string = 'update'
  private start: string = 'start';
  private stop: string = 'stop';
  private reset: string = 'reset';
  private delete: string = 'delete';
  private tasks: string = 'tasks';
  private project: string = 'project'
  private promote: string = 'promote';
  private demote: string = 'demote';
  private push: string = 'pushOnWorker';
  private metricproviders: string = 'metricproviders';
  private workers: string = 'workers';
  private jwtToken: string = null;

  constructor(
    private httpClient: HttpClient,
    private localStorageService: LocalStorageService
  ) { }

  createTask(executionTask: ExecutionTask): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.post(`${this.resourceUrl}/${this.analysis}/${this.task}/${this.create}`, executionTask,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  updateTask(executionTask: ExecutionTask): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.put(`${this.resourceUrl}/${this.analysis}/${this.task}/${this.update}`, executionTask,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) })
  }

  getTasks(): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.get(`${this.resourceUrl}/${this.analysis}/${this.tasks}`,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  getTasksbyProject(projectId: string): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.get(`${this.resourceUrl}/${this.analysis}/${this.tasks}/${this.project}/${projectId}`,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  getTaskByAnalysisTaskId(analysisTaskId: string): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.get(`${this.resourceUrl}/${this.analysis}/${this.tasks}/${analysisTaskId}`,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  startTask(analysisTaskId: string): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.post(`${this.resourceUrl}/${this.analysis}/${this.task}/${this.start}`,
      { "analysisTaskId": analysisTaskId },
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  stopTask(analysisTaskId: string): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.post(`${this.resourceUrl}/${this.analysis}/${this.task}/${this.stop}`,
      { "analysisTaskId": analysisTaskId },
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  resetTask(analysisTaskId: string): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.post(`${this.resourceUrl}/${this.analysis}/${this.task}/${this.reset}`,
      { "analysisTaskId": analysisTaskId },
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  deleteTask(analysisTaskId: string): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.delete(`${this.resourceUrl}/${this.analysis}/${this.task}/${this.delete}/${analysisTaskId}`,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  promoteTask(analysisTaskId: string): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.delete(`${this.resourceUrl}/${this.analysis}/${this.task}/${this.promote}/${analysisTaskId}`,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  demoteTask(analysisTaskId: string): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.delete(`${this.resourceUrl}/${this.analysis}/${this.task}/${this.demote}/${analysisTaskId}`,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }
  
  pushOnWorker(analysisTaskId: string,workerId: string): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.delete(`${this.resourceUrl}/${this.analysis}/${this.task}/${this.push}/${analysisTaskId}/w/${workerId}`,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  getMetricProviders(): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.get(`${this.resourceUrl}/${this.analysis}/${this.metricproviders}`,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

  getWorkers(): Observable<ExecutionTask> {
    if (this.jwtToken == null) {
      this.jwtToken = this.localStorageService.loadToken();
    }
    return this.httpClient.get(`${this.resourceUrl}/${this.analysis}/${this.workers}`,
      { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
  }

}