import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadFileResponse } from '../Model/upload-file-response';

@Injectable({
  providedIn: 'root',
})
export class UploadService {
  private baseUrl: string = 'http://localhost:8080/api/fileManager';
  constructor(private http: HttpClient) {}

  upload(file: File): Observable<UploadFileResponse> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post<UploadFileResponse>(
      `${this.baseUrl}/upload`,
      formData
    );
  }

  update(file: File, id: String): Observable<UploadFileResponse> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.put<UploadFileResponse>(
      `${this.baseUrl}/updateFile/${id}`,
      formData
    );
  }

  get(id: String): Observable<UploadFileResponse> {
    return this.http.get<UploadFileResponse>(`${this.baseUrl}/getFile/${id}`);
  }

  getAll(): Observable<UploadFileResponse[]> {
    return this.http.get<UploadFileResponse[]>(`${this.baseUrl}/getAll`);
  }

  delete(id: String) {
    return this.http.delete(`${this.baseUrl}/deleteFile/${id}`, {
      responseType: 'text',
    });
  }
}
