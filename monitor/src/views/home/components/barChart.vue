<template>
    <div class="home">
        <div class="barChart" ref="barChart" style="width: 750px;height:440px;"></div>
    </div>
</template>

<script> 
import * as echarts from 'echarts'
import { queryRuntimeSummary } from '@/api/runtimeSummary'

export default {
    name:'barChart',
    props:['timeRange','type'],
    data () {
        return {
            barSearch:{
                method: 'queryCityAvgHousePrice',
                timeRange: '',
                type: ''
            },
            barData:{
                cityList:[],
                cityData:[],
                priceData:[],
            }
        }
    },
    mounted() {
        this.getBarChartData(this.barSearch);
    },
    created(){
        this.getCityData();
    },
    computed:{
        codeList(){
            return this.$store.state.code.codeList.codeList;
        }
    },
    methods: {
        getFilterData(data,city){
            var dataFilter = data.filter((r) => {
                return r.city == city;
            });
            return dataFilter; 
        },
        getCityData(){
            const res = this.codeList;
            const cityName = this.getFilterCodeData(res,'CityName');
            for(var i=0,j=cityName.length;i<j;i++){
                var obj = {};
                this.$set(obj,"value",cityName[i].value);
                this.$set(obj,"label",cityName[i].name);
                this.barData.cityList.push(obj);
            }
        },
        getBarChartData(search){
            this.barSearch.timeRange = this.timeRange;
            this.barSearch.type = this.type;
            this.request(queryRuntimeSummary,search,this,data => {
                for(var i=0,j=this.barData.cityList.length;i<j;i++){
                    var name = this.barData.cityList[i].label;
                    var cityCode = this.barData.cityList[i].value;
                    try {
                        var value = JSON.parse(this.getFilterData(data,cityCode)[0].outputData).avgPrice;
                    } catch (error) {
                        var value = "0";
                    }
                    this.barData.cityData.push(name);
                    this.barData.priceData.push(value);
                }
                this.initBarChart();
            },false);
        },
        initBarChart(){
            var myChart = echarts.init(this.$refs.barChart);
            var option = this.barChartOption();
            option.xAxis[0].data = this.barData.cityData;
            option.series[0].data = this.barData.priceData;
            if(this.findArrayMaxCeil(this.barData.priceData) > 0){
                option.yAxis[0].max = this.findArrayMaxCeil(this.barData.priceData);
                option.yAxis[0].interval = this.findArrayMaxCeil(this.barData.priceData)/10;
            }else{
                option.yAxis[0].max = 10000;
                option.yAxis[0].interval = 1000;
            }
            myChart.setOption(option);
        },
        barChartOption(){
            var option = {
                title: {
                    show:true, 
                    text: '各城市平均房价概况',
                    x:'center', 
                    y:'top', 
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        crossStyle: {
                            color: '#999'
                        }
                    }
                },
                xAxis: [
                    {
                        type: 'category',
                        data: [],
                        axisPointer: {
                            type: 'shadow'
                        },
                        axisLabel: {
                            interval:0 
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '价格/元',
                        min: 0,
                        max: 10000,
                        interval: 1000,
                        axisLabel: {
                            formatter: '{value}'
                        }
                    },
                ],
                series: [
                    {
                        name: '价格',
                        type: 'bar',
                        data: []
                    },
                ]
            };
            return option;
        }

    },
}
</script>

<style lang="css" scoped>
.barChart{
    margin: 0 auto;
}
</style>