import { RES_STATUS } from './index';

export default (Component, status) => {
	switch(status) {
		case RES_STATUS.LOADING:
			return 'LOADING...';
		case RES_STATUS.ERROR:
			return 'Error';
		case RES_STATUS.OK:
			return Component;
	}
};