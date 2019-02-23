<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>付款</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/style.default.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

    <style>
        .payrow{
            margin-top: 15%;
        }

        .mpaytab{
            background-color: #eee;
            margin: 0px 17%;
        }

        .mpaytab th{
            padding: 6px;
            border: 1px solid lightgray;
        }

        .mpaytab td{
            padding: 6px;
            border: 1px solid lightgray;
        }
        .paytitle{
            max-width: 250px;
            text-overflow: ellipsis;
            overflow: hidden;
            display: inline-block;
            max-height: 22px;
        }

        .payborder{
            background-color: lightgray;
        }
        .surepay{
            margin-top: 20px;
            margin-left: 73%;
        }
        .surebtn{
            height: 40px;
        }

        .payyedinf{
            width: 500px;
            height: 150px;
            border: 1px solid gray;
            margin: 0px 10%;
        }

        .payyedtag{
            height: 30px;
            background-color: #ecffdc;
            color: black;
            font-size: 15px;
            font-weight: 600;
        }
        .paysusimg{
            height: 20px;
            width: 20px;
        }
        .paysuccess{
            padding-top: 5px;
            margin-left: 10px;
        }
        .paycontent{
            padding: 20px 30px;
        }

        #paysuccessinf{
            display: none;
        }

    </style>
</head>

<body style="background-color: #e4e4e4">
<div class="container">
    <%@include file="head.jsp"%>
    <div class="row payrow">
        <div class="col-md-2"></div>
        <div class="col-md-8" id="payorder">
            <table class="mpaytab">

                <tr>
                    <th style="width: 73%">描述</th>
                    <th style="width: 15%">单价</th>
                    <th style="width: 15%">数量</th>
                    <th style="width: 15%">总价</th>
                </tr>

                <s:iterator value="#request.proitems" var="oriitem">
                <tr>
                    <td><span class="paytitle"><s:property value="#oriitem.productBean.title"/> </span></td>
                    <td><span>￥<s:property value="#oriitem.productBean.promotePrice"/> </span></td>
                    <td><s:property value="#oriitem.num"/> </td>
                    <td>￥<s:property value="#oriitem.price"/> </td>
                </tr>
                </s:iterator>

                <tr class="payborder">
                    <td>订单号:<s:property value="#request.orderno"/> </td>
                    <td></td>
                    <td colspan="2">总价:￥<s:property value="#request.ordercount"/> </td>
                </tr>

            </table>

            <div class="surepay">
                <button class="btn btn-info surebtn" target="${request.orderid}">确认付款</button>
            </div>
        </div>

        <div class="col-md-8" id="paysuccessinf">
            <div class="payyedinf">
                <div class="payyedtag">
                    <div class="paysuccess">
                        <img src="images/paySuccess.png" class="paysusimg"><span>您已成功付款</span>
                    </div>
                </div>

                <div class="paycontent">
                    <ul>
                        <li>收货地址：<s:property value="#request.address"/></li>
                        <li>实付款：￥<s:property value="#request.ordercount"/></li>
                        <li>预计明日送达</li>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>
    (function(){
        $(".surebtn").click(function(){
            $.post("updateOrderAction",{oid:$(this).attr("target"),type:"unsend"},function(data){
                if(data.result=="success"){
                    $("#payorder").hide();
                    $("#paysuccessinf").show();
                }
                else{
                    alert(data.message);
                }
            });
        })
    })();
</script>
</body>
</html>
