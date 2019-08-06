import { Client } from './client';
import {OrderLine} from "./order-line";

export class Order {
  id: number;
  client: Client;
  date: Date;
  address: string;

  orderLines: OrderLine[];
}

