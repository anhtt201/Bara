import { Product } from './product';

export class Catalog {
  catalogId!: number;
  catalogName!: string;
  catalogParentId!: number;
  catalogImg!: string;
  catalogImgId!: string;
  catalogDescription!: string;
  catalogStatus!: boolean;
  catalogIsHot!: boolean;
  catalogType!: boolean;
  products!: Product[];
  constructor() {}
}
