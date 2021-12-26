import axios from '@/api'

/******************* 登录 *******************/
export const login = (data) => {
	return axios.request({
		url:'/login',
		method:'post',
		data:data
	});
};

/******************* 退出登录 *******************/
export const logout = () => {
	return axios.request({
		url:'/logout',
		method:'get',
	});
};

/******************* 用户注册 *******************/
export const register = (data) => {
	return axios.request({
		url:'/register',
		method:'post',
		data:data
	});
};


