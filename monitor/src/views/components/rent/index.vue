<template>
    <div class="rent">
        <div class="header">
            <div class="search" style="display: inline-block" :rules="rules">
                <el-form ref="search" :model="search" :rules="rules" :inline="true">
                    <el-form-item label="">
                        城市：<el-select v-model="search.city" style="width:120px" placeholder="请选择城市" clearable filterable @clear="selectClear" @change="selectChange">
                        <el-option v-for="(item,index) in citys" :key="index" :label="item.label" :value="item.value" /></el-select>
                    </el-form-item>
                    <el-form-item label="">
                        区县：<el-input v-model="search.district" style="width:150px" placeholder="请输入区县"></el-input>
                    </el-form-item>
                    <el-form-item label="">
                        区域：<el-input v-model="search.area" style="width:150px" placeholder="请输入区域"></el-input>
                    </el-form-item>
                    <el-form-item label="" prop="startPrice">
                        单价：<el-input v-model="search.startPrice" style="width:150px" placeholder="请输入最低单价" @change="handleStartPriceChange"></el-input> - 
                    </el-form-item>
                    <el-form-item label="" prop="endPrice">
                        <el-input v-model="search.endPrice" style="width:150px" placeholder="请输入最高单价" @change="handleEndPriceChange"></el-input> 元/月
                    </el-form-item>  
                    <el-form-item label="" prop="startSize">
                        大小：<el-input v-model="search.startSize" style="width:150px" placeholder="请输入最小空间" @change="handleStartSizeChange"></el-input> - 
                    </el-form-item>
                    <el-form-item label="" prop="endSize">
                        <el-input v-model="search.endSize" style="width:150px" placeholder="请输入最大空间" @change="handleEndSizeChange"></el-input> 平米
                    </el-form-item>
                    <el-form-item label="">
                        布局：<el-input v-model="search.layout" style="width:150px" placeholder="请输入布局"></el-input>
                    </el-form-item>
                    <el-form-item label="">
                        描述：<el-input v-model="search.descs" style="width:300px" placeholder="请输入描述"></el-input>
                    </el-form-item>               
                    <el-form-item label="">               
                        时间：<el-date-picker v-model="time" type="datetimerange" @change="changeTime" value-format="yyyy-MM-dd HH:mm:ss" 
                        :default-time="['00:00:00', '23:59:59']" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
                    </el-form-item>   
                    <el-form-item label="">
                        <el-button type="primary" @click="tableDataInit(search);">搜索</el-button>
                    </el-form-item>    
                    <el-form-item label="">
                        <el-button type="primary" @click="clearData">重置</el-button>
                    </el-form-item>           
                </el-form>
            </div>
        </div>
        <component-table :data="tableData">
        </component-table>
    </div>
</template>

<script>
import { getTableData } from '@/api/rent'

