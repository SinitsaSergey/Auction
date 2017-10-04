import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LotDetailsComponent } from './lot-details.component';

describe('LotDetailsComponent', () => {
  let component: LotDetailsComponent;
  let fixture: ComponentFixture<LotDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LotDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LotDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
