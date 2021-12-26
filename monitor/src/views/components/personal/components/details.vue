<template>
    <div class="personal-details" style="overflow:hidden">
        <div class="left" style="display:inline-block;margin-left:8%;vertical-align:middle">
            <div class="img" style="width:350px;height:250px">
                <img src="@/assets/images/timg.jpg" width="100%" height="100%">
            </div>
        </div>
        <div class="right" style="display:inline-block;vertical-align:middle">
            <component-form :data="form">
                <template slot="status">
                    <span v-if="form.formFields.status==1" style="color:#67C23A">正常</span>
                    <span v-if="form.formFields.status==2" style="color:red">锁定</span>
                </template>
            </component-form>
        </div>
    </div>
</template>
<script>
import { getPersonal } from '@/api/personal'
export default {
    name:'personal-details',
    data () {
        return {
            form:{
                labelWidth:'100px',
                formFields:{
                    username:'',
                    nickName: '',
                    email:'',
                    status: '',
                },
                formLabel:[
                    {prop: 'username', title: '账号', type: 'text'},
                    {prop: 'nickName', title: '昵称', type: 'text'},
                    {prop: 'email', title: '邮箱', type: 'text'},
                    {prop: 'status', title: '状态', type: 'slot',slot:'status'},
                ]
            },
        }
    },
    created() {
        this.$nextTick(() => this.request(getPersonal,{},this,data => {
            this.form.formFields = data;
        },false));
    }
}
</script>
<style lang="css" scoped>

</style>