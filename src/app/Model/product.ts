import { Brand } from './brand';
import { Catalog } from './catalog';
import { Size } from './size';
import { Color } from './color';

export class Product {
  productId!: number;
  productName!: String;
  productPriceIn!: number;
  productPriceOut!: number;
  productDiscount!: number;
  productImg!: String;
  productImgId!: String;
  productDescription!: String;
  productCreatedDay!: String;
  productQuantity!: number;
  productIsHot!: boolean;
  productStatus!: boolean;
  catalog!: Catalog;
  brand!: Brand;
  sizes!: Size[];
  colors!: Color[];
  comments!: Comment[];
}
