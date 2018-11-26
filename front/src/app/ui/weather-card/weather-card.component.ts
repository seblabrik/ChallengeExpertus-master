import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {WeatherService} from '../../services/weather/weather.service';
import {UiService} from '../../services/ui/ui.service';

@Component({
  selector: 'app-weather-card',
  templateUrl: './weather-card.component.html',
  styleUrls: ['./weather-card.component.css']
})
export class WeatherCardComponent implements OnInit {

  city: string;
  condition: string;
  currentTemp: number;
  maxTemp: number;
  minTemp: number;
  darkMode: boolean;

  constructor(public weather: WeatherService,
              public router: Router,
              public ui: UiService) {
  }

  ngOnInit() {
    this.ui.darkModeState.subscribe((isDark) => {
      this.darkMode = isDark;
    });
    if (sessionStorage.getItem("city1")!="" && sessionStorage.getItem("city_turn")=="1"){
      this.city = sessionStorage.getItem("city1");
      this.weather.getWeatherState(this.city)
      .subscribe((data: string) => {
        this.condition = data;
      });
      this.weather.getCurrentTemp(this.city).subscribe((data: number) => {
        this.currentTemp = data;
      });
      this.weather.getMinTemp(this.city).subscribe((data: number) => {
        this.minTemp = data;
      });
      this.weather.getMaxTemp(this.city).subscribe((data: number) => {
        this.maxTemp = data;
      });
      if (sessionStorage.getItem("number_of_cities")==2){sessionStorage.setItem("city_turn","2");}
    }
    else if (sessionStorage.getItem("city_turn")=="2"){
      this.city = sessionStorage.getItem("city2");
      this.weather.getWeatherState(this.city)
      .subscribe((data: string) => {
        this.condition = data;
      });
      this.weather.getCurrentTemp(this.city).subscribe((data: number) => {
        this.currentTemp = data;
      });
      this.weather.getMinTemp(this.city).subscribe((data: number) => {
        this.minTemp = data;
      });
      this.weather.getMaxTemp(this.city).subscribe((data: number) => {
        this.maxTemp = data;
      });
      sessionStorage.setItem("city_turn","1");
    }
  }

  openDetails() {
    this.router.navigateByUrl('/details/' + this.city);
  }

}
