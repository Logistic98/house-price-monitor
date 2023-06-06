<template>
	<div class="error-page">
		<div class="pic-404">
			<img class="pic-404__parent" src="@/assets/images/404.jpg" alt />
		</div>
		<div class="notFound">
			<span>啊哦~~~你访问的页面不存在哦</span>
			<span>你可以尝试<a class="link" @click="$router.go(-1)">返回上一页</a></span>
		</div>
	</div>
</template>
<script>
export default {
	name:'Notfound',
	data () {return {}}
}
</script>
<style lang="css" scoped>
/*.notfound{
	width: 100%;
	height: 100%;
	overflow: hidden;
}
.notfound > img{
	width: 100%;
	height: 100%;
}*/
.error-page {
	padding-top: 10%;
	padding-bottom: 10%;
	width: 100%;
	height: 100%;
	display: flex;
	overflow: hidden;
	justify-content: center;
}
.error-page > .pic-404 {
	width: 40%;
	height: 40%;
	padding-top: 50px;
	padding-bottom: 50px;
}
.error-page img.pic-404__parent {
	/*width: 600px;*/
	width: 100%;
	height: 100%;
}
.error-page .notFound {
	padding: 150px 0;
	overflow: hidden;
}
.error-page .notFound span {
	display: block;
	line-height: 60px;
	font-size: 18px;
	color: #8b7b8b;
}
.error-page .notFound span .link {
	color: #5cacee;
	font-size: 20px;
	cursor: pointer;
}
</style>