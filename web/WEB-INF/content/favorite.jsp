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
    <title>我的收藏</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/style.default.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

    <style>
        .favrow {
            margin-top: 10%;
        }

        .favi {
            width: 100%;
            height: 140px;
        }

        .deldiv {
            position: absolute;
            top: 15px;
            right: 15px;
        }

        .deldiv_a {
            color: gray;
            display: none;
            font-size: 17px;
            text-decoration: none;
        }

        .pagec {
            position: absolute;
            right: 0px;
        }

        .pagerow {
            margin: 0px 20px;
            margin-bottom: 110px;
        }

        .protitle{
            max-height: 28px;
        }

    </style>


</head>

<body style="background-color: #e4e4e4">
<%@include file="head.jsp"%>
<%@include file="search.jsp"%>

<div class="container">

    <div class="contentpanel favrow">

        <ul class="filemanager-options">
            <li>
                <span style="font-size: 16px">我的收藏</span>
            </li>
        </ul>

        <div class="row">
            <div class="col-sm-12">
                <div class="row filemanager">

                    <%--<div class="col-xs-6 col-sm-4 col-md-2 image">
                        <div class="thmb">
                            <div class="deldiv">
                                <a class="deldiv_a" href="#"><i class="fas fa-backspace"></i></a>
                            </div>

                            <div class="thmb-prev">
                                <a href="#">
                                    <img src="images/clothes/1/01.jpg" class="favi" alt="" />
                                </a>
                            </div>
                            <h5 class="fm-title text-muted"><a href="#">Vegetarian.png46546465464654646646</a></h5>
                            <span style="font-size: 16px;color: #e00">￥164</span>
                        </div>
                    </div>--%>

                </div>
            </div>
        </div>


    </div>

    <div class="row pagerow">
        <div class="col-sm-8"></div>
        <div class="col-sm-4">
            <div class="pagec" id="pagearea">
                <ul class="pagination pagination-lg">
                </ul>
            </div>
        </div>
    </div>



