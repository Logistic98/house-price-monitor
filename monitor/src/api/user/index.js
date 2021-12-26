import axios from '@/api'

/******************* 列表 *******************/
export const getTableData = (data) => {
	return axios.request({
		url:'/users',
		method:'post',
		data:data
	});
};


/******************* 添加 *******************/
export const add = (data) => {
	return axios.request({
		url:'/user',
		method:'post',
		data:data
	});
};

/******************* 编辑 *******************/
export const edit = (data) => {
	return axios.request({
		url:'/user',
		method:'put',
		data:data
	});
};

/******************* 重置密码 *******************/
export const resetPassword = (id) => {
	return axios.request({
		url:'/user/password/' + id,
		method:'get',
	});
};

/******************* 删除 *******************/
export const del = (data) => {
	return axios.request({
		url:'/user',
		method:'delete',
		data:data
	});
};

/******************* 获取拥有的权限ids *******************/
export const getPermissionsIds = (id) => {
	return axios.request({
		url:'/user/roles/' + id,
		method:'get',
	});
};

/******************* 更新拥有的权限ids *******************/
export const updatePermissionsIds = (data) => {
	return axios.request({
		url:'/user/roles',
		method:'put',
		data:data
	});
	
};