export interface Job {
  id: string;
  description: string;
  company: string;
  skills: string[];
  salary: number;
  isRemote: boolean;
  hostName: string;
}
