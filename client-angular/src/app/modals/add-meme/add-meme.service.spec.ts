import { TestBed } from '@angular/core/testing';

import { AddMemeService } from './add-meme.service';

describe('AddMemeService', () => {
  let service: AddMemeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddMemeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
