const getters = {
    device: state => state.device,
	isAuthToken: state => state.isAuthToken,
	userInfo: state => state.userInfo,
	siderList: state => state.siderList,
	routes:state => state.routes,
	// rules:state => state.rules
};

export default getters;