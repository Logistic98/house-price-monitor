<template>
    <div class="roles">
        <div class="header">
            <el-button v-has="'btn-role-add'" type="primary" @click="handleTable(true,'showTableAdd')" >添加</el-button>
        </div>
        <component-table :data="tableData">
            <template v-slot:type="{scope}">
                <el-tag v-if='scope.row.type==1'>目录</el-tag>
                <el-tag v-if='scope.row.type==2' type="success">菜单</el-tag>
                <el-tag v-if='scope.row.type==3' type="warning">按钮</el-tag>
            </template>
            <template v-slot:method="{scope}">
                <el-tag v-if='scope.row.method=="GET"'>{{scope.row.method}}</el-tag>
                <el-tag v-if='scope.row.method=="POST"' type="success">{{scope.row.method}}</el-tag>
                <el-tag v-if='scope.row.method=="PUT"' type="warning">{{scope.row.method}}</el-tag>
                <el-tag v-if='scope.row.method=="DELETE"' type="danger">{{scope.row.method}}</el-tag>
            </template>
            <template v-slot:parent="{scope}">
                <el-tag v-if='scope.row.type==1' type="info">顶级目录</el-tag>
                <el-tag v-if='scope.row.type==2'>{{scope.row.parent}}</el-tag>
                <el-tag v-if='scope.row.type==3' type="success">{{scope.row.parent}}</el-tag>
            </template>
            <template v-slot:button="{scope}">
                <el-button v-has="'btn-role-update'" type="primary" size="mini" @click="handleEdit(scope)">编辑</el-button>
                <component-popover v-has="'btn-role-delete'" :params="scope.row" @ok="handleOk"></component-popover>
            </template>
        </component-table>

        <component-dialog v-if="show" :width="50" :title="optionText" :visible.sync="show" :footer="false">
            <table-add slot="dialog" v-if="showTable.showTableAdd" @handleGetTableData="handleGetTableData" style="overflow: hidden"></table-add>
            <table-edit slot="dialog" ref="tableEdit" v-if="showTable.showTableEdit" :groups="parentData" @handleGetTableData="handleGetTableData" style="overflow: hidden"></table-edit>
        </component-dialog>
    </div>
</template>

<script>
import tableAdd from './components/add.vue'
import tableEdit from './components/edit.vue'
import { getTableData,del,updateStatus } from '@/api/roles'

export default {
    name: "component-roles",
    components:{
        tableAdd,
        tableEdit
    },
    data () {
        return {
            show:false,
            showTable:{
                showTableAdd:false,
                showTableEdit:false,
            },
            tableDataAll:null,
            currentPage:1,
            optionText:'',
            search:{
                pageNum:1,//当前页
                pageSize:10,//每页显示条数
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
                    width:150,
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
        // this.tableDataInit(this.tableData.page.currentPage,pageOffset);
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
                {prop:'name',title:'角色名称'},
                {prop:'description',title:'描述'},
                {prop:'status',title:'状态',isSwitch:true,activeText:'正常',inactiveText:'禁用',style:(params,item)=>{if(params.row.name=='超级管理员')return {display:'none'}},
                    change:(currentData) => {
                        this.$nextTick(() => {
                            let msg = currentData.row.status==0?'禁用':'恢复',
                                value = msg == '禁用' ? 1 : 0;
                            this.alert('确定要'+ msg +'吗？',()=>{
                                this.request(updateStatus,[currentData.row.id,currentData.row.status],this,null,true,fail => {
                                    currentData.row.status = value;
                                    this.error('数据异常');
                                });
                            },() => currentData.row.status = value);
                        })
                    }},
                {prop:'createTime',title:'创建时间',width:160,render:(params,col)=> [this.formatTime(params.row.createTime)]},
                {prop:'updateTime',title:'更新时间',width:160,render:(params,col)=> [this.formatTime(params.row.updateTime)]}
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
        handleTable(flag=false,type='',text='新增角色'){
            this.objectforIn(this.showTable,false);
            this.optionText = text;
            this.show = flag;
            this.showTable[type] = flag;
        },
        /****************************** 操作 ******************************/
        handleEdit(params){
            this.$nextTick(async () => {
                await this.handleTable(true, 'showTableEdit', '编辑角色');
                this.$refs.tableEdit && this.$refs.tableEdit.currentData(params.row)
            })
        },
        handleOk(params){
            this.request(del,params.id,this,data => {
                (this.tableData.tableData.length == 1 && this.search.pageNum > 1)&&(--this.search.pageNum);
                this.handleGetTableData();
            });
        }
    },
}
</script>

<style lang="css" scoped>

</style>