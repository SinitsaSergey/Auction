import {User} from './user';
import {Auction} from './auction';

export class Bid{
  id: number;
  value: number;
  bidder: User;
  bidTime: Date;
  auction: Auction;
}
