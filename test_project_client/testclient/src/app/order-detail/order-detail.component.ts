import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {Order} from "../order";
import {FormBuilder, FormGroup} from "@angular/forms";
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

  editableClient: Client;
  editableOrderLines: OrderLine[];

  clientCboSource: any[];
  goodsCboSource: any[];

  order: Order;
  form: FormGroup;

  rowErrors = new Map();
  isValidateRow: boolean;

  lineError: String;

  constructor(

    private fb: FormBuilder,
    private dialogRef: MatDialogRef<OrderDetailComponent>,
    @Inject(MAT_DIALOG_DATA) data) {

    this.order = data.order;
    this.clients = data.clients;

    this.goods = data.goods;
    this.editableOrderLines = [];
    this.editableClient = {...this.order.client};

    this.fillEditableLines();

    this.form = fb.group({
    });
  }

  ngOnInit() {
    this.clientCboSource = this.getCboClientsSource(this.clients);
    this.goodsCboSource = this.getCboGoodsSource(this.goods);
  }

  fillEditableLines(){
    this.order.orderLines.forEach(line => this.editableOrderLines.push({...line}));
  }

  saveLines(){
    this.order.orderLines = [];
    this.editableOrderLines.forEach(line => this.order.orderLines.push({...line}));
  }

  close() {
    //По этому событию должна быть перерисована родительская форма
    this.closeEvent.emit(null);

    this.dialogRef.close(this.form.value);
  }

  setRowValidationEnabled(): boolean{
    this.isValidateRow = true;
    return true;
  }

  setRowValidationDisabled(){
    this.isValidateRow = false;
  }

  save() {
    this.saveLines();
    this.order.client = this.editableClient;
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
      .map(o =>
        isNaN(Number(o.count))?
          0: (o.goods? o.goods.price: 0) * o.count)
      .reduce((a, b) => a + b, 0)
      .toFixed(2)
  }

  setRowErrors(rowNum:number, err:boolean){
    this.rowErrors.set(rowNum, err);
  }

  isRowErrors(): boolean{
    let result: boolean;

    if(this.editableOrderLines.length <=0){
      return true;
    }

    this.rowErrors.forEach((value: boolean) => {
      if(value === true){
        result = true;
      }
    });

    if(result){
      this.lineError = "Неверно заполнены строки заказа";
    }

    return result;
  }

  deleteLine(rowNum:number){
    //Удалить строку и пересчитать порядковые номера строк,
    //идущих далее.
    if(this.editableOrderLines.length <=0){
      return;
    }

    let idx = this.editableOrderLines.indexOf(
      this.editableOrderLines
        .find(o => o.rownum === rowNum));
    this.editableOrderLines.splice(idx, 1);

    let rest = this.editableOrderLines
      .slice(idx, this.editableOrderLines.length);
    this.deleteErrorByRowNum(rowNum);

    rest.forEach(line => {
      let err = this.rowErrors.get(line.rownum);

      line.rownum = rowNum++;

      if(!!err){
        this.setRowErrors(line.rownum, err);
      }
    });

    this.deleteErrors();
  }

  deleteErrorByRowNum(rowNum: number){
    for(let key of Array.from( this.rowErrors.keys()) ) {
      if(key === rowNum){
        this.rowErrors.delete(key);
      }
    }
  }

  deleteErrors(){
    for(let key of Array.from( this.rowErrors.keys()) ) {
      if(key > this.getMaxRowNum()){
        this.rowErrors.delete(key);
      }
    }
  }

  getMaxRowNum():number{
    let maxRowNum = 0;

    this.editableOrderLines.map(function(line){
      if(line.rownum > maxRowNum){
        maxRowNum = line.rownum;
      }
    });

    return maxRowNum;
  }

  addLine(){
    if(this.isRowErrors()){
      this.setRowValidationEnabled();
      return;
    }

    let newLine = new OrderLine();
    let rowNum = this.editableOrderLines.length + 1;

    newLine.rownum = rowNum;
    this.rowErrors.set(rowNum, true);
    newLine.orderId = this.order.id;

    this.editableOrderLines.push(newLine);

    //Сразу не будем выдавать ошибку, дадим пользователю возможность
    //заполнить новую строку.
    this.setRowValidationDisabled();
  }
}
