import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-approval-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="approval-list">
      <h3>Pending Approvals</h3>
      <div class="approval-item" *ngFor="let item of approvals">
        <div class="approval-info">
          <strong>{{ item.type }}</strong>
          <span>{{ item.requester }}</span>
          <span class="date">{{ item.date }}</span>
        </div>
        <div class="approval-actions">
          <button class="approve" (click)="approve(item.id)">Approve</button>
          <button class="reject" (click)="reject(item.id)">Reject</button>
        </div>
      </div>
      @if (!approvals.length) {
        <p class="empty">No pending approvals.</p>
      }
    </div>
  `,
  styles: [`
    .approval-list { padding: 16px; }
    .approval-item { display: flex; justify-content: space-between; align-items: center; padding: 12px; border: 1px solid #e0e0e0; border-radius: 8px; margin-bottom: 8px; }
    .approval-info { display: flex; flex-direction: column; gap: 4px; }
    .date { font-size: 12px; color: #666; }
    .approve { background: #4caf50; color: white; border: none; padding: 6px 16px; border-radius: 4px; cursor: pointer; margin-right: 8px; }
    .reject { background: #f44336; color: white; border: none; padding: 6px 16px; border-radius: 4px; cursor: pointer; }
    .empty { color: #999; text-align: center; padding: 24px; }
  `],
})
export class ApprovalListComponent {
  status = 'PENDING';

  approvals = [
    { id: 1, type: 'Leave Request', requester: 'Jane Smith', date: '2026-06-05' },
    { id: 2, type: 'Reimbursement', requester: 'Bob Johnson', date: '2026-06-04' },
  ];

  approve(id: number): void {
    console.log('Approved:', id);
  }

  reject(id: number): void {
    console.log('Rejected:', id);
  }
}
