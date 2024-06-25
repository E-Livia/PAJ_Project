import { Component } from '@angular/core';
import { NzCalendarMode } from 'ng-zorro-antd/calendar';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.scss',
})
export class MainPageComponent {
  selectedDate: Date = new Date(); // Data selectată inițial
  events = []; // Array-ul de evenimente

  constructor() {
  }

  onDateChange(date: Date): void {
    this.selectedDate = date;
  }

}
