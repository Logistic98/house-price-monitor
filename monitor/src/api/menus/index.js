import axios from '@/api'

/******************* 列表 *******************/
export const getTableData = () => {
	return axios.request({
		url:'/permissions',
		method:'get',
	});
};

/******************* 添加/编辑菜单树 *******************/
export const getDataTree = () => {
	return axios.request({
		url:'/permissions/tree',
		method:'get',
	});
};


/******************* 添加 *******************/
export const add = (data) => {
	return axios.request({
		url:'/permission',
		method:'post',
		data:data
	});
};

/******************* 编辑 *******************/
export const edit = (data) => {
	return axios.request({
		url:'/permission',
		method:'put',
		data:data
	});
};

/******************* 删除 *******************/
export const del = (id) => {
	return axios.request({
		url:'/permission/' + id,
		method:'delete',
	});
};


/******************* 权限树形结构数据（角色添加/编辑） *******************/
export const getPermissionsTree = () => {
	return axios.request({
		url:'/permissions/tree/all',
		method:'get',
	});
};