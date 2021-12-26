import axios from '@/api'

/******************* 运行数据缓存 *******************/
export const queryRuntimeSummary = (data) => {
	return axios.request({
		url:'/queryRuntimeSummary',
		method:'post',
		data:data
	});
};