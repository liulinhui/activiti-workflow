<div class="my-analysisView">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>题目选择</legend>
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
<script>
    layui.use(['layer'], function () {
        var $ = layui.jquery, layer = layui.layer;

        /**
         *
         * @param id  dom id
         * @param param  数据
         * @param courseCode  数据
         */
        var drawCommitTimeView = function (id, param, courseCode) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById(id));
            var date = param.date;
            var data = param.data;
            option = {
                tooltip: {
                    trigger: 'axis',
                    position: function (pt) {
                        return [pt[0], '10%'];
                    }
                },
                title: {
                    left: 'center',
                    text: '学生提交时间段折线图(' + courseCode + ')'
                },
                toolbox: {
                    feature: {
                        dataZoom: {
                            yAxisIndex: 'none'
                        },
                        restore: {},
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: date
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0, '100%']
                },
                dataZoom: [{
                    type: 'inside',
                    start: 0,
                    end: 10
                }, {
                    start: 0,
                    end: 10,
                    handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                    handleSize: '80%',
                    handleStyle: {
                        color: '#fff',
                        shadowBlur: 3,
                        shadowColor: 'rgba(0, 0, 0, 0.6)',
                        shadowOffsetX: 2,
                        shadowOffsetY: 2
                    }
                }],
                series: [
                    {
                        name: '学生提交次数',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',
                        itemStyle: {
                            normal: {
                                color: 'rgb(255, 70, 131)'
                            }
                        },
                        areaStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgb(255, 158, 68)'
                                }, {
                                    offset: 1,
                                    color: 'rgb(255, 70, 131)'
                                }])
                            }
                        },
                        data: data
                    }
                ]
            };
            myChart.setOption(option);
        };

        /**
         * 画成绩分布图
         * */
        var drawCommitGradeView = function (id, param, courseCode) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById(id));
            option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        crossStyle: {
                            color: '#999'
                        }
                    }
                },
                title: {
                    left: 'center',
                    text: '学生成绩分布(' + courseCode + ')'
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                xAxis: [
                    {
                        type: 'category',
                        data: ['0', '10', '20', '30', '40', '50', '60', '70', '80', '90', '100'],
                        axisPointer: {
                            type: 'shadow'
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '学生数',
                        min: 0,
                        max: 250,
                        interval: 30
                    }
                ],
                series: [
                    {
                        name: '学生数',
                        type: 'bar',
                        data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0]
                    }
                ]
            };
            myChart.setOption(option);
        };

        /**
         *  ajax 请求
         * @param url 请求URL
         * @param courseCode  课程代码
         * @param id  dom元素id
         * @param cb  回掉函数
         */
        var ajaxGetData = function (url, courseCode, id, cb) {
            $.ajax({
                url: url,
                data: {courseCode: courseCode},
                dataType: 'json',
                type: "POST",
                async: false,
                success: function (data) {
                    if (!data.success) {
                        layer.open({
                            title: '数据请求失败',
                            content: '<p>' + data.errorMessage + '</p>'
                        })
                    } else {
                        cb(id, data.data, courseCode)
                    }
                }
            })
        };

        /**
         * 画学生提交时间段折线图
         * @param courseCode
         */
        var commitTimePainting = function (courseCode) {
            var id = 'student-commit-time-analysis', url = './api/common/getStudentCommitTimeAnalysis';
            ajaxGetData(url, courseCode, id, drawCommitTimeView)
        };

        /**
         * 画学生成绩分布图
         * @param courseCode
         */
        var commitGradePainting = function (courseCode) {
            var id = 'student-commit-grade-analysis', url = './api/common/getStudentCommitTimeAnalysis';
            ajaxGetData(url, courseCode, id, drawCommitGradeView)
        };


        /**
         * 点击事件
         */
        $('.my-analysisView .my-analysis-courseBtn').on('click', function () {
            var courseCode = $(this).attr('courseCode');
            commitTimePainting(courseCode);
            commitGradePainting(courseCode);
        });

        function moNi() {
            var base = +new Date(1968, 9, 3);
            var oneDay = 24 * 3600 * 1000;
            var date = [];
            var data = [Math.random() * 300];
            for (var i = 1; i < 20000; i++) {
                var now = new Date(base += oneDay);
                date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
                data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]));
            }
            var param = {data: data, date: date};
            drawCommitTimeView('student-commit-time-analysis', param);
            drawCommitTimeView('student-commit-grade-analysis', param);
        }
    });
</script>