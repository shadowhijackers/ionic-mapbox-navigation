import {Component, OnInit} from '@angular/core';
import {Plugins} from '@capacitor/core';
import {environment} from "../../environments/environment";

const {MapboxNav} = Plugins;

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit{

  constructor() {}

  ngOnInit(): void {
  }

  setDirection(lat: number, lng:number) {
    MapboxNav?.init({
      accessToken: environment.mapboxToken,
        destLong: lng,
        destLat: lat
    }).then((res)=>{
      console.log('init: ', res)
    });
  }
}
