<template>
    <div class="menu-edit">
        <component-form :data="form" :width="80">
            <template slot="formItem">
                <el-tree ref="tree" :data="permissionsTree" show-checkbox node-key="id"
                     :default-expanded-keys="defaultCheckedKeys"
                     :default-checked-keys="defaultCheckedKeys"
                     :props="defaultProps"
                     @check="getChecked">
                    <span class="custom-tree-node" slot-scope="{ node }">
                        <span>{{ node.label }}</span>
                        <el-tag v-if='node.level==1' size="mini">目录</el-tag>
                        <el-tag v-if='node.level==2' type="success" size="mini">菜单</el-tag>
                        <el-tag v-if='node.level==3' type="warning" size="mini">按钮</el-tag>
                    </span>
                </el-tree>
            </template>
        </component-form>
    </div>
</template>
<script>
import { getPermissionsIds,edit } from '@/api/roles'
import { getPermissionsTree } from '@/api/menus'

export default {
    name:'menu-edit',
    data () {
        return {
            defaultProps: {
                children: 'children',
                label: 'title'
            },
            defaultCheckedKeys:[],
            permissionsTree:[],
            form:{
                labelWidth:'200px',
                formFields:{
                    id:'',
                    name: '',
                    description:'',
                    status:1,
                    permissionsIds:[]
                },
                formLabel:[
                    {prop: 'name', title: '角色名称', type: 'input',width:'80%',placeholder:'请输入姓名'},
                    {prop: 'description', title: '角色描述', type: 'input',width:'80%',placeholder:'请输入姓名'},
                    {prop: 'text', title: '选择权限', type: 'slot',slot:'formItem'},
                ],
                buttons:{
                    options:[
                        {title:'提交',type:'primary',loading:false,click:(form,item) => {
                            this.handleSubmit(edit,form,this,data => this.$emit('handleGetTableData'));
                        }},
                    ]
                },
                rules: {
                    id: [
                        { required: true},
                    ],
                    name: [
                        { required: true, message: '请输入活动名称', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 20 个字符内', trigger: 'blur' }
                    ],
                }
            },
            parentids:[]
        }
    },
    created (){
        // this.$nextTick(() => this.request(getPermissionsTree,{},this,data => {
        //     this.permissionsTree = data;
        //     this.parentids = parentids(data);
        //     console.log(20000,this.parentids);
        // },false));
    },
    methods:{
        currentData(currentData){
            this.$nextTick(() => {
                this.request(getPermissionsTree,{},this,data => {
                    this.permissionsTree = data;
                    let ids = parentids(data);
                    if (currentData){
                        Object.keys(this.form.formFields).forEach(key => this.form.formFields[key] = currentData[key]);
                        currentData.id && this.request(getPermissionsIds,currentData.id,this,data => {
                            //this.defaultCheckedKeys = data;
                            this.form.formFields.permissionsIds = data;
                            this.defaultCheckedKeys = data.filter(id => ids.findIndex(item => item == id) == -1);
                        },false);
                    }
                },false);
            })
        },
        change(val){this.form.pid = val;},
        getChecked(){
            this.form.formFields.permissionsIds = this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys());
        }
    }
}
var parentids = function (data) {
    var ids = [];
    if (data && data.length > 0){
        for (var key in data){
            if (data[key].children && data[key].children.length > 0){
                ids.push(data[key].id);
                ids = [...ids,...parentids(data[key].children)];
            }
        }
    }
    return ids;
}
</script>
<style lang="css" scoped>
.custom-tree-node {
    flex: 1;
    /*display: flex;*/
    /*align-items: center;*/
    /*justify-content: space-between;*/
    font-size: 14px;
    padding-right: 8px;
}
</style>