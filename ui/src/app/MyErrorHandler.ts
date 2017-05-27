
import {NgModule, ErrorHandler} from '@angular/core';

export class MyErrorHandler implements ErrorHandler {

 handleError(error: Error) {
    console.log(error)
  }
}
