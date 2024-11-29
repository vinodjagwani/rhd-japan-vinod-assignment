import { NgModule } from "@angular/core";
import { Routes,RouterModule } from '@angular/router'
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { ShoppingCartComponent } from "./shopping-cart/shopping-cart.component";
import { PageNotFoundComponent } from "./shared/page-not-found/page-not-found.component";


const routes: Routes =[
    {path: '', redirectTo: '/shop', pathMatch: 'full'},
    {path: 'login', component:LoginComponent },
    {path: 'register', component:RegisterComponent},
    {path: 'shop', component:ShoppingCartComponent},
    {path: '**', component: PageNotFoundComponent}
]

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule{

}