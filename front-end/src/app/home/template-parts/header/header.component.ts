import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../guard/auth.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
  }

  doLogout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
