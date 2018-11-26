import {Component, OnChanges, OnInit} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnChanges, OnInit {

  number_of_cities: string;

  constructor() {
  }

  ngOnChanges() {
      this.number_of_cities = sessionStorage.getItem("number_of_cities");
  }

  ngOnInit(){
      this.number_of_cities = sessionStorage.getItem("number_of_cities");
  }
}
