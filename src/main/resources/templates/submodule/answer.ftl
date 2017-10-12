<div class="my-answer">
    <fieldset class="layui-elem-field" style="margin-top: 30px;">
        <legend>课程列表:</legend>
        <div style="    margin: 20px 30px 20px;">
        <#if scheduleDtoList??>
            <#list scheduleDtoList as item>
                <button class="layui-btn layui-btn-normal my-answer-courseBtn" github="${item.githubAddress}"
                        courseCode="${item.courseCode}">${item.courseName}</button>
            </#list>
        </#if>
        </div>
    </fieldset>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend class="my-answer-fieldset-courseCode" courseCode="">课程ID:</legend>
    </fieldset>
    <div class="layui-form">
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">题目</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea my-answer-question" name="question" cols="" rows="10"
                          readonly></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">开始作答</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" lay-verify="required" name="answer" rows="10"
                          class="layui-textarea my-answer-answer"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn " lay-submit="" lay-filter="my-answer-commit-btn">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['form'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        var fieldset = $('.my-answer .my-answer-fieldset-courseCode');
        $('.my-answer .my-answer-courseBtn').on('click', function () {
            var githubUrl = $(this).attr('github');
            var courseCode = $(this).attr('courseCode');
            $.ajax({
                url: './api/common/getQAContent',
                data: {githubUrl: githubUrl},
                dataType: 'json',
                success: function (data) {
                    $('.my-answer-question').val(data.data);
                    fieldset.html('课程ID:' + courseCode);
                    fieldset.attr('courseCode', courseCode);
                }
            })
        });
        //监听提交
        form.on('submit(my-answer-commit-btn)', function (data) {
            var courseCode = fieldset.attr('courseCode');
            alert(JSON.stringify(data.field));
            $.ajax({
                url: './api/user/commitWork',
                data: {
                    courseCode: courseCode,
                    workDetail: data.field.answer
                },
                dataType: 'json',
                success: function (result) {
                    if (result.success) {
                        layer.alert(JSON.stringify(result), {
                            title: '提交成功'
                        });
                    }
                    else
                        layer.alert(JSON.stringify(result), {
                            title: '提交失败'
                        });
                }
            });
            return false;
        });
    })
</script>