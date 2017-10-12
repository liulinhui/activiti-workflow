<div class="my-job-done">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>已完成的答题任务</legend>
        <div>
            <table class="myJobAnswerDoneTable">
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

<script>
    layui.use(['table', 'laypage'], function () {
        var table = layui.table;
        var laypage = layui.laypage;
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
                                        {field: 'workDetail', title: '原始题目', width: 300}
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
    });
</script>