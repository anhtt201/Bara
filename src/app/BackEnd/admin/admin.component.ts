import { AfterViewInit, Component, OnInit } from '@angular/core';
import { LazyLoadScriptService } from 'src/app/Service/lazy-load.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit, AfterViewInit {
  constructor(private lazyService: LazyLoadScriptService) {}

  ngAfterViewInit() {
    this.lazyService.load('assets/js/plugins/sidebarmenu.js');
    this.lazyService.load('assets/js/custom.min.js');
  }
  ngOnInit(): void {}
}
