import types from '../types.js'

const modules = {
	state: {},
	mutations: {
		[types.SET_CONCATROUTES] (state,newRoutes) {
			state.routes = newRoutes.concat(routes);
			state.isAuthToken = true;
		}
	},
	actions: {},
	getters: {}
};

export default modules;