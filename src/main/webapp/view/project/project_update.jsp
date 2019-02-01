<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>添加客户</title>
    <link href="/css/tail.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-1.10.1.js"></script>
    <script src="/js/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
    <script src="/js/layer_v2.1/layer/layer.js"></script>
    <script src="/js/date/js/laydate.js"></script>
    <script src="/js/project_update.js"></script>

</head>

<body style="border-radius: 8px" onload="change(),init(),initPro(),initUser1(),initUser2()">
<form id="form_demo" >
<div class="top">
    <div>
        <div class="top_out">
            <table class="table" >
                <tbody>
                    <tr>
                        <td>认购日期：<i class="i_start"></i></td>
                        <td  style="text-align: left"><input type="text"  name ="id" minlength="2" id="invest_date"></td>
                        <td>投资人：</td>
                        <%--此处选择已有的客户，如果没有，让用户选择添加--%>
                        <td colspan = "1" style="text-align: left">
                            <select id="customerid" onchange="init(),initUser1(),initUser2()">
                                <c:forEach items="${customerList}" var="customer">
                                    <option value="${customer.id}">${customer.companyName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>投资额：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="invest_amount"></td>
                        <td>认购费：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="invest_fee"></td>
                        <td>汇总：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="collect"></td>
                    </tr>
                    <tr>
                        <td>备注1：</td>
                        <td colspan = "9" style="text-align: left"><textarea  id="project_memo" style="width: 100%;height: 150px"></textarea></td>
                    </tr>
                    <%---------------------客户相关--------------------%>
                    <tr>
                        <td>身份证号：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="identification"></td>
                        <td >联系电话：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="person_phone"></td>
                        <td >其他联系方式：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="person_phone2"></td>
                    </tr>

                    <tr>
                        <td >住所：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="home_address"></td>
                        <td >个人可投资产量级：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="assert_volumn"></td>
                        <td >所在公司：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="person_company"></td>
                    </tr>

                    <tr>
                        <td >职务：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="person_positoin"></td>
                    </tr>

                    <%--------------------理财师1-------------------------------%>
                    <tr>
                        <td >姓名：</td>
                        <td colspan = "1" style="text-align: left">
                        <select id="user1Name" onchange="initUser1()">
                            <c:forEach items="${userList}" var="user">
                                <%--<option selected="selected" disabled="disabled"  style='display: none' value=''></option>--%>
                                <option value="${user.id}">${user.name}</option>
                            </c:forEach>
                        </select>
                        </td>
                        <td >前端费用：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="user_fee1"></td>
                        <td >后端费用：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="user_fee2"></td>
                    </tr>

                    <%--------------------理财师2-------------------------------%>

                    <tr>

                        <td >姓名：</td>
                        <td colspan = "1" style="text-align: left">
                        <select id="user2Name" onchange="initUser2()">
                            <c:forEach items="${userList}" var="user">
                               <%-- <option selected="selected" disabled="disabled"  style='display: none' value=''></option>--%>
                                <option value="${user.id}">${user.name}</option>
                            </c:forEach>
                        </select>
                        </td>
                        <td >前端费用：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="user2_fee1"></td>
                        <td >后端费用：</td>
                        <td colspan = "1" style="text-align: left"><input type="text" class="long_text" id="user2_fee2"></td>
                    </tr>

                    <tr>
                        <td>备注2：</td>
                        <td colspan = "9" style="text-align: left"><textarea  id="cumemo" style="width: 100%;height: 150px"></textarea></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="bot_btn">
        <input class="btn" type="submit" value="保存"/>
        <button class="btn btn1"><a onclick="layerClose()" target="right">返回</a></button>
    </div>
</div>
</form>
<script src="/js/schedule.js"></script>
<script>
    !function () {
        laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
        laydate({ elem: '#invest_date' });//绑定元素
    }();

</script>
</body>

</html>
