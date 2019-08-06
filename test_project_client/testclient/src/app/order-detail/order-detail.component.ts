import {Component, Inject, Input, OnInit} from '@angular/core';
import {Order} from "../order";
import {FormBuilder, FormControl, FormControlName, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Client} from "../client";
import {OrderLine} from "../order-line";

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {
  clients: Client[];
  orderLines: OrderLine[];
  cboClientsSource: any[];

  order: Order;
  form: FormGroup;

  constructor(

    private fb: FormBuilder,
    private dialogRef: MatDialogRef<OrderDetailComponent>,
    @Inject(MAT_DIALOG_DATA) data) {

    this.order = data.order;
    this.clients = data.clients;
    this.orderLines = this.order.orderLines;

    this.form = fb.group({
      cboClients: new FormControl(),
      cmdOrderLines: new FormControl()
    });

    this.form.get('cboClients').setValue(this.order.client);
    this.cboClientsSource = this.getCboClientsSource(this.clients);
  }

  ngOnInit() {
  }

  close() {
    this.dialogRef.close();
  }

  save() {
    this.dialogRef.close(this.form.value);
  }

  getCboClientsSource(cl: Client[]){
    let result: any[];
    result = [];
    cl.forEach(i => result.push({'label': i.name, 'value': i}));
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
}
