import {Component, OnInit} from '@angular/core';
import {Goods} from '../goods';
import {GOODS} from '../mock/mock-goods'
import {MatDialog, MatDialogConfig} from "@angular/material";
import {GoodsDetailComponent} from "../goods-detail/goods-detail.component";

@Component({
  selector: 'app-goods-list',
  templateUrl: './goods-list.component.html',
  styleUrls: ['./goods-list.component.css']
})
export class GoodsListComponent implements OnInit {
  goodsList = GOODS;
  columns: any[];

  selectedGoods: Goods;
  onSelect(goods: Goods): void {
    this.selectedGoods = goods;
  }
  constructor( private dialog: MatDialog) { }

  ngOnInit() {
    this.columns = [
      { field: 'id', header: 'Код' },
      { field: 'name', header: 'Наименование' },
      { field: 'price', header: 'Цена, $' }
    ];
  }

  openDetails(id: number) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = this.selectedGoods;
    this.dialog.open(GoodsDetailComponent, dialogConfig);
  }
}
