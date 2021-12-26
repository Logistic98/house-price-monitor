import types from './types.js'
import newRoutes from '@/router/routers'
import {routes} from '@/router/routes.js'
import {handleRoutesMap,routesMap} from '@/router/routers/router_map.js'
const {components} = require('@/settings.js');

function hasAccess(routerList) {
    let newRouterList = [];
    if (!routerList || !routerList.length > 0){
        //console.log('%c ! Menu Data Array Error ','background:#67C23A;color:#fff',routerList);
        return newRouterList;
    }
    routerList.map(el => {
        if (!el.path || !el.name){
            console.log('%c ! Menu Data Error，path OR name is Not Null OR Empty ','background:#000;color:#bada55',el);
            return false;
        }
        if (el.children){
            if (el.children.length > 0){
                newRouterList = newRouterList.concat(hasAccess(el.children));
            }else {
                //router.component = () => import(/* webpackChunkName: "[request]" */ '@/views'+components+'/'+el.path.substr(1)+'');
                newRouterList.push({
                    path:el.path,
                    name:el.name,
                    meta:{title:el.title,icon:el.icon||''},
                    component:() => import(/* webpackChunkName: "[request]" */ '@/views'+components+'/'+(el.path.startsWith("/")?el.path.substr(1):el.path)+'')
                });
            }
        }
    });
    return newRouterList;
}
const actions = {
    setToggleDevice({commit},device){
        commit(types.TOGGLE_DEVICE,device);
    },
	setAuthToken ({commit},isAuthToken) {
		commit(types.SET_TOKEN,isAuthToken);
	},
	clearLoginOut ({commit}) {
		commit(types.SET_USER,null);
		commit(types.SET_SIDERLIST,null);
		commit(types.SET_NEWROUTES,newRoutes);
		commit(types.SET_TOKEN,false);
	},
	setSiderList ({commit},siderList) {
		commit(types.SET_SIDERLIST,siderList);
	},
	concatRlues ({commit},userInfo) {
		return new Promise((resolve,reject) => {
			try {
                // true开启权限验证模式 ，false 不使用权限验证
                let key = newRoutes.findIndex(item => item.path=="/");
                let child = newRoutes[key].children;
				let routerList = [];
				if (userInfo.isAuth==true && userInfo.menus && Array.isArray(userInfo.menus) && userInfo.menus.length > 0){
					routerList = userInfo.menus;
                    newRoutes[key].children = [...child,...hasAccess(routerList),routesMap.pop()];
				}else{
                    newRoutes[key].children = [...child,handleRoutesMap.pop()];
                }
                //newRoutes[key].children = userInfo.isAuth==false ? [...child,handleRoutesMap.pop()] : [...child,...hasAccess(routerList),routesMap.pop()];
				commit(types.SET_NEWROUTES,newRoutes);
				commit(types.SET_SIDERLIST,routes.filter(item => item.isMenu==true).concat(routerList));
				commit(types.SET_USER,userInfo);
                resolve(newRoutes);
			 } catch(error) {
				console.log('%c ! filter router error ','background:#000;color:#bada55',error);
			 	reject(error);
			 }
		});
	}
};
export default actions;