/************************ axios请求 ************************/
import { baseUrl } from '@/common/env.js';

const config = {
	baseUrl:baseUrl,
	headers:{},
    withCredentials:true // 跨域配置
};
export default config;