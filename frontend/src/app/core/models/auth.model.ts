export interface User {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  roles: string[];
}

export interface AuthResponse {
  accessToken: string;
  refreshToken: string;
  tokenType: string;
  expiresIn: number;
  user: User;
}

export interface ActionPayload {
  actionType: string;
  componentName: string;
  params?: Record<string, unknown>;
  displayMode?: 'SIDE_PANEL' | 'MODAL' | 'FULL_PAGE';
}

export interface ChatMessage {
  role: 'user' | 'assistant';
  content: string;
  agentUsed?: string;
  actionPayload?: ActionPayload;
  citations?: Citation[];
  timestamp?: Date;
}

export interface Citation {
  documentId: number;
  documentTitle: string;
  snippet: string;
  relevanceScore: number;
}

export interface ChatResponse {
  conversationId: number;
  message: string;
  agentUsed: string;
  confidence: number;
  actionPayload?: ActionPayload;
  citations?: Citation[];
}