</div>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>
    $(function() {



        $.post("getFavorByPageAction", {
            page: 1
        }, function(data) {
            var current = data.current;
            if(data.result=="success") {
                $(".filemanager").children().remove();
                $.each(data.inf, function (index, item) {
                    var html = '<div class="col-xs-6 col-sm-4 col-md-2 image">';
                    html += '<div class="thmb"> <div class="deldiv"> <a href="#nowhere" class="deldiv_a" data-target="'+item.proid+'"><i class="fas fa-backspace"></i></a></div> <div class="thmb-prev"> <a href="searchByIdAction?pid='+item.proid+'">';
                    html += '<img src="' + item.firstimg + '" class="favi" alt="" /> </a> </div>';
                    html += '<h5 class="fm-title text-muted"><a href="searchByIdAction?pid='+item.proid+'" class="protitle">' + item.title + '</a>';
                    html += '</h5> <span style="font-size: 16px;color: #e00">￥' + item.promotePrice + '</span></div></div>';

                    var ii = $(html);

                    $(".filemanager").append(ii);

                    $(".pagination").createPage({
                        totalPage: data.count,
                        currPage: 1,
                        backFn: function(p) {
                            console.log("回调函数：" + p);
                            findbypage(p);
                        }
                    });
                    bindclick();
                });
            }
            else{
                alert(data.message);
            }
        });

    });

    /*
    $(function() {
        $(".pagination").createPage({
            totalPage: 12,
            currPage: 1,
            backFn: function(p) {
                console.log("回调函数：" + p);
                alert(p);
            }
        });
    });*/

    (function($) {
        var ms = {
            init: function(totalsubpageTmep, args) {

                return (function() {
                    ms.fillHtml(totalsubpageTmep, args);
                    ms.bindEvent(totalsubpageTmep, args);
                })();
            },
            //填充html
            fillHtml: function(totalsubpageTmep, args) {
                return (function() {
                    totalsubpageTmep = "";
                    // 页码大于等于4的时候，添加第一个页码元素
                    if (args.currPage != 1 && args.currPage >= 4 && args.totalPage != 4) {
                        totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_pager' data-go='' >" + 1 + "</a></li>";
                    }
                    /* 当前页码>4, 并且<=总页码，总页码>5，添加“···”*/
                    if (args.currPage - 2 > 2 && args.currPage <= args.totalPage && args.totalPage > 5) {
                        totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_' data-go='' >...</a></li>";
                    }
                    /* 当前页码的前两页 */
                    var start = args.currPage - 2;
                    /* 当前页码的后两页 */
                    var end = args.currPage + 2;

                    if ((start > 1 && args.currPage < 4) || args.currPage == 1) {
                        end++;
                    }
                    if (args.currPage > args.totalPage - 4 && args.currPage >= args.totalPage) {
                        start--;
                    }
                    for (; start <= end; start++) {
                        if (start <= args.totalPage && start >= 1) {
                            totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_pager' data-go='' >" + start + "</a></li>";
                        }
                    }
                    if (args.currPage + 2 < args.totalPage - 1 && args.currPage >= 1 && args.totalPage > 5) {
                        totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_' data-go='' >...</a></li>";
                    }

                    if (args.currPage != args.totalPage && args.currPage < args.totalPage - 2 && args.totalPage != 4) {
                        totalsubpageTmep += "<li class='ali'><a href='javascript:void(0);' class='geraltTb_pager' data-go='' >" + args.totalPage + "</a></li>";
                    }
                    $(".pagination").html(totalsubpageTmep);
                })();
            },
            //绑定事件
            bindEvent: function(totalsubpageTmep, args) {
                return (function() {
                    totalsubpageTmep.on("click", "a.geraltTb_pager", function(event) {
                        var current = parseInt($(this).text());
                        ms.fillHtml(totalsubpageTmep, {
                            "currPage": current,
                            "totalPage": args.totalPage,
                            "turndown": args.turndown
                        });
                        if (typeof(args.backFn) == "function") {
                            args.backFn(current);
                        }
                    });
                })();
            }
        }
        $.fn.createPage = function(options) {
            ms.init(this, options);
        }
    })(jQuery);

    function findbypage(page){
        $.post("getFavorByPageAction", {
            page: page
        }, function(data) {
            var current = data.current;
            if(data.result=="success") {
                $(".filemanager").children().remove();
                $.each(data.inf, function (index, item) {
                    var html = '<div class="col-xs-6 col-sm-4 col-md-2 image">';
                    html += '<div class="thmb"> <div class="deldiv"> <a href="#nowhere" class="deldiv_a" data-target="'+item.proid+'"><i class="fas fa-backspace"></i></a></div> <div class="thmb-prev"> <a href="searchByIdAction?pid='+item.proid+'">';
                    html += '<img src="' + item.firstimg + '" class="favi" alt="" /> </a> </div>';
                    html += '<h5 class="fm-title text-muted"><a href="searchByIdAction?pid='+item.proid+'" class="protitle">' + item.title + '</a>';
                    html += '</h5> <span style="font-size: 16px;color: #e00">￥' + item.promotePrice + '</span></div></div>';

                    var ii = $(html);

                    $(".filemanager").append(ii);

                    bindclick();
                });
            }
            else{
                alert(data.message);
            }
        });
    }


    function bindclick() {

        $(".image").on("mouseenter", function() {
            var target = $(this).find(".deldiv_a");
            $(target).css("display", "block");
        });

        $(".image").on("mouseleave", function() {
            var target = $(this).find(".deldiv_a");
            $(target).hide();
        });

        $(".deldiv_a").click(function() {
            $(this).parents(".image").remove();

            $.post("changeFavorAction",{pid:$(this).attr("data-target"),type:"rm"},function(data,textStatus){
                if(data.result=="error"){
                    alert(data.message);
                }
                if(data.result=="exception"){
                    alert(data.message);
                }
            });

            return false;
        });

    };


</script>
</body>
</html>
