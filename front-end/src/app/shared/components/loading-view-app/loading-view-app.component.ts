import { Component, OnInit } from '@angular/core';
import { LoadingViewAppService } from '../../services/loading-view-app-service';
import { trigger, transition, style, animate } from '@angular/animations';

@Component({
  selector: 'app-loading-view-app',
  templateUrl: './loading-view-app.component.html',
  styleUrls: ['./loading-view-app.component.css'],
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [   // :enter is alias to 'void => *'
        style({opacity: 0}),
        animate(300, style({opacity: 0.6})),
      ]),
      transition(':leave', [   // :enter is alias to 'void => *'
        animate(300, style({opacity: 0})),
      ])
    ])
  ],
})
export class LoadingViewAppComponent implements OnInit {

  loading;
  constructor(
    private loadingStateService: LoadingViewAppService,
  ) { }

  ngOnInit() {
    this.loadingStateService.currentLoadingState.subscribe(
      (loadingState) => {
        this.setLoadingState(loadingState);
      }
    );
  }
  private setLoadingState(loadingState: boolean) {
    this.loading = loadingState;
  }

}
