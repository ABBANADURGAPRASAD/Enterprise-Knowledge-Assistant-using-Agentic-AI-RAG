import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { roleGuard } from './core/guards/role.guard';
import { MainLayoutComponent } from './layouts/main/main-layout.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./features/authentication/auth.routes').then((m) => m.AUTH_ROUTES),
  },
  {
    path: '',
    component: MainLayoutComponent,
    canActivate: [authGuard],
    children: [
      {
        path: 'dashboard',
        loadComponent: () =>
          import('./features/dashboard/dashboard.component').then((m) => m.DashboardComponent),
      },
      {
        path: 'chat',
        loadComponent: () =>
          import('./features/chat/chat.component').then((m) => m.ChatComponent),
      },
      {
        path: 'approvals',
        canActivate: [roleGuard(['CEO', 'HR', 'MANAGER'])],
        loadComponent: () =>
          import('./features/approvals/approval-list.component').then((m) => m.ApprovalListComponent),
      },
      {
        path: 'analytics',
        canActivate: [roleGuard(['CEO', 'HR', 'MANAGER'])],
        loadComponent: () =>
          import('./features/analytics/analytics-dashboard.component').then((m) => m.AnalyticsDashboardComponent),
      },
    ],
  },
  {
    path: 'recruitment',
    loadChildren: () =>
      import('./features/recruitment/recruitment.routes').then((m) => m.RECRUITMENT_ROUTES),
  },
  {
    path: 'hr',
    canActivate: [authGuard, roleGuard(['CEO', 'HR'])],
    loadChildren: () =>
      import('./features/hr/hr.routes').then((m) => m.HR_ROUTES),
  },
  {
    path: '**',
    redirectTo: 'dashboard',
  },
];
