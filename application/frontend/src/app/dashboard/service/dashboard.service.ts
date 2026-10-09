import { inject, Service } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../../environments/environment';
import { Job, Candidate } from '../model';

@Service()
export class DashboardService {
  private readonly httpClient = inject(HttpClient);
  private readonly apiUrl = environment.apiUrl;

  getJobs() {
    return this.httpClient.get<Job[]>(`${this.apiUrl}/job/all`);
  }

  getCandidates() {
    return this.httpClient.get<Candidate[]>(`${this.apiUrl}/candidate/all`);
  }

  getCandidateById(id: string) {
    return this.httpClient.get<Candidate>(`${this.apiUrl}/candidate/${id}`);
  }
}
