import { Component, OnInit } from '@angular/core';
import {Client} from "../client";
import {CLIENTS} from "../mock/mock-clients";
import {MenuItem} from "primeng/api";
import {Goods} from "../goods";

@Component({
  selector: 'app-main-journal',
  templateUrl: './main-journal.component.html',
  styleUrls: ['./main-journal.component.css']
})
export class MainJournalComponent implements OnInit {
  clients: Client[];

  mainMenuItems: MenuItem[];

  constructor() { }

  ngOnInit() {
    this.clients = this.fetchClients();

    this.mainMenuItems = [
      {label: 'Заказы', routerLink: ['/order-list']},
      {label: 'Товары', routerLink: ['/goods-list']},
    ];
  }

  fetchClients(): Client[]{
    return CLIENTS;
  }
}
