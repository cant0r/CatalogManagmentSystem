import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainerTypeViewComponent } from './container-type-view.component';

describe('ContainerTypeViewComponent', () => {
  let component: ContainerTypeViewComponent;
  let fixture: ComponentFixture<ContainerTypeViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContainerTypeViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContainerTypeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
