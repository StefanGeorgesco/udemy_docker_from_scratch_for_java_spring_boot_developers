import { Job } from './job.model';

export interface Candidate {
  id: string;
  name: string;
  skills: string[];
  recommendedJobs: Job[];
  hostName: string;
}
