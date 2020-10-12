import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnInit {
  @Input() numberOfPages: number;
  @Input() currentPage: number;

  @Output() changePage: EventEmitter<number> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  toArray(arrayLength) {
    return Array.from(Array(arrayLength), (_, i) => i + 1);
  }

  colorActivePage(element): string {    
    return element == this.currentPage ? "accent" : "primary";
  }

  emitChangePageEvent(element) {
    if (element != this.currentPage) {
      this.changePage.emit(element);
    }
  }
}
