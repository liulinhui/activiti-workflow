<div class="my-time-conf">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>课程配置</legend>
    </fieldset>
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">课程ID</label>
            <div class="layui-input-block">
                <input type="text" name="courseCode" lay-verify="required" placeholder="请输入" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">课程名称</label>
            <div class="layui-input-block">
                <input type="text" name="courseName" lay-verify="required" placeholder="请输入" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">GitHub地址</label>
            <div class="layui-input-block">
                <input type="text" name="githubAddress" lay-verify="required" placeholder="请输入" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>时间配置</legend>
        </fieldset>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">答题开始</label>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" lay-verify="required" class="layui-input my-time-conf-startTime"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">答题结束</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" name="commitEndTime"
                           class="layui-input my-time-conf-commitEndTime"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">互评开始</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" name="judgeStartTime"
                           class="layui-input my-time-conf-judgeStartTime"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">互评结束</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" name="judgeEndTime"
                           class="layui-input my-time-conf-judgeEndTime"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">审查开始</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" name="auditStartTime"
                           class="layui-input my-time-conf-auditStartTime"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">审查结束</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" name="auditEndTime"
                           class="layui-input my-time-conf-auditEndTime"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">成绩发布</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" name="publishTime"
                           class="layui-input my-time-conf-publishTime"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="my-time-conf-submit">立即提交</button>
                <button type="reset" class="my-time-conf-cancel layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
    <fieldset class="layui-elem-field" style="margin-top: 30px;">
        <legend>已配置的课程</legend>
        <div style="padding: 0% 10% 3% 5%;">
            <table class="layui-table">
                <colgroup>
                    <col width="200">
                    <col width="500">
                    <col width="200">
                    <col width="50">
                </colgroup>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>邮箱</th>
                    <th>描述</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </fieldset>
</div>

<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate, $ = layui.jquery;
        var list = ['startTime', 'commitEndTime', 'judgeStartTime', 'judgeEndTime', 'auditStartTime', 'auditEndTime', 'publishTime'];
        for (var index in list) {
            //时间选择器
            laydate.render({
                elem: '.my-time-conf-' + list[index],
                type: 'datetime'
            });
        }
        //监听提交
        form.on('submit(my-time-conf-submit)', function (data) {
            $.ajax({
                url:'./api/common/insertScheduleTime',
                data:{data:JSON.stringify(data.field)},
                dataType:'json',
                success:function (result) {
                    if (result.success)
                        layer.alert(JSON.stringify(result), {
                            title: '部署成功'
                        });
                    else
                        layer.alert(JSON.stringify(result), {
                        title: '部署失败'
                    });
                }
            });
            return false;
        });

        $('.my-time-conf .my-time-conf-cancel').on('click',function () {
            $('.my-time-conf input').val('');
        })
    })
</script>