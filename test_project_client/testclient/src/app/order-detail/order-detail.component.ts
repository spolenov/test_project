import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {Order} from "../order";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Client} from "../client";
import {OrderLine} from "../order-line";
import {Goods} from "../goods";

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {
  @Output() closeEvent = new EventEmitter<any>();

  clients: Client[];
  goods: Goods[];
  orderLines: OrderLine[];

  clientCboSource: any[];
  goodsCboSource: any[];

  order: Order;
  form: FormGroup;

  constructor(

    private fb: FormBuilder,
    private dialogRef: MatDialogRef<OrderDetailComponent>,
    @Inject(MAT_DIALOG_DATA) data) {

    this.order = data.order;
    this.clients = data.clients;

    this.goods = data.goods;
    console.log(this.goods);

    this.orderLines = this.order.orderLines;

    this.form = fb.group({
      cboClients: new FormControl(),
      cboGoods: new FormControl(),
      cmdOrderLines: new FormControl()
    });
  }

  ngOnInit() {
    this.form.get('cboClients').setValue(this.order.client);
    this.clientCboSource = this.getCboClientsSource(this.clients);
    this.goodsCboSource = this.getCboGoodsSource(this.goods);
  }

  close() {
    //По этому событию должна быть перерисована родительская форма
    this.closeEvent.emit(null);

    this.dialogRef.close(this.form.value);
  }

  save() {
    this.dialogRef.close(this.form.value);
  }

  getCboClientsSource(cl: Client[]){
    let result: any[];
    result = [];

    if(cl){
      cl.forEach(i => result.push({'label': i.name, 'value': i}));
    }
    return result;
  }

  getCboGoodsSource(cl: Goods[]){
    let result: any[];
    result = [];

    if(cl){
      cl.forEach(i => result.push({'label': i.name, 'value': i}));
    }
    return result;
  }

 getOrderTotalSum(orderLines: OrderLine[]): string{
    return orderLines
      .map(o => o.goods.price * o.count)
      .reduce((a, b) => a + b, 0)
      .toFixed(2)
  }

  setClient(){
    this.order.client = this.form.get('cboClients').value;
  }


  substractLine(rowNum:number){

  }

  addLine(line:OrderLine){

  }
}
