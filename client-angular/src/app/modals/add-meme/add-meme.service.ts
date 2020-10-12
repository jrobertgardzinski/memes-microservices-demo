import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddMemeService {

  constructor(
    private httpClient: HttpClient) { }

  submitForm(data): Observable<any> {
    return this.httpClient.post("http://localhost:5555/images", data);
  }
}
