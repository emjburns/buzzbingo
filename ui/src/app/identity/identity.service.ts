import { Injectable }       from '@angular/core';
import { Http, Response, Headers, RequestOptions }   from '@angular/http';
import { Observable }       from 'rxjs/Observable';
import { UUID } from 'angular2-uuid';
import { CookieService } from 'ngx-cookie';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

// import { BuzzUtils }    from '../buzzutils'

@Injectable()
export class IdentityService {
  cookieKeyID: string = "buzzID"

  constructor (
    private http: Http,
    // private buzzutils: BuzzUtils,w
    private cookieService: CookieService
  ) {}

  initializeIdentity() {
    if (!this.cookieService.get(this.cookieKeyID)) {
      console.log("Generating new buzzID");
      //uuid doesn't exist
      this.generateUniqueID();
    }
  }

  getID(): string {
    return this.cookieService.get(this.cookieKeyID);
  }

  private generateUniqueID(): void {
      this.cookieService.put(this.cookieKeyID, UUID.UUID());
  }
}
