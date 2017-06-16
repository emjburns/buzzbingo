import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

@Component({
  selector: 'not-found',
  templateUrl: './not-found.component.html',
  styleUrls: [ './not-found.component.css' ],
  providers: []
})
export class NotFoundComponent implements OnInit {
  errorMessage: string;
  notFoundImage: string = "../../cutebee.png"
  constructor(
    private router: Router,
  ) { }

  ngOnInit(): void {
  }
}
