import directives from './permission'
import common from '../common/common'

export default {
    install(Vue, options) {
        Vue.directive('has',directives);
        for (var key in common){
            Vue.prototype[key] = common[key];
        }
    }
}