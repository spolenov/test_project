import {Goods} from "./goods";

export class OrderLine {
  id: number;
  orderId: number;
  goods: Goods;
  count: number
}
