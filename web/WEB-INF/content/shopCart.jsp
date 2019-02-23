<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/12
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>购物车</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/css/shopCart.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

</head>
<body style="background-color: #E4E4E4">
<%@include file="head.jsp"%>
<%@include file="right.jsp"%>
<div class="container">
    <div class="row shoprow">
        <div class="col-md-12">
            <div class="shopcontent">
                <table id="protab" style="width: 100%;">
                    <thead>
                    <tr>
                        <th style="width: 8%"><input class="allcheck" type="checkbox"><span>全选</span></th>
                        <th style="width:38%"><span>描述</span></th>
                        <th style="width:8%"><span>价格</span></th>
                        <th style="width:10%"><span>数量</span></th>
                        <th style="width:8%"><span>价格</span></th>
                        <th style="width:8%"><span>操作</span></th>
                    </tr>
                    </thead>
                    <s:iterator value="#session.shopCart.items" var="citem">
                    <tr id="pro${citem.productBean.proid}" class="protr">
                        <td><input id="ischeck" type="checkbox"><span><img src="${citem.productBean.firstimg}" class="smallimg"></span></td>
                        <td>
                            <div>
                                <a href="searchByIdAction.action?pid=${citem.productBean.proid}" class="shopdes"><s:property value="#citem.productBean.title"/></a>
                            </div>
                        </td>

                        <td>
                            <div>
                                <span class="originpri">￥<s:property value="#citem.productBean.originalPrice"/></span>
                                <span style="color: red;font-weight: bold;font-size: 18px;">￥</span><span id="price${citem.productBean.proid}" class="promotepri"><s:property value="#citem.productBean.promotePrice"/></span>
                            </div>
                        </td>

                        <td>
                            <div>
                                <a href="#nowhere" class="btndes" style="display: inline-block;text-align:center;text-decoration: none;" onclick="des(${citem.productBean.proid})"><i class="far fa-minus-square"></i></a>
                                <input id="${citem.productBean.proid}" type="text" class="productinput" value="${citem.num}">
                                <a href="#nowhere" class="btnup" style="display: inline-block;text-align:center;text-decoration: none;" onclick="up(${citem.productBean.proid})"><i class="far fa-plus-square"></i></a>
                            </div>
                        </td>

                        <td>
                            <div>
                                <span id="count${citem.productBean.proid}" class="countpri">￥<s:property value="#citem.price"/> </span>
                            </div>
                        </td>

                        <td>
                            <div>
                                <a href="#nowhere" style="text-decoration: none;" onclick="remove(${citem.productBean.proid})">删除</a>
                            </div>
                        </td>
                    </tr>
                    </s:iterator>

                </table>
            </div>

            <div class="shortm">
                <div class="allcheck">
                    <input class="allcheck" type="checkbox">
                    <span>全选</span>
                </div>

                <div class="jies">
                    <span>已选商品</span>
                    <span class="shopnum">0</span>
                    <span>件&nbsp;合计(不含运费)</span>
                    <span class="shopcount">￥0</span>
                    <button id="createorder" class="btn btn-defalut">结算</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>
    var flag = false;
    var num = 0;
    var count = 0;

    function des(index) {
        var val = $("#" + index).attr("value");
        var price = $("#price" + index).html();
        //var val=parseInt(v);
        if (val > 1) {
            val--;

            $("#" + index).attr("value", val);
            $("#count" + index).html("￥" + price * val);

            if (flag) {
                setvalue();
            };

            $.post("updateShopCartAction",{pid:index,num:val},function(data,textStatus){
                if(data.result=="error"){
                    alert(data.message);
                }
            });

        } else {
            val = 1;
        }

    }

    function up(index) {
        var val = $("#" + index).attr("value");
        var price = $("#price" + index).html();
        //var val=parseInt(v);
        val++;
        $("#" + index).attr("value", val);
        $("#count" + index).html("￥" + price * val);


        if (flag) {
            setvalue();
        }

        $.post("updateShopCartAction",{pid:index,num:val},function(data,textStatus){
            if(data.result=="error"){
                alert(data.message);
            }
        });
    }

    function remove(index) {
        var p = $("#pro" + index);
        p.remove();
        setvalue();

        $.post("updateShopCartAction",{pid:index,num:0},function(data,textStatus){
            if(data.result=="error"){
                alert(data.message);
            }
        });
    }

    function findnum() {
        num = 0;
        count = 0;
        var checks = $("input[type=checkbox]:not(.allcheck)");
        var nums = $("input[type=text]");
        var pris = $(".promotepri");
        for (var i = 0; i < checks.length; i++) {
            var c = $(checks[i]);
            if (c.prop("checked") == true) {
                var n = $(nums[i]);
                var p = $(pris[i]);
                num += parseInt(n.attr("value"));
                var tempc = parseFloat(p.html()) * parseInt(n.attr("value"));
                count += tempc;
            }
        }
    }

    function setvalue() {
        findnum();
        $(".shopnum").html(num);
        $(".shopcount").html("￥" + count);
    }

    (function() {
        $(".allcheck").on("change", function() {
            var v = $(this).prop("checked");
            var array = $("input[type=checkbox]");
            for (var i = 0; i < array.length; i++) {
                $(array[i]).prop("checked", v);
            }
            setvalue();
        });

        $("input[type=checkbox]").on("change", function() {
            if ($(this).prop("checked") == true) {
                flag = true;
                setvalue();
            }
            setvalue();

            var tflag = true;
            var temp = $("input[type=checkbox]:not(.allcheck)");
            for (var k = 0; k < temp.length; k++) {
                if ($(temp[k]).prop("checked") != true) {
                    tflag = false;
                }
            }

            if (tflag) {
                var all = $(".allcheck");
                for (var j = 0; j < all.length; j++) {
                    $(all[j]).prop("checked", true);
                }
            }
            else{
                var all = $(".allcheck");
                for (var j = 0; j < all.length; j++) {
                    $(all[j]).prop("checked", false);
                }
            }
        });

        $("#createorder").click(function(){
            num=0;
            count=0;
            findnum();
            if(num==0){
                return false;
            }
            else{
                var result="";
                var checks = $("input[type=checkbox]:not(.allcheck)");
                var nums = $("input[type=text]");
                for(var i=0;i<checks.length;i++){
                    var c=$(checks[i]);
                    var n=$(nums[i]);
                    if(c.prop("checked")==true){
                        result+=n.attr("id");
                        result+=":";
                        result+=n.attr("value");
                        result+=",";
                    }
                }


                var form=document.createElement("form");
                form.action="clearShopCartAction";
                form.method="post";
                form.style.display="none";
                var input=document.createElement("input");
                input.type="text";
                input.name="param";
                input.value=result;
                form.appendChild(input);
                document.body.appendChild(form);
                form.submit();
            }
        });

    })();

</script>
</body>
</html>
