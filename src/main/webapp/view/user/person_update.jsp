<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
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
                    <td  colspan = "6"  style="text-align: left;border-top: none">
                        <input type="text"  name ="name" minlength="2" id="recodeTit" value="${user.name}">
                    </td>
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
                <td colspan = "3" style="text-align: left"><input type="password" class="long_text" id="person" value="${user.password}"></td>
                <tr>
                    <td>再次输入密码：</td>
                    <td colspan = "3" style="text-align: left"><input type="password" class="long_text" id="againPerson" value="${user.password}"></td>
                </tr>
                <tr>
                    <td style="line-height: 200px">备注：</td>
                    <td colspan = "2" style="text-align: left"><textarea  id="note" style="width: 100%;height: 200px">${user.node}</textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="bot_btn">
        <input class="btn" value="更新" onclick="eidtUser()"/>
        <button class="btn btn1"><a href="/view/user/person.jsp" target="right">返回</a></button>
    </div>
</div>
</form>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script src="/js/person.js"></script>
<script src="/js/date/js/laydate.js"></script>
<script src="/js/layer_v2.1/layer/layer.js"></script>
<script>
    !function () {
        laydate.skin('danlan');//切换皮肤，请查看skins下面皮肤库
        laydate({ elem: '#demo' });//绑定元素
        laydate({ elem: '#demo1' });
        laydate({ elem: '#demo2' });//绑定元素
    }();

    function eidtUser(){
        $.ajax({
            url: "/user/updateUser",
            type:"post",
            data:{
                id : parent.getCurrentID(),
                userName : $("#recodeTit").val(),
                role:$("#roleOption option:selected").val(),
                password: $("#person").val(),
                againPassword: $("#againPerson").val(),
                note: $("#note").val(),
            },
            success:function (data) {
                if (data){
                    layer.open({
                        anim:1,
                        title: '修改信息',
                        closeBtn:1,
                        content: '修改成功',
                        yes:function(){             //确定按钮回调方法
                            parent.location.reload();
                        }
                    });
                }
                layer.msg("修改失败");
                parent.location.reload();
            }
        });

    }
</script>
</html>
