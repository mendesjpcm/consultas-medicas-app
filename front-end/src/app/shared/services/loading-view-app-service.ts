import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoadingViewAppService {

  constructor() { }

  private loadingState = new BehaviorSubject<boolean>(false);
  currentLoadingState = this.loadingState.asObservable();


  setLoadingState(state: boolean) {
    this.loadingState.next(state);
  }
  getLoadingState() {
    return this.currentLoadingState;
  }
}
