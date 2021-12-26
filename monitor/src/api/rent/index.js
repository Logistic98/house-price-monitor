import axios from '@/api'

/******************* 列表 *******************/
export const getTableData = (data) => {
	return axios.request({
		url:'/rent',
		method:'post',
		data:data
	});
};