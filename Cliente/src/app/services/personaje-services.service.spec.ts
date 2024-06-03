import { TestBed } from '@angular/core/testing';

import { PersonajeServicesService } from './personaje-services.service';

describe('PersonajeServicesService', () => {
  let service: PersonajeServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PersonajeServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
