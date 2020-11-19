// 配置
layui.config({
	base: './hpModules/' // 扩展模块目录
}).extend({ // 模块别名 ，引入自定义模块
	hpTab: 'hpTab/hpTab',
	hpRightMenu: 'hpRightMenu/hpRightMenu',
	hpFormAll: 'hpFormAll/hpFormAll',
});

//JavaScript代码区域
layui.use(['element', 'carousel','hpTheme', 'hpTab', 'hpLayedit', 'hpRightMenu'], function() {
	
	var element = layui.element;
	var carousel = layui.carousel; //轮播
	var hpTab = layui.hpTab;
	var hpRightMenu = layui.hpRightMenu;
	var hpTheme=layui.hpTheme;
	$ = layui.$;
	
    // 初始化主题
	hpTheme.init();
	 //初始化轮播
	carousel.render({
		elem: '#test1',
		width: '100%', //设置容器宽度
		interval: 1500,
		height: '500px',
		arrow: 'none', //不显示箭头
		anim: 'fade', //切换动画方式
	});

    // 初始化 动态tab
    hpTab.init();
    // 右键tab菜单
    hpRightMenu.init();


    $("#exit").click(function () {
        layer.confirm('确定要退出登陆么？', function(index) {
            exit();  //退出
            layer.close(index);
        });
    });
    
    function exit() {
        $.ajax({
            type:'POST',
            url:'user/exit',
            success:function (data) {
                if(data=='success'){
                    layer.msg("退出成功！！",{icon:3,time:2000,anim:4});
                    setTimeout('window.location="model/loginUI"',1000);
                }else {
                    layer.msg("退出失败！！",{icon:3,time:2000,anim:4});
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

});