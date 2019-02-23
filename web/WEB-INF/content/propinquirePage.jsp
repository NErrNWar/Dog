<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/28
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询结果</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/css/inquirePage.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

</head>

<body style="background-color: #E4E4E4">
<%@include file="head.jsp"%>
<%@include file="search.jsp"%>
<%@include file="right.jsp"%>

<div class="container">
    <input type="text" value="${request.keyword}" id="ky" style="display: none;"/>
    <div id="prorow" class="row" style="margin-top: 10%;margin-left: 5%;">

        <s:iterator value="#request.products" var="pitem">
        <div class="col-md-2" style="margin: 10px; padding: 0px;">
            <div class="productitem">
                <div class="pimgbox">
                    <a href="searchByIdAction?pid=${pitem["proid"]}">
                        <img src="${pitem['firstimg']}" class="productimg">
                    </a>
                </div>
                <div class="proprice">
                    <p>￥<s:property value="#pitem.promotePrice"/></p>
                </div>
                <div class="protitle">
                    <a href="searchByIdAction?pid=${pitem["proid"]}"><span class="prodes"><s:property value="#pitem.title"/></span></a>
                </div>
                <div class="others">
                    <div class="outbox">
                        <span>成交量</span>
                        <span><s:property value="#pitem.outnum"/></span>笔
                    </div>
                    <div class="outbox">
                        <span>评价</span>
                        <span><s:property value="#pitem.reviewnum"/></span>
                    </div>
                </div>
            </div>
         </div>
        </s:iterator>


    </div>

</div>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>
    (function(){
        $(".productitem").on("mouseover",function(){
            $(this).css("border","2px solid red");
        });

        $(".productitem").on("mouseleave",function(){
            $(this).css("border","2px solid #E4E4E4");
        });
    })();
</script>

</body>
</html>
