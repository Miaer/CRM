<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>预约客户</title>
    <link href="/css/tail.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="/js/jquery-1.10.1.js"></script>
    <script src="/js/layer_v2.1/layer/layer.js"></script>
    <script>
        function change() {
            var height01 = $(window).height();
            $(".top").css('height', height01 - 35+"px");
        }
    </script>
</head>

<body style="border-radius: 8px" onload="change()">
<div class="top">
    <div>
        <div class="top_out">
            <table class="table">
                <tbody>
                <tr>
                    <td style="border-top: none" >客户名称<i class="i_start"></i></td>
                    <td  colspan = "6"  style="text-align: left;border-top: none">
                                <input disabled="disabled" value="${visitMap.company_name}"/>
                    </td>
                </tr>
                <tr>
                    <td style="border-top: none" >预约完成时间<i class="i_start"></i></td>
                    <td class="l_left" colspan = "6"  style="text-align: left;border-top: none">
                        <input id="time" placeholder="输入年月" value="${visitMap.visit_complete_time}" disabled="disabled" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 220px">洽谈事宜：</td>
                    <td colspan = "5" style="text-align: left"><textarea  id="arrangements" name="memo" style="width: 100%;height: 220px" disabled="disabled"> ${visitMap.visit_matters}</textarea></td>
                </tr>
                <tr>
                    <td style="line-height: 220px">备注：</td>
                    <td colspan = "5" style="text-align: left"><textarea  id="note" name="memo" style="width: 100%;height: 220px" disabled="disabled">${visitMap.visit_memo}</textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="bot_btn">
    </div>
</div>
</body>
<script src="/js/date/js/laydate.js"></script>
<script>
    !function(){
        laydate.skin('yalan');//切换皮肤，请查看skins下面皮肤库
    }();
</script>

</html>
