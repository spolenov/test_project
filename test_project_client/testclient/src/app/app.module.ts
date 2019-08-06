import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms'; // <-- NgModel lives here
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {GoodsListComponent} from './goods-list/goods-list.component';
import {TableModule} from "primeng/table";
import {TabViewModule} from 'primeng/tabview';
import {OrderDetailComponent} from './order-detail/order-detail.component';
import {OrderListComponent} from './order-list/order-list.component';

import {
  MAT_DIALOG_DEFAULT_OPTIONS,
  MatDatepickerModule,
  MatDialogModule,
  MatFormFieldModule,
  MatSelectModule
} from "@angular/material";

import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MainJournalComponent} from './main-journal/main-journal.component';
import {ButtonModule} from "primeng/button";
import {DropdownModule, TabMenuModule} from "primeng/primeng";
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';


@NgModule({
  declarations: [
    AppComponent,
    GoodsListComponent,
    OrderDetailComponent,
    OrderListComponent,
    MainJournalComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    TableModule,
    MatDialogModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatSelectModule,
    TabViewModule,
    ButtonModule,
    DropdownModule,
    TabMenuModule
  ],
  providers: [{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}],
  bootstrap: [AppComponent],
  entryComponents: [OrderDetailComponent]
})
export class AppModule { }
