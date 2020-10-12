import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Image } from 'src/app/common/model/image';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { ImageService } from 'src/app/images/image/image.service';

@Component({
  selector: 'app-change-title',
  templateUrl: './change-title.component.html',
  styleUrls: ['./change-title.component.scss']
})
export class ChangeTitleComponent implements OnInit {
  public form: FormGroup;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Image,
    public dialogRef: MatDialogRef<ChangeTitleComponent>,
    private fb: FormBuilder,
    private imageService: ImageService
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      title: new FormControl(this.data.title)
    });
  }

  submitForm() {
    this.data.title = this.form.controls['title'].value;
    this.imageService.update(this.data).subscribe(data => this.dialogRef.close(data));
  }
}
