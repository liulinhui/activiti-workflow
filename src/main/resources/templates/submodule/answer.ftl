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
        <legend>课程ID:</legend>
    </fieldset>
    <form class="layui-form" action="">
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">题目</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" name="" cols="" rows="" readonly></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">开始作答</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" lay-verify="required" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    layui.use(['form'], function () {
        var $ = layui.jquery;
        $('.my-answer .my-answer-courseBtn').on('click', function () {
            var githubUrl = $(this).attr('github');
            var courseCode = $(this).attr('courseCode');
            alert(githubUrl);
            $.ajax({
                url:'./api/common/getQAContent',
                data:{githubUrl:githubUrl},
                dataType:'json',
                success:function (data) {
                    alert(data)
                }
            })
        })
    })
</script>