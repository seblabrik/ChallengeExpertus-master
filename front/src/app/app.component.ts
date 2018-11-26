import {Component, OnInit} from '@angular/core';
import {UiService} from './services/ui/ui.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  showMenu = false;
  darkModeActive: boolean;
  constructor(public ui: UiService) {

  }

  ngOnInit() {
    this.ui.darkModeState.subscribe((value) => {
      this.darkModeActive = value;
    });
    sessionStorage.setItem("cursor", "0");
    sessionStorage.setItem("number_of_cities","0");
    sessionStorage.setItem("city1", "");
    sessionStorage.setItem("city2", "");
    sessionStorage.setItem("city_turn", "1");
  }

  toggleMenu() {
    this.showMenu = !this.showMenu;
  }

  modeToggleSwitch() {
    this.ui.darkModeState.next(!this.darkModeActive);
  }

}
