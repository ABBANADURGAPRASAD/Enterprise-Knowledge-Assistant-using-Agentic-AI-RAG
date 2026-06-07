import { Component } from '@angular/core';

@Component({
  selector: 'app-analytics-dashboard',
  standalone: true,
  template: `
    <div class="analytics">
      <h1>Analytics Dashboard</h1>
      <div class="kpis">
        <div class="kpi"><span class="value">1,247</span><span class="label">Queries Today</span></div>
        <div class="kpi"><span class="value">342ms</span><span class="label">Avg Response Time</span></div>
        <div class="kpi"><span class="value">89%</span><span class="label">Agent Accuracy</span></div>
        <div class="kpi"><span class="value">56</span><span class="label">Documents Indexed</span></div>
      </div>
    </div>
  `,
  styles: [`
    .analytics { padding: 24px; }
    .kpis { display: grid; grid-template-columns: repeat(4, 1fr); gap: 16px; margin-top: 24px; }
    .kpi { background: white; border: 1px solid #e0e0e0; border-radius: 8px; padding: 24px; text-align: center; }
    .value { display: block; font-size: 32px; font-weight: 700; color: #1565c0; }
    .label { display: block; color: #666; margin-top: 8px; }
  `],
})
export class AnalyticsDashboardComponent {}
