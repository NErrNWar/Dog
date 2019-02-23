<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/12
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap-editable.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/personinf.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

    <style>
        .mitem {
            width: 425px;
            background-color: #eee;
        }

        .mitem td {
            padding: 5px;
            border: 1px solid lightgray;
            font-family: 'LatoRegular', 'Lucida Grande', 'Lucida Sans Unicode', Helvetica, sans-serif !important;
            line-height: 21px;
            color: #636e7b;
            font-size: 12px;
        }


        .mitemtitle {
            max-width: 225px;
            overflow: hidden;
            text-overflow: ellipsis;
            display: block;
            float: left;
            max-height: 19px;
        }

        .mitemborder {
            background-color: lightgray;
        }

        .modalsize{
            width: 455px!important;
        }

        .reviewbox{
            width: 570px;
            height: 100px;
        }

        .reviewimg{
            height: 90px;
            width: 80px;
        }
        .reviewproinf{
            border: 1px solid gray;
            padding: 10px;
            margin: 10px 0px;
            background-color: lightgray;
        }
        .reviewprotitle{
            margin: 0px 35px;
            max-width: 300px;
            word-wrap:break-word;
            word-break:break-all;
            overflow: hidden;
            display: inline-block;
        }
    </style>

</head>

<body style="background-color: #e4e4e4">
<%@include file="search.jsp"%>
<%@include file="head.jsp"%>

