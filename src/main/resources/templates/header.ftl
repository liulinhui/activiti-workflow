<div class="layui-header">
    <div class="layui-logo">学生互评系统</div>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;"><img src="${base}/img/beier.png" class="layui-nav-img">${userEmail!"我"}</a>
        </li>
        <li class="layui-nav-item"><a href="./logout">退出</a></li>
    </ul>
</div>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item  layui-nav-itemed">
                <a href="javascript:;">我的任务</a>
                <dl class="layui-nav-child">
                    <dd><a id="my-answer" href="javascript:;">答题</a></dd>
                    <dd><a id="my-assessment" href="javascript:;">参与互评</a></dd>
                    <dd><a id="my-judgement" href="javascript:;">成绩审核</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a id="my-job-done" href="javascript:;">已完成任务</a></li>
            <li class="layui-nav-item"><a id="my-grade-info" href="javascript:;">成绩信息</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">管理员配置</a>
                <dl class="layui-nav-child">
                    <dd><a id="my-activiti-conf" href="javascript:;">工作流配置</a></dd>
                    <dd><a id="my-time-conf" href="javascript:;">配置时间表</a></dd>
                    <dd><a id="my-publish" href="javascript:;">发布题目</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>