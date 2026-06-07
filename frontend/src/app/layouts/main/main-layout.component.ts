import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, RouterOutlet],
  template: `
    <nav class="navbar">
      <div class="brand">EKA</div>
      <div class="nav-links">
        <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
        <a routerLink="/chat" routerLinkActive="active">Chat</a>
        <a routerLink="/approvals" routerLinkActive="active">Approvals</a>
        <a routerLink="/analytics" routerLinkActive="active">Analytics</a>
      </div>
    </nav>
    <main class="content">
      <router-outlet />
    </main>
  `,
  styles: [`
    .navbar { display: flex; align-items: center; padding: 0 24px; height: 64px; background: #1565c0; color: white; }
    .brand { font-size: 20px; font-weight: 700; margin-right: 32px; }
    .nav-links { display: flex; gap: 16px; }
    .nav-links a { color: rgba(255,255,255,0.8); text-decoration: none; padding: 8px 12px; border-radius: 4px; }
    .nav-links a.active, .nav-links a:hover { color: white; background: rgba(255,255,255,0.15); }
    .content { min-height: calc(100vh - 64px); }
  `],
})
export class MainLayoutComponent {}
