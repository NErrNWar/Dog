<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/19
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:property value="#request.product.name"/></title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/css/prodetail.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
</head>
<body style="background-color: #E4E4E4">
<%@include file="head.jsp"%>
<%@include file="search.jsp"%>

<div class="container">
    <div class="row" style="margin-top: 10%;">
        <div class="col-md-12" style="border:1px solid #c1c1c1;border-radius:8px;">
            <div class="col-md-1">
            </div>
            <div class="col-md-4">
                <div id="imgdel" class="imgdel">
                    <img src="${request.product.firstimg}" class="bigimg">
                    <div class="smimgbox">
                        <s:iterator value="#request.product.smaimgpath" var="img">
                          <img src="${img}" class="smallimg">
                        </s:iterator>
                    </div>
                </div>
            </div>

            <div class="col-md-5">
                <div class="infbox">
                    <div class="protitle">
                        <s:property value="#request.product.title"/>
                    </div>
                    <div class="prosubtitle">
                        <s:property value="#request.product.prodes"/>
                    </div>
                </div>
                <div class="activebox">
                        <span class="activetext">
                            <i class="far fa-clock"></i>全场促销
                        </span>
                </div>
                <div class="pricebox">
                    <div class="originalprice">
                        <span class="originalprice_des">价格</span>
                        <span class="originalprice_yuan">￥</span>
                        <span class="originalprice_price"><s:property value="#request.product.originalPrice"/></span>
                    </div>
                    <div class="promoteprice">
                        <span class="promoteprice_des">促销价</span>
                        <span class="promoteprice_yuan">￥</span>
                        <span class="promoteprice_price"><s:property value="#request.product.promotePrice"/></span>
                    </div>
                </div>
                <div class="detialbox">
                    <div class="detial_nu">
                        销量
                        <span class="redword"><s:property value="#request.product.outnum"/></span>
                    </div>
                    <div class="detial_re">
                        评论
                        <span class="redword"><s:property value="#request.product.reviewnum"/></span>
                    </div>
                </div>

                <div class="pronum">
                    <span>数量</span>
                    <input id="pid" type="text" value="${request.product.proid}" style="display: none;"/>
                    <input id="pnum" class="pronuminput" type="text" value="1" />
                    <span class="arrow">
                            <a href="#nowhere" id="up" style="text-decoration: none;"><span class="glyphicon glyphicon-chevron-up downup"></span></a>
                        <a href="#nowhere" id="down" style="text-decoration: none;"><span class="glyphicon glyphicon-chevron-down downup"></span></a>
                        </span>

                    <span>件</span>

                    <span>库存</span>
                    <span class="prnu"><s:property value="#request.product.num"/></span>
                    <span>件</span>
                </div>

                <div class="servicebox">
                    <span>服务承诺</span>
                    <span>正品保证</span>
                    <span>急速退款</span>
                    <span>赠运费险</span>
                    <span>七天无理由退货</span>
                </div>

                <div class="addtofavorite">
                    <s:if test="#request.isfavor=='no'">
                        <a href="#nowhere" class="favorite no" style="text-decoration: none;"><span><i class="far fa-star"></i>&nbsp;&nbsp;加入个收藏夹</span></a>
                        <a href="#nowhere" class="favorite yes" style="text-decoration: none;display: none;"><span><i class="fas fa-star"></i> &nbsp;&nbsp;已加入个收藏夹</span></a>
                    </s:if>
                    <s:elseif test="#request.isfavor=='yes'">
                        <a href="#nowhere" class="favorite no" style="text-decoration: none;display: none;"><span><i class="far fa-star"></i>&nbsp;&nbsp;加入个收藏夹</span></a>
                        <a href="#nowhere" class="favorite yes" style="text-decoration: none;"><span><i class="fas fa-star"></i> &nbsp;&nbsp;已加入个收藏夹</span></a>
                    </s:elseif>
                </div>

                <div class="addcart">
                    <span>
                        <button id="buynow" class="btn btn-danger buynow">&nbsp;立即购买</button>
                    </span>
                        <span>
                            <button id="addto" class="btn btn-info"><i class="fas fa-shopping-cart"></i>&nbsp;加入购物车</button>
                        </span>
                </div>
            </div>
        </div>

        <div class="col-md-12">
            <div class="props">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active">
                        <a href="#pinf" data-toggle="tab">商品详情</a>
                    </li>
                    <li><a href="#preview" data-toggle="tab">累计评论&nbsp;<s:property value="#request.product.reviewnum"/></a></li>
                </ul>
                <div id="myTabContent" class="tab-content productt">
                    <div class="tab-pane fade in active " id="pinf">
                        <div class="productparambox">
                            <div class="productparam">产品参数</div>
                            <div class="paramdetail">
                                <s:iterator value="#request.product.propertyValues">
                                   <span><s:property value="key"/>:<s:property value="value"/></span>
                                </s:iterator>
                            </div>
                            <div class="col-md-12" style="margin-top: 5%">
                                <s:iterator value="#request.product.detimgpath" var="imgitem">
                                    <div class="detailimg" style="padding: 20px;text-align: center;">
                                        <img src="${imgitem}">
                                    </div>
                                </s:iterator>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="preview">
                        <s:iterator value="#request.reviews" var="ritem">
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <div class="proreviewitem">
                                    <div class="review">
                                        <div class="reviewcontent">
                                            <s:property value="#ritem.content"/>
                                        </div>
                                        <div class="reviewtime">
                                            <s:property  value="#ritem.createTime"/>
                                        </div>
                                    </div>
                                    <div class="reviewuser">
                                        <s:property value="#ritem.username"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </s:iterator>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-12">

        </div>

    </div>
