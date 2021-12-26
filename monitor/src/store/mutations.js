import types from './types.js'

 // 后台直接请求回来菜单，筛选左侧导航菜单需要的属性
const getMenuList = (routesList) => {
    let siderList = [];
    for (let key in routesList) {
        let item = routesList[key];
        if (item.isMenu == false) {
            continue;
        }
        let sider = {name:'', icon:'', path:''};
        if (item.meta && item.meta.title && item.path){
            sider.name = item.meta.title;
            sider.icon = item.meta.icon;
            sider.path = item.path;
        }
        if (item.title && item.path){
            sider.name = item.title;
            sider.icon = item.icon || '';
            sider.path = item.path;
        }
        if (item.children && item.children.length > 0) {
            sider.children = getMenuList(item.children);
        }
        sider.name && sider.path && siderList.push(sider);
    }
    return siderList;
};

const mutations = {
	[types.TOGGLE_DEVICE] (state,device) {
        state.device = device;
	},
    [types.SET_TOKEN] (state,isAuthToken) {
		state.isAuthToken = isAuthToken;
	},
	[types.SET_USER] (state,userInfo) {
		if (userInfo){
			state.userInfo = userInfo;
		}else{
			state.userInfo = {};
		}
	},
	[types.SET_NEWROUTES] (state,newRoutes) {
		state.routes = newRoutes;
		state.isAuthToken = true;
	},
	[types.SET_SIDERLIST] (state,siderList) {
		if(!siderList){
			return state.siderList = siderList;
		}
        //siderList = getMenuList(siderList,state.userInfo.roles);
        siderList = getMenuList(siderList);
        if (Array.isArray(siderList) && siderList.length > 0) {
			state.siderList = siderList;
		}else {
			state.siderList = [];
		}
	}
};

export default mutations
