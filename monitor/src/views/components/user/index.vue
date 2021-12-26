<template>
    <div class="roles">
        <div class="header">
            <el-button v-has="'btn-user-add'" type="primary" @click="handleTable(true,'showTableAdd')" >添加</el-button>
            <div class="search" style="display: inline-block">
                账号：<el-input v-model="search.username" style="width:200px" placeholder="请输入账号"></el-input>
                昵称：<el-input v-model="search.nickName" style="width:200px" placeholder="请输入昵称"></el-input>
                时间：<el-date-picker v-model="time" type="datetimerange" @change="changeTime" value-format="yyyy-MM-dd HH:mm:ss" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
            </div>
            <el-button type="primary" @click="tableDataInit(search);">搜索</el-button>
            <el-button type="primary" @click="clearData">重置</el-button>
        </div>
        <component-table :data="tableData">
            <template v-slot:button="{scope}">
                <template v-if="scope.row.username!='admin'">
                    <el-button v-has="'btn-user-update-role'" type="success" size="mini" style="background: #19be6b" @click="handleAuth(scope)">角色</el-button>
                    <el-button v-has="'btn-user-update'" type="primary" size="mini" @click="handleEdit(scope)">编辑</el-button>
                    <component-popover v-has="'btn-user-delete'" :params="scope.row" @ok="handleOk"></component-popover>
                </template>
            </template>
        </component-table>

        <component-dialog v-if="show" :width="showTable.showTableAuth?'612px':'760px'" :title="optionText" :visible.sync="show" :footer="false">
            <table-add slot="dialog" v-if="showTable.showTableAdd" @handleGetTableData="handleGetTableData"></table-add>
            <table-edit slot="dialog" ref="tableEdit" v-if="showTable.showTableEdit" @handleGetTableData="handleGetTableData"></table-edit>
            <table-auth slot="dialog" ref="tableAuth" v-if="showTable.showTableAuth" @handleGetTableData="handleGetTableData"></table-auth>
        </component-dialog>
    </div>
</template>

<script>
import tableAdd from './components/add.vue'
import tableEdit from './components/edit.vue'
import tableAuth from './components/auth.vue'
import { getTableData,del,edit } from '@/api/user'

export default {
    name: "component-user",
    components:{
        tableAdd,
        tableEdit,
        tableAuth
    },
    data () {
        return {
            show:false,
            showTable:{
                showTableAdd:false,
                showTableEdit:false,
                showTableauth:false,
            },
            tableDataAll:null,
            currentPage:1,
            optionText:'',
            time:[],
            search:{
                pageNum:1,//当前页
                pageSize:10,//每页显示条数
                username:'',
                nickName:'',
                status:'',
                startTime:'',
                endTime:'',
                remark:''
            },
            parentData:null,
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
                    width:210,
                    slot:true,
                },
                // 分页
                page:{align:'right',total:1,size:10,currentPage:1,currentChange:(currentPage) => {
                    this.$nextTick(() => {
                        this.search.pageNum = currentPage;
                        this.tableDataInit(this.search);
                    })
                }},
            },
        }
    },
    created(){
        this.labelInit();
        this.handleGetTableData();
    },
    methods: {
        handleGetTableData(loading=true){
            this.handleTable();
            loading && this.tableDataInit(this.search);
        },
        // 列初始化
        labelInit(){
            this.tableData.tableLabel = [
                {type:'index',title:'序号',fixed:'left',width:60,align:'center'},
                {prop:'username',title:'账号',tooltip:true},
                {prop:'nickName',title:'昵称',tooltip:true},
                {prop:'email',title:'邮箱',tooltip:true},
                {prop:'createUserName',title:'创建人',width:80},
                {prop:'updateUserName',title:'更新人',width:80},
                {prop:'createTime',title:'创建时间',width:160,render:(params)=> [this.formatTime(params.row.createTime)]},
                {prop:'updateTime',title:'更新时间',width:160,render:(params)=> [this.formatTime(params.row.updateTime)]},
                {prop:'remark',title:'备注',tooltip:true},
                {prop:'status',title:'状态',width:80,isSwitch:true,activeText:'正常',inactiveText:'锁定',inactiveValue:2,style:(params)=>{if(params.row.username=='admin')return {display:'none'}},
                    change:(currentData) => {
                        this.$nextTick(() => {
                            let msg = currentData.row.status==2?'锁定':'解除锁定',
                                value = msg == '锁定' ? 1 : 2;
                            this.alert('确定要'+ msg +'吗？',()=>{
                                this.request(edit,{id:currentData.row.id,status:currentData.row.status},this,null,true,fail => {
                                    currentData.row.status = value;
                                    this.error('数据异常');
                                });
                            },() => currentData.row.status = value);
                        })
                    }}
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
            this.request(del,[params.id],this,data => {
                (this.tableData.tableData.length == 1 && this.search.pageNum > 1)&&(--this.search.pageNum);
                this.handleGetTableData();
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
            this.search.nickName = '';
            this.time = [];
        }
    },
}
</script>

<style lang="css" scoped>

</style>