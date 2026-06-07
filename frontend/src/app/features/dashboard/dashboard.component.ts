import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  template: `
    <div class="dashboard">
      <h1>Dashboard</h1>
      <div class="cards">
        <div class="card"><h3>Chat Assistant</h3><p>Ask questions about HR, projects, and policies</p></div>
        <div class="card"><h3>Pending Approvals</h3><p>3 items awaiting your review</p></div>
        <div class="card"><h3>Documents</h3><p>12 new documents indexed this week</p></div>
      </div>
    </div>
  `,
  styles: [`
    .dashboard { padding: 24px; }
    .cards { display: grid; grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); gap: 16px; margin-top: 24px; }
    .card { background: white; border: 1px solid #e0e0e0; border-radius: 8px; padding: 20px; }
    .card h3 { margin: 0 0 8px; }
    .card p { color: #666; margin: 0; }
  `],
})
export class DashboardComponent {}
