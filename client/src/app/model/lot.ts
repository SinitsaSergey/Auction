import {User} from './user';
import {Status} from './status';
import {Auction} from './auction';

export class Lot{
  id: number;
  title: string;
  description: string;
  startPrice: number;
  seller: User;
  status: Status;
  auction: Auction;

}
