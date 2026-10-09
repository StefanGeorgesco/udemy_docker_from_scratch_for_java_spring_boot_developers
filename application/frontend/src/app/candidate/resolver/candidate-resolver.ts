import { ResolveFn, Router } from '@angular/router';
import { inject } from '@angular/core';

import { Candidate } from '../../dashboard/model';

import { DashboardService } from '../../dashboard/service/dashboard.service';

export const candidateResolver: ResolveFn<Candidate | boolean> = (route) => {
  const dashboardService = inject(DashboardService);
  const router = inject(Router);
  const candidateId = route.paramMap.get('id');
  if (!candidateId) {
    return router.navigate(['/dashboard']);
  }
  return dashboardService.getCandidateById(candidateId);
};
