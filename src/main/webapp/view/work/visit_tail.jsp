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

<body style="border-radius: 8px" onload="init()">
<div class="top">
    <div>
        <div class="top_out">
            <table class="table">
                <tbody>
                <tr>
                    <td style="border-top: none" >客户名称<i class="i_start"></i></td>
                    <td  colspan = "6"  style="text-align: left;border-top: none">
                        <select id="tit"></select>
                    </td>
                </tr>
                <tr>
                    <td style="border-top: none" >预约完成时间<i class="i_start"></i></td>
                    <td class="l_left" colspan = "6"  style="text-align: left;border-top: none">
                        <input id="time" placeholder="输入年月" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 220px">洽谈事宜：</td>
                    <td colspan = "5" style="text-align: left"><textarea  id="arrangements" name="memo" style="width: 100%;height: 220px"></textarea></td>
                </tr>
                <tr>
                    <td style="line-height: 220px">备注：</td>
                    <td colspan = "5" style="text-align: left"><textarea  id="note" name="memo" style="width: 100%;height: 220px"></textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="bot_btn">
        <input class="btn" onclick="submit()" value="预约"/>
        <button class="btn btn1"><a onclick="layer.close(index)" target="right">返回</a></button>
    </div>
</div>
</body>

<script >
    /**、
     * 添加一条拜访记录
     */
    function submit(){
        var customerId = $("#tit option:selected").val();
        var visitCompleteTime = $("#time").val();
        var visitMatters = $("#arrangements").val();
        var visitMemo = $("#note").val();


        $.ajax({
            url: "/visit/addVisitRecords",
            type:"post",
            data:{
                customerId:customerId,
                visitCompleteTime:visitCompleteTime,
                visitMatters:visitMatters,
                visitMemo:visitMemo
            },
            success:function (data) {
                if (data){
                    layer.open({
                        anim:1,
                        title: '添加拜访记录成功',
                        closeBtn:1,
                        content: '添加拜访记录成功',
                        yes:function(){             //确定按钮回调方法
                            parent.location.reload();
                        }
                    });
                }
            }
        });

    }
</script>
<script src="/js/date/js/laydate.js"></script>
<script>
    var PROJECTID, TASKTYPE, TASKPHASE,  PERSONID;
    !function(){
        laydate.skin('yalan');//切换皮肤，请查看skins下面皮肤库
        /*laydate({
            elem: '#arrangementstime'},
        );//绑定元素*/
    }();

    function init() {
        var visit = parent.getVisit();
        alert(visit);
            $.ajax({
                url: '/visit/findCustomer',
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    if (result != null) {

                        for (i = 0;i < result.length;i++){
                            $("#tit").append("<option value='"+result[i].id+"'>"+result[i].companyName+"</option>");
                        }


                    }
                    else {
                        alert("获取失败！");
                    }
                },
                error: function (err) {
                }
            })
    }

</script>

</html>
