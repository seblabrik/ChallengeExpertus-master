import {Component, OnInit} from '@angular/core';
import {WeatherService} from '../../services/weather/weather.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  city: string;
  cursor: number;
  cities : string[];
  
  constructor(public weather: WeatherService) {
  }

  ngOnInit() {
    this.cities = ["paris", "doha"];
    this.cursor = JSON.parse(sessionStorage.getItem("cursor"));
    this.city = this.cities[this.cursor];
  }

  public nextCity(){
      if (sessionStorage.getItem("number_of_cities")=="0"){
        this.cursor = (this.cursor+1)%(this.cities.length);
        sessionStorage.setItem("cursor",JSON.stringify(this.cursor));
        this.city = this.cities[this.cursor];
      }
  }

  public void addCity(){
    var num = sessionStorage.getItem("number_of_cities");
    this.weather.addCity(this.city);
    if(num=="0"){
      this.cursor = (this.cursor+1)%(this.cities.length);
      sessionStorage.setItem("cursor",JSON.stringify(this.cursor));
      this.city = this.cities[this.cursor];
    }
  }
}
