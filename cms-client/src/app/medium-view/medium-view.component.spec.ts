import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MediumViewComponent } from './medium-view.component';

describe('MediumViewComponent', () => {
  let component: MediumViewComponent;
  let fixture: ComponentFixture<MediumViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MediumViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MediumViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