</div>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>
    (function(){
        $(".smallimg").on("mouseover",function(){

            var src=$(this).attr("src");

            var array=src.split("/");
            var last=array[array.length-1];
            var llen=last.length;
            var imgno=last.substr(1,llen);
            var target="";
            for(var i=0;i<array.length-1;i++){
                target=target+array[i]+"/";
            }
            target=target+imgno;


            $(".bigimg").attr("src",target);
            $(this).css("border","2px solid red");
        });

        $(".smallimg").on("mouseleave",function(){
            $(this).css("border","2px solid #E4E4E4");
        });

        $("#up").click(function(){
            var tar=$(".pronuminput").attr("value");
            var value=parseInt(tar);
            value++;
            console.log(value);
            $(".pronuminput").attr("value",value);
            return false;
        });

        $("#down").click(function(){
            var tar=$(".pronuminput").attr("value");
            var value=parseInt(tar);
            if(value>1){
                value--;
            }
            else{
                value=1;
            }
            console.log(value);
            $(".pronuminput").attr("value",value);
            return false;
        });

        $("#addto").click(function(){
            var form=document.createElement("form");
            form.action="addToShopCartAction";
            form.method="post";
            form.style.display="none";
            var input1=document.createElement("input");
            input1.name="pid";
            input1.type="text";
            input1.value=$("#pid").attr("value");
            form.appendChild(input1);

            var input2=document.createElement("input");
            input2.type="text";
            input2.name="num";
            input2.value=$("#pnum").attr("value");
            form.appendChild(input2);

            document.body.appendChild(form);

            form.submit();

        });

        $(".no").click(function(){
            $(".no").hide();
            $(".yes").show();

            var mpid=$("#pid").attr("value");

            $.post("changeFavorAction",{pid:mpid,type:"add"},function(data,textStatus){
                if(data.result=="error"){
                    alert(data.message);
                }
                else if(data.result=="success"){
                    //alert("success");
                }
            });

        });

        $(".yes").click(function(){
            $(".yes").hide();
            $(".no").show();

            var mpid=$("#pid").attr("value");

            $.post("changeFavorAction",{pid:mpid,type:"rm"},function(data,textStatus){
                if(data.result=="error"){
                    alert(data.message);
                }
            });
        });

        $("#buynow").click(function(){
            var form=document.createElement("form");
            form.action="clearShopCartAction";
            form.method="post";
            form.style.display="none";
            var input=document.createElement("input");
            input.name="param";
            input.type="text";

            var tarpid=$("#pid").val();
            var tarpnum=$("#pnum").val();

            input.value=tarpid+":"+tarpnum;

            form.appendChild(input);

            document.body.appendChild(form);

            form.submit();
        });

    })();
</script>
</body>
</html>
