<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>任务详情</title>
    <link href="/css/tail.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="/js/jquery.js"></script>
    <script>
        function change() {
            var height01 = $(window).height();
            $(".top").css('height', height01 - 35+"px");
        }
    </script>
</head>

<body style="border-radius: 8px" onload="change()">
<!--<div class="title"><h2>通知详情</h2></div>-->
<form id="form_demo" >
<div class="top">
    <div>
        <div class="top_out">
            <table class="table" >
                <tbody>
                <tr>
                    <td>执行时间：</td>
                    <td  style="text-align: left"><input type="text" id="demo"></td>
                    <td style="border-top: none" >拜访客户：<i class="i_start"></i></td>
                    <td  colspan = "6"  style="text-align: left;border-top: none"><input type="text"  name ="name" minlength="2"  required id="no"></td>
                </tr>
                <tr>
                    <td style="border-top: none" >任务标题：<i class="i_start"></i></td>
                    <td  colspan = "6"  style="text-align: left;border-top: none"><input type="text"  name ="name" minlength="2"  required id="recodeTit"></td>
                </tr>
                <tr>
                    <td>审批状态：</td>
                    <td  style="text-align: left"><select>
                        <option>未审核</option>
                    </select></td>
                    <td>发布人：</td>
                    <td colspan = "3" style="text-align: left"><input type="text" class="long_text" id="person"></td>
                </tr>
                <tr>

                    <td>执行人：</td>
                    <td colspan = "3" style="text-align: left"><input type="text" class="long_text" id="playPeron"></td>
                </tr>
                <tr>
                    <td style="line-height: 240px">发布内容：</td>
                    <td colspan = "8" style="text-align: left"><textarea  id="note" style="width: 100%;height: 240px"></textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="bot_btn">
        <input class="btn" type="submit" value="保存" onclick="RecodeSave()"/>
        <button class="btn btn1"><a href="notice.html" target="right">返回</a></button>
    </div>
</div>
</form>
</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script >
    $.validator.setDefaults({
        submitHandler: function() {
            alert("修改成功");
        }
    });
    $().ready(function() {
        $("#form_demo").validate();
    });
</script>
<script src="/js/date/js/laydate.js"></script>
<script>
    var PROJECTID, TASKTYPE, TASKPHASE,  PERSONID
    !function () {
        laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
        laydate({ elem: '#demo' });//绑定元素
        laydate({ elem: '#demo1' });
        laydate({ elem: '#demo2' });//绑定元素
    }();
    function init() {
        if (parent.getCurrentID() != "") {
            $.ajax({
                url: '../TaskRecord/SearchTaskById?taskId=' + parent.getCurrentID(),
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    var data = result.data;
                    if (data) {
                        PROJECTID = data.PROJECTID
                        PERSONID =data.PERSONID
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
            url: "../TaskRecord/UpdateTask",
            data: {
                TASKID: parent.getCurrentID(),
                PROJECTID: PROJECTID,
                STATE: $("#tit").val(),
                STARTTIME: $("#demo").val(),

                PERSONID: PERSONID,
                NOTE: $("#NOTE").val(),
                TASKTYPE: $("#part").val(),
                TASKPHASE: $("#person").val(),

            },
            dataType: "json",
            success: function (result) {
                if (result.data) {
                    alert("保存成功！！！");
                    parent.getRecodeTableData();
                    TaskCancel();
                } else {
                    alert("保存失败！！！")
                }
            }
        })
    }
    function TaskCancel() {
        var index = parent.layer.getFrameIndex(window.name)
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
