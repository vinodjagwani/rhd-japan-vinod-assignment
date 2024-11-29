import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators, FormArray, FormArrayName } from '@angular/forms';


/**
 * 
 * @param form 
 */

function symbolValidator(control) {  //control=registerForm.get('password')

  if (control.hasError('required')) return null;
  if (control.value.indexOf('@') > -1) {
    return null
  }
  else {
    return { symbol: true }
  }
}

function passwordMatch(form) {
  const password = form.get('password')
  const confirmPassword = form.get('confirmPassword')

  if (password.value !== confirmPassword.value) {
    confirmPassword.setErrors({ passwordMatch: true })
  }
  else {
    confirmPassword.setErrors(null)
  }
}


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  constructor(private builder: FormBuilder) { }

  validationMessages = {
    'name': {
      'required': "Name can't be blank",
      'minLength': " Name should be atleast 4 characters",
      'maxLength': "Name should be maximum 12 characters",

    },
    'email': {
      'required': 'Email is required',
      'email': 'Email must be valid'
    },
    'number': {
      'required': "Ph No can't be blank",
      'pattern': 'Ph No must be 10 digits'
    },
    'username': {
      'required': "Username can't be blank",
    },
    'password': {
      'required': "Password can't be blank.",
      'symbol': "Password must contain @",
      'minlength': "Password must be 8 characters long"
    }

  }

  formErrors = {};

  logValidationErrors(group: FormGroup = this.registerForm): void {

    Object.keys(group.controls).forEach((key: string) => {
      const abstractControl = group.get(key);

      this.formErrors[key] = '';
      if (abstractControl && !abstractControl.valid &&
        (abstractControl.touched || abstractControl.dirty)) {
        const messages = this.validationMessages[key];

        for (const errorKey in abstractControl.errors) {
          if (errorKey) {
            this.formErrors[key] += messages[errorKey] + ' ';
          }
        }
      }
      if (abstractControl instanceof FormGroup) {
        this.logValidationErrors(abstractControl);
      }
    });
  }

  
  register() {
    localStorage.setItem('usrname', JSON.stringify(this.registerForm.value.username)),
      localStorage.setItem('password', JSON.stringify(this.registerForm.value.password)),
      console.log(this.registerForm.value)
    alert("User registered ! ")
  }




  ngOnInit() {
    this.registerForm = this.builder.group({
      name: ['', Validators.required, Validators.minLength(4), Validators.maxLength(12)],
      email: ['', [Validators.required, Validators.email]],
      number: ['', [Validators.required, Validators.pattern("[0-9 ]{10}")]],
      username: ['', Validators.required],
      password: ['', [Validators.required, symbolValidator, Validators.minLength(8)]],
      address: this.builder.array([
        this.addAddressFormGroup()
      ]),
      confirmPassword: '',
    }, {
      validators: passwordMatch
    });
    this.registerForm.valueChanges.subscribe((data) => {
      this.logValidationErrors(this.registerForm);
    });
  }

  removeAddressButtonClick(addressGroupIndex: number):void{
      (<FormArray>this.registerForm.get('address')).removeAt(addressGroupIndex);
  }
   
  addAddressFormGroup(): FormGroup {
    return this.builder.group({
      street: ['', Validators.required],
      city: ['', Validators.required],
      pincode: ['', [Validators.required, Validators.pattern("[0-9]{6}")]]
    });
  }

  addAddressButtonClick(): void {
    (<FormArray>this.registerForm.get('address')).push(this.addAddressFormGroup());
  }
}
