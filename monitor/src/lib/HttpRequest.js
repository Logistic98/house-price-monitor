import axios from 'axios'
import config from '@/config'
import store from '@/store'
const settings = require('@/settings.js');

class HttpRequest{
	constructor(baseUrl = config.baseUrl){
		this.baseUrl = baseUrl;
		this.queue = {};
	}
	getInsideConfig(){
		return Object.assign({baseURL:this.baseUrl,headers:{}},config);
	}
	interceptors(instance,url){
		instance.interceptors.request.use(config => {
			if (Object.keys(this.queue).length) {
				this.queue[url] = true;
			}
			return config;
		},error => {
			delete this.queue[url];
			return Promise.reject(error);
		});
		instance.interceptors.response.use(response => {
			delete this.queue[url];
			const { data, status } = response;
			return { data, status };
		},error => {
			delete this.queue[url];
			return Promise.reject(error);
		});
	}
	request(options){
        options = Object.assign(this.getInsideConfig(),options);
		const userInfo = (store.getters.userInfo && store.getters.userInfo.accessToken) ? store.getters.userInfo : (sessionStorage.getItem('userInfo') ? JSON.parse(sessionStorage.getItem('userInfo')).value : '');
        if (userInfo && userInfo.accessToken) {
            options.headers[settings.authorization] = userInfo.accessToken;
		}
		const instance = axios.create();
		this.interceptors(instance,options.url);
		return instance(options);
	}
}

export default HttpRequest