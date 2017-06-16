import {Injectable} from "@angular/core";

@Injectable()
export class BuzzUtils {
  apiUrl ="http://localhost:8080/v1/";
  cookieKey: string = "username";

  gameboardName(gameName: string, playerName: string): string {
    return gameName + "-" + playerName;
  }

  baseURL(): string {
    return this.apiUrl;
  }
}
