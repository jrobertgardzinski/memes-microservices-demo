import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Image } from 'src/app/common/model/image';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(
    private http: HttpClient
  ) { }

  delete(id) {
    return this.http.delete(`http://localhost:5555/images/${id}`);
  }

  update(image: Image) {
    return this.http.patch(`http://localhost:5555/images/${image.id}`, image.title);
  }
}
