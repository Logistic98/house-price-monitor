import Vue from 'vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import Storage from 'vue-ls'
import NProgress from 'nprogress'
import store from '@/store'
import routes from './routers'
import {title} from '@/settings.js'
import 'element-ui/lib/theme-chalk/index.css'
import 'nprogress/nprogress.css'
const options = {
    namespace: '',
    name: 'ls',
    storage: 'session',
};
/*NProgress.inc(0.2);
NProgress.configure({ easing: 'ease', speed: 500, showSpinner: false });*/
NProgress.configure({ showSpinner: false });
Vue.use(VueRouter);
Vue.use(ElementUI);
Vue.use(Storage,options);
Vue.prototype.$progress = NProgress;

const router = new VueRouter({
	scrollBehavior: () => ({ y: 0 }),
	routes
});
/*let hasAccess = (userInfo,meta) => {
	let roles = Array.isArray(userInfo.roles) ? userInfo.roles[0]:userInfo.roles;
	if (roles == 1) return true;// 超级管理员
	// isShowMenu不存在为 undefined 表示公开路由  为 true 表示有权限访问
	if (meta.isShowMenu !== false) return true;
};*/
// 默认 true
let hasAccess = (userInfo,to) => {
	// true开启权限验证模式 ，false 不使用权限验证
	if (!userInfo.menus && (!userInfo.isAuth || userInfo.isAuth===false))return true;
	if (!to.meta)return true;
	// 公开路由
	//if(routes.some(item => (item.name == to.name || (item.children&&item.children.length==1&&item.children[0].name==to.name))? true : (item.children && hasAccessChildren(item.children,to.name))))return true;
	return true;
	// 权限路由 由后端返回，既然返回了，肯定有权限
	//return Array.isArray(userInfo.menus)?userInfo.menus.some(item => ((item.children && item.children.length > 1)?hasAccessChildren(item.children,to):(item.name == to.name))):false;
};
//const hasAccessChildren = (router,name) => router.some(item => item.name==name);

router.beforeEach((to,from,next) => {
    NProgress.start();
    document.title = (to.meta && to.meta.title?(to.meta.title+' - '):'') + title;
	var isLogin = Vue.ls.get('userInfo');
	if (to.path === '/login' || to.path === '/register') {
		next();
	}else{
		if (isLogin) {
			if (!store.getters.isAuthToken) {
				store.dispatch('concatRlues',isLogin).then(routes => {
                    router.matcher = new VueRouter({scrollBehavior:()=>({y:0})}).matcher;
					router.addRoutes(routes);
					next({ ...to, replace: true });
				}).catch(err => {
					Vue.ls.remove('userInfo');
					next('/login');
				});
			} else {
                if (to.path === '/401' || to.path === '/404'){
                    next();
                }else{
                    if (hasAccess(store.getters.userInfo,to)) {
                        next();
                    } else {
                        if (to.path === '/404'){
                            next({path:'/404',replace:true});
                        }else if (to.path !== '/401'){
                            ElementUI.Message.error({message:'没有权限',center: true});
                            next({path:'/401',replace:true});
                        }
                        NProgress.done();
                    }
                }
			}
		}else {
			ElementUI.Message.error({message:'请先登录',center: true});
			NProgress.done();
			next('/login');
		}
	}
});
router.afterEach((to,from) => {
    if ((to.matched[1] && !to.matched[1].components.default) || to.path === '*') {
        console.log('%c ! router components not exists','background:#52bea6;color:#fff');
        router.push({path:'/404',replace:true});
    }
	NProgress.done();
});
router.onError(error => {
    if (error.message && error.message.includes('Cannot') && error.message.includes('find') && error.message.includes('module')){
        console.log('%c ! router components not exists','background:#52bea6;color:#fff',error);
        router.push({path:'/404',replace:true});
    }else {
        console.log('%c ! router error','background:#000;color:#bada55',error);
    }
    NProgress.done();
});
export default router;