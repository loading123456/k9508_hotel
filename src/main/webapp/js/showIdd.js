var myChart = echarts.init(document.getElementById('main'));
//数据接口../json/data01.json文件  访问服务器端的数据的路径
$.get('roomSale/loadPriceByRoomNum').done(function (data) {
    myChart.setOption({
        title: {
            text: '异步数据加载示例'
        },
        tooltip: {},
        toolbox: {  //工具
            feature: {
                dataView: {}, //数据视图按钮
                saveAsImage: {
                    pixelRatio: 5  //保存为图片
                },
                restore: {},
                magicType : {show: true, type: ['line', 'bar']}
            }
        },
        legend: {
            data:['销量']
        },
        xAxis: {
            data: data.roomNumList
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: data.salePriceList
        }]
    });
});
