// import Vue from 'Vue'
import Vue from 'vue'
import Vuex from 'vuex'
import state from './state.js'
import mutations from './mutations.js'
import actions from './actions.js'
import getters from './getters.js'
import modules from './module/index.js'

Vue.use(Vuex);

export default new Vuex.Store({
	state,
	mutations,
	actions,
	getters,
	modules
});
