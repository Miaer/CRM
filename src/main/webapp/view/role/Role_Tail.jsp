﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%--<link href="/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />--%>
    <link rel="stylesheet" href="/css/base.css" />
    <link rel="stylesheet" href="/css/info-mgt.css" />
    <style>
        .tree_left {
            box-sizing: border-box;
            overflow-y: auto;
            width: 100%;
        }

            .tree_left h6, .tree_right h6 {
                line-height: 30px;
                font-weight: bold;
                padding-left: 6px;
                color: #000;
                font-size: 14px;
            }

        .tree_left_top {
            margin: 10px 0 0 0;
        }

        .tree_input input {
            border: 1px #ccc solid;
            margin: 3px 0 0 0px;
            border-radius: 4px;
        }
        .bot_btn{width: 150px;height: 30px;margin: 20px 0 0 34%}
        .bot_btn .btn{width: 60px;height: 30px;background-color: #68b86c;border: none;border-radius: 6px;color: #fff;margin-right: 15px}
        .bot_btn .btn1{background-color: #c1d3de;border: none;border-radius: 6px;color: #000;margin-right: 0}
    </style>
</head>
<body>
    <div class="tree_left l_left">
        <div class="tree_left_top">
            <div class="person_Box" style="margin:0 0 10px 20px">
                <label style="width:50px;font-size:13px;line-height:34px">角色名称：</label><input type="text" style="margin:0 10px;width:70%;border-radius:4px;height:34px;line-height:34px;border:1px #ccc solid" id="RoleName" />
            </div>
            <%--<div class="person_Box" style="margin:0 0 10px 20px">
                <label style="width:50px;font-size:13px;line-height:34px">角色描述：</label><textarea type="text" style="margin:0 10px;width:70%;border-radius:4px;height:100px;line-height:34px;border:1px #ccc solid" id="RoleMS" ></textarea>
            </div>--%>
            <div class="bot_btn">
                <button class="btn" onclick="RoleSave()">保存</button>
                <button class="btn btn1" onclick="checkCancel()">取消</button>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script>
    function RoleSave() {
        var RoleName = $("#RoleName").val();
        $.ajax({
            type: "POST",
            url: '/role/addRole',
            dataType: "json",
            data: {name: RoleName},
            success: function (result) {
                if (result) {
                    alert("保存成功");
                    parent.location.href="/role/toRolePage";
                    /* parent.getRoleTableData();
                     checkCancel();*/
                }
            }
        });
    }
</script>
<script>
   /* var sendID, sendName, zTree, nodes
    $(function () {
        Tree()
    })
    //DOM树初始化setting设置
    var setting = {
        check: {
            enable: true
        },
        data: {
            key: { name: "MODULENAME" },
            simpleData: {
                enable: true,
                idKey: "MODULEID",
                pIdKey: "pIdKey",
                rootPId: null
            }
        },
        callback: {
            onCheck: onCheck
        }
    };

    //加载DOM树数据方法
    function Tree() {
        $.ajax({
            type: "POST",
            url: '../RoleManage/GetFunctionModuleList',
            dataType: "json",
            success: function (result) {
                if (result) {
                    var nodes = result.data;
                    var item = { "MODULENAME": "权限目录", "pIdKey": "-1" };
                    nodes.push(item);
                    $.fn.zTree.init($("#treeDemo"), setting, nodes);
                    zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    zTree.expandAll(true);
                }
            }
        });
    }
    function onCheck(e, treeId, treeNode) {
        nodes = zTree.getCheckedNodes()
    };

    function checkCancel() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }*/
</script>
</html>
