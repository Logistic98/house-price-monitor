<template>
    <div class="register">
		<vue-particles 
			class="register-background"
			color="#97D0F2"
			:particleOpacity="0.7"
			:particlesNumber="50"
			shapeType="circle"
			:particleSize="4"
			linesColor="#97D0F2"
			:linesWidth="1"
			:lineLinked="true"
			:lineOpacity="0.4"
			:linesDistance="150"
			:moveSpeed="3"
			:hoverEffect="true"
			hoverMode="grab"
			:clickEffect="true"
			clickMode="push">
		</vue-particles>
        <div class="register-container">
			<h2 class="title">用户注册</h2>
			<div class="register-form" :rules="rules">
				<el-form ref="form" :model="form" :rules="rules" >
					<el-form-item label="" prop="username">
						<el-input v-model="form.username" prefix-icon="el-icon-user" placeholder="请输入账号"></el-input>
					</el-form-item>
                    <el-form-item label="" prop="nickName">
						<el-input v-model="form.nickName" prefix-icon="el-icon-s-custom" placeholder="请输入昵称"></el-input>
					</el-form-item>
					<el-form-item label="" prop="inputPassword">
						<el-input v-model="form.inputPassword" :show-password="true" prefix-icon="el-icon-lock" placeholder="请输入密码"></el-input>
					</el-form-item>
					<el-form-item label="" prop="checkPassword">
						<el-input v-model="form.checkPassword" :show-password="true" prefix-icon="el-icon-document-checked" placeholder="请再次输入密码"></el-input>
					</el-form-item>
                    <el-form-item label="" prop="email">
						<el-input v-model="form.email" prefix-icon="el-icon-message" placeholder="请输入邮箱" @keyup.enter.native="submit('form')"></el-input>
					</el-form-item>
					<el-form-item label="">
						<el-button type="primary" :loading="loading" style="width:100%;" @click.native="submit('form')"> 注 册 </el-button>
					</el-form-item>
				</el-form>
				<el-button type="text" @click="login">已有账号？去登录</el-button>
			</div>
		</div>
    </div>
</template>
<script>
import { register } from '@/api/login'
import md5 from "js-md5";

export default {
    name:'register',
    data () {
        return {
            loading:false,
            form:{   
                username:'',
                password:'', // 经过md5加密后的密码，给后端传这个。
				inputPassword:'', // 用户输入的密码，未经加密，仅用于校验格式。
				checkPassword:'', // 用户再次输入的密码，仅用于校验两次输入是否一致。
                nickName:'',
                email:'',
                status:1,
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
                    { required: true, message: '请输入再次密码', trigger: 'blur' },
                    { min: 6, max: 16, message: '密码长度须在 6-16 位之间', trigger: 'blur' }
                ],          
                nickName: [
                    { required: true, message: '请输入昵称', trigger: 'blur' },
                    { max: 20, message: '长度在 20 个字符内', trigger: 'blur' }
                ],
                email: [
                    { required: true, message: '请输入邮箱', trigger: 'blur' },
                    { pattern:this.validator.regExpEmail, message: '邮箱格式不正确', trigger: 'blur' }
                ]
            }
        }
    },
    methods:{
        submit(name) {
            this.loading = true;
			this.$progress.start();
            if(!this.form.username){
				this.error('请输入账号!');
				this.loading = false;
				this.$progress.done();
            	return;
        	}
            if(!this.form.inputPassword){
				this.error('请输入密码!');
				this.loading = false;
				this.$progress.done();
            	return;
        	}
			if(!this.form.checkPassword){
				this.error('请再次输入密码!');
				this.loading = false;
				this.$progress.done();
            	return;
        	}
        	if (!this.form.nickName) {
        		this.error('请输入昵称!');
				this.loading = false;
				this.$progress.done();
            	return;
        	}
        	if (!this.form.email) {
        		this.error('请输入邮箱!');
				this.loading = false;
				this.$progress.done();
            	return;
        	}
			if (this.form.inputPassword != this.form.checkPassword) {
        		this.error('两次输入的密码不一致!');
				this.loading = false;
				this.$progress.done();
            	return;
        	}
			this.form.password = md5(this.form.inputPassword);
            this.handleSubmit(register,this.$refs[name],this,data => {
                this.loading = false;
				this.success('注册成功');
				this.$router.push('/login')
			},false,fail => {
				this.error(fail.msg)
            });
        },
		login() {
            !this.loading && this.$router.push('/login')
        }
    }
}
</script>

<style lang="css" scoped>
.register-background {
	background:rgb(0, 5, 15);
	width: 100%;
	height: 100%; 
	z-index: -1; 
	position: absolute;
	justify-content: center;
	align-items: center; 
}

.register {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    height: 100%;
    overflow: auto;
}

.register .register-container{
	-webkit-box-flex: 1;
    -ms-flex: 1;
    flex: 1;
    padding: 32px 35px;
    text-align: center;
    width: 520px;
    max-width: 100%;
    margin: 0 auto;
}

.register .register-container .title {
    padding-top: 120px;
    margin-bottom: 40px;
    cursor: pointer;
    font-size: 26px;
    color: #fff;
    font-weight: 700;
    position: relative;
}

.register .register-container .register-form{
	width: 80%;
	margin: 0 auto;
	padding-bottom: 10px;
}
</style>