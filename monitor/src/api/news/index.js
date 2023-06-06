import axios from '@/api'

/******************* 列表 *******************/
export const getXxlNewsData = (data) => {
	return axios.request({
		url:'/xxlnews',
		method:'post',
		data:data
	});
};

/******************* 删除 *******************/
export const del = (data) => {
	return axios.request({
		url:'/deleteNewsLog',
		method:'delete',
		data:data
	});
};