<template>
    <el-container style="overflow:hidden;position:relative;height:100%;border:1px solid #eee">
        <div id="siderBarMask" @click.stop="handleSiderBarMask($event)"></div>
        <el-aside id="sider" class="sider" width="auto" style="background-color:#515a6e">
            <el-scrollbar id="slider-scrollbar" style="height:100%;overflow-x:hidden">
                <el-menu :collapse="isCollapse" :unique-opened="true"
                     :default-active="menuIndex"
                     class="el-menu-vertical-demo"
                     @select="handleSelectMenu"
                     background-color="#515a6e"
                     text-color="rgb(203,199,206)"
                     active-text-color="#52bea6">
                    <slider-bar :list="siderBarList" :isCollapse="isCollapse" />
                    <!--<template v-for="(item,key) in siderBarList">
                        <el-submenu :index="String(key)" v-if="item.children">
                            <template slot="title">
                                <i :class="item.icon+' fa-icon'"></i>
                                <span>{{ item.name }}</span>
                            </template>
                            <router-link tag="div" v-for="(subItem,index) in item.children" :to="subItem.path" :key="index">
                                <el-menu-item :index="String(key+'-'+index)">
                                    <i :class="subItem.icon+' fa-icon'"></i>
                                    <span>{{ subItem.name }}</span>
                                </el-menu-item>
                            </router-link>
                        </el-submenu>
                        <router-link tag="div" v-else :to="item.path">
                            <el-menu-item :index="String(key)" :key="key">
                                <i :class="item.icon"></i>
                                <span slot="title">{{ item.name }}</span>
                            </el-menu-item>
                        </router-link>
                    </template>-->
                </el-menu>
            </el-scrollbar>
        </el-aside>
        <el-container>
            <div class="header">
                <el-header style="color:#515a6e;text-align:right">
                    <div class="left" style="float: left;display: flex;">
                        <div @click.stop="handleCollapse"><i :class="iconClassName" style="font-size: 22px;"></i></div>
                        <div style="margin-left:22px">
                            <h4 style="margin:-4px 0 0;">{{ title }}</h4>
                        </div>
                    </div>
                    <personal/>
                </el-header>
                <div v-if="affix" ref="tabBtn" class="tabBtn">
                    <div ref="btnList" class="btn-list">
                        <template v-for="(item,key) in tabBtnList" >
                            <el-tag :ref="item.tabBtnActive?'tabBtnActive':''" size="small" :key="key" :class="item.tabBtnActive?'tabBtnActive':''" @click.native="handleBtnClickEvent(item,key)" @close="handleBtnCloseEvent(key)" :closable="key==0?false:true">
                                {{ item.name }}
                            </el-tag>
                        </template>
                    </div>
                </div>
            </div>
            <el-main id="main">
                <div class="content">
                    <fade-in>
                        <router-view slot='fadeIn'/>
                    </fade-in>
                </div>
            </el-main>
            <el-footer align="center" :style="footer?'background:#fff;line-height:60px;font-size:14px;overflow:hidden;':'display:none;'">
                <span>{{ footer }}</span>
            </el-footer>
        </el-container>
    </el-container>
</template>

