<template>
    <div class="slider-menu-bar">
        <template v-for="(item,index) of list" >
            <el-submenu :index="index+'_'+item.path" v-if="item.children" :key="index">
                <template slot="title">
                    <!--<i :class="item.icon"></i>-->
                    <i v-if="item.icon&&(item.icon.startsWith('http'))" style="padding-right:4px;width:24px;height:18px;overflow:hidden">
                        <img :src="item.icon" width="18" height="18" style="vertical-align:middle">
                    </i>
                    <i v-else :class="item.icon" style="width:24px;height:18px;overflow:hidden"></i>
                    <span :class="isCollapse?'slider-menu-item':''">{{item.name}}</span>
                </template>
                <!-- <template v-if='item.children && item.children.length>0' v-for='(children,index) in item.children'>
                    <el-menu-item :index="index+''">
                        <i class="el-icon-location"></i>
                        <span>{{children.title}}</span>
                    </el-menu-item>
                </template> -->
                 <menu-list v-if='item.children && item.children.length>0' :list="item.children"></menu-list>
            </el-submenu>
            <!--<router-link v-else tag="div" :to="item.path">-->
            <el-menu-item v-else :index="index+'_'+item.path" :key="index">
                <!--<i :class="item.icon"></i>-->
                <i v-if="item.icon&&(item.icon.startsWith('http'))" style="padding-right:4px;width:24px;height:18px;overflow:hidden">
                    <img :src="item.icon" width="18" height="18" style="vertical-align:middle">
                </i>
                <i v-else :class="item.icon" style="width:24px;height:18px;overflow:hidden"></i>
                <span slot="title">{{item.name}}</span>
            </el-menu-item>
            <!--</router-link>-->
        </template>
    </div>
</template>
<script>
export default {
    name:'MenuList',
    props: {list: Array,isCollapse:Boolean}
}
</script>
<style lang="css" scoped>
.slider-menu-bar span.slider-menu-item{
    height: 0;
    width: 0;
    overflow: hidden;
    visibility: hidden;
    display: inline-block;
}
/*.slider-menu-bar >>> .el-menu-item.is-active,*/
/*.slider-menu-bar >>> .el-menu-item:focus,*/
/*.el-menu-item:hover,.slider-menu-bar >>> .el-submenu__title:hover{
    background-color: #fef0f0 !important;
}*/
</style>