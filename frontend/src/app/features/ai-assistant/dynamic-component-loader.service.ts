import {
  ComponentRef,
  Injectable,
  Type,
  ViewContainerRef,
  inject,
} from '@angular/core';
import { ActionPayload } from '../models/auth.model';
import { ApprovalListComponent } from '../../features/approvals/approval-list.component';
import { JobApplicationComponent } from '../../features/recruitment/job-application.component';

type ComponentRegistry = Record<string, Type<unknown>>;

/**
 * Dynamic Component Loader — renders Angular components based on AI action payloads.
 */
@Injectable({ providedIn: 'root' })
export class DynamicComponentLoaderService {
  private viewContainerRef: ViewContainerRef | null = null;
  private activeComponentRef: ComponentRef<unknown> | null = null;

  private readonly registry: ComponentRegistry = {
    ApprovalListComponent,
    JobApplicationComponent,
  };

  setViewContainer(vcr: ViewContainerRef): void {
    this.viewContainerRef = vcr;
  }

  loadFromAction(payload: ActionPayload): void {
    if (payload.actionType !== 'OPEN_COMPONENT' || !this.viewContainerRef) {
      return;
    }

    const componentType = this.registry[payload.componentName];
    if (!componentType) {
      console.warn(`Unknown component: ${payload.componentName}`);
      return;
    }

    this.clear();
    this.activeComponentRef = this.viewContainerRef.createComponent(componentType);

    if (payload.params && this.activeComponentRef.instance) {
      Object.assign(this.activeComponentRef.instance as object, payload.params);
    }
  }

  clear(): void {
    this.activeComponentRef?.destroy();
    this.activeComponentRef = null;
    this.viewContainerRef?.clear();
  }
}
