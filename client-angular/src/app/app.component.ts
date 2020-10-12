import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AuthorizationService } from './common/services/authorization.service';
import { AddMemeComponent } from './modals/add-meme/add-meme.component';
import { LogInComponent } from './modals/log-in/log-in.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'DEMO Memes with microservices';

  constructor(
    public authorizationService: AuthorizationService,
    public dialog: MatDialog) {    
  }

  addMeme() {
    const dialogRef = this.dialog.open(AddMemeComponent);

    dialogRef.afterClosed().subscribe(() => {});
  }

  logIn() {
    const dialogRef = this.dialog.open(LogInComponent);

    dialogRef.afterClosed().subscribe(() => {});
  }

  logOut() {
    this.authorizationService.logOut();
  }
}
