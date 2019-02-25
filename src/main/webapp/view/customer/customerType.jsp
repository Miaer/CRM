<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/css/base.css" />
    <link rel="stylesheet" href="/css/info-mgt.css" />
    <style>
        .tree_input input{border: 1px #ccc solid;margin:3px 0 0 10px;border-radius: 4px;
        }
        .tabe_bot label,.right label{width: 70px;text-align: right;font-size: 14px;font-weight: 900;color: #46647e}
        .l_left{float: left}
        .tabe_bot input,.tabe_bot  select,.right input,.right input{width: 180px;height: 30px;border-radius: 6px;margin:0 20px 0 0;border: none;border: 1px #ccc solid}
        .right textarea{width: 80%;height: 60px;resize: none;border-radius: 6px;margin-bottom: 20px;border: 1px #ccc solid}
    </style>
    <title>客户类型列表</title>
</head>

<body onload="change(),Roleload()">
<div class="title"><h2>客户类型管理</h2></div>
<div class="l_left" style="width: 100%;margin-right: 1%">
<div class="table-operate ue-clear">
    <a href="javascript:;" class="add" onclick="addCustomerType()" >添加</a>
    <a href="javascript:;" class="del" onclick="delRole(id)">删除</a>

</div>
<div class="table-box">
    <table id="table" class="table_style"></table>
</div>
<div class="pagination ue-clear"></div>
</div>
</body>
<script type="text/javascript" src="/js/jquery-1.10.1.js"></script>
<script src="/js/customerType.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-table.js"></script>
<script src="/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/js/layer_v2.1/layer/layer.js"></script>
<script>
</script>
</html>
