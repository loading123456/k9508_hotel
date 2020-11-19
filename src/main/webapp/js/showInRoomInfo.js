layui.use(['jquery','layer','table','form','laydate'],function () {
    //创建出内置模块的对象
    var $ = layui.jquery,
        layer = layui.layer,
        table = layui.table,
        form = layui.form,
        laydate = layui.laydate;

    var INIobj;  //全局的表格行操作对象
    var currentPage=1; //表格当前页

    //执行一个laydate实例
    laydate.render({
        elem: '#endDate', //指定元素
        format: 'yyyy/MM/dd HH:mm:ss',
        value:new Date()
    });

    //表格的方法级渲染
    table.render({
        elem: '#demo'
        ,height: 512
        ,url: 'inRoomInfo/loadPageTByPramas' //数据接口
        ,limit: 3
        ,limits: [2,3,5,8,10,15]
        ,page: true //开启分页
        ,even: true
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', title: 'ID', width:80, align:'center',sort: true}
            ,{field: 'roomNum', title: '房屋号', align:'center',width:100,templet: '<div>{{d.rooms.roomNum}}</div>'}
            ,{field: 'roomPic', title: '封面图',align:'center', width:100, sort: true,templet: '<div><img src="img/{{d.rooms.roomPic}}"></div>'}
            ,{field: 'roomTypeName', title: '类型',align:'center', width:100,templet: '<div>{{d.rooms.roomType.roomTypeName}}</div>'}
            ,{field: 'roomPrice', title: '价格',align:'center',sort: true, width: 100,templet: '<div>{{d.rooms.roomType.roomPrice}}</div>'}
            ,{field: 'customerName', title: '客人姓名',align:'center', width: 120, sort: true}
            ,{field: 'gender', title: '性别',align:'center', width: 80, sort: true,templet: '#genderTpl'}
            ,{field: 'isVip', title: 'VIP', align:'center',width: 80,templet: '#isVipTpl'}
            ,{field: 'idcard', title: '身份证号',align:'center', width: 235, sort: true}
            ,{field: 'phone', title: '手机号',align:'center', width: 180, sort: true}
            ,{field: 'money', title: '押金',align:'center', width: 100, sort: true}
            ,{field: 'createDate', title: '入住时间',align:'center',sort: true, width: 215, sort: true}
            ,{field: 'outRoomStatus', title: '状态',align:'center', width: 120, sort: true,templet: '#outRoomStatusTpl'}
            ,{fixed: 'right', width:150,  title: '操作',align:'center', toolbar: '#barDemo'}
        ]]
        ,done:function (res,curr,count) {  //表格执行加载时的函数回调
            hoverOpenImg();
            currentPage = curr;
        }
    });

    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        INIobj = obj;
        if(layEvent === 'detail'){ //查看
            layer.msg("查看id："+data.id+"的数据。。");
        } else if(layEvent === 'del'){ //删除
            layer.confirm('真的删除此入住信息么？', function(index){
                //向服务端发送修改入住信息是否显示的状态，改为不显示
                var updupdStatus = {};
                updupdStatus['id'] = data.id;
                updupdStatus['status'] = 0;
                //执行服务器端操作
                updStatus(updupdStatus);
                layer.close(index);
            });
        } else if(layEvent === 'edit'){ //退房
            //1.数据的回显
            var vipStr = "否";
            if(data.isVip=='1'){
                vipStr = "是";
                loadVipByIdCard(data.idcard);  //根据身份证号查询vip
            }
            //给表单赋值
            form.val("exitInRoomInfoForm", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                "roomNum": data.rooms.roomNum // "name": "value"
                ,"customerName": data.customerName
                ,"idcard": data.idcard
                ,"roomPrice": data.rooms.roomType.roomPrice
                ,"createDate": data.createDate
                ,"isVip": vipStr
            });
            //将其他消费清零，将备注清空
            $("#otherPrice").val(0);
            $("#remark").val("");
            //计算入住的天数
            var stratTime = getDateStr(data.createDate);  //入住时间
            var endTime = getDateStr($("#endDate").val());  //退房时间
            //计算得到天数
            var days = getDays(stratTime,endTime);
            if(days==0){
                days = 1;
            }
            $("#days").text(days);
            //计算金额（除开其他消费金额）
            var zprice = days*data.rooms.roomType.roomPrice*$("#vipRate").val();
            $("#zprice").text(zprice);
            var otherPrice;
            $("#otherPrice").blur(function () {
                otherPrice = $(this).val();
               if(otherPrice>=0){
                   var zprices = zprice + otherPrice*1;
                   $("#zprice").text(zprices);
               }
            });
            //2.弹框
            layer.open({
                type:1,
                title:'退房操作界面',
                area:['850px','610px'],
                content:$("#exitInRoomInfoDiv"),
                anim:4
            });
            //3.退房
            form.on('submit(demo03)',function (INIdata) {
                //操作订单，定义订单json数据
                var saveJsonOrders = {};
                //得到当前时间的字符串
                var nowDate = getNowDate(new Date());
                saveJsonOrders['orderNum'] = dateReplace(nowDate) + getRandom(6);//订单编号
                saveJsonOrders['orderMoney'] = $("#zprice").text(); //订单总价
                saveJsonOrders['remark'] = INIdata.field.remark; //订单备注
                saveJsonOrders['orderStatus'] = 0;   //订单状态
                saveJsonOrders['iriId'] = data.id;   //入住信息id
                saveJsonOrders['createDate'] = nowDate; //订单创建时间
                saveJsonOrders['flag'] = 1;        //订单是否显示
                saveJsonOrders['orderOther'] = INIdata.field.roomNum+','+INIdata.field.customerName+','+INIdata.field.createDate+','+INIdata.field.endDate+','+days;
                //单价，其他消费，实际住房金额
                saveJsonOrders['orderPrice'] = INIdata.field.roomPrice+","+otherPrice+","+zprice;
                console.log(saveJsonOrders);//当前容器的全部表单字段，名值对形式
                saveOrders(saveJsonOrders);//服务器端的订单数据添加
                return false;//阻止表单跳转
            });

           /* //同步更新缓存对应的值
            obj.update({
                username: '123'
                ,title: 'xxx'
            });*/
        } else if(layEvent === 'LAYTABLE_TIPS'){
            layer.alert('Hi，头部工具栏扩展的右侧图标。');
        }
    });

    //表单验证
    form.verify({
        otherPrice: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value<0) {
                return '其他消费金额不能小于0';
            }
        }
    });






    /***************************自定义方法******************************/
    //表格重新加载
    function flush(currentPage){
        table.reload('demo',{
            page:{
                curr : currentPage
            }
        });
    }

    //放大图像
    function hoverOpenImg(){
        var img_show = null; // tips提示
        $('td img').hover(function(){
            var img = "<img class='img_msg' src='"+$(this).attr('src')+"' style='width:230px;' />";
            img_show = layer.tips(img, this,{
                tips:[2, 'rgba(41,41,41,.5)']
                ,area: ['260px']
            });
        },function(){
            layer.close(img_show);
        });
        $('td img').attr('style','max-width:70px');
    }

    //修改入住信息显示状态
    function updStatus(updupdStatus) {
        $.ajax({
           type:'POST',
           url:'inRoomInfo/updByPrimaryKeySelective',
           data:updupdStatus,
           success:function (data) {
               if(data=='success'){
                   layer.msg("删除成功。。",{icon:1,time:2000,anim:4});
                   INIobj.del(); //删除对应行（tr）的DOM结构，并更新缓存
               }else {
                   layer.msg("删除失败！！",{icon:2,time:2000,anim:3});
               }
           },
           error:function () {
               layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
           }
        });
    }

    //根据身份证号查询vip
    function loadVipByIdCard(idcard) {
        $.ajax({
            type:'POST',
            url:'vip/loadTByPramas',
            data:{'idcard':idcard},
            async:false,  //允许全局的变量取到异步加载的数据
            success:function (data) {
                $("#vipNum").val(data.vipNum);
                $("#vipRate").val(data.vipRate);
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

    //将目前的时间格式2019/08/06 12:12:08  -->  2019/08/06
    function getDateStr(dateStr) {
        var indexOf = dateStr.indexOf(" ");  //取到" "的下标
        dateStr = dateStr.substring(0,indexOf);  //第1个参数为下标，第2个参数为切割的字符串长度
        return dateStr;
    }

    //计算天数
    function getDays(startDate,endDate){  //2019/09/09   2019/10/10
        var date1Str = startDate.split("/");
        var date1Obj = new Date(date1Str[0],(date1Str[1]-1),date1Str[2]);
        var date2Str = endDate.split("/");
        var date2Obj = new Date(date2Str[0],(date2Str[1]-1),date2Str[2]);
        var t1 = date1Obj.getTime();
        var t2 = date2Obj.getTime();
        var datetime=1000*60*60*24;
        var minusDays = Math.floor(((t2-t1)/datetime));
        var days = Math.abs(minusDays);
        return minusDays;
    }
    //获取当前时间字符串     Date()   ---->  yyyy/MM//dd HH:mm:ss 格式的字符串
    function getNowDate(date) {
        var sign1 = "/";
        var sign2 = ":";
        var year = date.getFullYear() // 年
        var month = date.getMonth() + 1; // 月
        var day  = date.getDate(); // 日
        var hour = date.getHours(); // 时
        var minutes = date.getMinutes(); // 分
        var seconds = date.getSeconds() //秒
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (day >= 0 && day <= 9) {
            day = "0" + day;
        }
        if (hour >= 0 && hour <= 9) {
            hour = "0" + hour;
        }
        if (minutes >= 0 && minutes <= 9) {
            minutes = "0" + minutes;
        }
        if (seconds >= 0 && seconds <= 9) {
            seconds = "0" + seconds;
        }
        var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds ;
        return currentdate;
    }

    //把 2019/01/01 12:12:12  -->  20190101121212
    function dateReplace(date) {
        date = date.replace("/","");
        date = date.replace("/","");
        date = date.replace(" ","");
        date = date.replace(":","");
        date = date.replace(":","");
        return date;
    }

    //获取随机数
    function getRandom(num) {
        var count = '';   //随机数
        for (var i=0;i<num;i++){
            count += parseInt(Math.random()*10)  //0.123123123...
        }
        return count;
    }

    //订单数据添加
    function saveOrders(saveJsonOrders){
        $.ajax({
            type:'POST',
            url:'orders/saveT',
            data:saveJsonOrders,
            success:function (data) {
                if(data=="success"){
                    layer.msg("退房成功。。",{icon:1,time:2000,anim:3});
                    flush(currentPage)  //刷新数据表格
                }else {
                    layer.msg("退房失败！！",{icon:2,time:2000,anim:3});
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

});