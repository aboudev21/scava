import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ListProjectService } from '../../../../shared/services/project-service/list-project.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AnalysisAlgorithmMgmtAddDialogComponent } from './analysis-algorithm-add-dialog.component';
import { AnalysisTaskService } from '../../../../shared/services/analysis-task/analysis-task.service';
import { ExecutionTask } from './execution-task.model';

@Component({
    selector: 'app-configure-project',
    templateUrl: './configure-project.component.html',
    styleUrls: ['./configure-project.component.scss']
})
export class ConfigureProjectComponent implements OnInit {

    project: any = null;
    executionTasks: ExecutionTask[];

    constructor(
        private route: ActivatedRoute,
        private listProjectService: ListProjectService,
        private analysisTaskService: AnalysisTaskService,
        public modalService: NgbModal
    ) { }

    ngOnInit() {
        this.loadAll();
    }

    loadAll() {
        this.route.paramMap.subscribe(data => {
            this.listProjectService.getProject(data.get('id')).subscribe(data => {
                this.project = data;
                this.analysisTaskService.getTasksbyProject(this.project.name).subscribe((resp) => {
                    this.executionTasks = resp as ExecutionTask[];
                });
            }, error => {
                console.log(error);
            });
        });
    }

    setProgressStyles(executionTask: any) {
        let styles = {
            'width': executionTask.scheduling.progress + '%',
        };
        return styles;
    }

    addAnalysisAlgorithm() {
        const modalRef = this.modalService.open(AnalysisAlgorithmMgmtAddDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.result.then(
            result => {
                console.log('delete success');
                this.loadAll();
            },
            reason => {
                console.log('delete faild');
                this.loadAll();
            }
        );
    }



}
