import {Authority} from './authority';

export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  registrated: Date;
  authorities: Authority[];
  isBanned: boolean;
}
