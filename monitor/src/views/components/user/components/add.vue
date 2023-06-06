<template>
    <div class="user-add">
        <component-form :data="form" :width="80"></component-form>
    </div>
</template>
<script>
import { add } from '@/api/user'
import md5 from "js-md5";

export default {
    name:'user-add',
    data () {
        return {
            form:{
                labelWidth:'200px',
                formFields:{
                    username: '',
                    password:'', // 经过md5加密后的密码，给后端传这个。
                    inputPassword:'', // 用户输入的密码，未经加密，仅用于校验格式。
                    checkPassword:'', // 用户再次输入的密码，仅用于校验两次输入是否一致。
                    email:'',
                    nickName:'',
                    remark:'',
                    status:1,
                },
                formLabel:[
                    {prop: 'username', title: '账号', type: 'input',placeholder:'请输入账号'},
                    {prop: 'inputPassword', title: '密码', type: 'input',password:true,placeholder:'请输入密码'},
                    {prop: 'checkPassword', title: '校验', type: 'input',password:true,placeholder:'请再次输入密码'},
                    {prop: 'email', title: '邮箱', type: 'input',placeholder:'请输入邮箱'},
                    {prop: 'nickName', title: '昵称', type: 'input',placeholder:'请输入昵称'},
                    {prop: 'remark', title: '备注', type: 'input',placeholder:'请输入备注'}
                ],
                buttons:{
                    options:[
                        {title:'提交',type:'primary',loading:false,click:(form,item) => {
                            if (this.form.formFields.inputPassword != this.form.formFields.checkPassword) {
                                this.error('两次输入的密码不一致!');
                                this.loading = false;
                                this.$progress.done();
                                return;
                            }
                            this.form.formFields.password = md5(this.form.formFields.inputPassword);
                            this.handleSubmit(add,form,this,data => this.$emit('handleGetTableData'),true);
                        }},
                    ]
                },
                rules: {
                    username: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        { max: 20, message: '长度在 20 个字符内', trigger: 'blur' }
                    ],
                    inputPassword: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, max: 16, message: '密码长度须在 6-16 位之间', trigger: 'blur' }
                    ],  
                    checkPassword: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, max: 16, message: '密码长度须在 6-16 位之间', trigger: 'blur' }
                    ],            
                    email: [
                        { required: true, message: '请输入邮箱', trigger: 'blur' },
                        { pattern:this.validator.regExpEmail, message: '邮箱格式不正确', trigger: 'blur' }
                    ],
                    nickName: [
                        { required: true, message: '请输入昵称', trigger: 'blur' },
                        { max: 20, message: '长度在 20 个字符内', trigger: 'blur' }
                    ],
                }
            }
        }
    },
    methods:{
        change(val){
            this.form.pid = val;
        },
    }
}
</script>
<style lang="css" scoped>

</style>