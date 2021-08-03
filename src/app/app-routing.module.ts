import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CatalogAddComponent } from './BackEnd/catalog-add/catalog-add.component';
import { CatalogListComponent } from './BackEnd/catalog-list/catalog-list.component';
import { AdminComponent } from './BackEnd/admin/admin.component';
import { AboutComponent } from './FrontEnd/about/about.component';
import { AccountComponent } from './FrontEnd/account/account.component';
import { BlogComponent } from './FrontEnd/blog/blog.component';
import { CatalogComponent } from './FrontEnd/catalog/catalog.component';
import { ContactComponent } from './FrontEnd/contact/contact.component';
import { FAQComponent } from './FrontEnd/faq/faq.component';
import { HomeComponent } from './FrontEnd/home/home.component';
import { LoginComponent } from './FrontEnd/login/login.component';
import { NewsComponent } from './FrontEnd/news/news.component';
import { NotFoundComponent } from './FrontEnd/not-found/not-found.component';
import { PortfolioComponent } from './FrontEnd/portfolio/portfolio.component';
import { ProductComponent } from './FrontEnd/product/product.component';
import { RegisterComponent } from './FrontEnd/register/register.component';
import { CatalogUpdateComponent } from './BackEnd/catalog-update/catalog-update.component';
import { ProductListComponent } from './BackEnd/product-list/product-list.component';
import { ProductAddComponent } from './BackEnd/product-add/product-add.component';
import { ProductUpdateComponent } from './BackEnd/product-update/product-update.component';
import { CartComponent } from './FrontEnd/cart/cart.component';
import { CommonModule } from '@angular/common';
import { ProductDetailComponent } from './FrontEnd/product-detail/product-detail.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'account', component: AccountComponent },
  { path: 'shop', component: ProductComponent },
  { path: 'product/:id', component: ProductDetailComponent },
  { path: 'cart', component: CartComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'news', component: NewsComponent },
  { path: 'portfolio', component: PortfolioComponent },
  { path: 'FAQ', component: FAQComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'admin-catalog_list', component: CatalogListComponent },
  { path: 'admin-catalog_add', component: CatalogAddComponent },
  { path: 'admin-catalog_update/:id', component: CatalogUpdateComponent },
  { path: 'admin-product_list', component: ProductListComponent },
  { path: 'admin-product_add', component: ProductAddComponent },
  { path: 'admin-product_update/:id', component: ProductUpdateComponent },
  // { path: 'test', component: TestComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '404', component: NotFoundComponent },
  { path: '**', redirectTo: '404' },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' }),
  ],
  exports: [RouterModule, CommonModule],
})
export class AppRoutingModule {}