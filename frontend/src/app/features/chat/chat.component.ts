import { Component, inject, ViewChild, ViewContainerRef, AfterViewInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChatService } from '../../core/services/chat.service';
import { DynamicComponentLoaderService } from '../ai-assistant/dynamic-component-loader.service';
import { ChatMessage } from '../../core/models/auth.model';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="chat-container">
      <div class="chat-header">
        <h2>Enterprise Knowledge Assistant</h2>
        <span class="agent-badge" *ngIf="lastAgent">{{ lastAgent }}</span>
      </div>

      <div class="messages" #messagesContainer>
        @for (msg of messages; track $index) {
          <div class="message" [class.user]="msg.role === 'user'" [class.assistant]="msg.role === 'assistant'">
            <div class="message-content" [innerHTML]="msg.content"></div>
            @if (msg.citations?.length) {
              <div class="citations">
                @for (cite of msg.citations; track cite.documentId) {
                  <span class="citation">{{ cite.documentTitle }}</span>
                }
              </div>
            }
          </div>
        }
        @if (loading) {
          <div class="message assistant">
            <div class="typing-indicator"><span></span><span></span><span></span></div>
          </div>
        }
      </div>

      <div class="input-area">
        <textarea
          [(ngModel)]="inputMessage"
          (keydown.enter)="onEnter($event)"
          placeholder="Ask anything about HR, projects, policies..."
          rows="2"
        ></textarea>
        <button (click)="sendMessage()" [disabled]="loading || !inputMessage.trim()">Send</button>
      </div>

      <div class="dynamic-panel" #dynamicPanel></div>
    </div>
  `,
  styles: [`
    .chat-container { display: flex; flex-direction: column; height: calc(100vh - 64px); max-width: 900px; margin: 0 auto; }
    .chat-header { display: flex; align-items: center; gap: 12px; padding: 16px; border-bottom: 1px solid #e0e0e0; }
    .agent-badge { background: #e3f2fd; color: #1565c0; padding: 4px 12px; border-radius: 12px; font-size: 12px; }
    .messages { flex: 1; overflow-y: auto; padding: 16px; display: flex; flex-direction: column; gap: 12px; }
    .message { max-width: 80%; padding: 12px 16px; border-radius: 12px; line-height: 1.5; }
    .message.user { align-self: flex-end; background: #1976d2; color: white; }
    .message.assistant { align-self: flex-start; background: #f5f5f5; color: #333; }
    .citations { margin-top: 8px; display: flex; gap: 8px; flex-wrap: wrap; }
    .citation { font-size: 11px; background: #e8eaf6; padding: 2px 8px; border-radius: 8px; color: #3949ab; }
    .input-area { display: flex; gap: 8px; padding: 16px; border-top: 1px solid #e0e0e0; }
    .input-area textarea { flex: 1; resize: none; border: 1px solid #ccc; border-radius: 8px; padding: 12px; font-family: inherit; }
    .input-area button { padding: 12px 24px; background: #1976d2; color: white; border: none; border-radius: 8px; cursor: pointer; }
    .input-area button:disabled { opacity: 0.5; cursor: not-allowed; }
    .dynamic-panel { border-top: 1px solid #e0e0e0; min-height: 0; }
    .typing-indicator { display: flex; gap: 4px; }
    .typing-indicator span { width: 8px; height: 8px; background: #999; border-radius: 50%; animation: bounce 1.4s infinite; }
    .typing-indicator span:nth-child(2) { animation-delay: 0.2s; }
    .typing-indicator span:nth-child(3) { animation-delay: 0.4s; }
    @keyframes bounce { 0%, 80%, 100% { transform: translateY(0); } 40% { transform: translateY(-6px); } }
  `],
})
export class ChatComponent implements AfterViewInit {
  private readonly chatService = inject(ChatService);
  private readonly dynamicLoader = inject(DynamicComponentLoaderService);

  @ViewChild('dynamicPanel', { read: ViewContainerRef }) dynamicPanel!: ViewContainerRef;

  messages: ChatMessage[] = [];
  inputMessage = '';
  loading = false;
  lastAgent = '';
  conversationId?: number;

  ngAfterViewInit(): void {
    this.dynamicLoader.setViewContainer(this.dynamicPanel);
    this.messages.push({
      role: 'assistant',
      content: 'Hello! I\'m your Enterprise Knowledge Assistant. How can I help you today?',
      timestamp: new Date(),
    });
  }

  onEnter(event: Event): void {
    const keyEvent = event as KeyboardEvent;
    if (!keyEvent.shiftKey) {
      event.preventDefault();
      this.sendMessage();
    }
  }

  sendMessage(): void {
    const message = this.inputMessage.trim();
    if (!message || this.loading) return;

    this.messages.push({ role: 'user', content: message, timestamp: new Date() });
    this.inputMessage = '';
    this.loading = true;

    this.chatService.sendMessage(message, this.conversationId).subscribe({
      next: (response) => {
        const data = response.data;
        this.conversationId = data.conversationId;
        this.lastAgent = data.agentUsed;

        this.messages.push({
          role: 'assistant',
          content: data.message,
          agentUsed: data.agentUsed,
          actionPayload: data.actionPayload,
          citations: data.citations,
          timestamp: new Date(),
        });

        if (data.actionPayload) {
          this.dynamicLoader.loadFromAction(data.actionPayload);
        }

        this.loading = false;
      },
      error: () => {
        this.messages.push({
          role: 'assistant',
          content: 'Sorry, I encountered an error. Please try again.',
          timestamp: new Date(),
        });
        this.loading = false;
      },
    });
  }
}
