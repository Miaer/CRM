<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>橙叶投资管理系统</title>
    <script src="/js/jquery-1.10.1.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/temp.css">

    <script src="/js/layer_v2.1/layer/layer.js"></script>
</head>
<body class="bg-primary">
<div class="container d-flex h-100">
    <div class="row">
        <div class="single-page construction-bg cover-image">
            <div class="row p-5">
                <div class="col-lg-6 align-self-center">
                    <div class="wrapper wrapper2">
                        <form id="login" class="card-body" tabindex="500" method="post" action="/user/login">
                            <div class="mail">
                                <input type="text" id="userName" name="userName">
                                <label>用户名</label>
                            </div>
                            <div class="passwd">
                                <input type="password" id="userPass" name="password">
                                <label>密码</label>
                            </div>
                            <div class="submit">
                                <button class="btn btn-primary btn-block" type="submit">登陆</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-lg-6 align-self-center">
                    <div class="text-right">
                        <%--<img src="/images/logo-white.png" alt="logo">--%>
                        <h5 class="text-center">橙叶投资管理系统</h5>
                        <p class="text-right">
                            ————立足中国 放眼全球
                        </p>
                        <p class="text-left" style="text-indent: 28px">
                            我们通过自身丰富的投资和管理经验，致力于投资高成长性企业，同时为企业提供市场对接、资源整合等方面的支持，我们与全球重要券商、投行、资本等建立密切联系，为国内企业提供海内外融资、上市的多种通路，多方位支持企业成长与发展。
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
