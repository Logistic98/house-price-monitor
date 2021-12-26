<template>
    <div class="home">
        <home></home>
    </div>
</template>

<script>
import Home from './home'
export default {
    name:'index',
    components:{
        Home
    },
    props:{
        isSlot:{
            type:Boolean,
            default:false
        },
        text:{
            type:String,
            default:'删除'
        },
        tagStyle:{
           type:Object,
           default:function () {return {}}
        },
        trigger:{
            type:String,
            default:'click'
        },
        width:{
            type:Number,
            default:120
        },
        tag:{
            type:String,
            default:'button'
        },
        size:{
            type:String,
            default:'mini'
        },
        placement:{
            type:String,
            default:'top-end'
        },
        params:{
            type:[Object,Array,String,Boolean,Number],
            default: null
        }
    },
    methods:{
        handleCancel:function () {
            document.body.click();
            this.$emit('cancel',this.params);
        },
        handleOk:function () {
            document.body.click();
            this.$emit('ok',this.params);
        }
    }
}
</script>

<style lang="css" scoped>

</style>