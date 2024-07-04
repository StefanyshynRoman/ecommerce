import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OrderHistory } from '../common/order-history';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OrderHistoryService {
  private orderUrl = 'http:localhost:8080/api/orders';

  constructor(private httpClient: HttpClient) {}
  getOrderHistory(theEmail: string): Observable<GetResponseOrderHistory> {
    const orderHistoryUrl = `${this.orderUrl}/search/findByCustomerEmail?email=${theEmail}`;
    return this.httpClient.get<GetResponseOrderHistory>(orderHistoryUrl);
  }
}
interface GetResponseOrderHistory {
  _embedded: {
    orders: OrderHistory[];
  };
}
