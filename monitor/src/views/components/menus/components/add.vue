<template>
    <div class="menu-add">
        <el-form ref="form" :model="form" :rules="rules" style="width:80%">
            <el-form-item label="类型" prop="type" label-width="200px">
                <el-radio-group v-model="form.type" @change="changeType">
                    <el-radio :label="1">目录</el-radio>
                    <el-radio :label="2">菜单</el-radio>
                    <el-radio :label="3">按钮</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="菜单名称" prop="title" label-width="200px">
                <el-input v-model="form.title" placeholder="请输入菜单名称"></el-input>
            </el-form-item>
            <el-form-item label="所属菜单" prop="pid" label-width="200px">
                <el-select v-model="form.pid" @change="changePid" placeholder="请选择" style="width:100%">
                    <el-option v-for="(item,key) in dataTree" :key="key" :label="item.title" :value="item.id" :disabled="form.pid!=item.id && form.id!=item.id?false:true">
                        <span style="float: left">{{'|-----'.repeat(item.level||0) + ' ' + item.title}}</span>
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="接口URL" prop="url" label-width="200px">
                <el-input v-model="form.url" placeholder="请输入接口URL"></el-input>
            </el-form-item>
            <el-form-item label="图标icon" prop="icon" label-width="200px">
                <el-input v-model="form.icon" placeholder="请输入图标icon"></el-input>
            </el-form-item>
            <el-form-item label="name" prop="name" label-width="200px">
                <el-input v-model="form.name" placeholder="请输入name 必须与前端 vue 路由 name 一致且唯一，类型为菜单时必须填写"></el-input>
                <span style="color:red;font-size:12px">* name 必须与前端 vue 路由 name 一致且唯一，（类型为菜单时必须填写）</span>
            </el-form-item>
            <template v-if="form.type!=1">
                <el-form-item label="授权标识" prop="perms" label-width="200px">
                    <el-input v-model="form.perms" placeholder="请输入授权标识，如：sys:user:add"></el-input>
                    <span style="color:red;font-size:12px">* 类型为按钮时，必须填写授权标识</span>
                </el-form-item>
                <el-form-item label="请求方式" prop="method" label-width="200px">
                    <el-input v-model="form.method" placeholder="请输入请求方式，如：GET，POST，PUT，DELETE"></el-input>
                </el-form-item>
                <el-form-item label="按钮标识" prop="code" label-width="200px">
                    <el-input v-model="form.code" placeholder="请输入按钮标识，如：btn-dept-delete"></el-input>
                    <span style="font-size:12px"><i style="color:red;padding-right:2px">* </i>按钮标识，主要用于前端按钮权限的显示与隐藏</span>
                </el-form-item>
            </template>
            <el-form-item label="排序码" prop="orderNum" label-width="200px">
                <el-input v-model="form.orderNum" placeholder="请输入排序码"></el-input>
            </el-form-item>
            <el-form-item  label-width="200px">
                <el-button :loading="loading" type="primary" @click="submit">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import { getDataTree,add } from '@/api/menus'

export default {
    name:'menu-add',
    data () {
        return {
            loading:false,
            dataTree:[],
            form:{
                type:1,
                title: '',
                pid:'0',
                url:'',
                name:'',
                icon:'',
                perms:'',
                method:'',
                code:'',
                orderNum:0,
            },
            rules: {
                type: [
                    { required: true, message: '请输入类型', trigger: 'blur' },
                ],
                title: [
                    { required: true, message: '请输入菜单名称', trigger: 'blur' },
                    { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                ],
                pid: [
                    { required: true, message: '请输入所属菜单', trigger: 'blur' },
                ],
                url: [
                    { required: true, message: '请输入接口URL', trigger: 'blur' },
                    { min: 1, max: 255, message: '长度在 1 到 255 个字符', trigger: 'blur' }
                ]
            }
        }
    },
    created () {
        this.$nextTick(() => this.request(getDataTree,{},this,data => this.dataTree = data,false));
    },
    methods:{
        changeType(val){
            this.form.type = val;
        },
        changePid(val){
            this.form.pid = val;
        },
        submit(){
            this.loading = true;
            this.handleSubmit(add,this.$refs['form'],this,data => {
                this.loading = false;
                this.$emit('handleGetTableData');
            },true);
        }
    }
}
</script>
<style lang="css" scoped>

</style>