<div class="container">
    <div class="row" style="margin-top: 10%">
        <div class="col-md-1"></div>
        <div class="col-md-2 ">
            <div>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation" class="active"><a href="#">全部功能</a></li>
                    <li role="presentation"><a id="shcart" href="#">我的购物车</a></li>
                    <li role="presentation"><a id="prohadb" href="#">已买的宝贝</a></li>
                    <li role="presentation"><a id="favorpro" href="#">我的收藏</a></li>
                </ul>
            </div>
        </div>

        <div class="col-md-8" id="useinf">
            <div class="pinfbox">
                <div class="uinf">
                    <div>
                        <div class="uinf_name"><a href="#" class="uinf_name_a" style="text-decoration: none;"><s:property value="#session.user.username"/> </a></div>
                        <div class="uinf_pas"><a href="#" class="uinf_pas_a" style="text-decoration: none">修改密码&nbsp;<i class="fas fa-edit"></i></a></div>
                    </div>
                </div>

                <div class="otypebox">
                    <div class="pbase"><a id="pupay" href="#" class="pbase_a" style="text-decoration: none">待付款</a></div>
                    <div class="pbase"><a id="puse" href="#" class="pbase_a" style="text-decoration: none">待发货</a></div>
                    <div class="pbase"><a id="pure" href="#" class="pbase_a" style="text-decoration: none">待收货</a></div>
                    <div class="pbase" style="border: none;"><a id="puri" href="#" class="pbase_a" style="text-decoration: none">待评价</a></div>
                </div>
            </div>

            <div class="editpas">
                <div class="editcontent">
                    <div class="edaccount">
                        <span>账户:</span>
                        <span id="iusername"><s:property value="#session.user.account"/></span>
                    </div>
                    <form id="updatepas" method="post">
                        <div class="edcode">
                            <lable>验证码</lable><span id="inf" style="margin-left: 20px"><small></small></span>
                            <div class="input-group">
                                <input id="secode" type="text" class="form-control edcodein" name="secode" style="width: 100px;border-radius: 5px;" placeholder="验证码" maxlength="4">
                                <span>
                                <button id="sendscode" type="button" class="btn btn-default edcodebtn" data-placement="right" data-toggle="tooltip" data-original-title="验证码将发送到您的账户">发送</button>

                            </span>
                            </div>
                        </div>

                        <div>
                            <span>原密码</span>
                            <div class="input-group">
                                <input id="ypassword" type="password" class="form-control ypass" name="ypassword" style="width: 182px;border-radius: 5px;" placeholder="原密码"/>
                            </div>
                            <span id="ypassword_img" title="" data-placement="right" data-toggle="tooltip" class="tooltip" style="position: absolute;top: 285px;left: 480px"></span>
                        </div>
                        <div>
                            <span>新密码</span>
                            <div class="input-group">
                                <input id="npassword" type="password" class="form-control" name="npassword" style="height: 35px;width: 182px;border-radius: 5px;" placeholder="新密码"/>
                            </div>
                            <span id="npassword_img" title="" data-placement="right" data-toggle="tooltip" class="tooltip" style="position: absolute;top: 340px;left: 480px"></span>
                        </div>
                        <div>
                            <span>重复密码</span>
                            <div class="input-group">
                                <input id="cpassword" type="password" class="form-control" name="cpassword" style="width: 182px;height: 35px;border-radius: 5px;" placeholder="重复密码"/>
                            </div>
                            <span id="cpassword_img" title="" data-placement="right" data-toggle="tooltip" class="tooltip" style="position: absolute;top: 395px;left: 480px"></span>
                        </div>
                    </form>
                    <div class="edbtn">
                        <button id="sureupdate" type="button" class="btn btn-info">修改</button>
                    </div>

                </div>
            </div>

            <div class="editall">
                <div class="edtable">
                    <table>
                        <tr>
                            <th style="width: 15%"></th>
                            <th style="width: 30%"></th>
                        </tr>

                        <tr>
                            <td class="edtable_td">用户名:</td>
                            <td><a href="#" id="name" data-type="text" data-title="用户名"><s:property value="#session.user.username"/></a></td>
                        </tr>

                        <tr>
                            <td class="edtable_td">性别:</td>
                            <td><a href="#" id="sex" data-title="性别"></a></td>
                        </tr>

                        <tr>
                            <td class="edtable_td">出生日期:</td>
                            <td><a href="#" id="birth" data-title="出生日期" data-value="${request.birth}"></a></td>
                        </tr>

                        <tr>
                            <td class="edtable_td">地址:</td>
                            <td><a href="#" id="address" data-type="text" data-title="地址"><s:property value="#session.user.adress"/> </a></td>
                        </tr>

                        <tr>
                            <td class="edtable_td">电话:</td>
                            <td><a href="#" id="tel" data-type="text" data-title="电话"><s:property value="#session.user.tel"/></a></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td><button id="upall" type="button" class="btn btn-info">确认修改</button></td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>

        <div class="col-md-8 allorders">
            <div class="ordertype">
                <div>
                    <a id="allor" class="orderactive" href="#nowhere" style="text-decoration:none">所有订单</a>
                </div>
                <div>
                    <a id="upor" href="#nowhere" style="text-decoration:none">待付款</a>
                </div>
                <div>
                    <a id="usor" href="#nowhere" style="text-decoration:none">待发货</a>
                </div>
                <div>
                    <a id="uror" href="#nowhere" style="text-decoration: none;">待收货</a>
                </div>
                <div>
                    <a id="ureor" href="#nowhere" style="border-right: none;text-decoration: none">待评价</a>
                </div>

                <div class="typelast"></div>
            </div>

            <div class="ordes">
                <table class="orders">
                    <tr class="ortr">
                        <th style="width: 50%">宝贝</th>
                        <th style="width: 12.5%">单价</th>
                        <th style="width: 12.5%">数量</th>
                        <th style="width: 12.5%">实付款</th>
                        <th style="width: 12.5%">操作</th>
                    </tr>
                </table>
            </div>

            <div id="all" class="all">
                <div class="tablist">
                    <s:iterator value="#request.allorder" var="order">
                      <table class="oritab">
                        <tr class="oritr">
                            <th style="width: 50%">
                                <div class="orino"><span><s:property value="#order.buytime"/>&nbsp;订单号：<s:property value="#order.orno"/></span></div>
                            </th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%">
                                <div class="oridel"><a href="#" data-target="${order.id}" data-in="all" data-type="${order.type}"><i class="fas fa-trash-alt"></i></a></div>
                            </th>
                        </tr>

                        <s:set var="number" value="0" />
                        <s:iterator value="#order.orderItemBeans" var="iitem">
                        <s:set var="number" value="#number+1"/>
                        <tr class="oriit">
                            <td colspan="2">
                                <div class="tabori">
                                    <a href="searchByIdAction?pid=${iitem.productBean.proid}"><img src="${iitem.productBean.firstimg}" class="orimg"></a>
                                    <a href="searchByIdAction?pid=${iitem.productBean.proid}"><span class="tabori_span"><s:property value="#iitem.productBean.title"/></span>
                                    </a>

                                    <span class="oripr">￥<s:property value="#iitem.productBean.promotePrice"/></span>

                                </div>
                            </td>

                            <td>
                                <div class="orinu">
                                    <span><s:property value="#iitem.num"/> </span>
                                </div>
                            </td>

                            <td>
                                <div class="oricut">
                                    <span>￥<s:property value="#iitem.price"/> </span>
                                </div>
                            </td>

                            <s:if test="#order.type=='unreview'">
                                <td>
                                    <div class="oriop">
                                        <s:if test="#iitem.type=='unreview'">
                                        <a href="#nowhere" style="text-decoration: none;" class="toreview" target="${iitem.id}"><span>评价</span></a>
                                        </s:if>
                                    </div>
                                </td>

                            </s:if>

                            <s:else>
                                <s:if test="#number==1">
                                    <td rowspan="${order.size}">
                                        <div class="oriop">
                                            <s:if test="#order.type=='unpay'">
                                                <a href="#" style="text-decoration: none;" class="topay"><span>付款</span></a>
                                            </s:if>
                                            <s:elseif test="#order.type=='unsend'">
                                                <a href="#" style="text-decoration: none;" class="tosend"><span>催单</span></a>
                                            </s:elseif>
                                            <s:elseif test="#order.type=='unreceive'">
                                                <a href="#" style="text-decoration: none;" class="toreceive"><span>确认收货</span></a>
                                            </s:elseif>
                                            <s:else>
                                                <a href="#" style="text-decoration: none;"><span></span></a>
                                            </s:else>

                                        </div>
                                    </td>
                                </s:if>
                            </s:else>


                        </tr>
                        </s:iterator>


                        <tr class="oribot">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td colspan="2"><span>总价：￥<s:property value="#order.price"/></span></td>
                        </tr>
                    </table>
                    </s:iterator>
                </div>
            </div>

            <div id="unpay" class="upay">
                <div class="tablist">
                    <s:iterator value="#request.unpay" var="uporder">
                    <table class="oritab">
                        <tr class="oritr">
                            <th style="width: 50%">
                                <div class="orino"><span><s:property value="#uporder.buyTime"/>&nbsp;订单号：<s:property value="#uporder.orno"/> </span></div>
                            </th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%">
                                <div class="oridel"><a href="#" data-target="${uporder.id}" data-type="${uporder.type}"><i class="fas fa-trash-alt"></i></a></div>
                            </th>
                        </tr>

                        <s:set var="number" value="0"/>
                        <s:iterator value="#uporder.orderItemBeans" var="upitem">
                            <s:set var="number" value="#number+1"/>
                        <tr class="oriit">
                            <td colspan="2">
                                <div class="tabori">
                                    <a href="searchByIdAction?pid=${upitem.productBean.proid}"><img src="${upitem.productBean.firstimg}" class="orimg"></a>
                                    <a href="searchByIdAction?pid=${upitem.productBean.proid}"><span class="tabori_span"><s:property value="#upitem.productBean.title"/></span>
                                    </a>

                                    <span class="oripr">￥<s:property value="#upitem.productBean.promotePrice"/></span>

                                </div>
                            </td>

                            <td>
                                <div class="orinu">
                                    <span><s:property value="#upitem.num"/></span>
                                </div>
                            </td>

                            <td>
                                <div class="oricut">
                                    <span>￥<s:property value="#upitem.price"/> </span>
                                </div>
                            </td>

                            <s:if test="#number==1">
                                <td rowspan="${uporder.size}">
                                    <div class="oriop">
                                        <a href="#" style="text-decoration: none;" class="topay"><span>付款</span></a>
                                    </div>
                                </td>
                            </s:if>


                        </tr>
                        </s:iterator>

                        <tr class="oribot">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td colspan="2"><span>总价：￥<s:property value="#uporder.price"/> </span></td>
                        </tr>
                    </table>
                    </s:iterator>

                </div>
            </div>

            <div id="unsend" class="usend">
                <div class="tablist">

                    <s:iterator value="#request.unsend" var="usorder">
                    <table class="oritab">
                        <tr class="oritr">
                            <th style="width: 50%">
                                <div class="orino"><span><s:property value="#usorder.buyTime"/>&nbsp;订单号：<s:property value="#usorder.orno"/> </span></div>
                            </th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%">
                                <div class="oridel"><a href="#" data-target="${usorder.id}" data-type="${usorder.type}"><i class="fas fa-trash-alt"></i></a></div>
                            </th>
                        </tr>

                        <s:set var="number" value="0"/>
                        <s:iterator value="#usorder.orderItemBeans" var="usitem">
                            <s:set var="number" value="#number+1"/>
                        <tr class="oriit">
                            <td colspan="2">
                                <div class="tabori">
                                    <a href="searchByIdAction?pid=${usitem.productBean.proid}"><img src="${usitem.productBean.firstimg}" class="orimg"></a>
                                    <a href="searchByIdAction?pid=${usitem.productBean.proid}"><span class="tabori_span"><s:property value="#usitem.productBean.title"/></span>
                                    </a>

                                    <span class="oripr">￥<s:property value="#usitem.productBean.promotePrice"/></span>

                                </div>
                            </td>

                            <td>
                                <div class="orinu">
                                    <span><s:property value="#usitem.num"/> </span>
                                </div>
                            </td>

                            <td>
                                <div class="oricut">
                                    <span>￥<s:property value="#usitem.price"/> </span>
                                </div>
                            </td>

                            <s:if test="#number==1">
                                <td rowspan="${usorder.size}">
                                    <div class="oriop">
                                        <a href="#" style="text-decoration: none;" class="tosend"><span>催单</span></a>
                                    </div>
                                </td>
                            </s:if>


                        </tr>
                        </s:iterator>

                        <tr class="oribot">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td colspan="2"><span>总价：￥<s:property value="#usorder.price"/> </span></td>
                        </tr>
                    </table>
                    </s:iterator>

                </div>
            </div>

            <div id="unreceive" class="urec">
                <div class="tablist">
                    <s:iterator value="#request.unreceive" var="urecorder">
                    <table class="oritab">
                        <tr class="oritr">
                            <th style="width: 50%">
                                <div class="orino"><span><s:property value="urecorder.buyTime"/> &nbsp;订单号：<s:property value="#urecorder.orno"/> </span></div>
                            </th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%">
                                <div class="oridel"><a href="#" data-target="${urecorder.id}" data-type="${urecorder.type}"><i class="fas fa-trash-alt"></i></a></div>
                            </th>
                        </tr>

                        <s:set var="number" value="0"/>
                        <s:iterator value="#urecorder.orderItemBeans" var="urecitem">
                            <s:set var="number" value="#number+1"/>
                        <tr class="oriit">
                            <td colspan="2">
                                <div class="tabori">
                                    <a href="searchByIdAction?pid=${urecitem.productBean.proid}"><img src="${urecitem.productBean.firstimg}" class="orimg"></a>
                                    <a href="searchByIdAction?pid=${urecitem.productBean.proid}"><span class="tabori_span"><s:property value="#urecitem.productBean.title"/></span>
                                    </a>

                                    <span class="oripr">￥<s:property value="#urecitem.productBean.promotePrice"/></span>

                                </div>
                            </td>

                            <td>
                                <div class="orinu">
                                    <span><s:property value="#urecitem.num"/></span>
                                </div>
                            </td>

                            <td>
                                <div class="oricut">
                                    <span>￥<s:property value="#urecitem.price"/> </span>
                                </div>
                            </td>

                            <s:if test="#number==1">
                                <td rowspan="${urecorder.size}">
                                    <div class="oriop">
                                        <a href="#" style="text-decoration: none;" class="toreceive"><span>确认收货</span></a>
                                    </div>
                                </td>
                            </s:if>


                        </tr>
                        </s:iterator>


                        <tr class="oribot">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td colspan="2"><span>总价：￥<s:property value="#urecorder.price"/> </span></td>
                        </tr>
                    </table>
                    </s:iterator>

                </div>
            </div>

            <div id="unreview" class="urevi">
                <div class="tablist">
                    <s:iterator value="#request.unreview" var="ureviorder">
                    <table class="oritab">
                        <tr class="oritr">
                            <th style="width: 50%">
                                <div class="orino"><span><s:property value="#ureviorder.buyTime"/>&nbsp;订单号：<s:property value="#ureviorder.orno"/> </span></div>
                            </th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%"></th>
                            <th style="width: 12.5%">
                                <div class="oridel"><a href="#" data-target="${ureviorder.id}" data-type="${ureviorder.type}"><i class="fas fa-trash-alt"></i></a></div>
                            </th>
                        </tr>

                        <s:iterator value="#ureviorder.orderItemBeans" var="ureviitem">
                        <tr class="oriit">
                            <td colspan="2">
                                <div class="tabori">
                                    <a href="searchByIdAction?pid=${ureviitem.productBean.proid}"><img src="${ureviitem.productBean.firstimg}" class="orimg"></a>
                                    <a href="searchByIdAction?pid=${ureviitem.productBean.proid}"><span class="tabori_span"><s:property value="#ureviitem.productBean.title"/></span>
                                    </a>

                                    <span class="oripr">￥<s:property value="#ureviitem.productBean.promotePrice"/> </span>

                                </div>
                            </td>

                            <td>
                                <div class="orinu">
                                    <span><s:property value="#ureviitem.num"/> </span>
                                </div>
                            </td>

                            <td>
                                <div class="oricut">
                                    <span>￥<s:property value="#ureviitem.price"/> </span>
                                </div>
                            </td>

                            <td>
                                <div class="oriop">
                                    <s:if test="#ureviitem.type=='unreview'">
                                    <a href="#nowhere" style="text-decoration: none;" target="${ureviitem.id}" class="toreview"><span>评价</span></a>
                                    </s:if>
                                </div>
                            </td>

                        </tr>
                        </s:iterator>

                        <tr class="oribot">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td colspan="2"><span>总价：￥<s:property value="#ureviorder.price"/></span></td>
                        </tr>
                    </table>
                    </s:iterator>

                </div>
            </div>
        </div>

    </div>

    <div id="susinf" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4>提示</h4>
                </div>

                <div class="modal-body">
                    <p>修改成功！</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
            </div>

        </div>
    </div>

    <div id="topaymodal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg modalsize">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>付款</h4>
                </div>

                <div class="modal-body">
                    <div class="mitem">
                        <table>

                        </table>
                    </div>
                </div>

                <div class="modal-footer">
                    <button id="topaybtn" type="button" class="btn btn-default" target="" data-dismiss="modal">确定</button>
                </div>

            </div>
        </div>
    </div>

    <div id="tosendmodal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4>提示</h4>
                </div>

                <div class="modal-body">
                    <p>我们将尽快为您发货！</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
            </div>

        </div>
    </div>

    <div id="toreceivemodal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg modalsize">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>收货</h4>
                </div>

                <div class="modal-body">
                    <div class="mitem">
                        <table>

                        </table>
                    </div>
                </div>

                <div class="modal-footer">
                    <button id="toreceivebtn" type="button" class="btn btn-default" target="" data-dismiss="modal">确认收货</button>
                </div>

            </div>
        </div>
    </div>

    <div id="toreviewmodal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>评论</h4>
                </div>

                <div class="modal-body">
                    <div class="reviewproinf">

                    </div>
                    <div>
                        <textarea id="reviewcontent" class="reviewbox" autofocus="true"></textarea>
                    </div>
                </div>

                <div class="modal-footer">
                    <button id="toreviewbtn" type="button" class="btn btn-default" target="" data-dismiss="modal">提交评论</button>
                </div>

            </div>
        </div>
    </div>

