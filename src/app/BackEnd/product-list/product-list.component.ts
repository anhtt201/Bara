import {
  AfterViewInit,
  Component,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { Table } from 'primeng/table';
import { Subject } from 'rxjs';
import { Brand } from 'src/app/Model/brand';
import { Catalog } from 'src/app/Model/catalog';
import { Color } from 'src/app/Model/color';
import { Product } from 'src/app/Model/product';
import { Size } from 'src/app/Model/size';
import { BrandService } from 'src/app/Service/brand.service';
import { CatalogService } from 'src/app/Service/catalog.service';
import { ColorService } from 'src/app/Service/color.service';
import { LazyLoadScriptService } from 'src/app/Service/lazy-load.service';
import { ProductService } from 'src/app/Service/product.service';
import { SizeService } from 'src/app/Service/size.service';
import { UploadService } from 'src/app/Service/upload.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit, AfterViewInit {
  @ViewChild('dt1') table!: Table;

  catalogList!: Catalog[];
  selectedCatalog!: Catalog;

  brandList: Brand[] = [];
  selectedBrand!: Brand;

  colorList: Color[] = [];
  selectedColors!: Color[];

  sizeList: Size[] = [];
  selectedSizes!: Size[];

  productList!: Product[];
  productDialog!: boolean;
  product!: Product;
  selectedProducts!: Product[];
  submitted!: boolean;

  cols!: any[];

  constructor(
    private lazyService: LazyLoadScriptService,
    private proService: ProductService,
    private catService: CatalogService,
    private brandService: BrandService,
    private colorService: ColorService,
    private sizeService: SizeService,
    private router: Router,
    private fileService: UploadService,
    private toastrService: ToastrService
  ) {}

  ngAfterViewInit() {
    this.lazyService.load('assets/js/plugins/sidebarmenu.js');
    this.lazyService.load('assets/js/custom.min.js');

    this.getAll();
  }

  date!: Date;

  ngOnInit(): void {
    this.date = moment().toDate();
    // this.product.productCreatedDay = moment().format('DD-MM-YYYY');
    // console.log(typeof(this.product.productCreatedDay));

    this.cols = [
      { field: 'productId', header: '#' },
      { field: 'productName', header: 'Name' },
      { field: 'productPriceIn', header: 'PriceIn' },
      { field: 'productPriceOut', header: 'PriceOut' },
      { field: 'productDiscount', header: 'Discount' },
      { field: 'productDescription', header: 'Description' },
      { field: 'productQuantity', header: 'Quantity' },
      { field: 'productCreatedDay', header: 'CreatedDay' },
      { field: 'productIsHot', header: 'Hot' },
      { field: 'colors', header: 'Color' },
      { field: 'sizes', header: 'Size' },
      { field: 'brand', header: 'Catalog' },
      { field: 'catalog', header: 'Brand' },
    ];
  }

  getAll() {
    this.proService.getAllProduct().subscribe((data) => {
      this.productList = data;
      console.log(this.productList);
    });

    this.catService.getAllCatalog().subscribe((data) => {
      this.catalogList = data;
      console.log(this.catalogList);
    });

    this.brandService.getAllBrand().subscribe((data) => {
      this.brandList = data;
      console.log(this.brandList);
    });

    this.colorService.getAllColor().subscribe((data) => {
      this.colorList = data;
    });

    this.sizeService.getAllSize().subscribe((data) => {
      this.sizeList = data;
    });
  }

  openModal() {
    this.product = new Product();
    this.selectedCatalog = new Catalog();
    this.selectedBrand = new Brand();
    this.selectedColors = [];
    this.selectedSizes = [];
    this.submitted = false;
    this.productDialog = true;
    this.uploadedFiles = [];
  }

  editProduct(product: Product) {
    this.product = { ...product };
    const currentPro = { ...product };
    this.selectedCatalog = currentPro.catalog;
    this.selectedBrand = currentPro.brand;
    this.selectedColors = currentPro.colors;
    this.selectedSizes = currentPro.sizes;
    this.productDialog = true;
    console.log(currentPro);
  }

  hideProduct() {
    this.productDialog = false;
    this.submitted = false;
  }

  selectedFiles!: FileList;
  currentFile!: File;
  uploadedFiles: any[] = [];

  selectFile(evt: any) {
    for (let file of evt.files) {
      this.uploadedFiles.push(file);
      console.log(file);
    }
  }

  saveProduct() {
    this.submitted = true;

    const file: File = this.uploadedFiles[0];
    this.currentFile = file;
    console.log(this.currentFile);

    if (this.product.productId != null) {
      const currentPro = { ...this.product };
      currentPro.catalog = this.selectedCatalog;
      currentPro.brand = this.selectedBrand;
      currentPro.colors = this.selectedColors;
      currentPro.sizes = this.selectedSizes;
      currentPro.productCreatedDay = moment().format('DD-MM-YYYY');
      console.log(currentPro);

      this.fileService
        .update(this.currentFile, currentPro.productImgId)
        .subscribe((e) => {
          currentPro.productImg = String(e.fileDownloadUri);
          console.log(currentPro);

          this.productList[this.findIndexById(currentPro.productId)] =
            currentPro;

          this.proService
            .updateProduct(currentPro.productId, currentPro)
            .subscribe((data) => {
              console.log(data);
            });

          this.toastrService.success('Updated succesfully', 'Succesfully');
        });
    } else {
      const currentPro = { ...this.product };
      currentPro.catalog = this.selectedCatalog;
      currentPro.brand = this.selectedBrand;
      currentPro.colors = this.selectedColors;
      currentPro.sizes = this.selectedSizes;
      currentPro.productCreatedDay = moment().format('DD-MM-YYYY');
      console.log(currentPro);

      this.fileService.upload(this.currentFile).subscribe((e) => {
        currentPro.productImg = String(e.fileDownloadUri);
        currentPro.productImgId = String(e.fileId);
        console.log(currentPro);

        this.proService.addProduct(currentPro).subscribe((data) => {
          this.product = data;
          console.log(data);
          console.log(this.product);
          this.productList.push(data);
          this.table.totalRecords++;
        });

        this.toastrService.success(
          'Product has been added succesfully!',
          'Succesfull'
        );
      });
    }

    this.productList = [...this.productList];
    this.productList = this.productList.slice();
    this.productDialog = false;
    this.uploadedFiles = [];
    this.product = new Product();
  }

  deleteProduct(product: Product) {
    this.proService.deleteProduct(product.productId).subscribe((data) => {
      this.fileService.delete(product.productImgId).subscribe((e) => {
        console.log(e);
      });
      console.log(data);
    });

    this.productList = this.productList.filter(
      (val) => val.productId !== product.productId
    );

    this.product = new Product();
    this.table.totalRecords--;
  }

  findIndexById(id: number): number {
    let index = -1;
    for (let i = 0; i < this.productList.length; i++) {
      if (this.productList[i].productId === id) {
        index = i;
        break;
      }
    }
    return index;
  }
}
