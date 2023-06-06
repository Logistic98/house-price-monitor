import axios from '@/api'

/******************* 列表 *******************/
export const getTableData = (data) => {
	return axios.request({
		url:'/logs',
		method:'post',
		data:data
	});
};

/******************* 删除 *******************/
export const del = (data) => {
	return axios.request({
		url:'/log',
		method:'delete',
		data:data
	});
};