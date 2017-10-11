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
                <input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>时间配置</legend>
        </fieldset>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">答题开始</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" class="layui-input my-time-conf-input"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">答题结束</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" class="layui-input my-time-conf-input"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">互评开始</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" class="layui-input my-time-conf-input"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">互评结束</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" class="layui-input my-time-conf-input"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">审查开始</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" class="layui-input my-time-conf-input"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">审查结束</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" class="layui-input my-time-conf-input"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">成绩发布</label>
                <div class="layui-input-inline">
                    <input type="text" lay-verify="required" class="layui-input my-time-conf-input"
                           placeholder="yyyy-MM-dd HH:mm:ss">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //时间选择器
        laydate.render({
            elem: '.my-time-conf-input',
            type: 'datetime'
        });
    })
</script>