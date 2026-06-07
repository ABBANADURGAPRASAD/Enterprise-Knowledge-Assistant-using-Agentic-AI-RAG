import { Routes } from '@angular/router';

export const HR_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./hr-dashboard.component').then((m) => m.HrDashboardComponent),
  },
];
