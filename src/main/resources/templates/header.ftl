<div class="layui-header">
    <div class="layui-logo">学生互评系统</div>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href=""><img src="${base}/img/beier.png" class="layui-nav-img">${userEmail!"我"}</a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;">个人信息</a></dd>
                <dd><a href="javascript:;">操作记录</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a href="./logout">退出</a></li>
    </ul>
</div>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item layui-nav-itemed"><a class="" href="">已部署的工作流</a></li>
            <li class="layui-nav-item"><a href="">做题</a></li>
            <li class="layui-nav-item"><a href="">参与互评</a></li>
            <li class="layui-nav-item"><a href="">管理员配置</a></li>
        </ul>
    </div>
</div>