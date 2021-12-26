import axios from '@/api'

export const getCodeData = (data) => {
	return axios.request({
		url:'/code',
		method:'post',
		data:data
	});
};