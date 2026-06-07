# Prompt Templates

AI prompt templates for agents, RAG, system instructions, and safety.

## Structure

```
prompts/
├── agents/          # Agent-specific system prompts
│   ├── hr-agent.st
│   ├── manager-agent.st
│   ├── recruitment-agent.st
│   ├── knowledge-agent.st
│   └── general-assistant.st
├── rag/             # RAG context assembly prompts
│   └── context-template.st
├── system/          # Global system instructions
│   └── base-system.st
└── safety/          # Security and injection protection
    └── security-boundaries.st
```

## Usage

Prompts use `.st` (StringTemplate) format with `{variable}` placeholders.
Loaded by `PromptTemplateService` and injected into Spring AI ChatClient calls.

## Variables

| Variable | Description |
|----------|-------------|
| `{roles}` | User's RBAC roles |
| `{username}` | Authenticated username |
| `{date}` | Current date |
| `{user_message}` | Sanitized user input |
| `{context}` | RAG-retrieved document context |

## Security

All prompts include [safety/security-boundaries.st](safety/security-boundaries.st) constraints.
User input is wrapped in `<user_input>` delimiters before reaching the LLM.
