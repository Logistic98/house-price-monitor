<template>
    <div class="home">
        <div class="lineChart" ref="lineChart" style="width: 750px;height:440px;">
        </div>
    </div>
</template>

<script> 
import * as echarts from 'echarts'
import { queryRuntimeSummary } from '@/api/runtimeSummary'

export default {
    name:'lineChart',
    props:['timeRange','type','city'],
    data () {
        return {
            lineSearch:{
                method: 'queryTimeAvgHousePrice',
                timeRange: '',
                type: ''
            },
            lineData:{
                priceData:[],
                dateData:[],
            }
        }
    },
    mounted() {
        this.getLineChartData(this.lineSearch);
    },
    created(){
    },
    methods: {
        getFilterData(data,city){
            var dataFilter = data.filter((r) => {
                return r.city == city;
            });
            return dataFilter; 
        },
        initChartDefaultData(){
            var length = 365;
            if(this.timeRange == 'month'){
                length = 30;
            }else if(this.timeRange == 'week'){
                length = 7;
            }
            for(var i=length-1,j=0;i>=j;i--){
                this.lineData.priceData.push(0);
                this.lineData.dateData.push(this.getDay(-i))
            }
        },
        getLineChartData(search){
            this.lineSearch.timeRange = this.timeRange;
            this.lineSearch.type = this.type;
            this.request(queryRuntimeSummary,search,this,data => {
                var filterData = this.getFilterData(data,this.city);
                if(filterData.length == 0){
                    this.initChartDefaultData();
                }else{
                    for(var i=0,j=filterData.length;i<j;i++){
                        var price = JSON.parse(filterData[i].outputData).avgPrice;
                        var date = JSON.parse(filterData[i].outputData).date;
                        date = date.substring(0,4) + "-" + date.substring(4,6)  + "-" + date.substring(6,8);
                        this.lineData.priceData.push(price);
                        this.lineData.dateData.push(date);
                    }
                }
                this.initLineChart();
            },false);
        },
        initLineChart(){
            var myChart = echarts.init(this.$refs.lineChart);
            var option = this.lineChartOption();
            option.xAxis[0].data = this.lineData.dateData;
            option.series[0].data = this.lineData.priceData;
            if(this.findArrayMaxCeil(this.lineData.priceData) > 0){
                option.yAxis[0].max = this.findArrayMaxCeil(this.lineData.priceData);
                option.yAxis[0].interval = this.findArrayMaxCeil(this.lineData.priceData)/10;
            }else{
                option.yAxis[0].max = 10000;
                option.yAxis[0].interval = 1000;
            }
            myChart.setOption(option);
        },
        lineChartOption(){
            var option = {
                title: {
                    show:true, 
                    text: '近期房价波动概况',
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
                        type: 'line',
                        data: []
                    }
                ]
            };
            return option;
        }

    },
}
</script>

<style lang="css" scoped>
.lineChart{
    margin: 0 auto;
}
</style>