import store from '@/store'

export default {
    inserted:(el,binding,vnode) => {
        let permissions = store.getters.userInfo && store.getters.userInfo.permissions;
        permissions && !permissions.some(item => item==binding.value)&&(el.parentNode.removeChild(el));
    }
}