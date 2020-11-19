layui.use(['jquery','layer','table','form','laydate'],function () {
    //创建出内置模块的对象
    var $ = layui.jquery,
        layer = layui.layer,
        table = layui.table,
        form = layui.form,
        laydate = layui.laydate;

    //身份证是否可用 false不可用 true可用
    var idcardIf = false;

    //手机号是否可用
    var phoneIf = false;

    //定义当前时间
    var nowDate;

    //验证身份证号的唯一性
    $("#idcard").blur(function () {
       //身份证的正则表达式
       var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
       if ($(this).val()!='' && $(this).val()!=null){
           if(reg.test($(this).val())){
               //封装查询的条件
               var selCountVip = {};
               selCountVip['idcard'] = $(this).val();
               checkIdCard(selCountVip);
           }else{
               layer.tips('身份证号格式不正确！', '#idcard', {tips: [2,'#fc1505'], time:3000});  //吸附框
           }
       }
    });

    //验证手机号的唯一性
    $("#phone").blur(function () {
        //手机号的正则表达式
        var reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
        if ($(this).val()!='' && $(this).val()!=null){
            if(reg.test($(this).val())){
                //封装查询的条件
                var selCountVip = {};
                selCountVip['phone'] = $(this).val();
                checkPhone(selCountVip);
            }else{
                layer.tips('手机号格式不正确！', '#phone', {tips: [2,'#fc1505'], time:3000});  //吸附框
            }
        }
    });
    //根据会员类型自动生成会员卡号
    form.on('select(vipRate)', function(data){
        nowDate = new Date();
        var vipNum =  dateReplace(getNowDate(nowDate));
        if(data.value==0.9){
            //普通会员
            vipNum += "02";
        }else {
            vipNum += "01";
        }
        $("#vipNum").val(vipNum);
    });

    //添加vip信息
    form.on('submit(demo2)',function (data) {
        if(idcardIf ){
            if(phoneIf){
                var saveJsonVip=data.field;
                saveJsonVip['createDate'] = getNowDate(nowDate);
                saveVip(saveJsonVip);
            }else {
                layer.msg("此手机号已被使用,请重新填写",{icon:2,time:2000,anim:6});
            }
        }else {
            layer.msg("此身份证号已被使用,请重新填写",{icon:2,time:2000,anim:6});
        }

        return false;
    });

    /*********************************自定义方法*********************************************/
    //根据身份证号查询会员的数据条数
    function checkIdCard(selCountVip) {
        $.ajax({
            type:'POST',
            url:'vip/getCountByPramas',
            async:false,  //允许外部变量取到ajax里面的数据
            data:selCountVip,
            success:function (data) {

                if (data == 1){
                    layer.tips('此身份证号已被使用！', '#idcard', {tips: [2,'#fc1505'], time:3000});  //吸附框
                    idcardIf = false;
                }else{
                    layer.tips('身份证号可用！', '#idcard', {tips: [2,'#2bfc46'], time:3000});  //吸附框
                    idcardIf = true;
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

    //根据手机号查询会员的数据条数
    function checkPhone(selCountVip) {
        $.ajax({
            type:'POST',
            url:'vip/getCountByPramas',
            async:false,  //允许外部变量取到ajax里面的数据
            data:selCountVip,
            success:function (data) {

                if (data == 1){
                    layer.tips('此手机号已被使用！', '#phone', {tips: [2,'#fc1505'], time:3000});  //吸附框
                    phoneIf = false;
                }else{
                    layer.tips('手机号可用！', '#phone', {tips: [2,'#2bfc46'], time:3000});  //吸附框
                    phoneIf = true;
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
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
        date = date.replace("/", "");
        date = date.replace("/", "");
        date = date.replace(" ", "");
        date = date.replace(":", "");
        date = date.replace(":", "");
        return date;
    }


    //会员的添加
    function saveVip(saveJsonVip) {
        $.ajax({
            type:'POST',
            url:'vip/saveT',
            data:saveJsonVip,
            success:function (data) {
                if (data == 'success'){
                    layer.msg("添加会员信息成功！！",{icon:1,time:2000,anim:4});
                    setTimeout('window.location="model/toShowVip"',2000);
                }else{
                    layer.msg("添加会员信息失败！！",{icon:2,time:2000,anim:3});
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }
});