import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OrderListComponent} from "./order-list/order-list.component";
import {GoodsListComponent} from "./goods-list/goods-list.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";

const appRoutes: Routes = [
  {path: 'order-list', component: OrderListComponent, data: {title: 'Order list'}},
  {path: 'main', redirectTo: 'order-list', pathMatch: 'full'},
  {path: 'goods-list', component: GoodsListComponent, data: {title: 'Goods list'}},
  {path: '', redirectTo: 'order-list', pathMatch: 'full'},
  { path: '**', component: PageNotFoundComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
