<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/css/base.css" />
    <link rel="stylesheet" href="/css/info-mgt.css" />
    <style>
        .layui-layer-title{background:url(/images/righttitlebig.png) repeat-x;font-weight:bold;color:#46647e; border:1px solid #c1d3de;height: 33px;line-height: 33px;}
        .tabe_bot label{width: 70px;text-align: right;font-size: 14px;font-weight: 900;color: #46647e}
        .l_left{float: left}
        .tabe_bot input,.tabe_bot  select{width: 180px;height: 30px;border-radius: 6px;margin:0 20px 0 0;border: none;border: 1px #ccc solid}
        .tabe_btn{width: 60px;height: 30px;background-color: #68b86c;border: none;border-radius: 6px;color: #fff}
    </style>
</head>

<body onload="Recodeload()">
<div class="table-operate ue-clear">
    <a href="#" class="add"  onclick="addRecode()">++</a>
</div>
<div class="table-box">
    <table id="table" class="table_style"></table>
</div>
<div class="pagination ue-clear"></div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>

<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-table.js"></script>
<script src="/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/js/date/js/laydate.js"></script>
<script src="/js/project_tail.js"></script>

<script>
    !function(){
        laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
        laydate({elem: '#demo'});//绑定元素
    }();
</script>
<script src="/js/layer_v2.1/layer/layer.js"></script>

<script>
    /*function init() {
        var currentID = parent.getCurrentID();
        if (currentID != "") {
            $.ajax({
                url: '/project/initProject?projectid=' + currentID,
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result) {
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

    }*/
</script>
</html>
