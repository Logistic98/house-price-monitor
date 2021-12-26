<template>
    <div class="roles-add">
        <component-form :data="form" :width="80">
            <template slot="formItem">
                <el-tree ref="tree" :data="permissionsTree" show-checkbox node-key="id"
                    :default-expanded-keys="[2]"
                    :default-checked-keys="[5]"
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
import { add } from '@/api/roles'
import { getPermissionsTree } from '@/api/menus'

export default {
    name:'roles-add',
    data () {
        return {
            defaultProps: {
                children: 'children',
                label: 'title'
            },
            permissionsTree:[],
            form:{
                labelWidth:'200px',
                formFields:{
                    name: '',
                    description:'',
                    status:1,
                    permissionsIds:[]
                },
                formLabel:[
                    {prop: 'name', title: '角色名称', type: 'input',width:'80%',placeholder:'请输入角色名称'},
                    {prop: 'description', title: '角色描述', type: 'input',width:'80%',placeholder:'请输入角色描述'},
                    {prop: 'permissionsIds', title: '选择权限', type: 'slot',slot:'formItem'},
                ],
                buttons:{
                    options:[
                        {title:'提交',type:'primary',loading:false,click:(form,item) => {
                            this.handleSubmit(add,form,this,data => this.$emit('handleGetTableData'),true);
                        }},
                    ]
                },
                rules: {
                    id: [
                        { required: true},
                    ],
                    name: [
                        { required: true, message: '请输入角色名称', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 20 个字符内', trigger: 'blur' }
                    ],
                    permissionsIds:[
                        { required: true, message: '请输入选择权限', trigger: 'blur' },
                    ]
                }
            },
        }
    },
    created (){
        this.$nextTick(() => this.request(getPermissionsTree,{},this,data => this.permissionsTree = data,false));
    },
    methods:{
        change(val){
            this.form.pid = val;
        },
        getChecked(){
            this.form.formFields.permissionsIds = this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys());
        }
    }
}
</script>
<style lang="css" scoped>

</style>