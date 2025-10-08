import { Routes } from '@angular/router';
import { CustomerFormComponent } from './pages/customer-form/customer-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'customers', pathMatch: 'full' },
  { path: 'customers', component: CustomerFormComponent }
];