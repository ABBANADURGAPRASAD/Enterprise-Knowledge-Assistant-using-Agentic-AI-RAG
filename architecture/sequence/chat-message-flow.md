# Chat Message Flow — Sequence Diagram

```mermaid
sequenceDiagram
    autonumber
    participant User
    participant ChatUI as ChatComponent
    participant ChatSvc as ChatService (FE)
    participant API as ChatController
    participant Svc as ChatService (BE)
    participant Router as AgentRouterService
    participant Agent as Specialized Agent
    participant DynLoader as DynamicComponentLoader

    User->>ChatUI: Type message & send
    ChatUI->>ChatSvc: sendMessage(text, conversationId)
    ChatSvc->>API: POST /api/v1/chat/message
    API->>Svc: processMessage(username, request)
    Svc->>Svc: Save user message
    Svc->>Router: route(AgentContext)
    Router->>Router: classifyIntent()
    Router->>Router: selectAgent()
    Router->>Agent: process(context)
    Agent-->>Router: ChatResponse
    Router-->>Svc: ChatResponse
    Svc->>Svc: Save assistant message
    Svc-->>API: ChatResponse
    API-->>ChatSvc: ApiResponse<ChatResponse>
    ChatSvc-->>ChatUI: response
    ChatUI->>ChatUI: Display message

    alt actionPayload present
        ChatUI->>DynLoader: loadFromAction(payload)
        DynLoader->>DynLoader: createComponent(registry[name])
        DynLoader-->>User: Show ApprovalList / JobApplication
    end
```

## Key Points

1. User messages persisted before agent processing
2. Agent Router is the single entry point for all AI interactions
3. Action payloads trigger dynamic UI components without page navigation
4. Citations included when Knowledge Agent uses RAG retrieval
