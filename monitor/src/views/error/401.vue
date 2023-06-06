<template>
	<div class="notAuth">
		<h1>抱歉，你没有权限访问</h1>
		<div class="img" align="center">
			<img src="@/assets/images/401.jpg" alt="" width="45%">
		</div>
	</div>
</template>

<script>
export default {
	name:'NotAuth'
}
</script>

<style lang="css" scoped>
.notAuth{
	padding-top: 5%;
	padding-bottom: 8%;
	background: #fff;
	/*width: 100%;*/
	/*height: 100%;*/
	/*overflow: hidden;*/
}
.notAuth > h1{
	padding: 32px;
	font-size: 32px;
	text-align: center;
}
</style>