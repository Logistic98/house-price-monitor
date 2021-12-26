<template>
    <div class="home">
        <el-row>
            <el-col :span="24">
                <div class="grid-content">
                    <el-col :span="8">
                            当前位置：<el-select v-model="city" placeholder="请选择城市" filterable @change="selectChange">
                            <el-option v-for="(item,index) in cities" :key="index" :label="item.label" :value="item.value" /></el-select>
                    </el-col>
                    <el-col :span="8"><div class="text-center"><b>房价监测仪表盘</b></div></el-col>
                    <el-col :span="8"><div class="text-right">当前时间：{{dateFormat(date)}}</div></el-col>
                </div>
            </el-col>
        </el-row>
        <el-row :gutter="40" class="panel-group">
            <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
                <div class="card-panel card-panel-blue" @click="changeType('new_house')">
                    <div class="card-panel-description">
                        <div class="card-panel-text">新楼盘</div>
                        <div class="card-panel-num">{{cardData.newHouseCount}}套</div>
                    </div>
                </div>
            </el-col>
            <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
                <div class="card-panel card-panel-green" @click="changeType('second_hand')">
                    <div class="card-panel-description">
                        <div class="card-panel-text">二手房</div>
                        <div class="card-panel-num">{{cardData.secondHandCount}}套</div>
                    </div>
                </div>
            </el-col>
            <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
                <div class="card-panel card-panel-orange" @click="changeType('community')">
                    <div class="card-panel-description">
                        <div class="card-panel-text">小区</div>
                        <div class="card-panel-num">{{cardData.communityCount}}个</div>
                    </div>
                </div>
            </el-col>
            <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
                <div class="card-panel card-panel-purple" @click="changeType('rent')">
                    <div class="card-panel-description">
                        <div class="card-panel-text">租房</div>
                        <div class="card-panel-num">{{cardData.rentCount}}套</div>
                    </div>
                </div>
            </el-col>
        </el-row>
        <el-tabs v-model="timeRange" type="card" @tab-click="changePane">
            <el-tab-pane label="全部" name="all">
                <el-row :gutter="20">
                    <el-col :span="12"><div class="grid-content">
                        <lineChart ref="lineChart" :timeRange='timeRange' :type='type' :city='city' :key="timeSymbol"/>
                    </div></el-col>
                    <el-col :span="12"><div class="grid-content">
                        <barChart ref="barChart" :timeRange='timeRange' :type='type' :key="timeSymbol"/>
                    </div></el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="最近一年" name="year">
                <el-row :gutter="20">
                    <el-col :span="12"><div class="grid-content">
                        <lineChart ref="lineChart" :timeRange='timeRange' :type='type' :city='city' :key="timeSymbol"/>
                    </div></el-col>
                    <el-col :span="12"><div class="grid-content">
                        <barChart ref="barChart" :timeRange='timeRange' :type='type' :key="timeSymbol"/>
                    </div></el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="最近一月" name="month">
                <el-row :gutter="20">
                    <el-col :span="12"><div class="grid-content">
                        <lineChart ref="lineChart" :timeRange='timeRange' :type='type' :city='city' :key="timeSymbol"/>
                    </div></el-col>
                    <el-col :span="12"><div class="grid-content">
                        <barChart ref="barChart" :timeRange='timeRange' :type='type' :key="timeSymbol"/>
                    </div></el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="最近一周" name="week">
                <el-row :gutter="20">
                    <el-col :span="12"><div class="grid-content">
                        <lineChart ref="lineChart" :timeRange='timeRange' :type='type' :city='city' :key="timeSymbol"/>
                    </div></el-col>
                    <el-col :span="12"><div class="grid-content">
                        <barChart ref="barChart" :timeRange='timeRange' :type='type' :key="timeSymbol"/>
                    </div></el-col>
                </el-row>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
import barChart from './components/barChart.vue'
import lineChart from './components/lineChart.vue'
import { queryRuntimeSummary } from '@/api/runtimeSummary'

