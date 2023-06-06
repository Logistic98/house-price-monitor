import axios from '@/api'
import da from "element-ui/src/locale/lang/da";

/******************* 列表 *******************/
export const getTableData = (data={}) => {
	return axios.request({
		url:'/roles',
		method:'post',
		data:data
	});
};


/******************* 添加 *******************/
export const add = (data) => {
	return axios.request({
		url:'/role',
		method:'post',
		data:data
	});
};

/******************* 获取拥有的权限ids *******************/
export const getPermissionsIds = (id) => {
	return axios.request({
		url:'/role/' + id,
		method:'get',
	});
};

/******************* 编辑 *******************/
export const edit = (data) => {
	return axios.request({
		url:'/role',
		method:'put',
		data:data
	});
};

/******************* 更新角色状态 *******************/
export const updateStatus = (data) => {
	return axios.request({
		url:'/role/' + data[0] + '/' + data[1],
		method:'post',
	});
};

/******************* 删除 *******************/
export const del = (id) => {
	return axios.request({
		url:'/role/'+id,
		method:'delete',
	});
};