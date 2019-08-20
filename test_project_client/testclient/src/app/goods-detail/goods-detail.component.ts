import {Component, Inject, OnInit} from '@angular/core';
import {Goods} from "../goods";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

const NUMERIC_DIGITS_COUNT:number = 4;

@Component({
  selector: 'app-goods-detail',
  templateUrl: './goods-detail.component.html',
  styleUrls: ['./goods-detail.component.css']
})

export class GoodsDetailComponent implements OnInit {
  goods: Goods;
  goodsToEdit: Goods;

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
      private dialogRef: MatDialogRef<GoodsDetailComponent>,
      @Inject(MAT_DIALOG_DATA) data) {

    this.goods = data;

    //Клонируем исходный объект. Все изменения отражаются в клоне,
    //а в исходный объект копируются только при нажатии "Сохранить".
    this.goodsToEdit = {...this.goods};

    this.form = fb.group({
      txtItemName: new FormControl(),
      txtPrice: new FormControl(),
      internalForm: new FormControl()
    });
  }

  ngOnInit() {

  }

  close() {
    this.dialogRef.close(this.form.value);
  }

  setPrice(){
    this.goodsToEdit.price = Math.round(this.goodsToEdit.price*
      Math.pow(10, NUMERIC_DIGITS_COUNT)) /
      Math.pow(10, NUMERIC_DIGITS_COUNT);
  }

  saveAndClose(){
      this.setPrice();
      this.goods.price = this.goodsToEdit.price;
      this.goods.name = this.goodsToEdit.name;
      this.close();
  }
}
