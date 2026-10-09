import { Component, inject, signal } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';

import { DashboardService } from '../service/dashboard.service';
import { renderString } from '../util/string.util';

@Component({
  imports: [RouterLink, CurrencyPipe],
  selector: 'app-dashboard.component',
  styleUrl: './dashboard.component.css',
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent {
  private readonly dashboardService = inject(DashboardService);
  renderString = renderString;

  showJobs = signal(true);
  jobs = toSignal(this.dashboardService.getJobs(), { initialValue: [] });
  candidates = toSignal(this.dashboardService.getCandidates(), { initialValue: [] });
}
