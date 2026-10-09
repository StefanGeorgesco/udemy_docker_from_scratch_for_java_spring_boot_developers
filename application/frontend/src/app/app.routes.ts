import { Routes } from '@angular/router';

import { candidateResolver } from './candidate/resolver/candidate-resolver';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'dashboard',
    title: 'Dashboard',
    loadComponent: () =>
      import('./dashboard/component/dashboard.component').then((m) => m.DashboardComponent),
  },
  {
    path: 'candidate/:id',
    title: 'Candidate',
    loadComponent: () =>
      import('./candidate/component/candidate.component').then((m) => m.CandidateComponent),
    resolve: {
      candidate: candidateResolver,
    },
  },
];
