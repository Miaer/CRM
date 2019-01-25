<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>橙叶 CRM</title>

    <script src="/js/jquery-1.10.1.js"></script>
    <%--<script src="/js/Login.js"></script>--%>
    <!--Bootstrap.min css-->
    <%--<link rel="stylesheet" href="/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="/css/bootstrap4/css/bootstrap.min.css">
    <!--Icons css-->
    <%--<link rel="stylesheet" href="/css/icons.css">--%>
    <!--Style css-->
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/chengye.css">
</head>
<body class="bg-primary zborder">
    <div class="container zcenter">
        <div class="row h-100">
            <div class="single-page construction-bg cover-image">
                <div class="row p-4">
                    <div class="col-lg-6">
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
                                    <!--<a class="btn btn-primary btn-block" onclick="Login_method();">Login</a>-->
                                    <button class="btn btn-primary btn-block" type="submit">登陆</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-6 align-self-center">
                        <div class="log-wrapper text-center">
                            <img src="/img/logo-white.png" class="mb-2 mt-4 mt-lg-0 " alt="logo">
                            <p>
                                立足中国 放眼全球
                            </p>
                            <p class="text-left">
                                我们通过自身丰富的投资和管理经验，致力于投资高成长性企业，同时为企业提供市场对接、资源整合等方面的支持，我们与全球重要券商、投行、资本等建立密切联系，为国内企业提供海内外融资、上市的多种通路，多方位支持企业成长与发展。
                            </p>
                            <%--<a class="btn btn-primary mt-3" href="#">Read More</a>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>