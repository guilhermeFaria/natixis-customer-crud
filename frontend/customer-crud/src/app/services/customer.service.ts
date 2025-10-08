import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../models/customer.model';
import { Observable } from 'rxjs';
import { Address } from '../models/address.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

   private apiUrl = 'http://localhost:8080/customers';

  constructor(private http: HttpClient) {}

  createCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.apiUrl, customer);
  }

  updateCustomer(id: number, customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${this.apiUrl}/${customer.id}`, customer);
  }

  getCustomers(page: number = 0, size: number = 10): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getAddressByPostalCode(postalCode: string): Observable<any> {
    return this.http.get<Address>(`${this.apiUrl}/address/postal-code/${postalCode}`);
  }

}
