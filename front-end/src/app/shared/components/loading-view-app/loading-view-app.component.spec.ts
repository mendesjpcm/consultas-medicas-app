import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadingViewAppComponent } from './loading-view-app.component';

describe('LoadingViewAppComponent', () => {
  let component: LoadingViewAppComponent;
  let fixture: ComponentFixture<LoadingViewAppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoadingViewAppComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoadingViewAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
