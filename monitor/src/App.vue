<template>
	<div id="app">
		<!-- <router-view></router-view> -->
		<keep-alive>
			<router-view v-if="$route.meta.cache"/>
		</keep-alive>
		<router-view v-if="!$route.meta.cache"/>
	</div>
</template>

<script>
export default {
	name:'App',
}
</script>

<style lang="less">
html,body,#app{
	margin: 0;
  	width: 100%;
  	height: 100%;
  	overflow: hidden;
}
</style>