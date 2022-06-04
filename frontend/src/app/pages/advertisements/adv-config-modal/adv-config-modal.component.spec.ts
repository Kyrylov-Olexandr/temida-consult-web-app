import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvConfigModalComponent } from './adv-config-modal.component';

describe('AdvConfigModalComponent', () => {
  let component: AdvConfigModalComponent;
  let fixture: ComponentFixture<AdvConfigModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvConfigModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvConfigModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
