import {Component, Input, OnInit} from '@angular/core';
import {ORDERS} from "../mock/mock-order";
import {Order} from "../order";
import {DatePipe} from "@angular/common";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {OrderDetailComponent} from "../order-detail/order-detail.component";
import {Client} from "../client";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css'],
  providers: [DatePipe]
})
export class OrderListComponent implements OnInit {
  @Input() clients: Client[];

  orderList: Order[];
  orderListSnapshot: any[];

  selectedOrder: Order;
  cols: any[];

  onSelect(order: any): void {
    this.selectedOrder = this.fromSnapshot(order.id);
  }

  constructor(public datePipe: DatePipe, private dialog: MatDialog) {
  }

  ngOnInit() {
    this.getOrders();
    this.doSnapshot();

    this.cols = [
      { field: 'id', header: 'Код' },
      { field: 'clientName', header: 'Имя клиента' },
      { field: 'date', header: 'Дата' },
      { field: 'address', header: 'Адрес' }
    ];
  }

  doSnapshot(){
    function toSnapshot(order: Order, datePipe: DatePipe){

      return {
        id: order.id,
        clientName: order.client.name,
        address: order.address,
        date: datePipe.transform(order.date, 'dd.MM.yyyy HH:mm:ss')
      }
    }

    this.orderListSnapshot = [];
    this.orderList.forEach(o => this.orderListSnapshot.push(toSnapshot(o, this.datePipe)));
  }

  fromSnapshot(id: number): Order{
    return this.orderList.filter(s => s.id === id)[0];
  }

  openDetails(id: number) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = this.getSelectedDataForChild();
    this.dialog.open(OrderDetailComponent, dialogConfig);
  }

  getOrders(){
    this.orderList = ORDERS;
  }

  getSelectedDataForChild(): any{
    return{"order": this.selectedOrder, "clients": this.clients};
  }
}
