import { Injectable }       from '@angular/core';
import { Http, Response, Headers, RequestOptions }   from '@angular/http';
import { Observable }       from 'rxjs/Observable';
import { Wordbank }         from './wordbank';
import { BuzzUtils }        from './buzzutils';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class ApiWordbankService {
  constructor (
    private http: Http,
    private buzzutils: BuzzUtils
  ) {}


  // private wordbanksUrl = 'http://localhost:8080/v1/wordbank/';
  private wordbanksUrl = this.buzzutils.baseURL() + 'wordbank/';

  getWordbanks(): Observable<Wordbank[]> {
    console.log("service")
    return this.http.get(this.wordbanksUrl)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  getWordbankNames(): Observable<String[]> {
    return this.http.get(this.wordbanksUrl + "names")
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  getWordbank(name: String): Observable<Wordbank> {
    return this.http.get(this.wordbanksUrl + "/" + name)
      .map(this.extractData)
      .catch(this.handleError);
  }

  create(name: String, words: String[]): Observable<Wordbank> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.wordbanksUrl + name, { words }, options)
                    .map(this.extractData)
                    .catch(this.handleError);

  }

  delete(name: String)  {
    return this.http.delete(this.wordbanksUrl + name)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  deleteWord(name: String, word: String[]) {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(this.wordbanksUrl + name + "/removeWords", word, options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  addWord(name: String, word: String[]) {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(this.wordbanksUrl + name + "/addWords", word, options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }


  private extractData(res: Response) {
    let body = res.json();
    console.log(body)
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
