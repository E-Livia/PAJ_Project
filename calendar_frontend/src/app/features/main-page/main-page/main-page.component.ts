import { Component, ElementRef, ViewChild } from '@angular/core';
import { NzCalendarMode } from 'ng-zorro-antd/calendar';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.scss',
})
export class MainPageComponent {
  @ViewChild('fileInput') fileInput!: ElementRef;
  selectedDate: Date = new Date(); // Data selectată inițial
  events = []; // Array-ul de evenimente
  showAddEventTab: boolean = false;

  constructor() {
  }

  onDateChange(date: Date): void {
    this.selectedDate = date;
    this.showAddEventTab = true;
  }

  triggerFileInput() {
    this.fileInput.nativeElement.click(); // Deschide File Explorer
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      console.log('Fișier selectat:', file);
      // Prelucrarea fișierului aici, de exemplu încărcare
    }
  }
}
