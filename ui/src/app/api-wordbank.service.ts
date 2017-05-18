import { Injectable }       from '@angular/core';
import { Http, Response }   from '@angular/http';
import { Observable }       from 'rxjs/Observable';
import { Wordbank }         from './wordbank';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class ApiWordbankService {
  constructor (private http: Http) {}


  private wordbanksUrl = 'http://localhost:8080/v1/wordbank/';

  getWordbanks(): Observable<Wordbank[]> {
    return this.http.get(this.wordbanksUrl)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  getWordbank(name: String): Observable<Wordbank> {
    return this.http.get(this.wordbanksUrl + "/" + name)
      .map(this.extractData)
      .catch(this.handleError);
  }


  private extractData(res: Response) {
    let body = res.json();
    // console.log(body)
    return body || { };
  }
  private handleError (error: Response | any) {
    // In a real world app, you might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
