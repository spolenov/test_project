import { Component, OnInit } from '@angular/core';
import {Client} from "../client";
import {CLIENTS} from "../mock/mock-clients";
import {MenuItem} from "primeng/api";

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
    this.clients = this.getClients();

    this.mainMenuItems = [
      {label: 'Заказы', icon: 'fa fa-fw fa-bar-chart', routerLink: ['/order-list']},
      {label: 'Товары', icon: 'fa fa-fw fa-calendar', routerLink: ['/goods-list']},
    ];
  }

  getClients(): Client[]{
    return CLIENTS;
  }
}
