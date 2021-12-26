// 入口文件

// 引入包
import Vue from 'vue'
import VueParticles from 'vue-particles'
import store from './store'
import App from './App.vue'
import router from './router'
import directives from './directives'
import ElementComponent from 'element-component'
import ElementUI from 'element-ui' 

import 'normalize.css'
import './assets/style/globalstyle.css'
import 'element-ui/lib/theme-chalk/index.css'
import 'github-markdown-css';

Vue.use(directives);
Vue.use(ElementComponent);
Vue.use(ElementUI) 
Vue.use(VueParticles)

Vue.config.productionTip = false;
const vm = new Vue({
	el:'#app',
    store,
	router,
	render:c => c(App),
});
