import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  template: `
    <div class="login-container">
      <div class="login-card">
        <h1>Enterprise Knowledge Assistant</h1>
        <p>Sign in to continue</p>
        <form (ngSubmit)="login()">
          <input [(ngModel)]="username" name="username" placeholder="Username" required />
          <input [(ngModel)]="password" name="password" type="password" placeholder="Password" required />
          @if (error) { <p class="error">{{ error }}</p> }
          <button type="submit" [disabled]="loading">{{ loading ? 'Signing in...' : 'Sign In' }}</button>
        </form>
      </div>
    </div>
  `,
  styles: [`
    .login-container { display: flex; justify-content: center; align-items: center; min-height: 100vh; background: #f5f5f5; }
    .login-card { background: white; padding: 40px; border-radius: 12px; box-shadow: 0 2px 12px rgba(0,0,0,0.1); width: 400px; }
    .login-card h1 { font-size: 24px; margin-bottom: 8px; }
    .login-card p { color: #666; margin-bottom: 24px; }
    input { display: block; width: 100%; padding: 12px; margin-bottom: 12px; border: 1px solid #ccc; border-radius: 8px; box-sizing: border-box; }
    button { width: 100%; padding: 12px; background: #1976d2; color: white; border: none; border-radius: 8px; cursor: pointer; font-size: 16px; }
    .error { color: #f44336; font-size: 14px; }
  `],
})
export class LoginComponent {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  username = '';
  password = '';
  loading = false;
  error = '';

  login(): void {
    this.loading = true;
    this.error = '';
    this.authService.login(this.username, this.password).subscribe({
      next: () => { this.router.navigate(['/chat']); this.loading = false; },
      error: () => { this.error = 'Invalid credentials'; this.loading = false; },
    });
  }
}
