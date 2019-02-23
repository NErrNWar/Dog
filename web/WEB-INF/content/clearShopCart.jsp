<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/21
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>生成订单</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap-editable.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/clearshopcart.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">


</head>

<body style="background-color: #e4e4e4">

<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">

            <table id="prolist" style="width: 100%;margin-top: 8%">
                <thead>
                <tr>
                    <th style="width:55%"><span>描述</span></th>
                    <th style="width:8%"><span>单价</span></th>
                    <th style="width:8%"><span>数量</span></th>
                    <th style="width:8%"><span>小计</span></th>
                </tr>
                </thead>

                <tr class="trborder">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>

                <s:iterator value="#request.createorder.orderItemBeans" var="oitem">
                <tr id="pro" class="clearprotr">
                    <td>
                        <div>
                            <span><img src="${oitem.productBean.firstimg}" class="clearsmallimg"></span>
                            <a href="searchByIdAction?pid=${oitem.productBean.proid}" class="cleardes"><s:property value="#oitem.productBean.title"/></a>
                        </div>
                    </td>

                    <td>
                        <div>
                            <span>￥</span><span id="price"><s:property value="#oitem.productBean.promotePrice"/></span>
                        </div>
                    </td>

                    <td>
                        <div>
                            <span><s:property value="#oitem.num"/></span>
                        </div>
                    </td>

                    <td>
                        <div>
                            <span id="count" class="clearcount">￥<s:property value="#oitem.price"/></span>
                        </div>
                    </td>

                </tr>
                </s:iterator>

                <tr class="trborder_bottom">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>

                <tr>
                    <td></td>
                    <td></td>
                    <td colspan="2"><span class="countprice">共计￥<s:property value="#request.createorder.price"/></span></td>
                </tr>


            </table>

            <div class="prouserinf">

                <table style="margin: 20px">
                    <tr>
                        <th style="width: 100px;"></th>
                        <th style="width: 50px"></th>
                    </tr>
                    <tr>
                        <td><span class="infspan">收件人:</span></td>
                        <td><a href="#" id="username" data-type="text" data-title="收件人"><s:property value="#session.user.username"/></a></td>
                    </tr>
                    <tr>
                        <td><span class="infspan">联系电话:</span></td>
                        <td><a href="#" id="tel" data-type="text" data-title="电话"><s:property value="#session.user.tel"/></a></td>
                    </tr>
                    <tr>
                        <td><span class="infspan">地址:</span></td>
                        <td><a href="#" id="address" data-type="text" data-title="地址"><s:property value="#session.user.adress"/></a></td>
                    </tr>
                </table>
                <input id="orderparam" type="text" value="${request.orderparam}" style="display: none;">
            </div>

            <div class="subox">
                <div class="lstdiv">
                    <span>实付款(含运费)</span>
                    <span class="lastprice">￥<s:property value="#request.createorder.price"/></span>
                </div>

                <div>
                    <button id="createorder" class="btn btn-danger subbtn">提交订单</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap-editable.min.js"></script>

<script>
    $(function() {
        $('#username').editable({
            type: "text",
            title: "收件人",
            disabled: false,
            emptytext: "请填写",
            mode: "inline",
            validate: function(value) {
                if (!$.trim(value)) {
                    return "不能为空";
                }
            }
        });

        $('#tel').editable({
            type: "text",
            title: "电话",
            disabled: false,
            emptytext: "请填写",
            mode: "inline",
            validate: function(value) {
                var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
                if (!$.trim(value)) {
                    return "不能为空";
                }
                if (!mobile.test(value) || value.length != 11) {
                    return "电话格式不正确";
                }
            }
        });

        $('#address').editable({
            type: "text",
            title: "地址",
            disabled: false,
            emptytext: "请填写",
            mode: "inline",
            validate: function(value) {
                if (!$.trim(value)) {
                    return "不能为空";
                }
            }
        });

        $("#createorder").click(function(){
            var form=document.createElement("form");
            form.action="createOrderAction";
            form.method="post";
            form.style.display="none";

            var receive=document.createElement("input");
            receive.value=$("#username").html();
            receive.type="text";
            receive.name="receiver";
            form.appendChild(receive);

            var tel=document.createElement("input");
            tel.value=$("#tel").html();
            tel.type="text";
            tel.name="tel";
            form.appendChild(tel);

            var address=document.createElement("input");
            address.value=$("#address").html();
            address.type="text";
            address.name="address";
            form.appendChild(address);

            var orderparam=document.createElement("input");
            orderparam.value=$("#orderparam").attr("value");
            orderparam.type="text";
            orderparam.name="orderparam";
            form.appendChild(orderparam);

            document.body.appendChild(form);
            form.submit();

        });

    })();

</script>
</body>

</html>
