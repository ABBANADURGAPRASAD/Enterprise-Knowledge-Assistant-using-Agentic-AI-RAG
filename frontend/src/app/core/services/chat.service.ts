import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ChatResponse } from '../models/auth.model';

@Injectable({ providedIn: 'root' })
export class ChatService {
  private readonly http = inject(HttpClient);

  sendMessage(message: string, conversationId?: number): Observable<{ data: ChatResponse }> {
    return this.http.post<{ data: ChatResponse }>(`${environment.apiUrl}/chat/message`, {
      message,
      conversationId,
    });
  }
}