<script>
const settings = require('@/settings.js');
import SliderBar from './slider/slider.vue'
import ResizeMixin from './mixin/ResizeHandler'
import FadeIn from '@/components/fadeIn/FadeIn.vue'
import Personal from '@/views/components/personal'
export default {
    name:'Layout',
    components:{
        SliderBar,
        FadeIn,
        Personal,
    },
    mixins: [ResizeMixin],
    computed:{
        iconClassName(){
            return this.isCollapse?'el-icon-s-unfold':'el-icon-s-fold';
        }
    },
    data() {
        return {
            isCollapse:false,
            menuIndex:'',
            siderBarList:[],
            tabBtnList:[],
            title:settings.title,
            footer:settings.footer,
            affix:settings.affix
        }
    },
    created() {
        this.siderBarList = this.$store.getters.siderList;
        if (this.$store.getters.routes && this.$store.getters.routes.length > 0){
            let homeTabBtn = {name:'home',path:'/',tabBtnActive:true};
            this.$store.getters.routes.some(item => {
                if (item.path == "/"  && item.children && item.children.length > 0){
                    item.children.some(child => {
                        if (child.path == item.redirect){
                            homeTabBtn.name = child.meta.title;
                            homeTabBtn.path = item.redirect;
                            return true;
                        }
                    });
                    return item;
                }
            });
            this.affix && this.tabBtnList.push(homeTabBtn);
            settings.isRefrushBack && this.$router.push(homeTabBtn.path);
        }
    },
    methods: {
        handleSelectMenu (index,indexPath) {
            this.menuIndex = index;
            var active = indexPath[0].split('_');
            var parentslider = this.siderBarList[active[0]];
            var currentNav = !parentslider.children && parentslider.path == active[1] ? parentslider : findSliderChildren(parentslider.children,index.split('_')[1]);
            if (currentNav){
                if(this.affix){
                    let key = this.tabBtnList.findIndex(item => (item.name==currentNav.name)&&(item.path==currentNav.path));
                    this.tabBtnList.map(item => item.tabBtnActive=false);
                    if (key === -1) {
                        let tabBtnBar = {
                            name:'',
                            path:'',
                            tabBtnActive:false,
                            index:index
                        };
                        tabBtnBar.name = currentNav.name;
                        tabBtnBar.path = currentNav.path;
                        tabBtnBar.tabBtnActive = true;
                        this.tabBtnList.push(tabBtnBar);
                    }else {
                        this.tabBtnList[key].tabBtnActive = true;
                    }
                }
                this.$router.push(currentNav.path);
                this.scrollTabBtn();
            }
         
        },
        siderBarListFindSelected(currentItem){
            this.menuIndex = currentItem ? currentItem.index : '';
        },
        handleBtnClickEvent(currentBtnItem,index){
            this.tabBtnList.map(item => item.tabBtnActive=false);
            this.tabBtnList[index].tabBtnActive = true;
            this.siderBarListFindSelected(currentBtnItem);
            this.$router.push(currentBtnItem.path);
        },
        handleBtnCloseEvent (index){
            //if (this.tabBtnList.length <= 1)return this.warning('已达上限，不能关闭！');
            this.tabBtnList.splice(index,1);
            this.siderBarListFindSelected(this.tabBtnList[this.tabBtnList.length-1]);
            this.tabBtnList.map(item => item.tabBtnActive=false);
            this.tabBtnList[this.tabBtnList.length-1].tabBtnActive=true;
            this.$router.push(this.tabBtnList[this.tabBtnList.length-1].path);
            this.scrollTabBtn();
        },
        handleCollapse(){
            // this.isCollapse = this.$store.getters.device === 'mobile'?false:true;
            this.isCollapse = !this.isCollapse;
            var className = document.getElementById('sider').className;
            if (this.$_isMobile()){
                if (this.isCollapse){
                    if (className.includes('hiddenSiderBar')){
                        this.isCollapse = false;
                        document.getElementById('siderBarMask').classList.add('siderBarMask');
                        document.getElementById('sider').classList.remove('hiddenSiderBar');
                    }else{
                        document.getElementById('sider').classList.add('hiddenSiderBar');
                    }
                }else{
                    document.getElementById('sider').classList.remove('hiddenSiderBar');
                }
                document.getElementById('sider').classList.add('moblieSider');
            }else{
                if (className.includes('hiddenSiderBar')){
                    document.getElementById('sider').classList.remove('moblieSider');
                    document.getElementById('sider').classList.remove('hiddenSiderBar');
                }/*else{
                    document.getElementById('sider').classList.add('hiddenSiderBar');
                }*/
            }
        },
        handleSiderBarMask(e){
            e.target.classList.remove('siderBarMask');
            document.getElementById('sider').classList.replace('moblieSider','hiddenSiderBar');
        },
        // 设置滚动
        scrollTabBtn(){
            this.$nextTick(()=>{
                if (!this.affix)return;
                // let boxW = document.querySelector('.tabBtn').offsetWidth;
                // let btn = document.querySelector('.tabBtn .tabBtnActive');
                let boxW = this.$refs.tabBtn.offsetWidth;
                let btn = this.$refs.tabBtnActive[0].$el;
                let r = btn.offsetLeft;
                let w = btn.offsetWidth;
                if (this.$_isMobile())boxW = document.body.getBoundingClientRect().width;
                let scrollLeft = r+w - boxW;
                // document.querySelector('.tabBtn .btn-list').style.transform = "translateX(-"+(scrollLeft > 0 ? scrollLeft : 0)+"px)";
                this.$refs.btnList.style.transform = "translateX(-"+(scrollLeft > 0 ? scrollLeft : 0)+"px)";
                /*if (scrollLeft > 0){
                    document.querySelector('.tabBtn .btn-list').style.transform = "translateX(-"+scrollLeft+"px)";
                }else{
                    document.querySelector('.tabBtn .btn-list').style.transform = "translateX(0px)";
                }*/
            })
        }
    }
};
function findSliderChildren(data,val) {
    var slider = null;
    if (!data){
        return slider;
    }
    for (let i = 0,leng = data.length; i < leng; i++) {
        if (data[i].children && data[i].children.length>0){
            slider = findSliderChildren(data[i].children,val);
        }else{
            if(data[i].path == val){
                slider = data[i];
            }
        }
        if (slider)break;
    }
    return slider;
}
</script>

