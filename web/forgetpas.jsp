<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/style.default.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
</head>
<body style="background-color: #E4E4E4">
<%@include file="WEB-INF/content/head.jsp"%>
   <div class="container">
    <div class="row" style="margin-top: 10%;">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">忘记密码</h4>
                </div>
                <div class="panel-body panel-body-nopadding">

                    <!-- BASIC WIZARD -->
                    <div id="basicWizard" class="basic-wizard">

                        <ul class="nav nav-pills nav-justified">
                            <li><a href="#nowhere"><span>Step 1:</span> 验证码</a></li>
                            <li><a href="#nowhere"><span>Step 2:</span> 输入新密码</a></li>
                            <li><a href="#nowhere"><span>Step 3:</span> 修改成功</a></li>
                        </ul>

                        <div class="tab-content">
                            <div class="tab-pane active" id="tab1">
                                    <div class="form-group">
                                        <label class="col-sm-4">账户<span id="accountinf" style="margin-left: 20px"><small></small></span></label>
                                        <div class="col-sm-8">
                                            <input id="account" type="text" name="account" class="form-control" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-4">验证码<span id="secodeinf" style="margin-left: 20px"><small></small></span></label>
                                        <div class="col-sm-6" style="display: inline-block">
                                            <input id="secode" type="text" name="secode" class="form-control" />
                                        </div>
                                        <span><button id="sendsecode" class="btn btn-default">发送</button></span>
                                    </div>
                            </div>
                            <div class="tab-pane" id="tab2">

                                    <div class="form-group">
                                        <label class="col-sm-4">新密码</label>
                                        <div class="col-sm-5">
                                            <input id="npassword" type="password" name="npassword" class="form-control" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-4">重复密码<span id="pasinf" style="margin-left: 20px"><small></small></span></label>
                                        <div class="col-sm-5">
                                            <input id="cpassword" type="password" name="cpassword" class="form-control" />
                                        </div>
                                    </div>

                            </div>
                            <div class="tab-pane" id="tab3">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-6">
                                    <h4>修改成功！<a href="login.jsp">点击</a>返回登录界面</h4>
                                </div>
                            </div>
                        </div>

                        <ul class="pager wizard">
                            <li class="previous"><a href="javascript:void(0)">上一步</a></li>
                            <li class="next"><a href="javascript:void(0)">下一步</a></li>
                        </ul>

                    </div>

                </div>
            </div>
        </div>

    </div>
</div>


<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

<script>
    var f1=false;
    var realcode = "";
    (function() {
        $(".previous").click(function() {
            var act = $(".active");
            if ($(act).attr("id") == "tab1") {
                return false;
            } else {
                var pre = $(act).prev();
                pre.addClass("active");
                act.removeClass("active");
            }
        });

        $(".next").click(function() {
            var act = $(".active");

            if($(act).attr("id")=="tab1"){
                if(!f1){
                    return false;
                }

                var code=$("#secode").val();
                if(code!=realcode){
                    $("#secodeinf").addClass("text-danget");
                    $("#secodeinf small").html("验证码不正确");
                    return false;
                }
            }

            if($(act).attr("id")=="tab2"){
                var npas=$("#npassword").val();
                var cpas=$("#cpassword").val();
                if(npas==""||naps==null){
                    return false;
                }

                if(npas!=cpas){
                    $("#pasinf").addClass("text-danger");
                    $("#pasinf small").html("两次密码不正确");
                    return false;
                }
                else{
                    $("pasinf small").html("");
                    var acc=$("#account").val();

                    $.post("forgetPasswordAction",{account:acc,npassword:npas},function(data){
                       if(data.result=="error"){
                           alert(data.message);
                           return false;
                       }
                    });

                }
            }

            if ($(act).attr("id") == "tab3") {
                return false;
            } else {
                var net = $(act).next();
                net.addClass("active");
                act.removeClass("active");
            }
        });

        $("#sendsecode").click(function() {
            var email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            var mobile = /^1((3[0-9])|(4[5|7])|(5([0-3]|[5-9]))|(8[0,5-9]))\d{8}$/;
            var acc = $("#account").val();

            if(acc==null||acc==""){
                f1=false;
                $("#accountinf").addClass("text-danger");
                $("#accountinf small").html("请输入账户");
                return false;
            }
            if ((acc.length == 11 && mobile.test(acc)) || email.test(acc)) {
                $("#accountinf small").html("");

                $("#secodeinf").removeClass("text-danger");
                $("#secodeinf small").html("发送中");
                $.post("seCodeToUpAction", {
                    account: acc
                }, function(data, textStatus) {
                    if (data["result"] == "success") {

                        realcode = data["code"];
                        $("#secodeinf").removeClass("text-danger");
                        $("#secodeinf small").html("验证码已发送");

                        sendMessage();
                        f1=true;
                    } else if (data["result"] == "error") {
                        var msg = data["message"];
                        $("#secodeinf").addClass("text-danger");
                        $("#secodeinf small").html(msg);

                        f1=false;
                    }
                });

            }
            else{
                $("#accountinf").addClass("text-danger");
                $("#accountinf small").html("账户格式错误");

                f1=false;
                return false;
            }


        });

    })();

    function sendMessage() {
        mcount = 60;
        $("#sendsecode").attr("disabled", "true");
        mInterValObj = window.setInterval(function() {
            mcount--;
            if (mcount == 0) {
                window.clearInterval(mInterValObj);
                $("#sendsecode").removeAttr("disabled");
                $("#sendsecode").html("发送");

                $("#secodeinf").removeClass("text-danger");
                $("#secodeinf small").html("");
            } else {
                $("#sendsecode").html(mcount + "s");
            }
        }, 1000);

    };

</script>
</body>

</html>