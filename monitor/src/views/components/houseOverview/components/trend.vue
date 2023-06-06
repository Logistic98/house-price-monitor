<template>
    <div class="home">
        <div class="trend" id="trend" style="width: 600px;height:600px;">
        </div>
    </div>
</template>

<script> 
import * as echarts from 'echarts'

export default {
    name:'trend',
    mounted() {
        this.trendChart();
    },
    methods: {
        trendChart(){
            var myChart = echarts.init(document.getElementById('trend'));
            var option = {
                xAxis: {
                    type: 'category',
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: [150, 230, 224, 218, 135, 147, 260],
                    type: 'line'
                }]
            };
            myChart.setOption(option);
        }

    },
}
</script>

<style lang="css" scoped>
.trend{
    margin: 0 auto;
}
</style>