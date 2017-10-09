layui.use('element', function () {
    var $ = layui.jquery, layer = layui.layer;
    var element = layui.element;
    var layui_body = $('.layui-body');
    $('#my-activiti-conf').on('click', function () {
        layui_body.html('');
        layui_body.html(ajaxGet('./activitiConf'));
    });

    $('#my-answer').on('click', function () {
        layui_body.html('');
        layui_body.html(ajaxGet('./answer'));
    });

    $('#my-assessment').on('click', function () {
        layui_body.html('');
        layui_body.html(ajaxGet('./assessment'));
    });

    $('#my-judgement').on('click', function () {
        layui_body.html('');
        layui_body.html(ajaxGet('./judgement'));
    });

    $('#my-time-conf').on('click', function () {
        layui_body.html('');
        layui_body.html(ajaxGet('./timeConf'));
    });

    $('#my-publish').on('click', function () {
        layui_body.html('');
        layui_body.html(ajaxGet('./publish'));
    });

    $('#my-job-done').on('click', function () {
        layui_body.html('');
        layui_body.html(ajaxGet('./jobDone'));
    });

    $('#my-grade-info').on('click', function () {
        layui_body.html('');
        layui_body.html(ajaxGet('./gradeInfo'));
    });

    $('#my-user-role').on('click', function () {
        layui_body.html('');
        layui_body.html(ajaxGet('./userRole'));
    });

    $('#my-log-view').on('click', function () {
        layui_body.html('');
        layui_body.html(ajaxGet('./logView'));
    });

    var ajaxGet = function (url) {
        return $.ajax({url: url, async: false}).responseText;
    }
});
