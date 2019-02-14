<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>项目详情</title>
    <link href="/css/tail.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script>
        function change() {
            var height01 = $(window).height();
            $(".top").css('height', height01 - 35+"px");
        }
    </script>
</head>

<body style="border-radius: 8px" onload="change()">
<form id="form_demo" >
<div class="top">
    <div>
        <div class="top_out">
            <table class="table" >
                <tbody>
                    <tr>
                        <td style="border-top: none" >项目名称：<i class="i_start"></i></td>
                        <td  colspan = "1"  style="text-align: left;border-top: none"><input type="text"  name ="name" minlength="2"  required id="projectName"></td>
                </tbody>
            </table>
        </div>
    </div>
    <div class="bot_btn">
        <input class="btn" type="submit" value="保存"/>
        <button class="btn btn1"><a href="#" onclick="layerClose()" target="right">返回</a></button>
    </div>
</div>
</form>
</body>
<script type="text/javascript" src="/js/jquery-1.10.1.js"></script>
<script src="/js/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
<script src="/js/layer_v2.1/layer/layer.js"></script>
<script src="/js/schedule.js"></script>
<script >

    $.validator.setDefaults({
        submitHandler: function() {
            RecodeSave();
        }
    });
    $().ready(function() {
        $("#form_demo").validate();
    });
</script>
<script src="/js/date/js/laydate.js"></script>
<script>
    var PROJECTID, TASKTYPE, TASKPHASE,  PERSONID;
    !function () {
        laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
        laydate({ elem: '#invest_date' });//绑定元素
    }();

    function init() {
        var currentID = parent.getCurrentID();
        if (currentID != "") {
            $.ajax({
                url: '/project/initProject?projectid=' + currentID,
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    var data = result.data;
                    if (data) {
                        PROJECTID = data.PROJECTID;
                        PERSONID =data.PERSONID;
                        TASKPHASE = data.TASKPHASE;
                        TASKTYPE = data.TASKTYPE;
                        $("#tit").val(data.PROJECTNAME);
                        $("#person").val(data.TASKPHASENAME);
                        $("#demo").val(getFormatTime(data.STARTTIME.substring(6,19)));
                        $("#NOTE").val(data.NOTE);
                    }
                    else {
                        alert("获取失败！");
                    }
                },
                error: function (err) {
                }
            })
        }

    }
    function RecodeSave() {
        $.ajax({
            type: "POST",
            url: "/project/addProject",
            traditional:true,
            data: { projectname : $("#projectName").val() },
            dataType: 'json',
            success: function (result) {
                if (result) {
                    layer.msg('保存成功', {
                        icon: 1,
                        time: 800
                    }, function(){
                        parent.getRecodeTableData();
                        TaskCancel();
                    });
                } else {
                    alert("保存失败！！！")
                }
            }
        });
    }

    function TaskCancel() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }

    //时间格式化函数
    function getFormatTime(time) {
        var time = new Date(parseInt(time));
        var y = time.getFullYear();
        var m = time.getMonth() + 1;
        var d = time.getDate();
        var h = time.getHours();
        var mm = time.getMinutes();
        var s = time.getSeconds();
        return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm) + ':' + add0(s);
    }
    function add0(m) { return m < 10 ? '0' + m : m }
</script>

</html>
