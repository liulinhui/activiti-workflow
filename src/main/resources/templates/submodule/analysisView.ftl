<div class="my-analysisView">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>课程选择</legend>
    </fieldset>
    <div style="margin: 20px 30px 20px;">
    <#if scheduleDtoList??>
        <#list scheduleDtoList as item>
            <button class="layui-btn layui-btn-normal my-analysis-courseBtn"
                    courseCode="${item.courseCode}">${item.courseName}</button>
        </#list>
    </#if>
    </div>
    <br>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>报表统计</legend>
    </fieldset>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <div id="student-commit-time-analysis" style=" width: 600px;height:400px;"></div>
        </div>
        <div class="layui-col-xs6">
            <div id="student-commit-grade-analysis" style=" width: 600px;height:400px;"></div>
        </div>
    </div>


</div>
<#if projectEnv?? && projectEnv=="pro">
    <#assign base="/mooc-workflow"+request.contextPath />
<#else >
    <#assign base=request.contextPath />
</#if>
<script src="${base}/js/analysis.js"></script>