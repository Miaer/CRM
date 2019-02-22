<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户详情</title>
    <link href="/css/tail.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script src="/js/schedule.js"></script>
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
        <button class="btn btn1"><a onclick="layerClose()" target="right">返回</a></button>
    </div>
</div>
</form>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script src="/js/layer_v2.1/layer/layer.js"></script>
<script src="/js/date/js/laydate.js"></script>
<script>
    function RecodeSave() {
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
                if (result){
                    layer.open({
                        anim:1,
                        title: '添加信息',
                        closeBtn:1,
                        content: '添加成功',
                        yes:function(){             //确定按钮回调方法
                            parent.location.reload();
                        }
                    });
                }else {
                    alert("添加失败");
                }
            }
        });
    }
</script>

</html>
