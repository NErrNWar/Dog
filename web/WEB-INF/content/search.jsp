<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/28
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/search.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

    <style>
    </style>

</head>



<body>
<div class="search">
    <div class="input-group">
        <form action="toSearchAction.action">
            <input type="text" name="keyword" class="from-control searchbox" style="float: left;"/>
        </form>
        <span class="input-group-btn">
                      <button id="searchbtn" type="submit" class="btn btn-info" style="height: 34px;">
                         <i class="fas fa-search" style="background-color: transparent;"></i>
                      </button>
                  </span>
    </div>
</div>

<div id="searchscroll" class="div-srcoll" style="opacity: 1;">
    <div class="searchinf" >
        <p><strong>享你所想</strong><em class="fas fa-heartbeat" style="color: red"></em></p>
    </div>
    <div class="input-group search-scroll">
        <form action="toSearchAction">
            <input type="text" name="keyword" class="from-control searchbox-scroll" style="float: left;">
        </form>
        <span class="input-group-btn" style="">
                   <button id="searchrolbtn" type="submit" class="btn btn-info searchbtn-scroll">
                       <i class="fas fa-search fa-spin" style="background-color: transparent;"></i>
                   </button>
                </span>
    </div>
</div>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>
    (function(){
        $(window).scroll(function(){
            var top=$(this).scrollTop();
            var sel=$("#searchscroll");
            if(top-80<0){
                sel.css("display","none");
            }
            else{
                sel.css("display","block");
            }
        });

        $("#searchbtn").click(function(){
            var value=$(".searchbox").val();
            if(value==null||value==""){
                return false;
            }else{
                var form=$(this).parents(".input-group-btn").prev();
                form.submit();
            }
        });

        $("#searchrolbtn").click(function(){
            var value=$(".searchbox-scroll").val();
            if(value==null||value==""){
                return false;
            }
            else{
                var form=$(this).parents(".input-group-btn").prev();
                form.submit();
            }
        });
    })();
</script>
</body>
</html>
