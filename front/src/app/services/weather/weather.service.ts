import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Subject} from 'rxjs';

@Injectable()
export class WeatherService {

  URL: string;

  constructor(public http: HttpClient) {
    this.URL = "http://localhost:8080";
  }

  getWeatherState(city: string): Subject<Object> {
    const dataSubject = new Subject<Object>();
    this.http.get(
      this.URL + "/weather/" + city)
      .subscribe((data) => {
        dataSubject.next(data['state']);
      });
    return dataSubject;
  }

  getCurrentTemp(city: string, metric: 'metric' | 'imperial' = 'metric'): Subject<Object> {
    const dataSubject = new Subject<Object>();
    this.http.get(
      this.URL + "/weather/" + city)
      .subscribe((data: any) => {
        dataSubject.next(data["temp"]);
      });
    return dataSubject;
  }


  getCurrentHum(city: string, metric: 'metric' | 'imperial' = 'metric'): Subject<Object> {
    const dataSubject = new Subject<Object>();
    this.http.get(
      this.URL + "/weather/" + city)
      .subscribe((data: any) => {
        dataSubject.next(data["hum"]);
      });
    return dataSubject;
  }


  getCurrentWind(city: string, metric: 'metric' | 'imperial' = 'metric'): Subject<Object> {
    const dataSubject = new Subject<Object>();
    this.http.get(
      this.URL + "/weather/" + city)
      .subscribe((data: any) => {
        dataSubject.next(data["wind"]);
      });
    return dataSubject;
  }


  getMaxTemp(city: string, metric: 'metric' | 'imperial' = 'metric'): Subject<number> {
    const dataSubject = new Subject<number>();
    let max: number;
    this.http.get(
      `https://api.openweathermap.org/data/2.5/forecast?q=${city}&units=${metric}&APPID=952d6b1a52fe15a7b901720074680562`)
      .subscribe((weather: any) => {
        max = weather.list[0].main.temp;
        weather.list.forEach((value) => {
          if (max < value.main.temp) {
            max = value.main.temp;
          }
        });
        dataSubject.next(Math.round(max));
      });
    return dataSubject;
  }

  getMinTemp(city: string, metric: 'metric' | 'imperial' = 'metric'): Subject<number> {
    const dataSubject = new Subject<number>();
    let min: number;
    this.http.get(
      `https://api.openweathermap.org/data/2.5/forecast?q=${city}&units=${metric}&APPID=952d6b1a52fe15a7b901720074680562`)
      .subscribe((weather: any) => {
        min = weather.list[0].main.temp;
        weather.list.forEach((value) => {
          if (min > value.main.temp) {
            min = value.main.temp;
          }
        });
        dataSubject.next(Math.round(min));
      });
    return dataSubject;
  }

  getForecast(city: string, metric: 'metric' | 'imperial' = 'metric'): Subject<any> {
    const dataSubject = new Subject<any>();
    this.http.get(
      this.URL + "/forecast/" + city)
      .subscribe((weather: any) => {
        dataSubject.next(weather);
      });
    return dataSubject;
  }

  addCity(city : string): Subject<any> {

    var num = JSON.parse(sessionStorage.getItem("number_of_cities"));
    num++;
    sessionStorage.setItem("city"+num, city);
    sessionStorage.setItem("number_of_cities",JSON.stringify(num));

    const dataSubject = new Subject<any>();
    if(city=="doha"){
      this.http.post<string>(
        this.URL + "/add/doha", city)
        .subscribe((weather: any) => {
          dataSubject.next(weather);
        });
        return dataSubject;
    }
    if(city=="paris"){
      this.http.post<string>(
        this.URL + "/add/paris", city)
        .subscribe((weather: any) => {
          dataSubject.next(weather);
        });
        return dataSubject;
    }
  }

  deleteCity(city : string): Subject<any> {

    var num = JSON.parse(sessionStorage.getItem("number_of_cities"));
    num = num - 1;
    sessionStorage.setItem("number_of_cities",JSON.stringify(num));
    if(city == sessionStorage.getItem("city1")){
      sessionStorage.removeItem("city1");
    }
    else{
      sessionStorage.removeItem("city2");
    }

    const dataSubject = new Subject<any>();
    if(city=="doha"){
      this.http.post<string>(
        this.URL + "/delete/doha", city)
        .subscribe((weather: any) => {
          dataSubject.next(weather);
        });
        return dataSubject;
    }
    if(city=="paris"){
      this.http.post<string>(
        this.URL + "/delete/paris", city)
        .subscribe((weather: any) => {
          dataSubject.next(weather);
        });
        return dataSubject;
    }
  }
}
