<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/css/base.css" />
<link rel="stylesheet" type="text/css" href="/css/jquery.dialog.css" />
<link rel="stylesheet" href="/css/index.css" />
    <%--<style>
        .layui-layer-title{background:url(/images/righttitlebig.png) repeat-x;font-weight:bold;color:#46647e; border:1px solid #c1d3de;height: 33px;line-height: 33px;}
    </style>--%>
<title>橙叶投资管理系统</title>
</head>
<body>
<div id="container">
	<div id="hd">
    	<div class="hd-wrap ue-clear">
        	<div class="top-light"></div>
            <%--<h1 class="logo"></h1>--%>
            <div class="login-info ue-clear">
                <div class="welcome ue-clear"><span>欢迎您,</span><a href="javascript:void(0)" class="user-name">${sessionScope.uname}</a></div>
                <%--<div class="login-msg ue-clear">
                    <a href="javascript:void(0)" class="msg-txt">消息</a>
                    <a href="javascript:void(0)" class="msg-num">10</a>
                </div>--%>
            </div>
            <div class="toolbar ue-clear">
                <a href="/user/toHome" class="home-btn" target="right">首页</a>
                <a href="javascript:void(0)" class="home-btn1" target="right" onclick="openlayer()">修改密码</a>
                <a href="javascript:void(0)" class="quit-btn exit home-btn">退出</a>
            </div>
        </div>
    </div>
    <div id="bd">
    	<div class="wrap ue-clear">
        	<div class="sidebar">
            	<h2 class="sidebar-header"><p>功能导航</p></h2>
                <ul class="nav">
                	<%--<li class="office current">
                        <div class="nav-header">
                            <a href="/user/toHome" target="right" class="ue-clear">
                                <span>首页</span>
                                <i class="icon"></i>
                            </a>
                        </div>
                    </li>--%>
                    <li class="email">
                        <div class="nav-header">
                            <a href="/customer/toCustomerPage"class="ue-clear"  target="right">
                                <span>客户管理</span>
                                <i class="icon"></i>
                            </a>
                        </div>
                    </li>
                    <li class="land">
                        <div class="nav-header">
                            <a href="JavaScript:;" class="ue-clear" >
                                <span>日常办公</span>
                                <i class="icon hasChild"></i>
                            </a>
                        </div>
                        <ul class="subnav">
                            <%--<li><a href="/view/work/work.jsp" target="right">工作日志</a></li>
                            <li><a href="mywork.html" target="right">我的工作日志</a></li>--%>
                            <%--<li><a href="recode.html" target="right">定制任务</a></li>--%>
                            <li><a href="/view/work/schedule.jsp" target="right">预约拜访客户</a></li>
                            <li><a href="/view/work/visit.jsp" target="right">拜访记录</a></li>
                        </ul>
                    </li>
                    <%--<li class="train">
                        <div class="nav-header">
                            <a href="JavaScript:;" class="ue-clear" >
                                <span>培训管理</span>
                                <i class="icon hasChild"></i>
                            </a>
                        </div>
                        <ul class="subnav">
                            <li><a href="mytrain.html" target="right">我的培训</a></li>
                            <li><a href="train.html" target="right">培训管理</a></li>
                        </ul>
                    </li>--%>

                    <li class="list_m" id="disappear1">
                        <div class="nav-header" >
                        <a href="/view/project/project.jsp" class="ue-clear" target="right">
                        <span>项目管理</span>
                            <i class="icon"></i>
                        </a></div>
                    </li>
                    <%--<li class="news_m"><div class="nav-header"><a href="notice.html" class="ue-clear"  target="right"><span>统计分析</span><i class="icon"></i></a></div></li>--%>
                    <%--<li class="dossier"><div class="nav-header"><a href="JavaScript:;" class="ue-clear" ><span>档案管理</span><i class="icon hasChild"></i></a></div>
                        <ul class="subnav">
                            <li><a href="dossier_my.html" target="right">个人档案</a></li>
                            <li><a href="dossier_puplic.html" target="right">公共档案</a></li>

                        </ul>
                    </li>--%>
                    <li class="part" id="disappear">
                        <div class="nav-header">
                            <a href="JavaScript:;" class="ue-clear" >
                                <span>系统管理</span>
                                <i class="icon hasChild"></i>
                            </a>
                        </div>
                        <ul class="subnav">
                            <%--?后面默认都是传的String类型的参数   如果想要其他类型 需要转--%>
                            <li><a href="/user/toCustomerPage" target="right">用户管理</a></li>
                            <%--<li><a href="part.html" target="right">部门管理</a></li>--%>
                            <li><a href="/role/toRolePage" target="right">角色管理</a></li>
                            <li><a href="/customerType/toCustomerType" target="right">客户类型管理</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="content">
            	<iframe src="/user/toHome" id="iframe" width="100%" height="100%" frameborder="0" name="right" style="min-width: 1100px"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="exitDialog" style="display:none">
    <div class="dialog-content">
        <div class="ui-dialog-icon"></div>
        <div class="ui-dialog-text">
            <p class="dialog-content">你确定要退出系统？</p>
            <p class="tips">如果是请点击“确定”，否则点“取消”</p>
            <div class="buttons">
                <input type="button" class="button long2 ok" value="确定" />
                <input type="button" class="button long2 normal" value="取消" />
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/core.js"></script>
<script type="text/javascript" src="/js/jquery.dialog.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
<script src="/js/layer_v2.1/layer/layer.js"></script>
<script>
     $(function () {
         $.ajax({
             url:"/user/findRoleByUserId",
             success:function (data) {
                 if (data == "理财师"){
                    $("#disappear").css('visibility','hidden');
                    $("#disappear1").css('visibility','hidden');
                 }
             }
         });
     });
</script>
</html>
