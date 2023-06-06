<template>
	<div class="menu">
		<div class="header">
			<el-button v-has="'btn-permission-add'" type="primary" @click="handleTable(true,'showTableAdd')" >添加</el-button>
		</div>
		<!-- 表格 default-expand-all默认展开所有-->
		<el-table v-loading="loading" element-loading-text="loading" element-loading-spinner="el-icon-loading"
			:data="tableData"
			style="width:100%;margin-top:10px;margin-bottom:20px;"
			row-key="id"
			border
			:tree-props="{children: 'children', hasChildren: 'hasChildren'}">
			<el-table-column label="序号" type="index" fixed="left" width="60" align="center"></el-table-column>
			<el-table-column prop="title" label="菜单名称" min-width="220"></el-table-column>
			<el-table-column prop="icon" label="icon" width="56">
				<template slot-scope="scope"><i :class="scope.row.icon+' fa-icon'"></i></template>
			</el-table-column>
			<el-table-column prop="url" label="path" width="150"></el-table-column>
			<el-table-column prop="method" label="请求方式" width="100">
				<template slot-scope="scope">
					<el-tag v-if='scope.row.method=="GET"'>{{scope.row.method}}</el-tag>
					<el-tag v-if='scope.row.method=="POST"' type="success">{{scope.row.method}}</el-tag>
					<el-tag v-if='scope.row.method=="PUT"' type="warning">{{scope.row.method}}</el-tag>
					<el-tag v-if='scope.row.method=="DELETE"' type="danger">{{scope.row.method}}</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="type" label="类型" width="90">
				<template slot-scope="scope">
					<el-tag v-if='scope.row.type==1'>目录</el-tag>
					<el-tag v-if='scope.row.type==2' type="success">菜单</el-tag>
					<el-tag v-if='scope.row.type==3' type="warning">按钮</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="parent" label="父级名称" width="120">
				<template slot-scope="scope">
					<el-tag v-if='scope.row.type==1' type="info">顶级目录</el-tag>
					<el-tag v-if='scope.row.type==2'>{{scope.row.parent}}</el-tag>
					<el-tag v-if='scope.row.type==3' type="success">{{scope.row.parent}}</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="orderNum" label="排序" width="70"></el-table-column>
			<el-table-column prop="perms" label="资源标识" width="170">
				<template slot-scope="scope">
					<el-tag v-if='scope.row.perms' type="info">{{scope.row.perms}}</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="code" label="按钮标识" width="180">
				<template slot-scope="scope">
					<el-tag v-if='scope.row.code' type="info">{{scope.row.code}}</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="name" label="路由name" width="100">
				<template slot-scope="scope">
					<el-tag v-if='scope.row.name' type="danger" effect="plain">{{scope.row.name}}</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间" width="160">
				<template slot-scope="scope">{{formatTime(scope.row.createTime)}}</template>
			</el-table-column>
			<el-table-column prop="updateTime" label="更新时间" width="160">
				<template slot-scope="scope">{{formatTime(scope.row.updateTime)}}</template>
			</el-table-column>
			<el-table-column label="操作" fixed="right" align="center" width="150">
				<template slot-scope="scope">
					<el-button v-has="'btn-permission-update'" size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<component-popover v-has="'btn-permission-delete'" :text="'删除'" @ok="handleOk" :params="{id:scope.row.id}"/>
				</template>
			</el-table-column>
		</el-table>

		<!-- 添加 / 编辑弹窗 -->
		<component-dialog v-if="show" :width="50" :title="optionText" :visible.sync="show" :footer="false">
			<table-add slot="dialog" v-if="showTable.showTableAdd" @handleGetTableData="handleGetTableData" style="overflow: hidden"></table-add>
			<table-edit slot="dialog" ref="tableEdit" v-if="showTable.showTableEdit" @handleGetTableData="handleGetTableData" style="overflow: hidden"></table-edit>
		</component-dialog>
	</div>
</template>

<script>
import tableAdd from './components/add.vue'
import tableEdit from './components/edit.vue'
import { getTableData,del } from '@/api/menus'

export default {
	name: "component-menus",
	components:{
		tableAdd,
		tableEdit
	},
	data () {
		return {
			loading:false,
			show:false,
			showTable:{
				showTableAdd:false,
				showTableEdit:false,
			},
			optionText:'',
			visible:false,
			tableData: [],
		}
	},
	created(){
		this.handleGetTableData();
	},
	methods: {
		handleGetTableData(loading=true){
			this.handleTable();
			loading && this.tableDataInit();
		},
		// 数据
		tableDataInit() {
			this.loading = true;
			this.request(getTableData,{},this,data => {
				this.loading = false;
				this.tableData = getTreeTable(data);
			},false);
		},
		handleTable(flag=false,type='',text='新增菜单'){
			this.objectforIn(this.showTable,false);
			this.optionText = text;
			this.show = flag;
			this.showTable[type] = flag;return;
		},
		/****************************** 操作 ******************************/
		handleEdit(index,params){
			this.$nextTick(async () => {
				await this.handleTable(true, 'showTableEdit', '编辑菜单');
				this.$refs.tableEdit && this.$refs.tableEdit.currentData(params);
			})
		},
		handleOk(params){
			this.request(del,params.id,this,data => this.handleGetTableData());
		}
	},
}
var getTreeTable = function (data) {
	let tableData = [];
	if (data && data.length > 0){
		data.map(item => {
			item.children = [];
			data.map((child,index) => {
				if (item.id == child.pid){
					child.parent = item.title;
					item.children.push(child);
				}
			});
			tableData.push(item);
		});
	}
	return tableData.filter(item => item.pid == '0');
}
</script>

<style lang="css" scoped>
.menu .fa-icon{
	vertical-align: middle;
	margin-right: 5px;
	width: 24px;
	text-align: center;
	font-size: 18px;
}
</style>