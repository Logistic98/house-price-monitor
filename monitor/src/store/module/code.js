const state = {
    codeList: []
}
const getters = {
    codeList:state => state.codeList,
}
const actions = {}
const mutations = {
    setCodeList(state, codeList) { 
        state.codeList = codeList;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
