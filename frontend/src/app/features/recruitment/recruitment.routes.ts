import { Routes } from '@angular/router';
import { JobApplicationComponent } from './job-application.component';

export const RECRUITMENT_ROUTES: Routes = [
  { path: 'apply', component: JobApplicationComponent },
  { path: '', redirectTo: 'apply', pathMatch: 'full' },
];
