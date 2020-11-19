layui.use(['jquery','layer','table','form','laydate'],function () {
    //创建出内置模块的对象
    var $ = layui.jquery,
        layer = layui.layer,
        table = layui.table,
        form = layui.form,
        laydate = layui.laydate;

    var selJsonRoomSale = {};// 查询消费记录的条件  全局变量

    loadOrders(selJsonRoomSale);//初始化

    //时间范围选择
    laydate.render({
        elem:'#test3'
        ,form:'yyyy/MM/dd HH:mm:ss'//springMVC框架默认识别此种格式的字符串为日期
        ,type:'datetime' //时间格式
        ,range:true
    });

    //表格的方法级渲染
    function loadOrders(selJsonRoomSale){
        table.render({
            elem: '#demo'
            ,height: 512
            ,url: 'roomSale/loadPageTByPramas' //数据接口
            ,where:selJsonRoomSale
            ,limit: 3
            ,limits: [2,3,5,8,10,15]
            ,page: true //开启分页
            ,even: true
            ,cols: [[ //表头
                {type:'checkbox'}
                ,{field: 'id', title: 'ID', width:80, align:'center',sort: true}
                ,{field: 'roomNum', title: '房屋号', align:'center',width:100}
                ,{field: 'customerName', title: '客人姓名',align:'center', width: 120, sort: true}
                ,{field: 'startDate', title: '入住时间',align:'center',sort: true, width: 215, sort: true}
                ,{field: 'endDate', title: '退房时间',align:'center',sort: true, width: 215, sort: true}
                ,{field: 'roomPrice', title: '房间单价',align:'center', width: 120, sort: true}
                ,{field: 'days', title: '天数', align:'center',width: 80}
                ,{field: 'rentPrice', title: '住宿金额',align:'center', width: 120, sort: true}
                ,{field: 'otherPrice', title: '其他消费',align:'center', width: 120, sort: true}
                ,{field: 'salePrice', title: '支付金额',align:'center', width: 120, sort: true}
                ,{field: 'discountPrice', title: '优惠金额',align:'center', width: 120, sort: true}
                ,{fixed: 'right', width:110,  title: '操作',align:'center', toolbar: '#barDemo'}
            ]]
        });
    }


    //订单查询的表单提交
    form.on('submit(demo1)', function(data){
        selJsonRoomSale = {};  //每一次提交将之前的查询条件清空
        selJsonRoomSale['roomNum'] = data.field.roomNum;
        if(data.field.queryTimes!=""){
            var arrTimes = data.field.queryTimes.split(" - ");  //将查询的时间范围切割成数组
            selJsonRoomSale['startTime'] = arrTimes[0];  //开始时间
            selJsonRoomSale['endTime'] = arrTimes[1];   //截止时间
        }
        console.log(selJsonRoomSale);
        loadOrders(selJsonRoomSale); //根据条件查询
        return false; //阻止表单跳转
    });

});