import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Wordbank } from './wordbank';

@Injectable()
export class WordbankService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private wordbanksUrl = 'api/wordbanks';  // URL to web api

  constructor(private http: Http) { }

  getWordbanks(): Promise<Wordbank[]> {
    return this.http.get(this.wordbanksUrl)
               .toPromise()
               .then(response => response.json().data as Wordbank[])
               .catch(this.handleError);
  }

  getWordbank(id: number): Promise<Wordbank> {
    const url = `${this.wordbanksUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as Wordbank)
      .catch(this.handleError);
  }

  delete(id: number): Promise<void> {
    const url = `${this.wordbanksUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  // create(name: string, words: string[]): Promise<Wordbank> {
  //   return this.http
  //     .post(this.wordbanksUrl, JSON.stringify({name: name, word: words}), {headers: this.headers})
  //     .toPromise()
  //     .then(res => res.json().data)
  //     .catch(this.handleError);
  // }

  update(wordbank: Wordbank): Promise<Wordbank> {
    const url = `${this.wordbanksUrl}/${wordbank.id}`;
    return this.http
      .put(url, JSON.stringify(wordbank), {headers: this.headers})
      .toPromise()
      .then(() => wordbank)
      .catch(this.handleError);
  }

  //TODO: update error handling
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
