<template>
    <div class="home">
        <el-form ref="search" :inline="true" :rules="rules" :model="search">
            <el-form-item label="" prop="businessLoan">
                商业贷款金额：<el-input v-model="search.businessLoan" style="width:150px" placeholder="请输入贷款金额"></el-input> 万
            </el-form-item>
            <el-form-item label="" prop="businessRate">
                商业贷款利率：<el-select v-model="search.businessRate" filterable allow-create default-first-option placeholder="请选择或输入贷款利率">
                    <el-option v-for="item in businessRateOptions" :key="item.value" :label="item.value" :value="item.value">
                    </el-option></el-select> %                       
            </el-form-item>
            <el-form-item label="" prop="providentLoan">
                公积金贷款金额：<el-input v-model="search.providentLoan" style="width:150px" placeholder="请输入贷款金额"></el-input> 万
            </el-form-item>
            <el-form-item label="" prop="providentRate">
                公积金贷款利率：<el-select v-model="search.providentRate" filterable allow-create default-first-option placeholder="请选择或输入贷款利率">
                    <el-option v-for="item in providentRateOptions" :key="item.value" :label="item.value" :value="item.value">
                    </el-option></el-select> %                       
            </el-form-item>
            <el-form-item label="" prop="year">
                贷款年限：<el-input v-model="search.year" style="width:150px" placeholder="请输入贷款年限"></el-input> 年
            </el-form-item>
            <el-form-item label="">
                <el-button type="primary" @click="calculateData(search);pushFixPieData();pushCapitalPieData();">计算</el-button>
            </el-form-item>    
            <el-form-item label="">
                <el-button type="primary" @click="clearData">重置</el-button>
            </el-form-item>              
        </el-form>
        <el-row :gutter="20">
            <el-col :span="12"> 
                <div class="grid-content bg-purple">
                    <el-col :span="24">
                        <div class="grid-content bg-purple-light">
                            <h3>{{calData[0].type}}</h3>
                        </div>
                    </el-col>
                    <el-col :span="24">
                        <div ref="fixPieChart" class="grid-content bg-purple-light" style="width: 675px;height:280px;"></div>
                    </el-col>
                    <el-col :span="24">
                        <div class="grid-content bg-purple-light">
                            <el-descriptions title="计算结果">
                                <el-descriptions-item label="贷款总额">{{calData[0].loan}}万元</el-descriptions-item>
                                <el-descriptions-item label="还款总额">{{calData[0].total}}万元</el-descriptions-item>
                                <el-descriptions-item label="支付利息">{{calData[0].interest}}万元</el-descriptions-item>
                                <el-descriptions-item label="贷款年限">{{calData[0].years}}年（{{calData[0].months}}月）</el-descriptions-item>
                                <el-descriptions-item label="每月还款">{{calData[0].repaymentMonthly}}元</el-descriptions-item>
                            </el-descriptions>
                            <font class="alert-text">建议您的月收入流水须大于{{calData[0].incomeMonthly}}元</font>
                        </div>
                    </el-col>
                    <el-col :span="24">
                        <div class="grid-content bg-purple-light">
                            <el-divider></el-divider>
                            <div :key="0" class="text item">流水要求：收入月流水 > 每月还款（月供）x2.</div>
                            <div :key="1" class="text item">等额本息：每月按相等的金额还贷款本息，其中本金逐月递增，利息逐月递减，月还款额不变。</div>
                            <div :key="2" class="text item">计算公式：
                                <font class="dialog-text" @click="fixFormulaDialog = true">点击查看</font>
                                <el-dialog title="等额本息计算公式" :visible.sync="fixFormulaDialog" width="40%">
                                    <img src="../../../../../src/assets/images/mortageCal/fixFormula.png" width="100%">
                                </el-dialog>
                            </div>
                        </div>
                    </el-col>
                </div>
            </el-col>
            <el-col :span="12">
                <div class="grid-content bg-purple">
                    <el-col :span="24">
                        <div class="grid-content bg-purple-light">
                            <h3>{{calData[1].type}}</h3>
                        </div>
                    </el-col>
                    <el-col :span="24">
                        <div ref="CapitalPieChart" class="grid-content bg-purple-light" style="width: 675px;height:280px;"></div>
                    </el-col>
                    <el-col :span="24">
                        <div class="grid-content bg-purple-light">
                            <el-descriptions title="计算结果">
                                <el-descriptions-item label="贷款总额">{{calData[1].loan}}万元</el-descriptions-item>
                                <el-descriptions-item label="还款总额">{{calData[1].total}}万元</el-descriptions-item>
                                <el-descriptions-item label="支付利息">{{calData[1].interest}}万元</el-descriptions-item>
                                <el-descriptions-item label="贷款年限">{{calData[1].years}}年（{{calData[1].months}}月）</el-descriptions-item>
                                <el-descriptions-item label="首月还款">{{calData[1].repaymentMonthly}}元（每月还款递减）
                                    <font class="dialog-text" @click="monthlyDataDialog = true">查看详情 ></font>
                                    <el-dialog title="每月还款明细" :visible.sync="monthlyDataDialog" width="60%">
                                            <el-table :data="calData[1].monthlyData" style="width: 100%" max-height="515">
                                            <el-table-column type="index" label="期数" width="100"></el-table-column>
                                            <el-table-column prop="monthlyRepay" label="月供本金" width="180"></el-table-column>
                                            <el-table-column prop="monthlyInterest" label="月供利息" width="180"></el-table-column>
                                            <el-table-column prop="monthlyAll" label="月供"></el-table-column>
                                            <el-table-column prop="surplus" label="剩余本金"></el-table-column>
                                        </el-table>
                                    </el-dialog>
                                </el-descriptions-item>
                            </el-descriptions>
                            <font class="alert-text">建议您的月收入流水须大于{{calData[1].incomeMonthly}}元</font>
                        </div>
                    </el-col>
                    <el-col :span="24">
                        <div class="grid-content bg-purple-light">
                            <el-divider></el-divider>
                            <div :key="0" class="text item">流水要求：收入月流水 > 每月还款（月供）x2.</div>
                            <div :key="1" class="text item">等额本金：每月按相等的金额偿还贷款本金，其中本金保持相同，利息逐月递减，月还款额越来越少。</div>
                            <div :key="2" class="text item">计算公式：
                                <font class="dialog-text" @click="capitalFormulaDialog = true">点击查看</font>
                                <el-dialog title="等额本金计算公式" :visible.sync="capitalFormulaDialog" width="40%">
                                    <img src="../../../../../src/assets/images/mortageCal/capitalFormula.png" width="100%">
                                </el-dialog>
                            </div>
                        </div>
                    </el-col>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
    name:'mix',
    data() {
      return {
        fixFormulaDialog: false,
        capitalFormulaDialog: false,
        monthlyDataDialog: false,
        search:{
            businessLoan:'',
            businessRate:'',
            providentLoan:'',
            providentRate:'',
            year:'',
        },
        businessRateOptions: [],
        providentRateOptions: [],
        rules: {
            businessLoan: [
                {required: true, message: '请输入商业贷款金额', trigger: 'blur'},                    
                {validator: this.checkIsPositive, trigger: 'blur'}
            ],
            businessRate: [
                {required: true, message: '请输入商业贷款利率', trigger: 'blur'},
                {validator: this.checkIsPositive, trigger: 'blur'}
            ],
            providentLoan: [
                {required: true, message: '请输入公积金贷款金额', trigger: 'blur'},
                {validator: this.checkIsPositive, trigger: 'blur'}
            ],
            providentRate: [
                {required: true, message: '请输入公积金贷款利率', trigger: 'blur'},                    
                {validator: this.checkIsPositive, trigger: 'blur'}
            ],
            year: [
                {required: true, message: '请输入贷款年限', trigger: 'blur'},
                {validator: this.checkIsPositive, trigger: 'blur'}
            ]
        },   
        calData:[],
        fixIncomeMonthly: '',
        capitalIncomeMonthly: ''
      };
    },
    mounted() {
        this.pushFixPieData();
        this.pushCapitalPieData();
    },
    created(){
        this.InitRateOptions();     
        this.calculateData(this.search);
    },
    methods: {
        InitRateOptions(){
            let businessBaseRate = 4.9;  
            let providentBaseRate = 3.25; 
            var start = 0.8;
            var end = 1.3;
            var space = 0.05;
            var length = this.div(this.sub(end,start),space);
            // 商业贷款利率
            for(var i=0,j=length;i<j;i++){
                var obj = {};
                var magnification = this.add(start,this.mul(space,i));
                var interestRate = this.mul(businessBaseRate,magnification)
                this.$set(obj,"value",interestRate);
                this.businessRateOptions.push(obj);
            }
            // 公积金贷款利率
            for(var i=0,j=length;i<j;i++){
                var obj = {};
                var magnification = this.add(start,this.mul(space,i));
                var interestRate = this.mul(providentBaseRate,magnification)
                this.$set(obj,"value",interestRate);
                this.providentRateOptions.push(obj);
            }
        },
        calculateData(search){
            const loan = Number.parseFloat(this.add(this.search.businessLoan,this.search.providentLoan));
            const businessPart = this.mul(this.div(this.search.businessLoan,loan),this.search.businessRate);
            const providentPart = this.mul(this.div(this.search.providentLoan,loan),this.search.providentRate);
            const rate = Number.parseFloat(this.add(businessPart,providentPart));
            const year = Number.parseFloat(search.year);
            const fixCalc = this.fixCalc(loan, year, rate);
            const capitalCalc = this.capitalCalc(loan, year, rate);
            let calculateData = [];
            calculateData.push(fixCalc);
            calculateData.push(capitalCalc);
            this.calData = calculateData;
        },
        pushFixPieData(){
            let fixData = [];
            var fixLoanObj = {};
            var fixInterestObj = {};
            this.$set(fixLoanObj,"name","贷款总额");
            this.$set(fixLoanObj,"value",this.calData[0].loan);  
            this.$set(fixInterestObj,"name","支付利息");
            this.$set(fixInterestObj,"value",this.calData[0].interest);
            fixData.push(fixLoanObj);
            fixData.push(fixInterestObj);
            this.initFixPieChart(fixData);
        },
        pushCapitalPieData(){
            let capitalData = [];
            var capitalLoanObj = {};
            var capitalInterestObj = {};
            this.$set(capitalLoanObj,"name","贷款总额");
            this.$set(capitalLoanObj,"value",this.calData[1].loan);  
            this.$set(capitalInterestObj,"name","支付利息");
            this.$set(capitalInterestObj,"value",this.calData[1].interest);
            capitalData.push(capitalLoanObj);
            capitalData.push(capitalInterestObj);
            this.initCapitalPieChart(capitalData);
        },
        pieChartOption(){
            var option = {
                color: ["#3FA1FF", "#ff6666"],
                tooltip: {
                    trigger: 'item',
                    formatter: '{b}：{c}万({d}%)'
                },
                legend: {
                    textStyle: {
                        fontSize: 15,
						color:'#666666'
					},
					orient: 'vertical',  // 垂直显示
					icon: 'circle',
					right: 150,
					top: 120
                },
                series: [
                    {
                        type: 'pie',
                        center: ['35%', '50%'],
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        labelLine: {
                            show: false
                        },
                        data:[
                            {value: 0, name: '贷款总额'},
                            {value: 0, name: '支付利息'}
                        ]                       
                    }
                ]
            }
            return option;
            
        },
        initFixPieChart(data){
            var myChart = echarts.init(this.$refs.fixPieChart);
            var option = this.pieChartOption();
            if(data[0].value != "NaN" && data[1].value != "NaN"){
                option.series[0].data = data;
            }
            myChart.setOption(option);
        },
        initCapitalPieChart(data){
            var myChart = echarts.init(this.$refs.CapitalPieChart);
            var option = this.pieChartOption();
            if(data[0].value != "NaN" && data[1].value != "NaN"){
                option.series[0].data = data;
            }
            myChart.setOption(option);
        },
        clearData(){
            this.search.businessLoan = '';
            this.search.businessRate = '';
            this.search.providentLoan = '';
            this.search.providentRate = '';
            this.search.year = '';
        },
        // 等额本息计算方式
        fixCalc(loan, year, rate) {
            let oldLoan = loan;   
            loan = this.mul(loan,10000); 
            let months = Number.parseInt(this.mul(year,12)); 
            rate = this.div(rate,100);
            const rateMounth = this.div(rate,12);
        
            const a = Math.pow(this.add(rateMounth,1),months);  
            const b = this.mul(this.mul(loan,rateMounth),a);
            const c = this.sub(a,1);

            const repaymentMonthly = this.div(b,c);
            const interest = this.div(this.sub(this.mul(repaymentMonthly,months),loan),10000);
            const total = this.add(oldLoan,interest);
            const incomeMonthly = this.mul(repaymentMonthly,2);

            return {
                type: "等额本息",
                loan: oldLoan.toFixed(2), // 总贷款额
                total: total.toFixed(2), // 总还款额
                interest: interest.toFixed(2), // 总利息
                months: months.toString(), // 贷款总月份数
                years: year.toString(), // 贷款年限
                repaymentMonthly: repaymentMonthly.toFixed(2), // 每月月供额
                incomeMonthly: Number.parseInt(incomeMonthly), // 月收入建议
                monthlyData: [] //具体每月还款额（略）
            }
        },
        // 等额本金计算方式
        capitalCalc(loan, year, rate) {
           let oldLoan = loan;   
            loan = this.mul(loan,10000); 
            let months = Number.parseInt(this.mul(year,12)); 
            rate = this.div(rate,100);
            const rateMounth = this.div(rate,12);

            const a = this.div(loan,months);
            const b = this.mul(loan,rateMounth);
            const c = this.add(rateMounth,1);
            const d = this.add(this.add(a,b),(this.mul(a,c)));
            const e = this.div(loan,months);
            const f = this.mul(loan,rateMounth);
            
            const interest = this.div(this.sub(this.mul(this.div(d,2),months),loan),10000); // 总利息
            const total = this.add(oldLoan,interest); // 总还款额
            const repaymentMonthly =  this.add(e,f);
            const incomeMonthly = this.mul(repaymentMonthly,2);

            let calcDataArray = [];
            for(let i=0;i<months;i++){
                calcDataArray.push(this.calc(i,a,loan,rateMounth));
            }

            return {
                type: "等额本金",
                loan: oldLoan.toFixed(2), // 总贷款额
                total: total.toFixed(2), // 总还款额
                interest: interest.toFixed(2), // 总利息
                months: months.toString(), // 贷款总月份数
                years: year.toString(), // 贷款年限
                repaymentMonthly: repaymentMonthly.toFixed(2), // 每月月供额
                incomeMonthly: Number.parseInt(incomeMonthly), // 月收入建议
                monthlyData: calcDataArray, // 具体每月还款额
            }
        },
        // 计算每月具体还款额（等额本金计算方式调用）
        calc(n,a,loan,rateMounth) {
            let monthlyRepay = a;
            const g = this.sub(loan,this.mul(a,this.sub(n,1)));
            const h = this.mul(g,rateMounth);
            let monthlyInterest = h;
            let monthlyAll = this.add(monthlyRepay,monthlyInterest);
            let surplus = this.sub(loan,this.mul(a,n));

            return {
                monthlyRepay: monthlyRepay.toFixed(2),  // 月供本金
                monthlyInterest: monthlyInterest.toFixed(2), // 月供利息
                monthlyAll: monthlyAll.toFixed(2), // 月供 = 月供本金 + 月供利息
                surplus: Number.parseFloat(surplus).toFixed(2) // 剩余本金
            }
        },
    }
};
</script>

<style lang="css" scoped>
.text {
    font-size: 14px;
}
.item {
    padding: 8px 0;
}
.box-card {
    width: 675px;
}
.dialog-text{
    color: #409EFF;
    cursor:pointer;
}
.alert-text{
    color: #E6A23C;
    font-size: 14px;
}
</style>