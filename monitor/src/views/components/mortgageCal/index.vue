<template>
    <div class="home">
        <el-tabs :tab-position="tabPosition">
            <el-tab-pane label="商业贷款"><business /></el-tab-pane>  
            <el-tab-pane label="公积金贷款"><provident /></el-tab-pane>           
            <el-tab-pane label="组合贷款"><mix /></el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
import business from './components/business.vue'
import provident from './components/provident.vue'
import mix from './components/mix.vue'

export default {
    name:'moetgageCal',
    components:{
        business,
        provident,
        mix
    },
    data() {
      return {
        tabPosition: 'left',
      }
    }
}
</script>

<style lang="css" scoped>

</style>
