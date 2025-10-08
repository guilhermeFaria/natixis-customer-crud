import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private apiUrl = 'http://localhost:8080/address';

  constructor(private http: HttpClient) {}

  getAddressByPostalCode( cp4: string, cp3: string ): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/postal-code/${cp4}/${cp3}`);
  }
}