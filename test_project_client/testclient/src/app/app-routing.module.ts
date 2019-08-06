import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OrderListComponent} from "./order-list/order-list.component";
import {MainJournalComponent} from "./main-journal/main-journal.component";
import {GoodsListComponent} from "./goods-list/goods-list.component";
import {OrderDetailComponent} from "./order-detail/order-detail.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";

const appRoutes: Routes = [
  {path: 'order-list', component: OrderListComponent, data: {title: 'Order list'}},
  {path: 'main', component: MainJournalComponent, data: {title: 'Main page'}},
  {path: 'goods-list', component: GoodsListComponent, data: {title: 'Goods list'}},
  {path: 'order/:id', component: OrderDetailComponent, data: {title: 'Order details'}},
  {path: '', redirectTo: 'main', pathMatch: 'full'},
  { path: '**', component: PageNotFoundComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
