import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';
import { AddressService } from '../../services/address.services';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinner, MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { Customer } from '../../models/customer.model';
import { Address } from '../../models/address.model';

@Component({
  selector: 'app-customer-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatPaginatorModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.scss']
})
export class CustomerFormComponent implements OnInit {
  customerForm: FormGroup;
  customers: Customer[] = [];
  showForm = false;
  page = 0;
  size = 5;
  totalPages = 0;
  totalElements = 0;
  pageSizes = [5, 10, 20];
  errorMessage = '';
  successMessage = '';
  editingCustomerId: number | null = null;
  loadingAddress = false;

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private addressService: AddressService
  ) {
    this.customerForm = this.fb.group({
      id: [null],
      name: ['', Validators.required],
      birthDate: ['', Validators.required],
      vatNumber: ['', Validators.required],
      addressList: this.fb.array([], Validators.minLength(1))
    });
  }

  ngOnInit(): void {
    this.loadCustomers();
  }

  get addressList(): FormArray {
    return this.customerForm.get('addressList') as FormArray;
  }

  loadCustomers(page: number = 0) {
    this.customerService.getCustomers(page, this.size).subscribe({
      next: (res) => {
        this.customers = res.content;
        this.page = res.number;
        this.totalPages = res.totalPages;
        this.totalElements = res.totalElements;
      },
      error: (err) => { console.error( err ); this.showError( err.message || 'Generic error'); }     });
  }

  showError(msg: string) {
    this.errorMessage = msg;
    this.successMessage = '';
  }

  showSuccess(msg: string) {
    this.successMessage = msg;
    this.errorMessage = '';
  }

  submit() {
    this.errorMessage = '';
    this.successMessage = '';

    if (this.customerForm.invalid) {
      this.showError('Please fill all required fields.');
      return;
    }

    const formValue = this.customerForm.value;
    const id = formValue.id;

    const age = this.calculateAge(formValue.birthDate.toISOString());
    if (age < 18) {
      this.showError('Customer must be at least 18 years old.');
      return;
    }
    if (formValue.addressList.length === 0) {
      this.showError('Customer must have at least one address.');
      return;
    }

    if( id ) {
      this.customerService.updateCustomer(id, formValue).subscribe({
        next: () => {
          this.showSuccess('Customer updated successfully');
          this.loadCustomers();
          this.cancelForm();
        },
        error: (err) => { console.error( err ); this.showError( 'Error updating customer' ); }
      });
    } else {
      this.customerService.createCustomer(formValue).subscribe({
        next: () => {
          this.showSuccess('Customer created successfully');
          this.loadCustomers();
          this.cancelForm();
        },
        error: (err) => { console.error( err ); this.showError( 'Error creating customer' ); }
      });
    }
  }

  editCustomer(customer: any) {
    this.editingCustomerId = customer.id;
    this.showForm = true;

    this.customerForm.patchValue({
      id: customer.id,
      name: customer.name,
      birthDate: customer.birthDate,
      vatNumber: customer.vatNumber
    });

    this.addressList.clear();
    customer.addressList.forEach((addr: Address) => {
      this.addressList.push(this.fb.group({
        id: [addr.id],
        street: [addr.street],
        number: [addr.number],
        complement: [addr.complement],
        postalCode: [addr.postalCode],
        county: [addr.county],
        district: [addr.district]
      }));
    });
  }

  deleteCustomer(id: number) {
    this.customerService.deleteCustomer(id).subscribe({
      next: () => {
        this.showSuccess('Customer deleted successfully');
        this.loadCustomers();
      },
      error: (err) => { console.error( err ); this.showError( 'Error deleting customer' ); }
    });
  }

  openForm() {
    this.showForm = true;
    this.editingCustomerId = null;
    this.customerForm.reset();
    this.addressList.clear();
  }

  cancelForm() {
    this.showForm = false;
    this.editingCustomerId = null;
    this.customerForm.reset();
    this.addressList.clear();
  }

  calculateAge(birthDate: string): number {
    if (!birthDate) return 0;
    const today = new Date();
    const birth = new Date(birthDate);
    let age = today.getFullYear() - birth.getFullYear();
    const m = today.getMonth() - birth.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) {
      age--;
    }
    return age;
  }

  removeAddress(index: number) {
    this.addressList.removeAt(index);
  }

  addAddressByPostalCode(postalCode: string) {
    if (!postalCode) {
      this.showError('Please enter a postal code.');
      return;
    }

    const [code1, code2] = postalCode.split('-');
    if (!code1 || !code2) {
      this.showError('Postal code must be in format XXXX-XXX');
      return;
    }

    this.loadingAddress = true;

    this.addressService.getAddressByPostalCode(code1, code2).subscribe({
      next: (res) => {
        const address = this.fb.group({
          street: [res.street || ''],
          number: [res.number || ''],
          complement: [res.complement || ''],
          postalCode: [res.postalCode || postalCode],
          county: [res.county || ''],
          district: [res.district || '']
        });
        this.addressList.push(address);
        this.loadingAddress = false;
      },
      error: (error) => {
        console.error( error );
        this.showError('Could not fetch address for this postal code.');
        this.loadingAddress = false;
        this.addressList.push(this.fb.group({
          street: [''],
          number: [''],
          complement: [''],
          postalCode: [postalCode],
          county: [''],
          district: ['']
        }));

        this.errorMessage = "Unable to retrieve address by postal code. Please enter manually.";
      }
      
    });
  }

  onPageChange(event: PageEvent) {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.loadCustomers(this.page);
  }
}