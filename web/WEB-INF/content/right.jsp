<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/28
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/right.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
    <style>



    </style>

</head>

<body>
<div class="mright">
    <div style="margin-top: 40px">
        <a href="personInfAction.action" class="abasic" style="text-decoration: none;"><i class="fas fa-user"></i>个人中心</a>
    </div>

    <div class="rborder"></div>

    <div class="rbasic">
        <a href="shopCartAction.action" class="abasic"><i class="fas fa-shopping-basket"></i>购物车</a>
    </div>

    <div class="rborder"></div>

    <div class="rbasic">
        <a href="favoriteAction.action" class="abasic"><i class="fas fa-heart"></i>收藏</a>
    </div>

    <div class="rborder"></div>

    <div class="rbasic">
        <a href="<%=request.getContextPath()%>/feedback.jsp" class="abasic"><i class="fas fa-comment-alt"></i>反馈</a>
    </div>
</div>
</body>

</html>
