<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增客户</title>
    <link href="/css/tail.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="/js/jquery-1.10.1.js"></script>
    <script src="/js/schedule.js"></script>
    <script>
        function change() {
            var height01 = $(window).height();
            $(".top").css('height', height01 - 35+"px");
        }

        /*初始化修改页面*/
        $(function () {
            var currentID = parent.getCurrentID();
            if(currentID != null && currentID != ""){
                $.ajax({
                    url:"/customer/findCustomerById?id="+currentID,
                    success:function (data) {
                        $("#companyName").val(data.company_name);
                        $("#customerType").val(data.customer_type);
                        $("#personName").val(data.person_name);
                        $("#personPhone").val(data.person_phone);
                        $("#address").val(data.address);
                        $("#personPositoin").val(data.person_positoin);
                        $("#memo").val(data.memo);
                    }
                })
            }
        })
    </script>
</head>

<body style="border-radius: 8px" onload="change()">
<form id="form_demo">
<div class="top">
    <div>
        <div class="top_out">
            <table class="table" >
                <tbody>
                <tr>
                    <td style="border-top: none" >客户名称<i class="i_start"></i></td>
                    <td  colspan = "6"  style="text-align: left;border-top: none"><input type="text" minlength="2"  required id="companyName"></td>
                </tr>
                <tr>
                    <td>客户类型：<i class="i_start"></i></td>
                   <td colspan="6">
                       <select style="width: 40%" id="customerType">
                           <c:forEach items="${customerAll}" var="customer">
                               <option value="${customer.id}" id="customer_type">${customer.name}</option>
                           </c:forEach>
                        </select>
                   </td>
                </tr>
                <tr>
                    <td>联系人：</td>
                    <td colspan = "3" style="text-align: left"><input type="text" class="long_text" id="personName"></td>
                    <td>联系电话：</td>
                    <td colspan = "3" style="text-align: left"><input type="text" class="long_text" id="personPhone"></td>
                </tr>
                <tr>
                    <td>地址：</td>
                    <td colspan = "3" style="text-align: left"><input type="text" class="long_text" id="address"></td>
                    <td>职务：</td>
                    <td colspan = "3" style="text-align: left"><input type="text" class="long_text" id="personPositoin"></td>
                </tr>
                <tr>
                    <td style="line-height: 220px">备注：</td>
                    <td colspan = "5" style="text-align: left"><textarea  id="memo"style="width: 100%;height: 220px"></textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="bot_btn">
        <%--TaskCancel--%>
        <input class="btn" onclick="TaskCancel()" value="保存"/>
        <button class="btn btn1"><a onclick="layerClose()" target="right">返回</a></button>
    </div>
</div>
</form>
</body>

<script >
    /*
    * 提交表单，新增『 parent.getCurrentID() 』没有值，修改客户数据是有值的。依此在前端进行请求分发。
    * */
    function TaskCancel() {

        var currentID = parent.getCurrentID();

        var companyName = $("#companyName").val();
        var customerType = $("#customerType").val();
        var personName = $("#personName").val();
        var personPhone = $("#personPhone").val();
        var address = $("#address").val();
        var personPositoin = $("#personPositoin").val();
        var memo = $("#memo").val();

        if (currentID == null && currentID == ""){
            $.ajax({
                url:"/customer/InsertCustomer",
                data:{
                    companyName:companyName,
                    customerType:customerType,
                    personName:personName,
                    personPhone:personPhone,
                    address:address,
                    personPositoin:personPositoin,
                    memo:memo
                },
                success: function (data) {
                    if (data){
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
                        layer.alert('添加失败');
                    }
                }
            });
        }else {
            $.ajax({
                url:"/customer/updateCustomer",
                data:{
                    id:currentID,
                    companyName:companyName,
                    customerType:customerType,
                    personName:personName,
                    personPhone:personPhone,
                    address:address,
                    personPositoin:personPositoin,
                    memo:memo
                },
                success: function (data) {
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
                    }else {
                        layer.alert('修改失败');
                    }
                }
            });
        }

    }
</script>
<script src="/js/layer_v2.1/layer/layer.js"></script>
<script src="/js/date/js/laydate.js"></script>
</html>
