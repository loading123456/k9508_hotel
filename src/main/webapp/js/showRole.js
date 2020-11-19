layui.use(['jquery','layer','table','form','laydate'],function () {
    //创建出内置模块的对象
    var $ = layui.jquery,
        layer = layui.layer,
        table = layui.table,
        form = layui.form,
        laydate = layui.laydate;



        table.render({
            elem: '#demo'
            , height: 512
            , url: 'role/loadPageTByPramas' //数据接口
            , limit: 3
            , limits: [2, 3, 5, 8, 10, 15]
            , page: true //开启分页
            , even: true
            , cols: [[ //表头
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', width: 80, align: 'center', sort: true}
                , {field: 'roleName', title: '角色名称', align: 'center', width: 100}
                , {field: 'authNames', title: '权限名称', align: 'center', width: 600}
                , {field: 'createDate', title: '创建时间', align: 'center', width: 320, sort: true}
                , {field: 'status', title: '是否可用', align: 'center', sort: true, width: 215, sort: true,templet: '#statusTpl'}
                , {field: 'flag', title: '是否显示', align: 'center', sort: true, width: 215, sort: true,templet: '#flagTpl'}
                , {fixed: 'right', width: 110, title: '操作', align: 'center', toolbar: '#barDemo'}
            ]]
        });

});