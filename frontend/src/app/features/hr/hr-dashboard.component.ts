import { Component } from '@angular/core';

@Component({
  selector: 'app-hr-dashboard',
  standalone: true,
  template: `<div class="hr-dashboard"><h1>HR Dashboard</h1><p>Employee management, policies, and complaints.</p></div>`,
  styles: [`.hr-dashboard { padding: 24px; }`],
})
export class HrDashboardComponent {}
