<!DOCTYPE html>
<html>
<head>
    <title>index</title>
<#include "include.ftl">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "header.ftl">
    <div class="layui-body">
        <div style="padding: 15px;">
            <blockquote class="layui-elem-quote">
                最新通告<a>
            <@identity_validate userEmail="1249055292@qq.com">
                ${identity!""}
            </@identity_validate>
            </a>
            </blockquote>
        </div>
    </div>
<#include "footer.ftl">
</div>
<script>
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
</body>
</html>
