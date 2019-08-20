import {Order} from '../order';
import {CLIENTS} from "./mock-clients";
import {GOODS} from "./mock-goods";

export const ORDERS: Order[] = [
  {id: 1, client: CLIENTS[0],
    date: new Date(1564666711000), address: 'test_address_1', orderLines: [
      {id: 1001, orderId: 1, goods: GOODS[0], count: 16.00, rownum:1}
    ]},
  {id: 2, client: CLIENTS[1],
    date: new Date(1564666812000), address: 'test_address_2', orderLines: [
      {id: 1002, orderId: 2, goods: GOODS[0], count: 22.00, rownum:1},
      {id: 1003, orderId: 2, goods: GOODS[1], count: 35.00, rownum:2},
      {id: 1004, orderId: 2, goods: GOODS[2], count: 14.00, rownum:3}
    ]},
  {id: 3, client: CLIENTS[2],
    date: new Date(1564666913000), address: 'test_address_3', orderLines: [
      {id: 1005, orderId: 3, goods: GOODS[3], count: 1.00, rownum:1},
      {id: 1006, orderId: 3, goods: GOODS[4], count: 2.00, rownum:2},
      {id: 1007, orderId: 3, goods: GOODS[5], count: 3.00, rownum:3}
    ]},
  {id: 4, client: CLIENTS[3],
    date: new Date(1564667014000), address: 'test_address_4', orderLines: [
      {id: 1008, orderId: 4, goods: GOODS[6], count: 101.00, rownum:1},
      {id: 1009, orderId: 4, goods: GOODS[7], count: 102.00, rownum:2},
      {id: 1010, orderId: 4, goods: GOODS[8], count: 102.00, rownum:3}
    ]},
  {id: 5, client: CLIENTS[4],
    date: new Date(1564667115000), address: 'test_address_5', orderLines: [
      {id: 1011, orderId: 5, goods: GOODS[9], count: 2.00, rownum:1},
      {id: 1012, orderId: 5, goods: GOODS[1], count: 3.00, rownum:2},
      {id: 1013, orderId: 5, goods: GOODS[0], count: 14.00, rownum:3}
    ]},
  {id: 6, client: CLIENTS[5],
    date: new Date(1564667216000), address: 'test_address_6', orderLines: [
      {id: 1014, orderId: 6, goods: GOODS[0], count: 5.00, rownum:1},
      {id: 1015, orderId: 6, goods: GOODS[1], count: 4.00, rownum:2},
      {id: 1016, orderId: 6, goods: GOODS[2], count: 3.00, rownum:3}
    ]}
];
