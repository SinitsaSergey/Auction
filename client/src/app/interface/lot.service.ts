import {Lot} from '../model/lot';

interface LotService {

insert (lot): Promise<Lot>;

}

export default LotService;
