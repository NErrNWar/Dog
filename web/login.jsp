<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/8
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="images/favicon.png" type="image/png">

    <title>享受生活</title>

    <link href="<%=request.getContextPath()%>/css/style.default.css" rel="stylesheet">


</head>

<body class="signin">

<section>

    <div class="signinpanel">

        <div class="row">

            <div class="col-md-6">

                <div class="signin-info " >
                    <div class="logopanel">
                        <h1><span> Dog </span></h1>
                    </div>

                    <div class="mb20"></div>

                    <h3><strong>欢迎来到Dog!</strong></h3>

                    <img src="images/gouzi.png" style="height: 217px;width:333px;">
                    <div class="mb20"></div>
                    <strong>还没注册? <a href="sigup.jsp" style="text-decoration: none">注册</a></strong>
                </div>

            </div><!-- col-sm-7 -->

            <div class="col-md-5">

                <form method="post" action="loginAction">
                    <h4 class="nomargin">登录</h4>
                    <p class="mt5 mb20">登录你的账号.</p>

                    <input type="text" class="form-control uname" placeholder="用户名" name="username" required autofocus/>
                    <input type="password" class="form-control pword" placeholder="密码" name="password" required/>
                    <a href="forgetpas.jsp" style="text-decoration: none"><small>忘记密码?</small></a>
                    <small>
                        <s:if test="error!=null">
                            <p class="text-danger">错误信息:${error}</p>
                        </s:if>
                    </small>
                    <button class="btn btn-success btn-block">登录</button>

                </form>
            </div>

        </div>

        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2018. All Rights Reserved. Ybh corporation.
            </div>
            <div class="pull-right">
                Created By:ybh
            </div>
        </div>

    </div>

</section>


<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-migrate-1.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/modernizr.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.sparkline.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.cookies.js"></script>

<script src="<%=request.getContextPath()%>/js/toggles.min.js"></script>
<script src="<%=request.getContextPath()%>/js/retina.min.js"></script>

<script src="<%=request.getContextPath()%>/js/custom.js"></script>
<script>
    jQuery(document).ready(function(){

        // Please do not use the code below
        // This is for demo purposes only
        var c = jQuery.cookie('change-skin');
        if (c && c == 'greyjoy') {
            jQuery('.btn-success').addClass('btn-orange').removeClass('btn-success');
        } else if(c && c == 'dodgerblue') {
            jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
        } else if (c && c == 'katniss') {
            jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
        }
    });
</script>

</body>
</html>
