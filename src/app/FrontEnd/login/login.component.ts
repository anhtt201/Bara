import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/Model/user';
import { AuthService } from 'src/app/Service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submitted: boolean = false;
  errorMessage: string = '';
  isLogin: boolean = false;
  isLoginFailed: boolean = false;
  isAdmin: boolean = false;
  isUser: boolean = true;

  username!: string;
  userRoles!: string;
  email!: string;
  phone!: string;
  address!: string;
  avatar!: string;
  balance!: number;
  dob!: Date;

  id!: number;
  frmUserUp!: FormGroup;
  user: User = new User(0, '', '', '', '', [], '', '', 0, new Date(), false);
  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initLoginForm();
    this.authService.getUserById(this.id).subscribe((data) => {
      this.user = data;
      // this.id = this.user.id;
      // this.username = this.user.username;
      // this.email = this.user.email;
      // this.phone = this.user.phone;
      // this.address = this.user.address;
      // this.avatar = this.user.avatar;
      // this.balance = this.user.balance;
      // this.dob = this.user.dob;
      this.frmUserUp.get('id')!.setValue(this.user.id);
      this.frmUserUp.get('username')!.setValue(this.user.username);
      this.frmUserUp.get('email')!.setValue(this.user.email);
      this.frmUserUp.get('phone')!.setValue(this.user.phone);
      this.frmUserUp.get('address')!.setValue(this.user.address);
      this.frmUserUp.get('avatar')!.setValue(this.user.avatar);
      this.frmUserUp.get('balance')!.setValue(this.user.balance);
      this.frmUserUp.get('dob')!.setValue(this.user.dob);
    });
    this.initFormUp();
  }

  initFormUp() {
    this.frmUserUp = this.fb.group({
      id: new FormControl(),
      username: new FormControl(),
      email: new FormControl(),
      phone: new FormControl(),
      address: new FormControl(),
      avatar: new FormControl(),
      balance: new FormControl(),
      dob: new FormControl(),
    });
  }

  initLoginForm() {
    this.loginForm = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
    });
    this.id = parseInt(sessionStorage.getItem('id') || '0');
    this.username = sessionStorage.getItem('username')!;
    this.email = sessionStorage.getItem('username')!;
    this.phone = sessionStorage.getItem('phone') || '';
    this.address = sessionStorage.getItem('address') || 'hello';
    this.avatar = sessionStorage.getItem('avatar') || 'hello';
    this.balance = parseFloat(sessionStorage.getItem('balance') || '0');
    this.dob = new Date(sessionStorage.getItem('dob') || 'hello');
  }

  onSubmit() {
    this.submitted = true;
    this.authService
      .login(this.loginForm.value.username, this.loginForm.value.password)
      .subscribe(
        (data) => {
          this.isLogin = true;
          this.id = data.id;
          console.log(this.id);
          this.router.navigate(['/home']);
        },
        (error) => {
          console.log(error);
          this.errorMessage = error;
          this.isLogin = false;
          this.isLoginFailed = true;
        }
      );
  }

  updateUser() {
    let user = this.frmUserUp.value;
    this.authService.updateUser(user.id, user).subscribe((data) => {
      console.log(data);
      sessionStorage.setItem('username', user.username);
      sessionStorage.setItem('token', user);
      sessionStorage.setItem('roles', JSON.stringify(user.roles));
      sessionStorage.setItem('email', user.email);
      sessionStorage.setItem('phone', user.phone);
      sessionStorage.setItem('address', user.address);
      sessionStorage.setItem('avatar', user.avatar);
      sessionStorage.setItem('balance', user.balance);
      sessionStorage.setItem('dob', user.dob);
      sessionStorage.setItem('status', user.status);
      this.initLoginForm();
      window.location.reload();
    });
  }

  loggedIn() {
    return this.authService.isLoggedIn();
  }
}
