import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, catchError, throwError} from 'rxjs';
import {ApiResponse} from '../models/api-response';

@Injectable({
  providedIn: 'root'
})
export class LabSeqService {

  private http: HttpClient = inject(HttpClient);

  public getLabSeq(term: number): Observable<ApiResponse<any>> {
    return this.http.get<ApiResponse<BigInt>>(`http://localhost:8080/labseq/${term}`)
      .pipe(
        catchError((error) => {
          console.error('API Error:', error);
          return throwError(() => error);
        })
      );
  }
}
