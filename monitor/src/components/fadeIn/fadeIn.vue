<template>
	<transition name="fade-transform" mode="out-in" appear>
		<slot name='fadeIn'></slot>
	</transition>
</template>
<script>
export default {
	name:'fadeIn'
}
</script>
<style lang="css" scoped>
/*.fade-enter,.fade-leave-to{*/
	/*transform: translateX(-10px);*/
	/*width:120%;*/
	/*opacity:0;*/
	/*margin-left:-10px;*/
	/*margin-right:-10px;*/
    /*overflow:hidden;*/
/*}*/
/*.fade-leave-to{
	transform: translateX(-10px);
	width: 100
	opacity:0
}*/
/*.fade-enter-active,.fade-leave-active{*/
/*	transition:all .4s ease .2s;*/
/*}*/

/* fade */
.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.28s;
}
.fade-enter,
.fade-leave-active {
    opacity: 0;
}
/* fade-transform */
.fade-transform-leave-active,
.fade-transform-enter-active {
    transition: all .5s;
}
.fade-transform-enter {
    opacity: 0;
    transform: translateX(-30px);
}
.fade-transform-leave-to {
    opacity: 0;
    transform: translateX(30px);
}
</style>