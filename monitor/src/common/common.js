import { getCodeData } from '@/api/code'
/************************* 公共函数文件(会被加到 vue 实例上) *************************/
export default {
    /**
     * 发送请求统一处理
     * @param method   方法
     * @param params   参数（多个参数，用数组传递，如：[参数1，参数2]）
     * @param _this    vue实例
     * @param success  回调函数
     * @param message  信息提示（为 true表示提示请求回来的提示，false表示不提示，字符串表示提示传递的信息）
     * @param fail     失败回调 (可能是自定义提示信息，可能是回调函数)
     * @param error    异常回调
     */
    request:(method,params,_this,success,message=true,fail,error) => {
        return method(params).then(res => {
            _this.jumpUrl(res,_this,data => {
                success && success(data);
                if (data.msg){
                    if (message == true)return _this.success(data.msg);
                    if (typeof(message)=='string')return _this.success(message);
                }
                //return (data.msg||message) && _this.success(typeof(message)=='string'?message:data.msg);
            },fail);
        }).catch(err => {
            console.log('%c ! Send Request Error','background:#000;color:#bada55',err);
            !error ? _this.error('服务异常') : (typeof(error) == 'string' ? _this.error(error) : error(err));
        });
    },
    /**
     * 表单提交统一处理
     * @param method      请求方法
     * @param form        表单对象
     * @param _this       vue实例
     * @param success     成功回调
     * @param isFormClear 是否清空表单 (添加后清空，编辑后不清空)
     * @param fail        失败回调 (可能是自定义提示信息，可能是回调函数)
     * @param error       异常回调
     */
    handleSubmit:(method,form,_this,success,isFormClear=false,fail,error) => {
        _this.loading = true;
        form.validate(valid => {
            if (!valid){
                return _this.loading = false;
            }
            method(form.model).then(res => {
                !fail && (_this.loading = false);
                _this.jumpUrl(res,_this,data => {
                    isFormClear && form.resetFields();
                    data.msg && _this.success(data.msg);
                    return success && success(data);
                },fail);
            }).catch(err => {
                console.log('%c ! Send Form error','background:#000;color:#bada55',err);
                !error ? _this.error('服务异常') : (typeof(error) == 'string' ? _this.error(error) : error(err));
            });
        });
    },
    /**
     * 页面跳转
     * @param res 请求返回的数据对象
     * @param _this vue实例
     * @param success 成功回调
     * @param error 失败回调 (可能是自定义提示信息，可能是回调函数)
     */
    jumpUrl:(res,_this,success,error) => {
        res = res.data ? res.data : res;
        if (res.code == 3){// 无权限
            return _this.$router.push('/401');
        }else if (res.code == 1){// 成功
            return success && success (res.data ? res.data : res);
        }else if (res.code == 0){// 失败 (可能是自定义提示信息，可能是回调函数)
            return !error ? _this.error(res.msg) : (typeof(error) == 'string' ? _this.error(error) : error(res));
        } else {
            _this.error(res.msg,() => {
                _this.$ls.clear();
                _this.$store.dispatch('clearLoginOut');
                _this.$router.push('/login');
                setTimeout(() => window.location.reload(),600);
            });
        }
    },
    /**
     * 截取数组中指定的项
     * @param data
     * @param start
     * @param end
     * @returns {*}
     */
    arraySplice:(data,start,end) => {
        return Array.isArray(data) &&　data.slice(start,start+end);
    },
    /**
     * 格式化时间格式
     * @param val
     * @returns {string}
     */
    formatTime: function (val) {
        if(val == null || val == undefined){
            return "";
        }
        var date=new Date(val);
        var year=date.getFullYear();
        var month=date.getMonth()+1;
        month=month>9?month:('0'+month);
        var day=date.getDate();
        day=day>9?day:('0'+day);
        var hh=date.getHours();
        hh=hh>9?hh:('0'+hh);
        var mm=date.getMinutes();
        mm=mm>9?mm:('0'+mm);
        var ss=date.getSeconds();
        ss=ss>9?ss:('0'+ss);
        var time=year+'-'+month+'-'+day+' '+hh+':'+mm+':'+ss;
        return time;
    },
    /**
     * 获取当前时间
     * @returns 
     */
    getDayTime: function(){
        var date=new Date();
        var year=date.getFullYear();
        var month=date.getMonth()+1;
        month=month>9?month:('0'+month);
        var day=date.getDate();
        day=day>9?day:('0'+day);
        var hh=date.getHours();
        hh=hh>9?hh:('0'+hh);
        var mm=date.getMinutes();
        mm=mm>9?mm:('0'+mm);
        var ss=date.getSeconds();
        ss=ss>9?ss:('0'+ss);
        var time=year+'-'+month+'-'+day+' '+hh+':'+mm+':'+ss;
        return time;
    },
    /**
     * 获取当天的起始时间
     * @returns 
     */
    getDayStartTime: function () {
        var date=new Date();
        var year=date.getFullYear();
        var month=date.getMonth()+1;
        month=month>9?month:('0'+month);
        var day=date.getDate();
        day=day>9?day:('0'+day);
        var time=year+'-'+month+'-'+day+' '+'00'+':'+'00'+':'+'00';
        return time;
    },
    /**
     * 获取当天的结束时间
     * @returns 
     */
    getDayEndTime: function () {
        var date=new Date();
        var year=date.getFullYear();
        var month=date.getMonth()+1;
        month=month>9?month:('0'+month);
        var day=date.getDate();
        day=day>9?day:('0'+day);
        var time=year+'-'+month+'-'+day+' '+'23'+':'+'59'+':'+'59';
        return time;
    },
    /**
     * 批量修改对象值
     * @param data
     * @param val
     * @constructor
     */
    objectforIn:(data,val) => {
        for (var key in data){
            data[key] = val;
        }
    },
    /**
     * 从接口获取所有的系统字典存到VueX里
     * @returns 
     */
    setCodeList(){
        this.request(getCodeData,'',this,data => {
            this.$store.commit("code/setCodeList", {  
                codeList: data
            });
        })
    },
    /**
     * 根据code和value从系统字典里筛选name
     * @param res 
     * @param code 
     * @param value （可不传）
     * @returns {string}
     */
     getFilterCodeData: function (res, code, value) {
        if(res == null || res == undefined){
            return "";
        }
        if(code == null || code == undefined){
            return "";
        }
        if(value == null || value == undefined){
            var codeFilter = res.filter((r) => {
                return r.code == code;
            });
            return codeFilter; 
        }else{
            var codeFilter = res.filter((r) => {
                return r.code == code && r.value == value;
            });
            return codeFilter[0].name;
        }
     },
    /**
     * 大数字单位转换
     * @param {*} param 
     * @returns 
     */
    convertValue(param){
		if(param >= 10000 && param < 100000000){
			param = param/10000;
			param = param.toFixed(2) + "万";
		} else if(param >= 100000000){
			param= param/100000000;
			param = param.toFixed(2) + "亿";
		}
		return param;
	},
     /**
      * 加法函数，用来得到精确的加法结果
      * 调用：add(a,b)
      * 返回值：a加上b的精确结果
      */
    add(a, b){
        let c = 0 // a的小数部分长度
        let d = 0 // b的小数部分长度
        try {
          c = a.toString().split('.')[1].length
        } catch (f) { }
        try {
          d = b.toString().split('.')[1].length
        } catch (f) { }
        let e = 10 ** Math.max(c, d) //保证a、b为整数的最小10次幂
        return (a * e + b * e) / e
    },
     /**
      * 减法函数，用来得到精确的减法结果
      * 调用：sub(a,b)
      * 返回值：a减去b的精确结果
      */
    sub(a, b){
        let c = 0 // a的小数部分长度
        let d = 0 // b的小数部分长度
        try {
            c = a.toString().split('.')[1].length
        } catch (f) { }
        try {
            d = b.toString().split('.')[1].length
        } catch (f) { }
        let e = 10 ** Math.max(c, d) //保证a、b为整数的最小10次幂
        return (a * e - b * e) / e
    },
    /**
     * 乘法函数，用来得到精确的乘法结果
     * 调用：mul(a,b)
     * 返回值：a乘以b的精确结果
     */
     mul(a, b){
        let c = 0 // a的小数部分长度
        let d = 0 // b的小数部分长度
        try {
          c = a.toString().split('.')[1].length
        } catch (f) { }
        try {
          d = b.toString().split('.')[1].length
        } catch (f) { }
      
        return (Number(a.toString().replace('.', '')) * Number(b.toString().replace('.', ''))) / (10 ** (c + d))
    },
    /**
     * 除法函数，用来得到精确的除法结果
     * 调用：div(a,b)
     * 返回值：a除以b的精确结果
     */
    div(a, b){
        let c = 0 
        let d = 0 
        try {
          c = a.toString().split('.')[1].length
        } catch (f) { }
        try {
          d = b.toString().split('.')[1].length
        } catch (f) { }
        const molecular = Number(a.toString().replace('.', '')) * (10 ** (c + d))
        const denominator = Number(b.toString().replace('.', '')) * (10 ** (c + d))
        return molecular / denominator / (10 ** (c - d))
    },
    /**
     * 验证正整数+正小数+0
     */
    checkIsPositive(rule, value, callback){
        var reg = /^\d+(?=\.{0,1}\d+$|$)/
        if(reg.test(value)) {
            callback()
        }else{
            callback(new Error('请输入大于等于0的正数'))
        }
    },
    /**
     * 验证正整数+正小数
     */
    checkIsPositiveEx0(rule, value, callback){
        var reg = /^(\d|[1-9]\d+)(\.\d+)?$/
        if(reg.test(value)) {
            if(value == '0') {
                callback(new Error('请输入大于0的正数'))
            } else {
                callback()
            }
        }else{
            callback(new Error('请输入大于0的正数'))
        }
    },
    /**
     * 验证正整数+0
     */
    checkIsPositiveInteger(rule, value, callback){
        var reg = /^(0|[1-9][0-9]*)$/
        if(reg.test(value)) {
            callback()
        }else{
            callback(new Error('请输入大于等于0的正整数'))
        }
    },
    /**
     * 验证正整数
     */
    checkIsPositiveIntegerEx0(rule, value, callback){
        var reg = /^([1-9][0-9]*)$/
        if(reg.test(value)) {
            callback()
        }else{
            callback(new Error('请输入大于0的正整数'))
        }
    },
    /**
     * 查找数字字符串数组中的最大值，并以千为单位向上取整
     * @param {*} arrs 
     * @returns 
     */
    findArrayMaxCeil(arrs){
        var max = parseFloat(arrs[0]);
        for(var i = 1, ilen = arrs.length; i < ilen; i+=1) {
            if(parseFloat(arrs[i]) > max) {
                max = parseFloat(arrs[i]);
            }
        }
        max = (Math.ceil(max/1000)*1000).toString();
        return max;
    },
    /**
     * 生成近x日的日期
     * @param {*} day 
     * @returns 
     */
    getDay(day){  
	    var today = new Date();  
	    var targetday_milliseconds=today.getTime() + 1000*60*60*24*day;
	    today.setTime(targetday_milliseconds);
        var tYear = today.getFullYear();  
        var tMonth = today.getMonth();  
        var tDate = today.getDate();  
        tMonth = this.doHandleMonth(tMonth + 1);  
        tDate = this.doHandleMonth(tDate);  
        return tYear+"-"+tMonth+"-"+tDate; 
    },
    doHandleMonth(month){  
        var m = month;  
        if(month.toString().length == 1){  
            m = "0" + month;  
        }  
        return m;  
    }

}