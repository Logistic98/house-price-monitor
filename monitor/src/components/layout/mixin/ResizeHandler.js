import store from '@/store'

const {body} = document;
const WIDTH = 720; // refer to Bootstrap's responsive design

export default {
    watch: {
        $route(route) {
            this.$_isMobile() && this.$_hiddenSiderBar();
            /*if (this.$_isMobile()){
                this.$_hiddenSiderBar();
            }*/
        }
    },
    beforeMount() {
        window.addEventListener('resize', this.$_resizeHandler)
    },
    beforeDestroy() {
        window.removeEventListener('resize', this.$_resizeHandler)
    },
    created(){
        store.dispatch('setToggleDevice', this.$_isMobile() ? 'mobile' : 'desktop');
    },
    mounted() {
        this.$_hiddenSiderBar();
    },
    methods: {
        // use $_ for mixins properties
        // https://vuejs.org/v2/style-guide/index.html#Private-property-names-essential
        $_hiddenSiderBar() {
            var className = document.getElementById('sider').className;
            if (this.$_isMobile()){
                document.getElementById('sider').classList.add('hiddenSiderBar');
                document.getElementById('siderBarMask').classList.remove('siderBarMask');
            }else{
                if (className.includes('hiddenSiderBar')){
                    document.getElementById('sider').classList.remove('hiddenSiderBar');
                    document.getElementById('sider').classList.remove('moblieSider');
                }
            }
        },
        $_isMobile() {
            const rect = body.getBoundingClientRect();
            return rect.width - 1 < WIDTH;
        },
        $_resizeHandler() {
            this.$_hiddenSiderBar();
            if (!document.hidden) {
                const isMobile = this.$_isMobile();
                store.dispatch('setToggleDevice', isMobile ? 'mobile' : 'desktop');
                // if (isMobile) {
                    // store.dispatch('app/closeSideBar', { withoutAnimation: true })
                // }
            }
        }
    }
}
