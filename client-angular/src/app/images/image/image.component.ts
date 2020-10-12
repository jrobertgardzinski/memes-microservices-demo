import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Image } from 'src/app/common/model/image';
import { ImageService } from './image.service';
import { MatDialog } from '@angular/material/dialog';
import { ChangeTitleComponent } from 'src/app/modals/change-title/change-title.component'
import { ImageDetailsComponent } from 'src/app/images/image/image-details/image-details.component';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.scss']
})
export class ImageComponent implements OnInit {
  @Input() image: Image;
  @Output() deleted: EventEmitter<any> = new EventEmitter();

  constructor(
    private imageService: ImageService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
  }

  delete(id) {
    this.imageService.delete(id).subscribe(() => {
      this.deleted.emit(this.image);
    });
  }

  openUpdateDialog(image) {
    this.dialog.open(ChangeTitleComponent, {
      data: this.image
    });
  }
  
  showImageDetails(id: string) {
    const dialogRef = this.dialog.open(ImageDetailsComponent,
      {
        data: {
          id: id
        }
      });

    dialogRef.afterClosed().subscribe(() => {});
  }
}