</div>


<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap-editable.min.js"></script>
<script>
    var code = "";
    var yflag = false;
    var nflag = false;
    var cflag = false;
    (function() {
        $("#name").editable({
            type: "text",
            //mode: "inline",
            disabled: false,
            title: "用户名",
            emptytext: "请填写",
            validate: function(value) {
                if (!$.trim(value)) {
                    return "不能为空";
                }
            }
        });

        $("#sex").editable({
            type: "select",
            disabled: false,
            title: "性别",
            mode: "inline",
            value: "man",
            source: [{
                value: "man",
                text: "男"
            }, {
                value: "woman",
                text: "女"
            }],
            validate: function(value) {
                if (!$.trim(value)) {
                    return "不能为空";
                }
            }
        });

        $("#birth").editable({
            type: "text",
            title: "出生日期",
            validate: function(value) {
                if (!$.trim(value)) {
                    return "不能为空";
                }
            }
        });

        $("#address").editable({
            type: "text",
            title: "地址",
            emptytext: "请填写",
            //mode:"inline",
            validate: function(value) {
                if (!$.trim(value)) {
                    return "不能为空";
                }
            }
        });

        $("#tel").editable({
            type: "text",
            title: "电话",
            emptytext: "请填写",
            //mode:"inline",
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

        $(".oritab").on("mouseenter", function() {
            /*$(this).css("margin","17px 0px")*/
            $(this).css("border", "1px solid #d00");
        });
        $(".oritab").on("mouseleave", function() {
            $(this).css("border", "1px solid transparent");
            /*$(this).css("border","none");*/
            /*$(this).css("margin","20px 3px");*/
        });


        $(".uinf_name_a").click(function() {
            $("#useinf").show();
            mshow($(".editall"));
        });

        $(".uinf_pas_a").click(function() {
            $("#useinf").show();
            mshow($(".editpas"));
        });

        $("#shcart").click(function() {
            window.open("shopCartAction");
        });

        $("#prohadb").click(function() {
            $("#useinf").hide();
            mshow($(".allorders"));
        });

        $("#favorpro").click(function() {
            window.open("favoriteAction");
        });

        $("#ypassword").keyup(function() {
            var value = $("#ypassword").val();
            if (value.length < 3 || value.length > 16) {
                $("#ypassword_img").removeClass("success");
                $("#ypassword_img").addClass("error");

                $("#ypassword_img").attr("data-original-title", "密码长度在3-16之间");
                $("#ypassword_img").tooltip();
                yflag = false;
            } else {
                $("#ypassword_img").removeClass("error");
                $("#ypassword_img").addClass("success tooltip");

                $("#ypassword_img").attr("title", "");
                $("#ypassword_img").attr("data-original-title", "");
                yflag = true;
            }
        });

        $("#npassword").keyup(function() {
            var value = $("#npassword").val();
            if (value.length < 3 || value.length > 16) {
                $("#npassword_img").removeClass("success");
                $("#npassword_img").addClass("error");

                $("#npassword_img").attr("data-original-title", "密码长度在3-16之间");
                $("#npassword_img").tooltip();
                nflag = false;
            } else {
                $("#npassword_img").removeClass("error");
                $("#npassword_img").addClass("success tooltip");

                $("#npassword_img").attr("title", "");
                $("#npassword_img").attr("data-original-title", "");
                nflag = true;
            }
        });

        $("#cpassword").keyup(function() {
            var value1 = $("#npassword").val();
            var value2 = $("#cpassword").val();
            if (value1 != value2) {
                $("#cpassword_img").removeClass("success");
                $("#cpassword_img").addClass("error");

                $("#cpassword_img").attr("data-original-title", "两次密码不相同");
                $("#cpassword_img").tooltip();
                cflag = false;
            } else {
                $("#cpassword_img").removeClass("error");
                $("#cpassword_img").addClass("success");

                $("#cpassword_img").attr("title", "");
                $("#cpassword_img").attr("data-original-title", "");
                cflag = true;
            }
        });

        $("#sendscode").tooltip();

        $("#sureupdate").click(function() {

            console.log($("#ypassword").val());
            console.log($("#npassword").val());

            if ($("#secode").val() != code) {
                $("#inf").addClass("text-danger");
                $("#inf small").html("验证码错误");
                return false;
            }

            if (!yflag || !nflag || !cflag) {
                return false;
            }

            $.post("updatePasswordAction", $("#updatepas").serialize(), function(data) {
                if (data.result == "success") {
                    $("#susinf").modal("show");
                    $(".editpas").hide();
                } else {
                    alert(data.message);
                }
            });

        });

        $("#sendscode").click(function() {
            $("#inf").removeClass("text-danger");
            $("#inf small").html("发送中");

            console.log($("#iusername").html());

            $.post("seCodeToUpAction", {account: $("#iusername").html()}, function (data, textStatus) {
                if (data["result"] == "success") {

                    code = data["code"];
                    $("#inf").removeClass("text-danger");
                    $("#inf small").html("验证码已发送");

                    sendMessage();
                }
                else{
                    $("#inf").addClass("text-danger");
                    $("#inf small").html(data.message);
                }
            });
        });

        $("#upall").click(function() {

            $.post("updateUserAction", {
                    username:$("#name").html(),
                    sex:$("#sex").html(),
                    birth:$("#birth").html(),
                    address:$("#address").html(),
                    tel:$("#tel").html()
                },
                function (data,textStatus){
                  if(data.result=="success"){
                      $("#susinf").modal("show");
                  }
                  else{
                      alert(data.message);
                  }
            });

            /*
            var form = document.createElement("form");
            form.action = "updatekjkUserAction";
            form.method = "post";
            form.style.display = "none";
            var input1 = document.createElement("input");
            input1.type = "text";
            input1.name = "username";
            input1.value = $("#name").html();
            form.appendChild(input1);

            var input2 = document.createElement("input");
            input2.type = "text";
            input2.name = "sex";
            input2.value = (($("#sex").html() == "男") ? "man" : "woman");
            form.appendChild(input2);

            var input3 = document.createElement("input");
            input3.type = "text";
            input3.name = "birth";
            input3.value = $("#birth").html();
            form.appendChild(input3);

            var input4 = document.createElement("input");
            input4.type = "text";
            input4.name = "address";
            input4.value = $("#address").html();
            form.appendChild(input4);

            document.body.appendChild(form);
            form.submit();*/
        });

        $("#allor").click(function(){
            $("#allor").addClass("orderactive");
            $("#upor").removeClass("orderactive");
            $("#usor").removeClass("orderactive");
            $("#uror").removeClass("orderactive");
            $("#ureor").removeClass("orderactive");

            $("#all").show();
            $("#unpay").hide();
            $("#unsend").hide();
            $("#unreceive").hide();
            $("#unreview").hide();
        });

        $("#upor").click(function(){
            $("#allor").removeClass("orderactive");
            $("#upor").addClass("orderactive");
            $("#usor").removeClass("orderactive");
            $("#uror").removeClass("orderactive");
            $("#ureor").removeClass("orderactive");

            $("#all").hide();
            $("#unpay").show();
            $("#unsend").hide();
            $("#unreceive").hide();
            $("#unreview").hide();
        });

        $("#usor").click(function(){
            $("#allor").removeClass("orderactive");
            $("#upor").removeClass("orderactive");
            $("#usor").addClass("orderactive");
            $("#uror").removeClass("orderactive");
            $("#ureor").removeClass("orderactive");


            $("#all").hide();
            $("#unpay").hide();
            $("#unsend").show();
            $("#unreceive").hide();
            $("#unreview").hide();
        });

        $("#uror").click(function(){
            $("#allor").removeClass("orderactive");
            $("#upor").removeClass("orderactive");
            $("#usor").removeClass("orderactive");
            $("#uror").addClass("orderactive");
            $("#ureor").removeClass("orderactive");


            $("#all").hide();
            $("#unpay").hide();
            $("#unsend").hide();
            $("#unreceive").show();
            $("#unreview").hide();
        });

        $("#ureor").click(function(){
            $("#allor").removeClass("orderactive");
            $("#upor").removeClass("orderactive");
            $("#usor").removeClass("orderactive");
            $("#uror").removeClass("orderactive");
            $("#ureor").addClass("orderactive");


            $("#all").hide();
            $("#unpay").hide();
            $("#unsend").hide();
            $("#unreceive").hide();
            $("#unreview").show();
        });

        $("#pupay").click(function(){
            $("#useinf").hide();
            mshow($(".allorders"));

            $("#allor").removeClass("orderactive");
            $("#upor").addClass("orderactive");
            $("#usor").removeClass("orderactive");
            $("#uror").removeClass("orderactive");
            $("#ureor").removeClass("orderactive");

            $("#all").hide();
            $("#unpay").show();
            $("#unsend").hide();
            $("#unreceive").hide();
            $("#unreview").hide();
        });

        $("#puse").click(function(){
            $("#useinf").hide();
            mshow($(".allorders"));

            $("#allor").removeClass("orderactive");
            $("#upor").removeClass("orderactive");
            $("#usor").addClass("orderactive");
            $("#uror").removeClass("orderactive");
            $("#ureor").removeClass("orderactive");


            $("#all").hide();
            $("#unpay").hide();
            $("#unsend").show();
            $("#unreceive").hide();
            $("#unreview").hide();
        });

        $("#pure").click(function(){
            $("#useinf").hide();
            mshow($(".allorders"));

            $("#allor").removeClass("orderactive");
            $("#upor").removeClass("orderactive");
            $("#usor").removeClass("orderactive");
            $("#uror").addClass("orderactive");
            $("#ureor").removeClass("orderactive");


            $("#all").hide();
            $("#unpay").hide();
            $("#unsend").hide();
            $("#unreceive").show();
            $("#unreview").hide();
        });

        $("#puri").click(function(){
            $("#useinf").hide();
            mshow($(".allorders"));

            $("#allor").removeClass("orderactive");
            $("#upor").removeClass("orderactive");
            $("#usor").removeClass("orderactive");
            $("#uror").removeClass("orderactive");
            $("#ureor").addClass("orderactive");


            $("#all").hide();
            $("#unpay").hide();
            $("#unsend").hide();
            $("#unreceive").hide();
            $("#unreview").show();
        });

        $(".oridel a").on("click",function(){
            var din=$(this).attr("data-in");
            var doid=$(this).attr("data-target");
            var dtype=$(this).attr("data-type");

            var target=$(this).parents(".oritab");
            alert(typeof(target));
            alert($(target).attr("class"));

            $(target).remove();

            if(din=="all"){
                var ts=$("#"+dtype).find("table");
                for(var i=0;i<ts.length;i++){
                    var tdiv=$(ts[i]).find(".oridel");
                    var a=$(tdiv).children("a");
                    if($(a).attr("data-target")==doid){
                        $(ts[i]).remove();
                    }
                }
            }
            else{
                var atables=$("#all").find("table");
                for(var i=0;i<atables.length;i++){
                    var tdiv=$(atables[i]).find(".oridel");
                    var a=$(tdiv).children("a");
                    if($(a).attr("data-target")==doid){
                        $(atables[i]).remove();
                    }
                }
            }

            $.post("deleteOrderAction",{oid:doid,type:dtype},function(data){
                if(data.result=="success"){

                }
                else{
                    alert(data.message);
                }
            });
        });

        $(".topay").on("click",function(){
            $("#topaymodal table").children().remove();
            var target=$(this).parents(".oritab");
            var inf=$(target).find(".oriit");
            var tooid=$(target).find(".oridel");
            var oid=$($(tooid).find("a")).attr("data-target");

            $("#topaymodal #topaybtn").attr("target",oid);

            var h='<tr><td style="width: 56%">描述</td>' + '<td style="width: 15%">单价</td>' + '<td style="width: 10%">数量</td>' +
                '<td style="width: 0%">实付款</td></tr>';
            $.each(inf,function(index,item){
                var title=$(item).find(".tabori_span").html();
                var price=$(item).find(".oripr").html();
                var num=$(item).find(".orinu").children("span").html();
                var count=$(item).find(".oricut").children("span").html();

                h+='<tr>' +
                    '<td><span class="mitemtitle">'+title+'</span></td>\n' +
                    '<td>'+price+'</td>' +
                    '<td>'+num+'</td>' +
                    '<td>'+count+'</td>' +
                    '</tr>'
            });
            var c=$(target).find(".oribot");
            var count=$(c).find("span").html();

            h+='<tr class="mitemborder">\n' +
                '<td>'+$(target).find(".orino").html()+'</td>' +
                '<td></td>' +
                '<td></td>' +
                '<td>'+count+'</td>' +
                ' </tr>';

            $("#topaymodal table").append($(h));

            $("#topaymodal").modal("show");
        });

        $("#topaybtn").click(function(){
            $.post("updateOrderAction",{oid:$(this).attr("target"),type:"unsend"},function(data){
                if(data.result=="success"){

                }
                else{
                    alert(data.message);
                }
            });
        });

        $(".tosend").on("click",function(){
            var target=$(this).parents(".oritab");
            var tooid=$(target).find(".oridel");
            var toid=$($(tooid).find("a")).attr("data-target");

            $.post("updateOrderAction",{oid:toid,type:"unreceive"},function(data){
                if(data.result=="success"){
                    $("#tosendmodal").modal("show");
                }
                else{
                    alert(data.message);
                }
            });
        });

        $(".toreceive").on("click",function(){
            $("#toreceivemodal table").children().remove();
            var target=$(this).parents(".oritab");
            var inf=$(target).find(".oriit");
            var tooid=$(target).find(".oridel");
            var oid=$($(tooid).find("a")).attr("data-target");

            $("#toreceivemodal #toreceivebtn").attr("target",oid);

            var h='<tr><td style="width: 56%">描述</td>' + '<td style="width: 15%">单价</td>' + '<td style="width: 10%">数量</td>' +
                '<td style="width: 0%">实付款</td></tr>';
            $.each(inf,function(index,item){
                var title=$(item).find(".tabori_span").html();
                var price=$(item).find(".oripr").html();
                var num=$(item).find(".orinu").children("span").html();
                var count=$(item).find(".oricut").children("span").html();

                h+='<tr>' +
                    '<td><span class="mitemtitle">'+title+'</span></td>\n' +
                    '<td>'+price+'</td>' +
                    '<td>'+num+'</td>' +
                    '<td>'+count+'</td>' +
                    '</tr>'
            });

            h+='<tr class="mitemborder">\n' +
                '<td>'+$(target).find(".orino").html()+'</td>' +
                '<td></td>' +
                '<td></td>' +
                '<td></td>' +
                ' </tr>';

            $("#toreceivemodal table").append($(h));

            $("#toreceivemodal").modal("show");
        });

        $("#toreceivebtn").click(function(){
            $.post("updateOrderAction",{oid:$(this).attr("target"),type:"unreview"},function(data){
                if(data.result=="success"){

                }
                else{
                    alert(data.message);
                }
            });
        });

        $(".toreview").on("click",function(){

            var target=$(this).parents(".oriit");
            var tpid=$(this).attr("target");
            var targetimg=$(target).find("img");
            var imgsrc=$(targetimg).attr("src");
            var targettitle=$(target).find(".tabori_span");
            var title=$(targettitle).html();

            $("#toreviewmodal .reviewproinf").children().remove();

            var h='<div><img src="'+imgsrc+'" class="reviewimg">' +
                '<span class="reviewprotitle">'+title+'</span></div>';

            $("#toreviewmodal .reviewproinf").append($(h));

            $("#toreviewbtn").attr("target",tpid);

            $("#toreviewmodal").modal("show");

            return false;
        });

        $("#toreviewbtn").click(function(){
            var moiid=$(this).attr("target");
            var mcontent=$("#reviewcontent").val();

            $.post("addReviewAction",{oiid:moiid,content:mcontent},function(data){
                if(data.result=="error"){
                    alert(data.message);
                }
            });
        });

    })();

    function mshow(target) {
        $(".allorders").hide();
        $(".editall").hide();
        $(".editpas").hide();

        target.show();
    }

    function sendMessage() {
        mcount = 60;
        $("#sendscode").attr("disabled", "true");
        mInterValObj = window.setInterval(function() {
            mcount--;
            if (mcount == 0) {
                window.clearInterval(mInterValObj);
                $("#sendscode").removeAttr("disabled");
                $("#sendscode").html("发送");

                $("#inf").removeClass("text-danger");
                $("#inf small").html("");
            } else {
                $("#sendscode").html(mcount + "s");
            }
        }, 1000);

    };

</script>

</body>
</html>
