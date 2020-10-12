import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImagesService {

  constructor(
    private http: HttpClient
  ) { }

  getImages(currentPage: number) {
    return this.http.get(`http://localhost:5555/images?page=${currentPage-1}&size=10`);
  }
}
