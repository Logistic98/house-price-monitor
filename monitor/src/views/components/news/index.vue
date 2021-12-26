<template>
    <div class="home">
        <div class="header">
            <div class="search" style="display: inline-block">
                <el-form ref="search" :model="search" :inline="true">
                    <el-button v-has="'btn-log-delete'" type="danger" @click="handleDelete(ids)" >批量删除</el-button>
                    <el-form-item label="">
                        调度结果：<el-select v-model="search.triggerCode" placeholder="请选择调度结果" clearable filterable @clear="selectTriggerClear" @change="selectTriggerChange">
                        <el-option v-for="(item,index) in codes" :key="index" :label="item.label" :value="item.value" /></el-select>
                    </el-form-item>         
                     <el-form-item label="">
                        执行结果：<el-select v-model="search.handleCode" placeholder="请选择调度结果" clearable filterable @clear="selectHandleClear" @change="selectHandleChange">
                        <el-option v-for="(item,index) in codes" :key="index" :label="item.label" :value="item.value" /></el-select>
                    </el-form-item> 
                    <el-form-item label="">               
                        执行完成时间：<el-date-picker v-model="time" type="datetimerange" @change="changeTime" value-format="yyyy-MM-dd HH:mm:ss" 
                        :default-time="['00:00:00', '23:59:59']" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="">
                        <el-button type="primary" @click="tableDataInit(search);">搜索</el-button>
                    </el-form-item>    
                    <el-form-item label="">
                        <el-button type="primary" @click="clearData">重置</el-button>
                    </el-form-item>             
                </el-form>
            </div>
        </div>
        <component-table :data="tableData">
            <template v-slot:button="{scope}">
                <component-popover v-has="'btn-log-delete'" :params="scope.row" @ok="handleOk"></component-popover>
            </template>
        </component-table>
    </div>
</template>

<script>
import { getXxlNewsData,del } from '@/api/news'

export default {
    name:'news',
    data () {
        return {
            show:false,
            tableDataAll:null,
            currentPage:1,
            optionText:'',
            search:{
                pageNum:1,//当前页
                pageSize:10,//每页显示条数
                triggerCode:'',
                handleCode:'',
                startTime:'',
                endTime:'',
            },
            time:[],
            codes:[],
            parentData:null,
            ids:[],
            visible:false,
            tableData: {
                loading:false,
                border:true,
                // 请求回来的数据
                tableData:[],
                // 列
                tableLabel:[],
                // 操作
                tableOption:{
                    label:'操作',
                    width:120,
                    slot:true,
                },
                // 分页
                page:{align:'right', currentChange:(currentPage) => {
                    this.$nextTick(() => {
                        this.search.pageNum = currentPage;
                        this.tableDataInit(this.search);
                    })
                }},
                // 表格实现多选时的 change 事件
                selectionChange:(params)=>{
                    
                    console.log(params)
                    this.ids = [];
                    params.map(item => this.ids.push(item.id));
                    console.log(ids)

                }
            },

        }
    },
    created(){
        this.labelInit();
        this.initTimeLimit();
        this.handleGetTableData();
    },
    methods: {
        handleGetTableData(loading=true){
            loading && this.tableDataInit(this.search);
        },
        initTimeLimit(){
            this.time = [this.getDayStartTime(),this.getDayEndTime()];
            this.changeTime(this.time)
        },
        // 列初始化
        labelInit(){
            this.tableData.tableLabel = [
                {type:'selection',width:40,fixed:'left',align:'center'},
                {type:'index',title:'序号',fixed:'left',width:60,align:'center'},
                {prop:'triggerTime',title:'调度时间', width:230, tooltip:true},
                {prop:'triggerCode',title:'调度结果',width:100, tooltip:true},
                {prop:'triggerMsg',title:'调度备注', width:500, tooltip:true},
                {prop:'handleTime',title:'执行时间',width:230, tooltip:true},
                {prop:'handleCode',title:'执行结果',width:100, tooltip:true},
                {prop:'handleMsg',title:'执行备注', tooltip:true}
            ];
        },
        // 数据初始化
        tableDataInit(search) {
            this.tableData.loading = true;
            this.request(getXxlNewsData,search,this,data => {
                this.tableData.loading = false;
                this.tableData.tableData = data.rows;
                const res = this.$store.state.code.codeList.codeList;
                // 数据展示列code转换
                for(var i=0,j=data.rows.length;i<j;i++){
                    data.rows[i].triggerCode = this.getFilterCodeData(res,'TaskStatus',data.rows[i].triggerCode);
                    data.rows[i].handleCode = this.getFilterCodeData(res,'TaskStatus',data.rows[i].handleCode);
                }
                // 下拉框code转换
                if(this.codes.length == 0 ){
                    const TaskStatusName = this.getFilterCodeData(res,'TaskStatus');
                    for(var i=0,j=TaskStatusName.length;i<j;i++){
                        var obj = {};
                        this.$set(obj,"value",TaskStatusName[i].value);
                        this.$set(obj,"label",TaskStatusName[i].name);
                        this.codes.push(obj);
                    }
                }
                this.tableData.page.total = data.totalRows;
            },false);
        },
        clearData(){
            this.search.triggerCode = '';
            this.search.handleCode = '';
            this.search.startTime = '';
            this.search.endTime = '';
            this.time = [];
        },
        changeTime(val){
            if (val){
                this.search.startTime = val[0];
                this.search.endTime = val[1];
            }else{
                this.search.startTime = '';
                this.search.endTime = '';
            }
        },
        selectTriggerClear() {
            this.search.triggerCode = ''
            this.$forceUpdate()
        },
        selectTriggerChange(val) {
            this.search.triggerCode = val
            this.$forceUpdate()
        },
        selectHandleClear() {
            this.search.handleCode = ''
            this.$forceUpdate()
        },
        selectHandleChange(val) {
            this.search.handleCode = val
            this.$forceUpdate()
        },
        handleOk(params){
            this.requestDelete([params.id]);
        },
        handleDelete(ids){
            if (this.ids.length == 0)return this.error('请先选择');
            this.alert('确定要删除吗？',()=>this.requestDelete(this.ids));
        },
        requestDelete(ids){

            console.log(ids)

            this.request(del,ids,this,data => {
                (this.tableData.tableData.length == 1 && this.search.pageNum > 1)&&(--this.search.pageNum);
                this.handleGetTableData();
                this.ids.length = 0;
            });
        },
    }
}
</script>

<style lang="css" scoped>
</style>

<style lang="css">
.el-tooltip__popper {
    max-width: 400px;
    line-height: 180%;
}
</style>