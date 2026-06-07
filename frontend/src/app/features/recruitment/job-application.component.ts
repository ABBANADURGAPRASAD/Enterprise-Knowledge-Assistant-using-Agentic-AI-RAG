import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-job-application',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="job-application">
      <h3>Apply for: {{ jobTitle || 'Position' }}</h3>
      <form (ngSubmit)="submit()">
        <div class="field">
          <label>Full Name</label>
          <input [(ngModel)]="form.name" name="name" required />
        </div>
        <div class="field">
          <label>Email</label>
          <input [(ngModel)]="form.email" name="email" type="email" required />
        </div>
        <div class="field">
          <label>Resume</label>
          <input type="file" (change)="onFileSelect($event)" accept=".pdf,.doc,.docx" />
        </div>
        <div class="field">
          <label>Cover Letter</label>
          <textarea [(ngModel)]="form.coverLetter" name="coverLetter" rows="4"></textarea>
        </div>
        <button type="submit">Submit Application</button>
      </form>
    </div>
  `,
  styles: [`
    .job-application { padding: 16px; }
    .field { margin-bottom: 12px; }
    .field label { display: block; margin-bottom: 4px; font-weight: 500; }
    .field input, .field textarea { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
    button { background: #1976d2; color: white; border: none; padding: 10px 24px; border-radius: 4px; cursor: pointer; }
  `],
})
export class JobApplicationComponent {
  jobTitle = '';
  form = { name: '', email: '', coverLetter: '' };

  onFileSelect(event: Event): void {
    const input = event.target as HTMLInputElement;
    console.log('Resume selected:', input.files?.[0]?.name);
  }

  submit(): void {
    console.log('Application submitted:', this.form);
  }
}
