import { Doctor } from './doctor.model';
import { Customer } from './customer.model';
export class Appointments{
  id: number;
  local: number;
  date: string;
  doctor: Doctor;
  customer: Customer;
}
