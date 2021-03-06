import { Injectable }       from '@angular/core';
import { Http, Response, Headers, RequestOptions }   from '@angular/http';
import { Observable }       from 'rxjs/Observable';
import { Game }         from './game';
import { BuzzUtils }    from '../buzzutils';

import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

@Injectable()
export class GameService {
  private gameUrl = this.buzzutils.baseURL() + 'game/';

  constructor (
    private http: Http,
    private buzzutils: BuzzUtils
  ) {}


  getGame(name: String): Observable<Game> {
    return this.http.get(this.gameUrl + name)
      .map(this.extractData)
      .catch(this.handleError);
  }

  createGame(gameName: String, wordbank: string): Observable<Game> {
    console.log("Creating new game");
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.gameUrl + gameName, {wordbank}, options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  addPlayer(gameName: string, playerName: string): Observable<Game> {
    console.log("adding player " + playerName + " to game " + gameName)
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(this.gameUrl + gameName + "/players/" + playerName, "" , options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    console.log("<<<< requ body: " + body);
    return body || { };
  }
  private handleError (error: Response | any) {
    // In a real world app, you might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      switch(+`${error.status}`){
        case 404: {
          errMsg = "This game does not exist. Try creating a game!";
          break;
        }
        case 403: {
          errMsg = "You can't join this game, it's over.";
          break;
        }
        default: {
          errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
          break;
        }
      }
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
