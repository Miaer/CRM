<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Kharna-Admin Dashboard</title>

    <script src="/js/jquery-1.10.1.js"></script>
    <%--<script src="/js/Login.js"></script>--%>
    <!--Bootstrap.min css-->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <!--Icons css-->
    <%--<link rel="stylesheet" href="/css/icons.css">--%>
    <!--Style css-->
    <link rel="stylesheet" href="/css/style.css">
</head>

<body class="bg-primary">
${msg}
<div id="app">
    <section class="section section-2">
        <div class="container">
            <div class="row">
                <div class="single-page single-pageimage construction-bg cover-image "
                     data-image-src="/images/img14.jpg">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="wrapper wrapper2">
                                <form id="login" class="card-body" tabindex="500" method="post" action="/user/login">
                                    <h3>Login</h3>
                                    <div class="mail">
                                        <input type="text" id="userName" name="userName">
                                        <label>Mail or Username</label>
                                    </div>
                                    <div class="passwd">
                                        <input type="password" id="userPass" name="password">
                                        <label>Password</label>
                                    </div>
                                    <div class="submit">
                                        <!--<a class="btn btn-primary btn-block" onclick="Login_method();">Login</a>-->
                                        <button class="btn btn-primary btn-block" type="submit">Login</button>
                                    </div>
                                    <p class="mb-2"><a href="forgot.html">Forgot Password</a></p>
                                    <p class="text-dark mb-0">Don't have account?<a href="register.html"
                                                                                    class="text-primary ml-1">Sign
                                        UP</a></p>
                                </form>
                            </div>
                        </div>
                        123456
                        <div class="col-lg-6">
                            <div class="log-wrapper text-center">
                                <img src="/images/logo-white.png" class="mb-2 mt-4 mt-lg-0 " alt="logo">
                                <p>There are many variations of passages of Lorem Ipsum available, but the majority have
                                    suffered alteration in some form, by injected humour, or randomised words which
                                    don't look even slightly believable. If you are going to use a passage of Lorem
                                    Ipsum, you need to be sure</p>
                                <a class="btn btn-primary mt-3" href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>