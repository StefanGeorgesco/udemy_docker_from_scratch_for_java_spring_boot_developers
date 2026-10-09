import { Component, input } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { RouterLink } from '@angular/router';

import { Candidate } from '../../dashboard/model';
import { renderString } from '../../dashboard/util/string.util';

@Component({
  imports: [RouterLink, CurrencyPipe],
  selector: 'app-candidate.component',
  styleUrl: './candidate.component.css',
  templateUrl: './candidate.component.html',
})
export class CandidateComponent {
  candidate = input.required<Candidate>();
  renderString = renderString;
}
