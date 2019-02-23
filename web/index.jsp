<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <title>旺旺商城</title>
    <meta charset="utf-8">
    <link href="css/bootstrap.css" rel="stylesheet">
      <link href="css/index.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

  </head>
  <body style="background-color: #E4E4E4">

  <%
      Object o= request.getAttribute("category");
      if(o==null){
          request.getRequestDispatcher("initAction").forward(request,response);
      }
  %>

  <%@include file="WEB-INF/content/head.jsp"%>
  <%@include file="WEB-INF/content/search.jsp"%>
  <%@include file="WEB-INF/content/right.jsp"%>

  <div class="container">
      <div class="row  leftorder">

          <div class="col-md-2" style="padding: 0px;margin-left: 50px;">
              <div class="orderbox">
                  <div class="orderhead">
                      <p style="padding-top: 9px;padding-left: 31%;"><span style="font-size: 15px;margin-left: 0%;"><i class="fas fa-list-ul" style="margin-right: 20px;"></i>分类</span></p>
                  </div>
                  <s:iterator value="#request.category" var="citem">
                      <div class="orderitem" data-target="${citem.name}">
                          <a href="searchByCategoryAction?keyword=${citem.name}" style="display:block;text-decoration: none;"><span class="itemleft"><s:property value="#citem.name"/></span><span class="itemright"><i class="fas fa-angle-right"></i></span></a>
                      </div>
                  </s:iterator>

              </div>


          </div>

          <div class="col-md-9" style="padding: 0px">
              <div class="carousel slide" id="carousel-779404">
                  <ol class="carousel-indicators">
                      <li class="active" data-slide-to="0" data-target="#carousel-779404">
                      </li>
                      <li data-slide-to="1" data-target="#carousel-779404">
                      </li>
                      <li data-slide-to="2" data-target="#carousel-779404">
                      </li>
                  </ol>
                  <div class="carousel-inner">
                      <div class="item active">
                          <img alt="" src="images/1.jpg" style="width:100%;height:530px" class="img-responsive img-thumbnail" />
                          <div class="carousel-caption">
                              <h4>

                              </h4>
                              <p>

                              </p>
                          </div>
                      </div>
                      <div class="item">
                          <img alt="" src="images/2.jpg" style="width:100%;height:530px" class="img-responsive img-thumbnail" />
                          <div class="carousel-caption">

                          </div>
                      </div>
                      <div class="item">
                          <img alt="" src="images/3.jpg" style="width:100%;height:530px" class="img-responsive img-thumbnail" />
                          <div class="carousel-caption">

                          </div>
                      </div>
                  </div> <a class="left carousel-control" href="#carousel-779404" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-779404" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
              </div>
          </div>

      </div>

      <div id="hotproduct" class="row">
          <div class="col-md-6" style="margin-left: 50px;">
              <h3>
                  <span><span class="glyphicon glyphicon-fire itemtag hottag"></span>热销商品&nbsp;HOT-SALE</span>
              </h3>
          </div>

          <div class="col-md-12 mborder"></div>

          <s:iterator value="#request.hotproduct" var="hitem">
          <div class="col-md-3">
              <div class="mitem">
                  <a href="searchByIdAction?pid=${hitem.proid}" style="text-decoration:none;">
                      <img src="${hitem.firstimg}" class="itemimg">
                      <span class="itemdes"><s:property value="#hitem.title"/>
                    </span>
                      <span class="itemprice">￥<s:property value="#hitem.promotePrice"/></span>
                  </a>
              </div>
          </div>
          </s:iterator>


      </div>

      <div id="newproduct" class="row">
          <div class="col-md-6" style="margin-left: 50px;">
              <h3>
                  <span><i class="fab fa-phoenix-framework itemtag othertag"></i>新品上市&nbsp;NEW ARRIVAL</span>
              </h3>
          </div>

          <div class="col-md-12 mborder"></div>

          <s:iterator value="#request.newproduct" var="nitem">
          <div class="col-md-3">
              <div class="mitem">
                  <a href="searchByIdAction?pid=${nitem.proid}" style="text-decoration: none;">
                      <img src="${nitem.firstimg}" class="itemimg">
                      <span class="itemdes"><s:property value="#nitem.title"/>
                    </span>
                      <span class="itemprice">￥<s:property value="#nitem.promotePrice"/></span>
                  </a>
              </div>
          </div>
          </s:iterator>

      </div>

      <div id="lifeproduct" class="row">
          <div class="col-md-6" style="margin-left: 50px;">
              <h3>
                  <span><i class="fas fa-couch itemtag othertag"></i>居家生活&nbsp;GROCERY&nbsp;& &nbsp;HEALTH</span>
              </h3>
          </div>

          <div class="col-md-12 mborder"></div>

          <s:iterator value="#request.lifeproduct" var="litem">
              <div class="col-md-3">
                  <div class="mitem">
                      <a href="searchByIdAction?pid=${litem.proid}" style="text-decoration: none;">
                          <img src="${litem.firstimg}" class="itemimg">
                          <span class="itemdes"><s:property value="#litem.title"/>
                    </span>
                          <span class="itemprice">￥<s:property value="#litem.promotePrice"/></span>
                      </a>
                  </div>
              </div>
          </s:iterator>

      </div>

      <div id="elecproduct" class="row">
          <div class="col-md-6" style="margin-left: 50px;">
              <h3>
                  <span><i class="fas fa-helicopter itemtag othertag"></i>潮电酷玩&nbsp;ELECTRONICS</span>
              </h3>
          </div>

          <div class="col-md-12 mborder"></div>

          <s:iterator value="#request.elcproduct" var="eitem">
              <div class="col-md-3">
                  <div class="mitem">
                      <a href="searchByIdAction?pid=${eitem.proid}" style="text-decoration: none;">
                          <img src="${eitem.firstimg}" class="itemimg">
                          <span class="itemdes"><s:property value="#eitem.title"/>
                    </span>
                          <span class="itemprice">￥<s:property value="#eitem.promotePrice"/></span>
                      </a>
                  </div>
              </div>
          </s:iterator>

      </div>

      <div id="fruitproduct" class="row">
          <div class="col-md-6" style="margin-left: 50px;">
              <h3>
                  <span><i class="fas fa-apple-alt itemtag othertag"></i>生鲜水果&nbsp;FRUIT&nbsp;SEAFOOD</span>
              </h3>
          </div>

          <div class="col-md-12 mborder"></div>

          <s:iterator value="#request.fruproduct" var="fitem">
              <div class="col-md-3">
                  <div class="mitem">
                      <a href="searchByIdAction?pid=${fitem.proid}" style="text-decoration: none;">
                          <img src="${fitem.firstimg}" class="itemimg">
                          <span class="itemdes"><s:property value="#fitem.title"/>
                    </span>
                          <span class="itemprice">￥<s:property value="#fitem.promotePrice"/></span>
                      </a>
                  </div>
              </div>
          </s:iterator>

      </div>

      <div id="hidebox" class="hide-box" style="opacity: 1;">
          <a href="#" id="template" class="hideboxa" style="text-decoration: none;display: none"></a>
          <div class="hide-title">

          </div>
          <div class="hide-border"></div>
          <div class="hide-content">

          </div>
      </div>

  </div>

  <script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script>
      (function() {
          //幻灯片左侧显示hidebox;
          $(".orderitem").on('mouseenter', function(event) {
              console.log("mouseenter");

              $(this).css("background-color","#fdc08b");
              var x = $(this).offset().top;
              var y = $(this).offset().left + $(this).outerWidth();

              $("#hidebox").css("position", "absolute");
              $("#hidebox").css("top", x);
              $("#hidebox").css("left", y);
              //$("#hidebox").show();

              var target=$(this).attr("data-target");

              var title=$("#hidebox").children(".hide-title");
              var content=$("#hidebox").children(".hide-content");

              title.children(".hideboxa").remove();
              content.children(".hideboxa").remove();
              content.children("span").remove();

              $.post("categorySearchAction",{categoryName:target},function(data,textStaturs){
                  var propertyValue=data.propertyValues;
                  var product=data.products;

                  title.children(".hideboxa").remove();
                  content.children(".hideboxa").remove();
                  content.children("span").remove();

                  var i=0;
                  $.each(propertyValue,function(index,item){

                      var t=$("#template").clone();
                      t.attr("href","searchByPropertyValueAction?categoryName="+target+"&value="+item);
                      t.css("display","inline-block");
                      t.html(item);
                      title.append(t);
                      i++;
                      if(i==6){
                          return false;
                      }
                  });

                  $.each(product,function(index,item){

                      var c=$("#template").clone();
                      c.css("display","inline-block");
                      c.attr("href","searchByProductNameAction?name="+item);
                      c.html(item);
                      content.append(c);
                      content.append($("<span>|<span>"));
                  });

                  $("#hidebox").show();
              },"json");

          });

          $(".orderitem").on("mouseleave", function() {
              console.log("mouseleave");

              $("#hidebox").hide();
              $(this).css("background-color","white");
              var title=$("#hidebox").children(".hide-title");
              var content=$("#hidebox").children(".hide-content");

              /*
              title.children(".hideboxa").remove();
              content.children(".hideboxa").remove();
              content.children("span").remove();*/
          });


          $("#hidebox").mouseover(function() {
              $(this).show();
          }).mouseleave(function() {
              $(this).hide();
              var title=$("#hidebox").children(".hide-title");
              var content=$("#hidebox").children(".hide-content");

              /*title.children(".hideboxa").remove();
              content.children(".hideboxa").remove();
              content.children("span").remove();*/
          });

          $(".mitem").on("mouseover mouseleave",function(event){
              if(event.type=="mouseover"){
                  $(this).css("margin","9px");
                  $(this).css("border","1px solid red");
              }
              else{
                  $(this).css("border","none");
                  $(this).css("margin","10px");
              }
          });

      })();

  </script>
  </body>
</html>
