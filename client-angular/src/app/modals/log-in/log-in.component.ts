import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthorizationService } from 'src/app/common/services/authorization.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.scss']
})
export class LogInComponent implements OnInit {
  
  public logInForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<LogInComponent>,
    private fb: FormBuilder,
    private authorizationService: AuthorizationService
  ) { }

  ngOnInit(): void {
    this.logInForm = this.fb.group({
      username: new FormControl(''),
      password: new FormControl(''),
    });
  }

  submitCredentials() {
    this.authorizationService.logIn(this.logInForm.value).subscribe(data => {
      this.authorizationService.setUpAuthorizationData(data);
      this.dialogRef.close(data);
    });
  }  

}
