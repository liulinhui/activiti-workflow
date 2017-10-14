<div class="my-judgement">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>待审核的作业</legend>
        <div>
            <table class="myJudgementWaitTable">
            </table>
            <div id="myJudgementWaitPage"></div>
        </div>
    </fieldset>
    <br>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>已经审核的作业</legend>
        <div>
            <table class="myJudgementDoneTable">
            </table>
            <div id="myJudgementDonePage"></div>
        </div>
    </fieldset>
</div>

<script>
    layui.use(['table', 'laypage', 'layer'], function () {
        var table = layui.table, laypage = layui.laypage, $ = layui.jquery;

        /**
         * 加载待审核的成绩
         */
        function loadMyJudgementWaitTable() {
            var status = 'wait';
            $.ajax({
                url: './api/user/selectMyJudgementWait',
                data: {status: status},
                jsonType: 'json',
                success: function (data) {
                    if (!data.success) {
                        layer.open({
                            title: '数据请求失败',
                            content: '<p>' + data.errorMessage + '</p>'
                        })
                    }
                    laypage.render({
                        elem: 'myJudgementWaitPage',
                        count: data.data.count,
                        layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
                        theme: '#FF5722',
                        jump: function (obj) {
                            var param = {status: status, page: obj.curr, limit: obj.limit};
                            $.ajax({
                                type: 'POST',
                                url: './api/user/selectMyJudgementWait',
                                data: param,
                                dataType: 'json',
                                success: function (result) {
                                    table.render({
                                        elem: '.myJudgementWaitTable',
                                        data: result.data.list,
                                        height: 280,
                                        width: 2000,
                                        cols: [[ //标题栏
                                            {field: 'email', title: '邮箱', width: 200},
                                            {field: 'courseCode', title: '课程代码', width: 200},
                                            {field: 'answer', title: '提交作业内容', width: 1000},
                                            {field: 'judgeTimes', title: '被打分次数', width: 100},
                                            {field: 'grade', title: '成绩', width: 100},
                                            {
                                                field: 'originQuestion',
                                                title: '提交',
                                                width: 300,
                                                templet: '#my-job-done-answer-detail'
                                            }
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
        }

        /**
         * 加载已经审核的成绩
         */
        function loadMyJudgementDoneTable() {
            var status = 'done';
            $.ajax({
                url: './api/user/selectMyJudgementWait',
                data: {status: status},
                jsonType: 'json',
                success: function (data) {
                    if (!data.success) {
                        layer.open({
                            title: '数据请求失败',
                            content: '<p>' + data.errorMessage + '</p>'
                        })
                    }
                    laypage.render({
                        elem: 'myJudgementDonePage',
                        count: data.data.count,
                        layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
                        theme: '#FF5722',
                        jump: function (obj) {
                            var param = {status: status, page: obj.curr, limit: obj.limit};
                            $.ajax({
                                type: 'POST',
                                url: './api/user/selectMyJudgementWait',
                                data: param,
                                dataType: 'json',
                                success: function (result) {
                                    table.render({
                                        elem: '.myJudgementDoneTable',
                                        data: result.data.list,
                                        height: 280,
                                        width: 2000,
                                        cols: [[ //标题栏
                                            {field: 'email', title: '邮箱', width: 200},
                                            {field: 'courseCode', title: '课程代码', width: 200},
                                            {field: 'answer', title: '提交作业内容', width: 1000},
                                            {field: 'judgeTimes', title: '被打分次数', width: 100},
                                            {field: 'grade', title: '成绩', width: 100},
                                            {
                                                field: 'originQuestion',
                                                title: '提交',
                                                width: 300,
                                                templet: '#my-job-done-answer-detail'
                                            }
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
        }

        loadMyJudgementWaitTable();
        setTimeout(function () {
            loadMyJudgementDoneTable();
        }, 300);
    })
</script>
