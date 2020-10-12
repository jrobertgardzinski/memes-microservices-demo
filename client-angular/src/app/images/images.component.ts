import { Component, OnInit } from '@angular/core';
import { ImagesService } from './images.service';
import { Image } from 'src/app/common/model/image'
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.scss']
})
export class ImagesComponent implements OnInit {
  images: Image[];
  numberOfPages: number;
  currentPage: number = 1;

  constructor(
    private imagesService: ImagesService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.setPage(this.currentPage);
  }

  setPage(pageNumber: number) {
    this.imagesService.getImages(pageNumber).subscribe(result => {
      this.images = result['content'] as Image[];
      this.images.forEach(x => x.image = "data:image/gif;base64," + x.image);
      this.numberOfPages = result['totalPages'];
    });
  }

  delete(deletedImage) {
    this.images = this.images.filter(element => element !== deletedImage);
  }

  changePage(event) {
    this.currentPage = event;
    this.setPage(this.currentPage);
    scroll(0,0);
  }
}
