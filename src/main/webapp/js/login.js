layui.use(['jquery','layer','form'],function () {
    //创建出内置模块的对象
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form;

    //定义一个全局变量（验证是否通过） 为了进行登录提交
    var userLoginVerifyIf = false;
    var loginMsg=$("#loginMsg").val();
    if (loginMsg!='') {
        layer.msg("你还未登录！",{icon:2,time:2000,anim:6});
    }

    //表单验证
    form.verify({
        userName: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value.length>9 ||value.length<3) {
                return '用户名长度在3-9之间';
            }
        },
        pwd: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value.length>12 ||value.length<6) {
                return '密码长度在6-12之间';
            }
        }
    });

    //验证码的验证
    $("#yzm").blur(function () {
        //进行服务器端的验证
        userLoginVerify($(this).val());
    });

    //监听登录表单提交
    form.on('submit(login)',function (data) {
        if(userLoginVerifyIf){
            var loginJsonUser = {};
            loginJsonUser = data.field;
            login(loginJsonUser);
        }else{
            layer.msg("验证码输入错误！！",{icon:2,time:2000,anim:6});
        }
        return false;//组织表单跳转
    });
    /************************************自定义方法************************************************************/
    //进行验证码的验证
    function userLoginVerify(verify) {
        $.ajax({
            type:'POST',
            url:'user/userLoginVerify',
            async:false, //允许外部变量取到ajax里面的值
            data:{"verify":verify},
            success:function (data) {
                if(data=='success'){
                    layer.tips("验证码输入正确","#yzm",{tips: [2,'#0dfc23'], time:3000});
                    //修改成功之后 同步更新缓存对应的值 这是点击修改方法才需要更新缓存
                    userLoginVerifyIf = true;
                }else {
                    layer.tips("验证码输入错误！！","#yzm",{tips: [2,'#fc2b1f'], time:3000});
                    userLoginVerifyIf = false;
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }


    function login(loginJsonUser){
        $.ajax({
            type:'POST',
            url:'user/login',
            data:loginJsonUser,
            success:function (data) {
                if(data=='success'){
                    layer.msg("登录成功！！",{icon:3,time:2000,anim:4});
                    setTimeout('window.location="auth/toIndex"',1000);
                }else {
                    layer.msg("登录失败！！",{icon:3,time:2000,anim:4});
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

});