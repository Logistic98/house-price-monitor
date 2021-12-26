/************************* 配置 *************************/
module.exports = {
    components:'/components',// 指定views下组件所在目录
    affix:true,// 是否显示图钉
    isRefrushBack:true,// 刷新后是否停在当前页,默认回到首页
    authorization:'authorization',// 请求头 token key
    title:'房价监测系统', // 标题
    footer:'Copyright © eula.club all right reserved',// 底部文字，注释掉默认没有底部
    iconPath:'./assets/favicon.ico',// 网页 icon 图标路径
    publicPath:'/',// 网站根目录，打包时用到
    devServer: {
        port: 8081,
        host: '0.0.0.0',// 便于手机局域网访问
        open:true,// 是否启动时打开浏览器
        disableHostCheck: true, // 映射外网时，需要设置为true
        /**
         * 域名，他将会基于 window.location来链接服务器，需要使用public配置
         * dev-server被代理到nginx中配置的 myapp.com
         */
        public: "127.0.0.1:8081",// myapp.com
        publicPath:'/',
        compress:true,
        overlay: {// 是否在浏览器全屏显示错误与警告
            warnings: false,
            errors:true
        },
        headers: {
            'Access-Control-Allow-Origin': '*'
        },
        proxy: {// 跨域请求配置
            "/api": {
                secure: false,// 关闭安全检测，默认请求 https
                target: "http://localhost:8080",
                changeOrigin: true,
                pathRewrite: {"^/api" : ""},
            }
        },
    },
    alias:{
        // 如：jquery
        //"$": 'jquery.js'
    }
};