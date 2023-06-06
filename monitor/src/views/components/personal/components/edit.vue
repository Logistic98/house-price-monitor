<template>
    <div class="personal-password">
        <component-form :data="form" :width="80"></component-form>
    </div>
</template>
<script>
import { editPassword } from '@/api/personal';
import md5 from "js-md5";

export default {
    name:'personal-password',
    data () {
        return {
            formData:null,
            form:{
                labelWidth:'200px',
                formFields:{
                    oldPwd: '', // 经过md5加密后的旧密码，给后端传这个。
                    oldPwdInput: '', // 用户输入的旧密码，未经加密，仅用于校验格式。
                    newPwd: '',  // 经过md5加密后的新密码，给后端传这个。
                    newPwdInput: '',  // 用户输入的新密码，未经加密，仅用于校验格式。
                    newPwdCheck: '',  // 用户再次输入的密码，仅用于校验两次输入是否一致。
                },
                formLabel:[
                    {prop: 'oldPwdInput', title: '旧密码', type: 'input',password:true,placeholder:'请输入旧密码（ 6 ~ 16 位）'},
                    {prop: 'newPwdInput', title: '新密码', type: 'input',password:true,placeholder:'请输入新密码（ 6 ~ 16 位）'},
                    {prop: 'newPwdCheck', title: '校验', type: 'input',password:true,placeholder:'请再次输入新密码'}],
                buttons:{
                    align:'center',
                    options:[
                        {title:'提交',type:'primary',loading:false,click:(form,item) => {
                            if (!this.form.formFields.newPwdInput) {
                                this.error('新密码不能为空!');
                                this.loading = false;
                                this.$progress.done();
                                return;
                            }
                            if (this.form.formFields.newPwdInput == this.form.formFields.oldPwdInput) {
                                this.error('新密码不能与旧密码一致!');
                                this.loading = false;
                                this.$progress.done();
                                return;
                            }
                            if (this.form.formFields.newPwdInput != this.form.formFields.newPwdCheck) {
                                this.error('两次输入的新密码不一致!');
                                this.loading = false;
                                this.$progress.done();
                                return;
                            }
                            this.form.formFields.oldPwd = md5(this.form.formFields.oldPwdInput);
                            this.form.formFields.newPwd = md5(this.form.formFields.newPwdInput);
                            item.loading = true;
                            this.request(editPassword,this.form.formFields,this,data => {
                                item.loading = false;
                                this.$emit('handleChildrenCloseDialog');
                                this.success(data.msg + '，即将重新登录',() => {
                                    setTimeout(()=>this.$parent.$parent.$parent.logout(),1000);
                                })
                            },false,fail => {
                                this.error(fail.msg);
                                setTimeout(()=>item.loading = false,2000);
                            });
                        }}
                    ]
                },
                rules: {
                    oldPwdInput: [
                        { required: true, message: '请输入旧密码', trigger: 'blur' },
                        { min: 6, max: 16, message: '密码长度须在 6-16 位之间', trigger: 'blur' }
                    ],
                    newPwdInput: [
                        { required: true, message: '请输入新密码', trigger: 'blur' },
                        { min: 6, max: 16, message: '密码长度须在 6-16 位之间', trigger: 'blur' },
                    ],
                    newPwdCheck: [
                        { required: true, message: '请再次输入新密码', trigger: 'blur' },
                    ],
                }
            },
        }
    },
    methods:{
    }
}
</script>
<style lang="css" scoped>

</style>