<template>
	<div class="login">
		<vue-particles
				class="login-background"
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
		<div class="login-container">
			<div class="title">用户登录</div>
			<div class="login-form" :rules="rules">
				<el-form ref="form" :model="form" :rules="rules" >
					<el-form-item label="" prop="username">
						<el-input v-model="form.username" prefix-icon="el-icon-user" placeholder="请输入账号"></el-input>
					</el-form-item>
					<el-form-item label="" prop="inputPassword">
						<el-input v-model="form.inputPassword" :show-password="true" prefix-icon="el-icon-lock" @keyup.enter.native="submit('form')" placeholder="请输入密码"></el-input>
					</el-form-item>
					<el-form-item label="" prop="code">
						<el-input v-model="form.code" style='width:72%;display:inline-block;vertical-align:top;' prefix-icon="el-icon-check" placeholder="请输入验证码" @keyup.enter.native="submit('form')"></el-input>
						<s-identify style='display:inline-block;height:40px;vertical-align:top;cursor:pointer;' :identifyCode="code" :contentHeight='40' @click.native="handleRefreshCode" />
					</el-form-item>
					<el-form-item label="">
						<el-button type="primary" :loading="loading" style="width:100%;" @click.native="submit('form')"> 登 录 </el-button>
					</el-form-item>
				</el-form>
				<el-button type="text" @click="register">没有账号？去注册</el-button>
			</div>
		</div>
	</div>
</template>
<script>
import SIdentify from './captcha'
import { login } from '@/api/login'
import md5 from "js-md5";

export default {
    name:'login',
    components:{SIdentify},
    data () {
        return {
			loading:false,
			code: this.handleRefreshCode(),
            form: {
                username: '',
				password:'', // 经过md5加密后的密码，给后端传这个。
				inputPassword:'', // 用户输入的密码，未经加密，仅用于校验格式。
                code:'',
            },
            rules: {
                username: [
                    { required: true, message: '请输入账号', trigger: 'blur' },
                ],
                inputPassword: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                ],
                code: [
                    { required: true, message: '请输入验证码', trigger: 'blur' },
                ]
            }
        }
    },
    methods: {		
        handleRefreshCode(){
            this.code = (Math.random()*8999 + 1000).toFixed(0).toString();
			return this.code;
        },
		register() {
            !this.loading && this.$router.push('/register')
        },
        submit(name) {
			this.loading = true;
			this.$progress.start();
            if(!this.form.username){
				this.error('请输入账号!');
				this.loading = false;
				this.$progress.done();
            	return;
        	}
        	if (!this.form.inputPassword) {
        		this.error('请输入密码!');
				this.loading = false;
				this.$progress.done();
            	return;
        	}
            if (!this.form.code) {
                this.error('请输入验证码!');
                this.loading = false;
                this.$progress.done();
                return;
            }
			if(this.form.code != this.code){
				this.error('验证码输入错误!');
                this.loading = false;
                this.$progress.done();
				this.handleRefreshCode();
                return;
			}
			this.form.password = md5(this.form.inputPassword);
        	this.handleSubmit(login,this.$refs[name],this,data => {
	            sessionStorage.removeItem("codeList") // 清除sessionStorage里缓存的codeList
                this.loading = false;
				// true开启权限验证模式 ，false 不使用权限验证
                data.isAuth = true;
				this.success('登录成功');
				this.$ls.set("userInfo",data);
        		this.setCodeList(); // 请求接口，将系统字典存入VueX
				setTimeout(() =>{
					this.$router.push('/'); // 设置延时是为了解决登录时VueX还没请求完就加载页面的问题。
				},1000);
			},false,fail => {
				this.handleRefreshCode();
				this.error(fail.msg + '，请5秒后再试');
                setTimeout(() => {
                    this.loading = false;
                    this.$progress.done();
                },5000)
			});
        }
    }
}
</script>

<style lang="css" scoped>
.login-background {
	background:rgb(0, 5, 15);
	width: 100%;
	height: 100%; 
	z-index: -1; 
	position: absolute;
	justify-content: center;
	align-items: center; 
}

.login {
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

.login .login-container{
	-webkit-box-flex: 1;
    -ms-flex: 1;
    flex: 1;
    padding: 32px 35px;
    text-align: center;
    width: 520px;
    max-width: 100%;
    margin: 0 auto;
}

.login .login-container .title {
    padding-top: 120px;
    margin-bottom: 40px;
    cursor: pointer;
    font-size: 26px;
    color: #fff;
    font-weight: 700;
    position: relative;
}

.login .login-container .login-form{
	width: 80%;
	margin: 0 auto;
	padding-bottom: 10px;
}
</style>