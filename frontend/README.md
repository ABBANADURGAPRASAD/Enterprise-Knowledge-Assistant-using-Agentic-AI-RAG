# Frontend — Enterprise Knowledge Assistant

Angular 20+ SPA providing a ChatGPT-style conversational interface with dynamic component loading.

## Technology

- **Angular 20+** — Standalone components, signals, lazy loading
- **Angular Material** — UI components and theming
- **RxJS** — Reactive state management
- **ngx-markdown** — Markdown rendering in chat

## Module Structure

```
src/app/
├── core/              # Singleton services, guards, interceptors
├── shared/            # Reusable components, pipes, directives
├── layouts/           # Main, auth, dashboard layouts
├── features/          # Feature modules (lazy-loaded)
│   ├── authentication/
│   ├── dashboard/
│   ├── chat/
│   ├── ai-assistant/  # Dynamic component loader
│   ├── hr/
│   ├── recruitment/
│   ├── approvals/
│   ├── employees/
│   ├── managers/
│   ├── analytics/
│   ├── policies/
│   ├── projects/
│   ├── complaints/
│   ├── notifications/
│   └── settings/
├── services/          # Shared API services
├── guards/            # Route guards
├── interceptors/      # HTTP interceptors (JWT)
├── models/            # TypeScript interfaces
└── routes/            # Route configuration
```

## Running Locally

```bash
npm install
npm start
```

App available at `http://localhost:4200`. API proxy configured to `http://localhost:8080`.

## Key Features

- **Chat UI** — ChatGPT-style message bubbles with streaming
- **Dynamic Components** — AI action payloads open Angular components automatically
- **Role-Based UI** — Routes and elements hidden based on JWT roles
- **Responsive Design** — Desktop and mobile layouts

## Dynamic Component Loading

When the AI returns an `actionPayload`, the `DynamicComponentLoaderService` opens the corresponding component:

| Action | Component | Trigger Example |
|--------|-----------|-----------------|
| OPEN_COMPONENT | ApprovalListComponent | "Show pending approvals" |
| OPEN_COMPONENT | JobApplicationComponent | "Apply for Java Developer" |
| OPEN_COMPONENT | EmployeeProfileComponent | "Show my profile" |

See [features/ai-assistant/README.md](src/app/features/ai-assistant/README.md).
