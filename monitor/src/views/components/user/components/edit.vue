<template>
    <div class="user-edit">
        <component-form :data="form" :width="80"></component-form>
    </div>
</template>
<script>
import { edit,resetPassword } from '@/api/user'

export default {
    name:'user-edit',
    data () {
        return {
            form:{
                labelWidth:'200px',
                formFields:{
                    id:'',
                    username: '',
                    email:'',
                    nickName:'',
                    remark:'',
                    status:1,
                },
                formLabel:[
                    {prop: 'username', title: '账号', type: 'input',placeholder:'请输入账号'},
                    {prop: 'email', title: '邮箱', type: 'input',placeholder:'请输入邮箱'},
                    {prop: 'nickName', title: '昵称', type: 'input',placeholder:'请输入昵称'},
                    {prop: 'remark', title: '备注', type: 'input',placeholder:'请输入备注'}
                ],
                buttons:{
                    options:[
                        {title:'提交',type:'primary',loading:false,style:{marginRight:'20px'},click:(form,item) => {
                            this.handleSubmit(edit,form,this,data => this.$emit('handleGetTableData'));
                        }},
                        {title:'重置密码',type:'warning',loading:false,click:(form,item) => {
                            item.loading = true;
                            this.alert('确定要重置密码吗？默认密码 123456',()=>{
                                this.request(resetPassword,form.model.id,this,() => item.loading = false,true,fail => {
                                    item.loading = false;
                                    this.error(fail.msg);
                                });
                            });
                        }},
                    ]
                },
                rules: {
                    username: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在 20 个字符内', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入邮箱', trigger: 'blur' },
                        { pattern:this.validator.regExpEmail, message: '邮箱格式不正确', trigger: 'blur' }
                    ],
                    nickName: [
                        { required: true, message: '请输入昵称', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 20 个字符内', trigger: 'blur' }
                    ],
                }
            }
        }
    },
    methods:{
        currentData(currentData){
            currentData && Object.keys(this.form.formFields).forEach(key => this.form.formFields[key] = currentData[key]);
        },
        change(val){this.form.pid = val;}
    }
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