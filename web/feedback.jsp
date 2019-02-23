<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>反馈</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/feedback.css"/>

</head>

<body style="background-color: #E4E4E4">
<%@include file="WEB-INF/content/head.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-3">
        </div>

        <div class="col-md-6 feedback">
            <div class="fbtitle">
                <p><i class="fas fa-thumbtack itag"></i>您好，以下是您的反馈</p>
            </div>

            <div class="smtitle">
                <p>提交建议</p>
            </div>

            <div class="inf">
                <p>请写下您的意见或者建议</p>
            </div>

            <div>
                <textarea class="inputbox"></textarea>
                <p class="text-danger inf">请不要填写您的账号和密码</p>
            </div>

            <div class="subarea">
                <button class="btn btn-info subbtn">提交</button>
            </div>

        </div>
    </div>

    <div id="feedinf" class="modal fade" tabindex="-1" aria-hidden="true" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>提示</h4>
                </div>
                <div class="modal-body">
                    <span>我们已收到您的反馈！</span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>
    (function(){
        $(".subbtn").click(function (){
            var fcontent=$(".inputbox").val();

            $.post("addFeedBackAction",{content:fcontent},function (data) {
                if(data.result=="success"){
                    $("#feedinf").modal("show");
                }
                else{
                    alert(result.message);
                }
            })
        });
    })();

</script>
</body>
</html>
