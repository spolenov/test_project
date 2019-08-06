import {Order} from '../order';
import {CLIENTS} from "./mock-clients";
import {Goods} from "../goods";
import {GOODS} from "./mock-goods";

export const ORDERS: Order[] = [
  {id: 1, client: CLIENTS[0],
    date: new Date(1564666711000), address: 'test_address_1', orderLines: [
      {id: 1001, orderId: 1, goods: GOODS[0], count: 16.00}
    ]},
  {id: 2, client: CLIENTS[1],
    date: new Date(1564666812000), address: 'test_address_2', orderLines: [
      {id: 1002, orderId: 2, goods: GOODS[0], count: 22.00},
      {id: 1003, orderId: 2, goods: GOODS[1], count: 35.00},
      {id: 1004, orderId: 2, goods: GOODS[2], count: 14.00}
    ]},
  {id: 3, client: CLIENTS[2],
    date: new Date(1564666913000), address: 'test_address_3', orderLines: []},
  {id: 4, client: CLIENTS[3],
    date: new Date(1564667014000), address: 'test_address_4', orderLines: []},
  {id: 5, client: CLIENTS[4],
    date: new Date(1564667115000), address: 'test_address_5', orderLines: []},
  {id: 6, client: CLIENTS[5],
    date: new Date(1564667216000), address: 'test_address_6', orderLines: []}
]
