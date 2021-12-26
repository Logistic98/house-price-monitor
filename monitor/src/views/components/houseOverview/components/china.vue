<template>
    <div class="home">
        <div class="chinaMap" id="chinaMap" style="width: 600px;height:600px;">
        </div>
    </div>
</template>

<script> 

import * as echarts from 'echarts'
import '../../../../../src/assets/js/china.js'

export default {
    name:'china-map',
    mounted() {
        this.chinaMap();
    },
    methods: {
        chinaMap(){
            var data = [
                {name: '北京', value: [116.46,39.92]},
                {name: '成都', value: [104.06,30.67]},
                {name: '重庆', value: [106.54,29.59]},
                {name: '长沙', value: [113,28.21]},
                {name: '东莞', value: [113.75,23.04]},
                {name: '大连', value: [121.62,38.92]},
                {name: '佛山', value: [113.11,23.05]},
                {name: '广州', value: [113.23,23.76]},
                {name: '杭州', value: [120.19,29.96]},
                {name: '合肥', value: [117.27,31.86]},
                {name: '济南', value: [117,36.65]},
                {name: '南京', value: [118.78,32.04]},
                {name: '青岛', value: [120.33,36.07]},
                {name: '上海', value: [121.48,31.22]},
                {name: '深圳', value: [114.07,22.62]},
                {name: '苏州', value: [120.62,32.62]},
                {name: '沈阳', value: [123.38,41.8]},
                {name: '天津', value: [117.2,39.13]},
                {name: '武汉', value: [114.31,30.52]},
                {name: '厦门', value: [118.1,24.46]},
                {name: '烟台', value: [121.39,37.52]},
            ]
            
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('chinaMap'));
            
            // 指定图表的配置项和数据
            var option = {
                tooltip : {
                    trigger: 'none',
                },
                geo: {
                    map: 'china',
                    aspectScale: 0.75, //长宽比
                    layoutCenter: ['50%', '50%'],
                    layoutSize: 600, 
                    itemStyle: {
                        // 定义地图样式
                        normal: {					// 普通状态下的样式
                            areaColor: '#3C9AFF',
                            borderColor: '#fff',
                            show: false
                        },
                        emphasis: {					// 高亮状态下的样式
                            areaColor: '#3C9AFF',
                            borderColor: '#fff',
                            show: false
                        }
                    },
                    select:{
                        label:{
                            show: false
                        },
                        itemStyle:{
                            color: '#fff'
                        }
                    },
                    label:{ //不显示省份名称
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: false
                        }
                    },
                },
                series : [
                    {
                        coordinateSystem: 'geo', // series坐标系类型
                        type: 'scatter', //散点图
                        mapType: 'china',
                        roam: false,
                        data: data,
                        symbol:'path://M512 736.981333L775.68 896l-69.76-299.904L938.666667 394.410667l-306.816-26.325334L512 85.333333 392.149333 368.085333 85.333333 394.410667l232.746667 201.685333L248.32 896z',
                        symbolSize: 12,
                        encode: {
                            value: 2
                        },
                        showEffectOn: 'render',
                        rippleEffect: {
                            brushType: 'stroke'
                        },
                        hoverAnimation: true,
                        label: {
                            formatter: '{b}',
                            position: 'right',
                            lineHeight : 15,
                            show: true,
                            emphasis:{
                                textStyle:{
                                    color: '#EC652A' //设置活动状态下字体样式，会跟随散点高亮
                                }
                            }
                        },
                        itemStyle: {
                            normal: {			// 散点图标普通状态下的样式
                                color: '#333'
                            },
                            emphasis: {			// 散点图标高亮状态下的样式
                                color:'#EC652A',
                            },
                            shadowBlur: 10,
                            shadowColor: '#333'
                        },
                        zlevel: 1,
                    }
                ]
            };
            myChart.setOption(option);
        }
    },
}
</script>

<style lang="css" scoped>
.chinaMap{
    margin: 0 auto;
}
</style>