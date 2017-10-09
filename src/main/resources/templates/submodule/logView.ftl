<div class="my-log-view">
    <div>
        <blockquote class="layui-elem-quote">
            日志查看
        </blockquote>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>接口调用日志查询</legend>
        <div>
            <table class="demo">
            </table>
        </div>
    </fieldset>
</div>
<script>
    layui.use('table', function () {
        var table = layui.table;
        //展示已知数据
        table.render({
            elem: '.demo'
            ,data: [{
                "id": "10001"
                ,"username": "杜甫"
                ,"email": "xianxin@layui.com"
                ,"sex": "男"
                ,"city": "浙江杭州"
                ,"sign": "人生恰似一场修行"
                ,"experience": "116"
                ,"ip": "192.168.0.8"
                ,"logins": "108"
                ,"joinTime": "2016-10-14"
            }, {
                "id": "10002"
                ,"username": "李白"
                ,"email": "xianxin@layui.com"
                ,"sex": "男"
                ,"city": "浙江杭州"
                ,"sign": "人生恰似一场修行"
                ,"experience": "12"
                ,"ip": "192.168.0.8"
                ,"logins": "106"
                ,"joinTime": "2016-10-14"
                ,"LAY_CHECKED": true
            }, {
                "id": "10003"
                ,"username": "王勃"
                ,"email": "xianxin@layui.com"
                ,"sex": "男"
                ,"city": "浙江杭州"
                ,"sign": "人生恰似一场修行"
                ,"experience": "65"
                ,"ip": "192.168.0.8"
                ,"logins": "106"
                ,"joinTime": "2016-10-14"
            }, {
                "id": "10004"
                ,"username": "贤心"
                ,"email": "xianxin@layui.com"
                ,"sex": "男"
                ,"city": "浙江杭州"
                ,"sign": "人生恰似一场修行"
                ,"experience": "666"
                ,"ip": "192.168.0.8"
                ,"logins": "106"
                ,"joinTime": "2016-10-14"
            }, {
                "id": "10005"
                ,"username": "贤心"
                ,"email": "xianxin@layui.com"
                ,"sex": "男"
                ,"city": "浙江杭州"
                ,"sign": "人生恰似一场修行"
                ,"experience": "86"
                ,"ip": "192.168.0.8"
                ,"logins": "106"
                ,"joinTime": "2016-10-14"
            }, {
                "id": "10006"
                ,"username": "贤心"
                ,"email": "xianxin@layui.com"
                ,"sex": "男"
                ,"city": "浙江杭州"
                ,"sign": "人生恰似一场修行"
                ,"experience": "12"
                ,"ip": "192.168.0.8"
                ,"logins": "106"
                ,"joinTime": "2016-10-14"
            }, {
                "id": "10007"
                ,"username": "贤心"
                ,"email": "xianxin@layui.com"
                ,"sex": "男"
                ,"city": "浙江杭州"
                ,"sign": "人生恰似一场修行"
                ,"experience": "16"
                ,"ip": "192.168.0.8"
                ,"logins": "106"
                ,"joinTime": "2016-10-14"
            }, {
                "id": "10008"
                ,"username": "贤心"
                ,"email": "xianxin@layui.com"
                ,"sex": "男"
                ,"city": "浙江杭州"
                ,"sign": "人生恰似一场修行"
                ,"experience": "106"
                ,"ip": "192.168.0.8"
                ,"logins": "106"
                ,"joinTime": "2016-10-14"
            }]
            ,height: 272
            ,cols: [[ //标题栏
                {space: true}
                ,{field: 'id', title: 'ID', width: 80, sort: true}
                ,{field: 'username', title: '用户名', width: 120}
                ,{field: 'email', title: '邮箱', width: 150}
                ,{field: 'sign', title: '签名', width: 150}
                ,{field: 'sex', title: '性别', width: 80}
                ,{field: 'city', title: '城市', width: 100}
                ,{field: 'experience', title: '积分', width: 80, sort: true}
            ]]
            ,skin: 'row' //表格风格
            ,height: 500 //容器高度
            ,even: true
            ,page: true //是否显示分页
            ,limits: [5, 7, 10]
            ,limit: 5 //每页默认显示的数量
        });
    })
</script>