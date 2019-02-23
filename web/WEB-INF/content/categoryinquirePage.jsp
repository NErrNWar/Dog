<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询结果</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/css/inquirePage.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

    <style>
        .pager{
            margin-top: 20px;
        }
    </style>
</head>

<body style="background-color: #E4E4E4">
<%@include file="head.jsp"%>
<%@include file="search.jsp"%>
<%@include file="right.jsp"%>

<div class="container">
    <input id="cattype" value="${request.cattype}" style="display: none;"/>
    <div id="prorow" class="row" style="margin-top: 10%;margin-left: 5%;">


    </div>

    <div class="row pagerow">
        <div class="col-sm-7"></div>
        <div class="col-sm-5">
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

    $(function(){
        var ctype=$("#cattype").val();
        $.post("searchByCategoryByPageAction",{page:0,cat:ctype},function(data){
            if(data.result=="success") {

                console.log(data.inf.length);
                console.log("success");

                $("#prorow").children().remove();
                $.each(data.inf,function (index,item) {
                    var h='<div class="col-md-2" style="margin: 10px; padding: 0px;">' +
                        '                <div class="productitem">' +
                        '                    <div class="pimgbox">' +
                        '                        <a href="searchByIdAction?pid='+item.proid+'">' +
                        '                            <img src="'+item.firstimg+'" class="productimg">' +
                        '                        </a>' +
                        '                    </div>' +
                        '                    <div class="proprice">' +
                        '                        <p>￥'+item.promotePrice+'</p>' +
                        '                    </div>' +
                        '                    <div class="protitle">' +
                        '                        <a href="searchByIdAction?pid='+item.proid+'"><span class="prodes">'+item.title+'</span></a>' +
                        '                    </div>' +
                        '                    <div class="others">' +
                        '                        <div class="outbox">' +
                        '                            <span>成交量</span>' +
                        '                            <span>'+item.outnum+'</span>笔' +
                        '                        </div>' +
                        '                        <div class="outbox">' +
                        '                            <span>评价</span>' +
                        '                            <span>'+item.reviewnum+'</span>' +
                        '                        </div>' +
                        '                    </div>' +
                        '                </div>' +
                        '            </div>';

                    $("#prorow").append($(h));

                });

                $(".pagination").createPage({
                    totalPage: data.count,
                    currPage: 1,
                    backFn: function(p) {
                        findbypage(p);
                    }
                });

                bind();
            }
            else{
                console.log(data.message);
                console.log("error");

                alert(data.message);
            }
        })
    });

    function bind(){
        $(".productitem").on("mouseover",function(){
            $(this).css("border","2px solid red");
        });

        $(".productitem").on("mouseleave",function(){
            $(this).css("border","2px solid #E4E4E4");
        });
    };

    function findbypage(page) {
        var ctype=$("#cattype").val();
        $.post("searchByCategoryByPageAction",{page:page,cat:ctype},function(data){
            if(data.result=="success") {
                $("#prorow").children().remove();
                $.each(data.inf,function (index,item) {
                    var h='<div class="col-md-2" style="margin: 10px; padding: 0px;">' +
                        '                <div class="productitem">' +
                        '                    <div class="pimgbox">' +
                        '                        <a href="searchByIdAction?pid='+item.proid+'">' +
                        '                            <img src="'+item.firstimg+'" class="productimg">' +
                        '                        </a>' +
                        '                    </div>' +
                        '                    <div class="proprice">' +
                        '                        <p>￥'+item.promotePrice+'</p>' +
                        '                    </div>' +
                        '                    <div class="protitle">' +
                        '                        <a href="searchByIdAction?pid='+item.proid+'"><span class="prodes">'+item.title+'</span></a>' +
                        '                    </div>' +
                        '                    <div class="others">' +
                        '                        <div class="outbox">' +
                        '                            <span>成交量</span>' +
                        '                            <span>'+item.outnum+'</span>笔' +
                        '                        </div>' +
                        '                        <div class="outbox">' +
                        '                            <span>评价</span>' +
                        '                            <span>'+item.reviewnum+'</span>' +
                        '                        </div>' +
                        '                    </div>' +
                        '                </div>' +
                        '            </div>';

                    $("#prorow").append($(h));

                });

                bind();
            }
            else{
                alert(data.message);
            }
        })
    };


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
</script>
</body>

</html>

