<div class="my-job-done">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>已完成的答题任务</legend>
        <div>
            <table class="myJobAnswerDoneTable" lay-filter="myJobAnswerDoneTable">
            </table>
            <div style="margin-left: 15%;" id="myJobAnswerDonePage"></div>
        </div>
    </fieldset>
    <br><br>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>已完成的互评任务</legend>
        <div>
            <table class="myJobAssessmentTable">
            </table>
            <div style="margin-left: 15%;" id="myJobAssessmentPage"></div>
        </div>
    </fieldset>
</div>

<div style="display: none" id="yuner-test">
    <img src=${request.contextPath}/img/meinv.jpeg>
</div>

<script>
    layui.use(['table', 'laypage', 'layer'], function () {
        var table = layui.table;
        var laypage = layui.laypage;
        var layer = layui.layer;
        var $ = layui.jquery;
        $.ajax({
            url: './api/user/selectStudentWorkInfo',
            data: {count: true},
            dataType: 'json',
            success: function (data) {
                laypage.render({
                    elem: 'myJobAnswerDonePage',
                    count: data.data.count,
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
                    theme: '#FF5722',
                    jump: function (obj) {
                        var param = {page: obj.curr, limit: obj.limit};
                        $.ajax({
                            url: './api/user/selectStudentWorkInfo',
                            data: param,
                            dataType: 'json',
                            success: function (result) {
                                table.render({
                                    elem: '.myJobAnswerDoneTable',
                                    data: result.data,
                                    height: 250,
                                    width: 2000,
                                    cols: [[ //标题栏
                                        {field: 'courseCode', title: '课程代码', width: 150},
                                        {field: 'emailAddress', title: '邮箱', width: 200},
                                        {field: 'lastCommitTimeString', title: '提交时间', width: 200},
                                        {field: 'workDetail', title: '提交作业内容', width: 500},
                                        {
                                            field: 'originQuestion',
                                            title: '详细信息',
                                            width: 300,
                                            templet: '#my-job-done-answer-detail'
                                        },
                                    ]],
                                    skin: 'row', //表格风格
                                    even: true,
                                    page: false //是否显示分页
                                })
                            }
                        })
                    }
                });
            }
        });

        //已完成的答题任务监控工具条
        table.on('tool(myJobAnswerDoneTable)', function (obj) {
            console.log(obj);
            var courseCode = obj.data.courseCode;
            if (obj.event === 'origin') {
                $.ajax({
                    url: './api/common/getQAContent',
                    data: {courseCode: courseCode},
                    dataType: 'json',
                    success: function (result) {
                        var question = result.data;
                        layer.open({
                            type: 1,
                            title: false,
                            closeBtn: 0,
                            shadeClose: true,
                            content: '<p>' + question + '<p>'
                        });
                    }
                })
            }
            if (obj.event === 'detail') {
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    area: ['700px', '1000px'],
                    shadeClose: true,
                    content: $('#yuner-test')
                });
            }
        });
    });
</script>

<script type="text/html" id="my-job-done-answer-detail">
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="origin">查看原始题目</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="detail">查看流程图</a>
</script>