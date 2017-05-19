import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'my-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ],
  // providers: [HeroService]
})
export class DashboardComponent implements OnInit {

  constructor(
    // private heroService: HeroService
  ) { }

  ngOnInit(): void {
    // this.heroService.getHeroes()
    //   .then(heroes => this.heroes = heroes.slice(1, 5));
  }
}