<style lang="css" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}
#sider.sider{
    /*position: absolute;*/
    /*left: 0;*/
    /*top: 0;*/
    /*z-index: 2;*/
    height: 100%;
    overflow: hidden;
    /*transition: all 1s;*/
}
.moblieSider{
    position: absolute;
    left: 0;
    top: 0;
    z-index: 9;
}
.hiddenSiderBar{
    width: 0 !important;
}
#sider.sider #slider-scrollbar >>> .el-scrollbar__wrap{
    overflow-x: hidden;
}
#sider.sider .fa-icon{
    vertical-align: middle;
    margin-right: 5px;
    width: 24px;
    text-align: center;
    font-size: 18px;
}
.header .el-header {
    /*background-color: #B3C0D1;*/
    color: #333;
    line-height: 60px;
    border-bottom: 1px solid #f5f6f6;
}
.header > .tabBtn{
    padding-top: 2px;
    padding-bottom: 2px;
    border-bottom: 1px solid #f5f6f6;
    box-shadow: 0 0 0 0 rgba(0,0,0,.5);
    user-select: none;
    overflow-x: hidden;
    /*box-shadow: 0 1px 3px 0 rgba(0,0,0,.12), 0 0 3px 0 rgba(0,0,0,.04);*/
}
.header > .tabBtn .btn-list{
    position: relative;
    display:flex;
    align-items: center;
    transition: transform 1s ease;
}
.header > .tabBtn .tabBtnActive,.el-tag:hover{
    background-color: #52bea6 !important;
    color: #fff !important;
    cursor: pointer;
}
.el-tag{
    margin-left: 2px;
    margin-right: 2px;
    background-color: #fff;
    color: #515a6e !important;
    border-color: #eee !important;
    transition: all .2s;
}
.el-tag--small >>> .el-icon-close{
    right: -1px;
    color: #cacaca !important;
    transition: all .5s;
}
.el-tag >>> .el-tag__close:hover{
    background-color: #6b5b5b;
    color: #fff !important;
}
#main.el-main{
    padding:0;
    background: #f5f7f9;
    /*overflow: hidden;*/
}
#main.el-main > .content{
    overflow: hidden;
    margin: 6px;
    padding: 10px;
    min-height: calc(100% - 34px);
    background: #fff;
    border: 1px solid #dcdee2;
    border-color: #e8eaec;
}
.siderBarMask{
    position: absolute;
    width: 100%;
    height: 100%;
    overflow: hidden;
    background-color: #333;
    opacity: .3;
    z-index: 9;
}
</style>