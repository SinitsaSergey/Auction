import {NgModule} from "@angular/core";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
  MdButtonModule,
  MdCardModule,
  MdDatepickerModule,
  MdExpansionModule,
  MdGridListModule,
  MdIconModule,
  MdInputModule,
  MdMenuModule, MdNativeDateModule,
  MdProgressSpinnerModule,
  MdSelectModule,
  MdTabsModule,
  MdToolbarModule
} from "@angular/material";

@NgModule({
imports: [
  BrowserAnimationsModule,
  MdToolbarModule,
  MdTabsModule,
  MdButtonModule,
  MdMenuModule,
  MdIconModule,
  MdCardModule,
  MdInputModule,
  MdProgressSpinnerModule,
  MdExpansionModule,
  MdDatepickerModule,
  MdNativeDateModule,
  MdGridListModule,
  MdSelectModule
],
  exports: [
    BrowserAnimationsModule,
    MdToolbarModule,
    MdTabsModule,
    MdButtonModule,
    MdMenuModule,
    MdIconModule,
    MdCardModule,
    MdInputModule,
    MdProgressSpinnerModule,
    MdExpansionModule,
    MdDatepickerModule,
    MdNativeDateModule,
    MdGridListModule,
    MdSelectModule
  ],
  providers: []
})

export class MaterialDesignModule {

}
