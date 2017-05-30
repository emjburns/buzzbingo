import {Injectable} from "@angular/core";

@Injectable()
export class BuzzUtils {
  protocol: string = "http://";
  hostname: string = "localhost";
  port: string = "8080";
  version: string = "v1/";

  cookieKey: string = "username"

  gameboardName(gameName: string, playerName: string): string {
    return gameName + "-" + playerName;
  }

  baseURL(): string {
    return this.protocol + this.hostname + (this.port? ":" + this.port : "") + "/" + this.version;
  }
}
