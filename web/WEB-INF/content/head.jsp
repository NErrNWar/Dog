<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/12
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/head.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

    <style>


    </style>
</head>
<body>
<div id="head" class="head" style="background-color: rgba(0, 86, 162, 0.28)">

    <div class="searchitem" style="margin-left: 3%;width: 12%">
        <span class="mcontent"><a href="index.jsp" style="text-decoration: none;color:black;">欢迎来到Dog</a></span>
    </div>

    <div class="searchitem">
        <s:if test="#session.user==null">
            <a href="<%=request.getContextPath()%>/login.jsp" class="mcontent" style="text-decoration: none;display: block;">登录</a>
        </s:if>
        <s:else>
            <a href="personInfAction.action" class="mcontent" style="text-decoration: none;display: block;">${session.user.username}</a>
        </s:else>
    </div>

    <div class="searchitem">
        <s:if test="#session.user==null">
            <a href="<%=request.getContextPath()%>/sigup.jsp" class="mcontent" style="text-decoration: none;display: block;">注册</a>
        </s:if>
        <s:else>
            <a href="#" id="logout" class="mcontent" style="text-decoration: none;display: block;">退出</a>
        </s:else>
    </div>

    <div class="searchitem" style="margin-left: 38%">
        <a href="personInfAction.action" class="mcontent" style="text-decoration: none;display: block;">
            <i class="fas fa-user" style="margin-right: 5px;"></i>个人中心
        </a>
    </div>

    <div class="searchitem" style="">
        <a href="shopCartAction.action" class="mcontent" style="text-decoration: none;display: block;">
            <span class="glyphicon glyphicon-shopping-cart" style="margin-right: 5px;"></span>购物车
        </a>
    </div>

    <div class="searchitem">
        <a href="favoriteAction.action" class="mcontent" style="text-decoration: none;display: block;">
            <i class="fas fa-star" style="margin-right: 5px;"></i>我的收藏
        </a>
    </div>

</div>

<div class="modal fade" id="logoutmodal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm" role="document" style="width: 300px;height:270px;margin-left: 38%;margin-top: 10%;">
        <div class="">
            <div class="modal-header" style="background-color: #afc1ff;">
                <h3>退出</h3>
            </div>
            <div class="modal-body"  style="background-color: #afc1ff;">
                <h4 class="text-danger">确认退出!</h4>
            </div>
            <div class="modal-footer"  style="background-color: #afc1ff;">
                <button class="btn btn-info" data-dismiss="modal">取消</button>
                <button id="surelogout" class="btn btn-danger">退出</button>
            </div>
        </div>
    </div>
</div>


<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>
    (function(){
        $("#logout").click(function(){
            $("#logoutmodal").modal("show");
        });

        $("#surelogout").click(function(){
            window.location.href="logoutAction";
        });

        $(window).scroll(function(){
            var top=$(this).scrollTop();
            var sel=$("#head");
            if(top-80<0){
                sel.css("display","block");
            }
            else{
                sel.css("display","none");
            }
        });
    })();
</script>
</body>
</html>
