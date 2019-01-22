<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户更新</title>
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

<form id="form_demo" >
<div class="top">
    <div>
        <div class="top_out">
            <table class="table" >
                <tbody>

                <tr>
                    <td style="border-top: none" >用户名：<i class="i_start"></i></td>
                    <td  colspan = "6"  style="text-align: left;border-top: none"><input type="text"  name ="name" minlength="2"  required id="recodeTit"></td>
                </tr>
                <tr>
                    <td>角色：</td>
                    <td  style="text-align: left">
                        <select id="roleOption">
                            <c:forEach items="${roleAll}" var="role">
                                <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <td>用户密码：</td>
                <td colspan = "3" style="text-align: left"><input type="password" class="long_text" id="person"></td>
                <tr>
                    <td>再次输入密码：</td>
                    <td colspan = "3" style="text-align: left"><input type="password" class="long_text" id="againPerson"></td>
                </tr>
                <tr>
                    <td style="line-height: 200px">备注：</td>
                    <td colspan = "2" style="text-align: left"><textarea  id="note" style="width: 100%;height: 200px"></textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="bot_btn">
        <input class="btn" type="submit" value="保存" onclick="RecodeSave()"/>
        <button class="btn btn1"><a href="/view/user/person.jsp" target="right">返回</a></button>

    </div>
</div>
</form>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script >
    /*$.validator.setDefaults({
        submitHandler: function() {
            alert("修改成功");
        }
    });
    $().ready(function() {
        $("#form_demo").validate();
    });*/
</script>
<script src="/js/date/js/laydate.js"></script>
<script>
    var PROJECTID, TASKTYPE, TASKPHASE,  PERSONID
    !function () {
        laydate.skin('danlan');//切换皮肤，请查看skins下面皮肤库
        laydate({ elem: '#demo' });//绑定元素
        laydate({ elem: '#demo1' });
        laydate({ elem: '#demo2' });//绑定元素
    }();
    /*function init() {
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

    }*/
    function RecodeSave() {
        var user = {
            userName : $("#recodeTit").val(),
            role:$("#roleOption option:selected").val(),
            password: $("#person").val(),
            againPassword: $("#againPerson").val(),
            note: $("#note").val(),
        };
        $.ajax({
            type: "POST",
            url: "/customer/addCustomer",
            data: {
                name : $("#recodeTit").val(),
                role:$("#roleOption option:selected").val(),
                password: $("#person").val(),
                againPassword: $("#againPerson").val(),
                note: $("#note").val(),
            },
            dataType: "json",
            success: function (result) {
                alert(result);
                if (result) {
                    alert("保存成功！！！");
                    /*parent.getRecodeTableData();
                    TaskCancel();*/
                    parent.location.href="/view/user/person.jsp";
                } else {
                    alert("保存失败！！！");
                }
            }
        })
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
