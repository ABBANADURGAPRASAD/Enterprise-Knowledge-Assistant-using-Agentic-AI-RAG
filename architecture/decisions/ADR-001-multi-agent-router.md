# ADR-001: Multi-Agent Architecture with Central Router

## Status
Accepted

## Context
The enterprise assistant must handle diverse query types (HR, approvals, recruitment, knowledge search) with role-specific access and tool calling.

## Decision
Implement a central Agent Router that classifies intent, validates roles, and delegates to specialized agents. Each agent extends BaseAgent with domain-specific prompts and tools.

## Consequences
- **Positive**: Clear separation of concerns, extensible agent registry, centralized security validation
- **Negative**: Additional LLM call for intent classification adds latency (~200ms)

## Alternatives Considered
- Single monolithic prompt — rejected due to poor accuracy and security boundaries
- Rule-based routing — rejected due to inflexibility with natural language
