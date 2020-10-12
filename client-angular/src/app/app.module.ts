import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ImagesComponent } from './images/images.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ImageComponent } from 'src/app/images/image/image.component';
import { PaginationComponent } from './images/pagination/pagination.component';
import { AddMemeComponent } from './modals/add-meme/add-meme.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import { ChangeTitleComponent } from './modals/change-title/change-title.component';
import { LogInComponent } from './modals/log-in/log-in.component';
import { JwtModule } from '@auth0/angular-jwt';
import {MatTooltipModule} from '@angular/material/tooltip';
import { environment } from 'src/environments/environment';
import { ImageDetailsComponent } from 'src/app/images/image/image-details/image-details.component';

@NgModule({
  declarations: [
    AppComponent,
    ImagesComponent,
    ImageComponent,    
    PaginationComponent, 
    AddMemeComponent, 
    ChangeTitleComponent, LogInComponent, ImageDetailsComponent
  ],
  imports: [
    BrowserModule,
    MatDialogModule,
    HttpClientModule,
    FormsModule, 
    ReactiveFormsModule,
    MatInputModule,
    AppRoutingModule,
    MatButtonModule,
    MatToolbarModule,
    MatTooltipModule,
    MatIconModule,
    MatDividerModule,
    BrowserAnimationsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        authScheme: "Bearer ",
        allowedDomains: ['localhost:5555'],
        disallowedRoutes: ['//localhost:5555/auth/oauth/token']
      },
    }),
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function tokenGetter() {
  return sessionStorage.getItem("access_token");
}