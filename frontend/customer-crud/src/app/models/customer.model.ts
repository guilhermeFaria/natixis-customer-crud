import { Address } from './address.model';

export interface Customer {
  id?: number;
  name: string;
  birthDate: string;
  vatNumber: string;
  addressList: Address[];
}