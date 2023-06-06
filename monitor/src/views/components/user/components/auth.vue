<template>
    <div class="user-auth">
        <div class="transfer">
            <el-transfer ref="transfer" v-model="ownRoleIds" filterable
                :titles="['角色列表', '拥有角色']"
                :props="{key: 'id',label: 'name'}"
                :data="data">
            </el-transfer>
        </div>
        <div class="footer">
            <el-button type="primary" size="small" @click="submit">提交</el-button>
        </div>
    </div>
</template>
<script>
import { getPermissionsIds,updatePermissionsIds } from '@/api/user'

export default {
    name:'user-auth',
    data() {
        return {
            row:[],
            data: [],
            ownRoleIds: []
        };
    },
    methods:{
        currentData(params){
            if (params){
                this.row = params;
                this.request(getPermissionsIds,params.id,this,data => {
                    this.ownRoleIds = data.ownRoleIds;
                    this.data = data.allRole;
                },false);
            }
        },
        submit(){
            this.request(updatePermissionsIds,{userId:this.row.id,roleIds:this.ownRoleIds},this,data => this.$emit('handleGetTableData',false));
        }
    }
}
</script>
<style lang="css" scoped>
.user-auth{
    overflow: hidden;
}
.user-auth .transfer{
    display: flex;
    justify-content: center;
}
.user-auth .footer{
    text-align: center;
    padding-top: 20px;
}
</style>