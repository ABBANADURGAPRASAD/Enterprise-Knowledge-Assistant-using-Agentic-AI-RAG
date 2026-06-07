# Dynamic Component Loader

AI-triggered dynamic UI rendering for the Enterprise Knowledge Assistant.

## Overview

When the AI assistant determines a user request requires a specific UI action, it returns an `actionPayload` in the chat response. The `DynamicComponentLoaderService` maps this payload to a registered Angular component and renders it in a side panel.

## Architecture

```
Chat Response (actionPayload)
        ↓
DynamicComponentLoaderService
        ↓
ComponentRegistry[componentName]
        ↓
ViewContainerRef.createComponent()
        ↓
Rendered in side panel
```

## Registered Components

| Component Name | Trigger Example | Params |
|---------------|-----------------|--------|
| ApprovalListComponent | "Show pending approvals" | `{ status: "PENDING" }` |
| JobApplicationComponent | "Apply for Java Developer" | `{ jobTitle: "Java Developer" }` |
| EmployeeProfileComponent | "Show my profile" | `{ employeeId: 123 }` |

## Adding New Components

1. Create standalone Angular component
2. Register in `DynamicComponentLoaderService.registry`
3. Update agent prompt to return correct `componentName`
4. Add route if full-page navigation needed

## Action Payload Schema

```typescript
interface ActionPayload {
  actionType: 'OPEN_COMPONENT' | 'NAVIGATE' | 'DOWNLOAD';
  componentName: string;
  params?: Record<string, unknown>;
  displayMode?: 'SIDE_PANEL' | 'MODAL' | 'FULL_PAGE';
}
```
