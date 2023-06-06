<template>
    <div class="home">
        <template>
            <el-backtop target=".el-main">
                <div class ='up'>UP</div>
            </el-backtop>
        </template>
        <div v-html="md" class="markdown-body"></div>      
    </div>
</template>

<script>
import commonSense from './md/commonSense.md';

export default {
    name:'commonSense',
    data () {
        return {
            md: commonSense
        };
    },
}
</script>

<style lang="css" scoped>
.el-main {
    height: 100vh;
    overflow-x: hidden;
}
.up{
    height: 100%;
    width: 100%;
    background-color: #f2f5f6;
    box-shadow: 0 0 6px rgba(0,0,0, .12);
    text-align: center;
    line-height: 40px;
    color: #1989fa;
}
</style>