import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainerViewComponent } from './container-view.component';

describe('ContainerViewComponent', () => {
  let component: ContainerViewComponent;
  let fixture: ComponentFixture<ContainerViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContainerViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContainerViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
