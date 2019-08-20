import {Component, Inject, OnInit} from '@angular/core';
import {ORDERS} from "../mock/mock-order";
import {Order} from "../order";
import {DatePipe} from "@angular/common";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {OrderDetailComponent} from "../order-detail/order-detail.component";
import {Client} from "../client";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {MainJournalComponent} from "../main-journal/main-journal.component";
import {Goods} from "../goods";
import {GOODS} from "../mock/mock-goods";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css'],
  providers: [DatePipe]
})
export class OrderListComponent implements OnInit {
  clients: Client[];
  goods: Goods[];

  orderList: Order[];
  orderListSnapshot: any[];

  selectedOrder: Order;
  cols: any[];

  routeQueryParams: Subscription;

  onSelect(order: any): void {
    this.selectedOrder = this.fromSnapshot(order.id);
  }

  constructor(public datePipe: DatePipe,
              private dialog: MatDialog,
              private router: Router,
              @Inject(MainJournalComponent) private parent: MainJournalComponent) {
  }

  ngOnInit() {
    this.doSnapshot();

    this.cols = [
      { field: 'id', header: 'Код' },
      { field: 'clientName', header: 'Имя клиента' },
      { field: 'date', header: 'Дата' },
      { field: 'address', header: 'Адрес' }
    ];

    this.clients = this.parent.clients;
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

    this.fetchOrders();
    this.orderList.forEach(o => this.orderListSnapshot.push(toSnapshot(o, this.datePipe)));
  }

  fromSnapshot(id: number): Order{
    return this.orderList.filter(s => s.id === id)[0];
  }

  openDetails(id: number) {
    this.fetchGoods();

    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = this.getSelectedDataForChild();

    let dialogRef = this.dialog.open(OrderDetailComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      this.doSnapshot();
    });
  }

  getSelectedDataForChild(): any{
    return{"order": this.selectedOrder, "clients": this.clients, "goods": this.goods};
  }

  fetchGoods(){
    this.goods = GOODS;
  }

  fetchOrders(){
    this.orderList = ORDERS;
  }
}
