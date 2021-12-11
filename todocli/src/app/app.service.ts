import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


const API_BASE = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(
    private http: HttpClient
  ) { }




  getAll() {
    return this.http.get(`${API_BASE}/tasks`);
  }


  create(task: any) {
    return this.http.post(`${API_BASE}/task`, task);
  }

  update(id: string, task: any) {
    return this.http.put(`${API_BASE}/task/${id}`, task);
  }

  delete(id: string) {
    return this.http.delete(`${API_BASE}/task/${id}`);
  }

}