export default {
    name:'home-index',
    components:{
        barChart,
        lineChart
    },
    data() {
        return {
            activeName: 'all',
            timeSymbol: '',
            date: new Date(),
            city: 'yt',
            cities:[],
            timeRange:'all',
            type:'new_house',
            cardSearch:{
                method: 'queryHouseCount',
                city: '',
            },
            cardData:{
                newHouseCount:'',
                communityCount:'',
                secondHandCount:'',
                rentCount:'',
            },
            
        };
    },
    created(){
        //在页面加载时读取sessionStorage里的状态信息
        if (sessionStorage.getItem("codeList")) {
            this.$store.replaceState(
                Object.assign(
                {},
                this.$store.state,
                JSON.parse(sessionStorage.getItem("codeList"))
                )
            );
        }
        //在页面刷新时将vuex里的信息保存到sessionStorage里
        window.addEventListener("beforeunload", () => {
            sessionStorage.setItem("codeList", JSON.stringify(this.$store.state));
        });
        this.dropdownInit();
        this.getCardData(this.cardSearch);
    },
    mounted(){
        let _this = this; // 声明一个变量指向Vue实例this，保证作用域一致
        this.timer = setInterval(() => {
            _this.date = new Date(); // 修改数据date
        }, 1000)
    },
    beforeDestroy() {
        if (this.timer) {
            clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
        }
    },
    computed:{
        codeList(){
            return this.$store.state.code.codeList.codeList;
        }
    },
    methods: {
        dateFormat(time) {
            var date=new Date(time);
            var year=date.getFullYear();
            // 在日期格式中，月份是从0开始的，因此要加1。使用三元表达式在小于10的前面加0，以达到格式统一。
            var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
            var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
            var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
            var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
            var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
            // 拼接
            return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
        },
        dropdownInit() {
            const res = this.codeList;
            const cityName = this.getFilterCodeData(res,'CityName');
            for(var i=0,j=cityName.length;i<j;i++){
                var obj = {};
                this.$set(obj,"value",cityName[i].value);
                this.$set(obj,"label",cityName[i].name);
                this.cities.push(obj);
            }
        },
        selectChange(val) {
            this.city = val;
            this.getCardData(this.cardSearch);
            this.timeSymbol = new Date().getTime()
            this.$forceUpdate();
        },
        getFilterData(data,type){
            var codeFilter = data.filter((r) => {
                return r.type == type;
            });
            return codeFilter; 
        },
        getCardData(search){
            this.cardSearch.city = this.city;
            this.request(queryRuntimeSummary,search,this,data => {
                try {
                    this.cardData.newHouseCount = this.convertValue(JSON.parse(this.getFilterData(data,"new_house")[0].outputData).count);
                } catch (error) {
                    this.cardData.newHouseCount = "0";
                }
                try {
                    this.cardData.communityCount = this.convertValue(JSON.parse(this.getFilterData(data,"community")[0].outputData).count);
                } catch (error) {
                    this.cardData.communityCount = "0";
                }
                try {
                    this.cardData.secondHandCount = this.convertValue(JSON.parse(this.getFilterData(data,"second_hand")[0].outputData).count);
                } catch (error) {
                    this.cardData.secondHandCount = "0";
                }
                try {
                    this.cardData.rentCount = this.convertValue(JSON.parse(this.getFilterData(data,"rent")[0].outputData).count);
                } catch (error) {
                    this.cardData.rentCount = "0";
                }       
            },false);
        },
        changeType(val){
            this.type = val;
            this.timeSymbol = new Date().getTime()
        },
        changePane(tab){
            this.timeRange = tab.name;
            this.timeSymbol = new Date().getTime()
        },
    }
}
</script>

<style lang="css" scoped>
.el-row {
    margin-bottom: 20px;
}
.el-col {
    border-radius: 4px;
}
.grid-content {
    border-radius: 4px;
    min-height: 36px;
}
.text-center{
    text-align: center
}
.text-right{
    text-align: right
}

.panel-group {
    margin-top: 18px;
}
.card-panel-col {
    margin-bottom: 32px;
}
.card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);
}
.card-panel-blue{
    background:#3fa1ff;
}
.card-panel-green{
    background:#3ecbcb;
}
.card-panel-orange{
    background:#ff7f50;
}
.card-panel-purple{
    background:#9860e5;
}
.card-panel-description {
    float: left;
    font-weight: bold;
    margin: 26px;
}
.card-panel-text {
    width: 80px;
    float: left;
    line-height: 18px;
    color: #fff;
    font-size: 22px;
    margin-bottom: 12px;
}
.card-panel-num {
    width: 200px;
    float: right;
    font-size: 32px;
    text-align: right;
    padding-top: 20px;
}
</style>