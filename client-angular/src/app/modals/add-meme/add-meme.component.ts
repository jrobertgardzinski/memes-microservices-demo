import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { AddMemeService } from './add-meme.service';
import { MatDialogRef } from '@angular/material/dialog';
import { ChangeTitleComponent } from '../change-title/change-title.component';

@Component({
  selector: 'app-add-meme',
  templateUrl: './add-meme.component.html',
  styleUrls: ['./add-meme.component.scss']
})
export class AddMemeComponent implements OnInit {
  
  public newMemeForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<AddMemeComponent>,
    private fb: FormBuilder,
    private addMemeService: AddMemeService
  ) { }

  ngOnInit(): void {
    this.newMemeForm = this.fb.group({
      title: new FormControl(''),
      image: new FormControl(''),
    });
  }

  submitForm() {
    const formData = new FormData();
    formData.append('image', this.newMemeForm.get('image').value);
    formData.append('title', this.newMemeForm.get('title').value);

    this.addMemeService.submitForm(formData).subscribe(data => this.dialogRef.close(data));
  }

  onFileSelect(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.newMemeForm.get('image').setValue(file);
    }
  }
}
