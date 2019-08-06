import { Component, OnInit } from '@angular/core';
import { Goods } from '../goods';
import { GOODS } from '../mock/mock-goods'

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
  constructor() { }

  ngOnInit() {
    this.columns = [
      { field: 'id', header: 'Код' },
      { field: 'name', header: 'Наименование' },
      { field: 'price', header: 'Цена, $' }
    ];
  }

  onDblClick(){
    console.info("Dbl click!")
  }
}