export default {
    name: "rent",
    data () {
        return {
            show:false,
            tableDataAll:null,
            currentPage:1,
            optionText:'',
            time:[],
            price:[],
            search:{
                pageNum:1,//当前页
                pageSize:10,//每页显示条数
                city:'',
                date:'',
                area:'',
                descs:'',
                layout:'',
                size:'',
                price:'',
                district:'',
                time:'',
                source:'',
                startSize:'',
                endSize:'',
                startPrice:'',
                endPrice:'',
                startTime:'',
                endTime:'',
            },
            citys:[],
            rules: {
                startPrice: [
                    { validator: this.checkIsPositive, trigger: 'blur' },
                    { validator: this.validateStartPrice, trigger: 'blur' },
                ],
                endPrice: [
                    { validator: this.checkIsPositive, trigger: 'blur' },
                    { validator: this.validateEndPrice, trigger: 'blur' },
                ],
                startSize: [
                    { validator: this.checkIsPositive, trigger: 'blur' },
                    { validator: this.validateStartSize, trigger: 'blur' },
                ],
                endSize: [
                    { validator: this.checkIsPositive, trigger: 'blur' },
                    { validator: this.validateEndSize, trigger: 'blur' },
                ],
            },
            parentData:null,
            visible:false,
            tableData: {
                loading:false,
                border:true,
                // 请求回来的数据
                tableData:[],
                // 列
                tableLabel:[],
                // 分页
                page:{align:'right', currentChange:(currentPage) => {
                    this.$nextTick(() => {
                        this.search.pageNum = currentPage;
                        this.tableDataInit(this.search);
                    })
                }},
            },
        }
    },
    created(){
        this.labelInit();
        this.initTimeLimit();
        this.handleGetTableData();
    },
    computed:{
        codeList(){
            return this.$store.state.code.codeList.codeList;
        }
    },
    methods: {
        handleGetTableData(loading=true){
            loading && this.tableDataInit(this.search);
        },
        initTimeLimit(){
            this.time = [this.getDayStartTime(),this.getDayEndTime()];
            this.changeTime(this.time)
        },
        // 列初始化
        labelInit(){
            this.tableData.tableLabel = [
                {type:'index',title:'序号',fixed:'left',width:60,align:'center'},
                {prop:'city',title:'城市', width:140, tooltip:true},
                {prop:'district',title:'区县',width:140, tooltip:true},
                {prop:'area',title:'区域', width:140, tooltip:true},
                {prop:'descs',title:'描述',width:230, tooltip:true},
                {prop:'layout',title:'布局',width:140, tooltip:true},
                {prop:'size',title:'大小',width:140, tooltip:true},
                {prop:'price',title:'价格',width:140, tooltip:true},
                {prop:'time',title:'抓取时间',width:180, tooltip:true},
                {prop:'source',title:'数据来源', tooltip:true}
            ];
        },
        // 数据初始化
        tableDataInit(search) {
            this.tableData.loading = true;
            this.request(getTableData,search,this,data => {
                this.tableData.loading = false;
                this.tableData.tableData = data.rows;
                const res = this.codeList;
                // 数据展示列code转换
                for(var i=0,j=data.rows.length;i<j;i++){
                    data.rows[i].city = this.getFilterCodeData(res,'CityName',data.rows[i].city);
                    data.rows[i].source = this.getFilterCodeData(res,'DataSource',data.rows[i].source);
                }
                // 下拉框code转换
                if(this.citys.length == 0 ){
                    const cityName = this.getFilterCodeData(res,'CityName');
                    for(var i=0,j=cityName.length;i<j;i++){
                        var obj = {};
                        this.$set(obj,"value",cityName[i].value);
                        this.$set(obj,"label",cityName[i].name);
                        this.citys.push(obj);
                    }
                }
                this.tableData.page.total = data.totalRows;
            },false);
        },
        changeTime(val){
            if (val){
                this.search.startTime = val[0];
                this.search.endTime = val[1];
            }else{
                this.search.startTime = '';
                this.search.endTime = '';
            }
        },
        clearData(){
            this.search.city = '';
            this.search.startPrice = '';
            this.search.endPrice = '';
            this.search.startSize = '';
            this.search.endSize = '';
            this.search.district = '';
            this.search.area = '';
            this.search.descs = '';
            this.search.layout = '';
            this.search.startTime = '';
            this.search.endTime = '';
            this.time = [];
        },
        selectClear() {
            this.search.city = ''
            this.$forceUpdate()
        },
        selectChange(val) {
            this.search.city = val
            this.$forceUpdate()
        },
        // 关联校验单价输入值
        getFormData() {
            const ret = {};
            this.$refs.search.validate((valid) => {
                ret.valid = valid;
                ret.search = this.search;
            });
            return ret;
        },
        resetForm() {
            this.$refs.search.resetFields();
        },
        handleStartPriceChange() {
            this.$refs.search.validateField('endPrice');
        },
        handleEndPriceChange() {
            this.$refs.search.validateField('startPrice');
        },
        handleStartSizeChange() {
            this.$refs.search.validateField('endSize');
        },
        handleEndSizeChange() {
            this.$refs.search.validateField('startSize');
        },
        validateStartPrice(rule, value, callback) {
            const one = Number(value);
            const endPrice = Number(this.search.endPrice);
            if (!endPrice || one < endPrice) {
                return callback();
            }
            return callback(new Error('输入值不得高于上限'));
        },
        validateEndPrice(rule, value, callback) {
            const one = Number(value);
            const startPrice = Number(this.search.startPrice);
            if (!startPrice || one > startPrice) {
                return callback();
            }
            return callback(new Error('输入值不得低于下限'));
        },
        validateStartSize(rule, value, callback) {
            const one = Number(value);
            const endSize = Number(this.search.endSize);
            if (!endSize || one < endSize) {
                return callback();
            }
            return callback(new Error('输入值不得高于上限'));
        },
        validateEndSize(rule, value, callback) {
            const one = Number(value);
            const startSize = Number(this.search.startSize);
            if (!startSize || one > startSize) {
                return callback();
            }
            return callback(new Error('输入值不得低于下限'));
        },
    },
}
</script>

<style lang="css" scoped>

</style>