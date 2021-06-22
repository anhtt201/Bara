import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Brand } from 'src/app/Model/brand';
import { Catalog } from 'src/app/Model/catalog';
import { Color } from 'src/app/Model/color';
import { Comment } from 'src/app/Model/comment';
import { Product } from 'src/app/Model/product';
import { Size } from 'src/app/Model/size';
import { CommentService } from 'src/app/Service/comment.service';
import { LazyLoadScriptService } from 'src/app/Service/lazy-load.service';
import { ProductService } from 'src/app/Service/product.service';
import * as moment from 'moment';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css'],
})
export class ProductDetailComponent implements OnInit, AfterViewInit {
  @ViewChild('userInput') input!: ElementRef;

  productDetail!: Product;
  productCatalog!: Catalog;
  productBrand!: Brand;
  productSize!: Size[];
  productColor!: Color[];
  productComment!: Comment[];
  productId!: number;
  userId!: number;

  constructor(
    private lazyService: LazyLoadScriptService,
    private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    private commentService: CommentService,
    private datePipe: DatePipe
  ) {
    this.productId = this.activatedRoute.snapshot.params['id'];
    this.userId = parseInt(sessionStorage.getItem('id')!);
    this.commentService.allComment.subscribe((data) => {
      this.productComment = data;
    });
  }

  ngAfterViewInit() {
    this.lazyService.load('assets/js/main.js');
  }

  ngOnInit(): void {
    this.productService.getProductById(this.productId).subscribe((data) => {
      this.productDetail = data;
      this.productCatalog = data.catalog;
      this.productBrand = data.brand;
      this.productSize = data.sizes;
      this.productColor = data.colors;
      console.log(this.productCatalog);
      console.log(this.productBrand);
      console.log(this.productSize);
      console.log(this.productColor);
    });
    this.commentService
      .getAllCommentByProduct(this.productId)
      .subscribe((data) => {
        this.productComment = data;
        console.log(data);
      });
  }

  postComment(content: String) {
    let date = moment().format('DD-MM-YYYY');
    console.log(date);
    console.log(content);
    let comment: Comment = new Comment(content, date, 4, true);
    // if(event.keydown === 13){}
    this.commentService
      .postUserComment(comment, this.productId, this.userId)
      .subscribe((data) => {
        this.productComment.push(data);
        this.commentService.allComment.next(this.productComment);
        console.log(data);
      });
    this.input.nativeElement.value = '';
  }
}
