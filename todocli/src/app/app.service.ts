import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


const API_BASE = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(
    private http: HttpClient
  ) { }




  getAll() {
    return this.http.get(`${API_BASE}/api/tasks`);
  }


  create(task: any) {
    delete task.id;
    return this.http.post(`${API_BASE}/api/task`, task);
  }

  update(id: string, task: any) {
    return this.http.put(`${API_BASE}/api/task/${id}`, task);
  }

  delete(id: string) {
    return this.http.delete(`${API_BASE}/api/task/${id}`);
  }

}
