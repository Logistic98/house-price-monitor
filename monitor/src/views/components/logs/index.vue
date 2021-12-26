<template>
    <div class="roles">
        <div class="header">
            <el-button v-has="'btn-log-delete'" type="danger" @click="handleDelete(ids)" >批量删除</el-button>
            <div class="search" style="display: inline-block">
                账号：<el-input v-model="search.username" style="width:200px" placeholder="请输入账号"></el-input>
                操作：<el-input v-model="search.operation" style="width:200px" placeholder="请输入用户操作"></el-input>
                <el-date-picker v-model="time" type="datetimerange" @change="changeTime" value-format="yyyy-MM-dd HH:mm:ss" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
            </div>
            <el-button type="primary" @click="tableDataInit(search);">搜索</el-button>
            <el-button type="primary" @click="clearData">重置</el-button>
        </div>
        <component-table :data="tableData">
            <template v-slot:button="{scope}">
                <component-popover v-has="'btn-log-delete'" :params="scope.row" @ok="handleOk"></component-popover>
            </template>
        </component-table>
    </div>
</template>

<script>
import { getTableData,del } from '@/api/log'

export default {
    name: "component-user",
    data () {
        return {
            show:false,
            showTable:{
                showTableAdd:false,
                showTableEdit:false,
                showTableauth:false,
            },
            tableDataAll:null,
            optionText:'',
            time:[],
            search:{
                pageNum:1,//当前页
                pageSize:10,//每页显示条数
                username:'',
                operation:'',
                startTime:'',
                endTime:''
            },
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
                page:{align:'right',total:1,size:10,currentPage:1,currentChange:(currentPage) => {
                    this.$nextTick(() => {
                        this.search.pageNum = currentPage;
                        this.tableDataInit(this.search);
                    })
                }},
                // 表格实现多选时的 change 事件
                selectionChange:(params)=>{
                    this.ids = [];
                    params.map(item => this.ids.push(item.id));
                }
            }
        }
    },
    created(){
        this.labelInit();
        this.handleGetTableData();
        this.time = [this.getDayStartTime(),this.getDayEndTime()];
    },
    methods: {
        handleGetTableData(loading=true){
            this.handleTable();
            loading && this.tableDataInit(this.search);
        },
        // 列初始化
        labelInit(){
            this.tableData.tableLabel = [
                {type:'selection',width:40,fixed:'left',align:'center'},
                {type:'index',title:'序号',fixed:'left',width:60,align:'center'},
                {prop:'username',title:'用户名',width:116},
                {prop:'operation',title:'用户操作',minWidth:150,tooltip:true},
                {prop:'time',title:'响应时间(毫秒)',width:120,tooltip:true},
                {prop:'method',title:'请求方法',minWidth:150,tooltip:true},
                {prop:'params',title:'请求参数',tooltip:true},
                {prop:'ip',title:'IP地址',width:120},
                {prop:'createTime',title:'创建时间',width:160,render:(params)=> [this.formatTime(params.row.createTime)]},
            ];
        },
        // 数据初始化
        tableDataInit(search) {
            this.tableData.loading = true;
            this.request(getTableData,search,this,data => {
                this.tableData.loading = false;
                this.tableData.tableData = data.rows;
                this.tableData.page.total = data.totalRows;
            },false);
        },
        handleTable(flag=false,type='',text='新增用户'){
            this.objectforIn(this.showTable,false);
            this.optionText = text;
            this.show = flag;
            this.showTable[type] = flag;
        },
        /****************************** 操作 ******************************/
        handleAuth(params){
            this.$nextTick(async () => {
                await this.handleTable(true, 'showTableAuth', '角色管理');
                this.$refs.tableAuth && this.$refs.tableAuth.currentData(params.row)
            })
        },
        handleEdit(params){
            this.$nextTick(async () => {
                await this.handleTable(true, 'showTableEdit', '编辑用户');
                this.$refs.tableEdit && this.$refs.tableEdit.currentData(params.row)
            })
        },
        handleOk(params){
            this.requestDelete([params.id]);
        },
        handleDelete(ids){
            if (this.ids.length == 0)return this.error('请先选择');
            this.alert('确定要删除吗？',()=>this.requestDelete(this.ids));
        },
        requestDelete(ids){
            this.request(del,ids,this,data => {
                (this.tableData.tableData.length == 1 && this.search.pageNum > 1)&&(--this.search.pageNum);
                this.handleGetTableData();
                this.ids.length = 0;
            });
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
        clearData(){
            this.search.username = '';
            this.search.operation = '';
            this.search.startTime = '';
            this.search.endTime = '';
            this.time = [];
        }
    },
}
</script>

<style lang="css" scoped>

</style>