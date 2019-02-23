<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/9
  Time: 7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册</title>

    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>


    <style>
        li {
            list-style: none;
            display: block;
            background-color: transparent;
            padding: 6px;
            border-radius: 6px;
            text-align: center;
        }

        div.basic {
            border-radius: 5px;
            text-align: center;
            background-color: #AAAAAA;
        }

        div.hi {
            display: none;
        }

        div.active {
            display: block;
            background-color: #e67e22;
        }

        div.username {
            background-color: #C2C2C2;
            padding: 6%;
            padding-top: 6%;
            padding-left: 22%;
            border-radius: 6px;
            border: solid 1px #B8B8B8;
        }

        div.userinf {
            padding: 10%;
            padding-left: 20%;
        }

        span.error {
            height: 15px;
            width: 15px;
            background: url("images/unchecked.gif") no-repeat 0px 0px;
            padding-left: 15px;
        }

        span.success {
            height: 15px;
            background: url("images/checked.gif") no-repeat 0px 0px;
            padding-left: 15px;
        }


        .tooltip-inner {
            max-width: 400px !important;
            background-color: #656565; //修改背景色
        color: red !important; //字体颜色
        text-align: left;

        }

        .tooltip {
            opacity: 1 !important; //让整个tooltip不透明
        }
    </style>

</head>

<body style="background-color: #E4E4E4">
<div class="container">

    <div class="row" style="margin-top: 20px">
        <div class="col-md-2">
        </div>
        <div id="tag_username" class="col-md-2 basic active">
            <li><strong>1.</strong>填写用户名</li>
        </div>
        <div class="col-md-1">

        </div>
        <div id="tag_userinf" class="col-md-2 basic">
            <li><strong>2.</strong>填写用户信息</li>
        </div>
        <div class="col-md-1">
        </div>
        <div id="tag_success" class="col-md-2 basic">
            <li><strong>3.</strong>注册成功</li>
        </div>
        <div class="col-md-2">
        </div>
    </div>

    <div id="row_username" class="row" style="margin-top: 80px">
        <div class="col-md-4">

        </div>

        <div class="col-md-4" style="border-radius: 8px;">
            <div id="username" class="username" style="">
                <h3>注册</h3>
                <p>已经注册？<a href="login.jsp" style="text-decoration: none;">登录</a></p>

                <form id="telphone">
                    <div>
                        <label for="username">&nbsp;账号</label>
                        <br>
                        <div class="input-group">
                            <input name="maccount" id="maccount" type="text" class="form-control" placeholder=" 手机号/邮箱" required
                                   style="width: 190px;border-radius: 5px;"/>
                        </div>
                        <span id="account_error" style="position: absolute;top: 145px;left: 290px;" title="" data-placement="right" data-toggle="tooltip" class="tooltip"></span>
                    </div>
                </form>

                <div>
                    <label>&nbsp;验证码</label><span id="inf" style="margin-left: 20px"><small></small></span>
                    <br>
                    <div class="input-group">
                        <input id="checkcode" type="text" class="form-control" placeholder=" 验证码"
                               style="width:122px;border-radius: 5px"/>
                        <span>
				               <button id="send" class="btn btn-default" style="margin-left: 15px;border-radius: 5px;">发送</button>
                        </span>
                    </div>
                </div>

                <div style="margin-top: 16px;">
                    <button id="next" class="btn btn-primary">下一步</button>
                </div>
            </div>

        </div>

        <div class="col-md-4"></div>
    </div>

    <div id="row_userinf" class="row hi" style="margin-top: 80px;">
        <div class="col-md-4">
        </div>
        <div class="col-md-4" style="background-color: #c2c2c2;border-radius: 8px;">
            <div class="userinf">
                <form id="register">
                    <label> 昵称</label>
                    <br>
                    <div class="input-group">
                        <input id="muser_name" type="text" name="username" class="form-control" placeholder=" 昵称"
                               style="width:200px;border-radius: 5px;"/>
                    </div>
                    <span id="muser_name_img" title="" data-placement="right" data-toggle="tooltip" class="tooltip" style="position: absolute;top: 70px;left: 290px"></span>

                    <label> 密码</label>
                    <br>
                    <div class="input-group">
                        <input id="password" type="password" name="password" class="form-control" placeholder=" 密码"
                               style="width:200px;border-radius: 5px;"/>
                    </div>
                    <span id="password_img" title="" data-placement="right" data-toggle="tooltip" class="tooltip" style="position: absolute;top: 130px;left: 290px"></span>

                    <label> 再次输入密码</label>
                    <br>
                    <div class="input-group">
                        <input id="rpassword" name="rpassword" type="password" class="form-control" placeholder=" 密码"
                               style="width:200px;border-radius: 5px;"/>
                    </div>
                    <span id="rpassword_img" title="" data-placement="right" data-toggle="tooltip" class="tooltip" style="position: absolute;top: 190px;left: 290px"></span>

                    <label> 性别</label>
                    <br>
                    <div class="">
                        <input type="radio" class="radio" checked name="sex" value="man" style="display: inline-block"/>
                        <label>男</label>
                        <input type="radio" class="radio" name="sex" value="woman" style="display: inline-block;"/>
                        <label>女</label>
                    </div>

                    <label> 出生日期</label>
                    <br>
                    <div class="input-group">

                        <select id="year" name="year" class="form-control"
                                style="width:80px;margin-right: 5px;border-radius: 5px">

                        </select>

                        <select id="mon" name="mon" class="form-control"
                                style="width:60px;margin-right: 5px;border-radius: 5px;">

                        </select>

                        <select id="day" name="day" class="form-control" style="width:60px;border-radius: 5px;">

                        </select>
                    </div>

                    <label> 收货地址</label>
                    <br>
                    <div class="input-group">
                        <input id="adress" type="text" name="adress" class="form-control" placeholder="地址"
                               style="width:200px;border-radius: 5px;"/>
                    </div>
                    <span id="adress_img" title="" data-placement="right" data-toggle="tooltip" class="tooltip" style="position: absolute;top: 360px;left: 290px"></span>

                    <input id="account" name="account" style="display: none"/>

                    <button id="return" class="btn btn-primary" style="margin-top: 16px;">上一步</button>
                    <button id="check" type="button" class="btn btn-primary" style="margin-top: 16px;margin-left: 20%">
                        确定
                    </button>
                </form>
            </div>
        </div>
        <div class="col-md-4">
        </div>
    </div>

    <div id="row_success" class="row hi" style="margin-top: 80px;">
        <div class="col-md-4">
        </div>
        <div class="col-md-4" style="background-color: #c2c2c2;border-radius: 8px;">
            <div style="padding: 20%;text-align: center;">
                <h3>注册成功</h3>
                <h4><a href="login.jsp" style="text-decoration: none;">点击</a>返回登录界面</h4>
            </div>
        </div>
        <div class="col-md-4">
        </div>
    </div>


</div>

<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

<script type="text/javascript">

    var accountflag=false;
    var usernameflag=false;
    var passwordflag=false;
    var rpasswordflag=false;
    var adressflag=false;

    $(function () {


        //初始化，给select（日期）添加内容
        (function init() {

            for (var i = 1990; i < 2020; i++) {
                var item = $("<option></option>");
                item.attr("value", i);
                item.html(i);
                $("#year").append(item);
            }

            for (var j = 1; j <= 12; j++) {
                var m = $("<option></option>");
                m.attr("value", j);
                m.html(j);
                $("#mon").append(m);
            }

            for (var k = 1; k <= 31; k++) {
                var d = $("<option></option>");
                d.attr("value", k);
                d.html(k);
                $("#day").append(d);
            }
            //initValidate();
            addEvent();
        })();

        //发送验证码，并实现计数
        var realcode;
        $("#send").click(function () {
            if(accountflag==false){
                return false;
            };
            $("#inf").removeClass("text-danger");
            $("#inf small").html("发送中");
            $.post("codeAction", {account: $("#maccount").val()}, function (data, textStatus) {
                if (data["result"] == "success") {

                    realcode = data["code"];
                    $("#inf").removeClass("text-danger");
                    $("#inf small").html("验证码已发送");

                    sendMessage();
                }
                else if (data["result"] == "error") {
                    var msg = data["message"];
                    $("#inf").addClass("text-danger");
                    $("#inf small").html(msg);
                }
            });
        });

        //点击下一步，判断验证码是否正确
        $("#next").click(function () {
            if(accountflag==false){
                return false;
            }

            var ycode = $("#checkcode").val();
            if (ycode == realcode) {
                $("#row_username").hide();
                $("#row_userinf").show();
                $("#tag_username").css("background-color", "#AAAAAA");
                $("#tag_userinf").css("background-color", "#E67E22");


                $("#account").attr("value", $("#maccount").val());
            }
            else {
                $("#checkcode").focus();
                //$("#errorhidden").show();
                $("#inf").addClass("text-danger");
                $("#inf small").html("验证码错误");
            }
        });

        //点击上一步，返回第一个界面
        $("#return").click(function () {
            $("#row_userinf").hide();
            $("#row_username").show();
            $("#tag_userinf").css("background-color", "#AAAAAA");
            $("#tag_username").css("background-color", "#E67E22");
        });

        //注册用户
        $("#check").click(function () {

            /*$.post("registerAction",$("#register").serialize(),function(data,textStatus){
                if(data["result"]=="success"){
                    alert(data["message"]);
                    $("#row_userinf").hide();
                    $("#tag_userinf").css("background-color", "#AAAAAA");
                    $("#row_success").show();
                    $("#tag_success").css("background-color", "#E67E22");
                }
                else{
                    alert(data["message"]);
                }
            });*/

            if(usernameflag&&passwordflag&&rpasswordflag&&adressflag) {
                $.ajax({
                    type: "POST",
                    url: "registerAction",
                    data: $("form").serialize(),
                    dataType: "json",
                    success: function (data) {

                        $("#row_userinf").hide();
                        $("#tag_userinf").css("background-color", "#AAAAAA");
                        $("#row_success").show();
                        $("#tag_success").css("background-color", "#E67E22");
                    },
                    error: function (data, XMLHttpRequest, textStatus, errorThrown) {
                        alert("error");
                        alert(data.message);
                    }
                });
            }

        });

        //点击发送验证码后，发送按钮不可用，并显示60s倒数
        function sendMessage() {
            mcount = 60;
            $("#send").attr("disabled", "true");
            mInterValObj = window.setInterval(function () {
                mcount--;
                if (mcount == 0) {
                    window.clearInterval(mInterValObj);
                    $("#send").removeAttr("disabled");
                    $("#send").html("发送");

                    $("#inf").removeClass("text-danger");
                    $("#inf small").html("");
                } else {
                    $("#send").html(mcount + "s");
                }
            }, 1000);

        };


    });

    /*
    function initValidate(base) {
        $("#telphone").validate({
            rules:{
                account:{
                    required:true,
                    isRight:true
                }
            },
            success: function(element) {
                //正确时的样式
                element.text("").addClass("success");
            },
            messages:{
                account: {
                    required: "请填写账户",
                    isRight: "请正确填写"
                }
            },
            errorPlacement:function(error,element){
                error.insertAfter(element);
            },
            errorElement:"em"
        });

        jQuery.validator.addMethod("isRight", function(value, element) {
            var length = value.length;
            var email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value))||email.test(value);
        }, "请正确填写您的账号");

    };*/

    function addEvent(){

        $("#maccount").keyup(function (event) {
            var email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            var mobile = /^1((3[0-9])|(4[5|7])|(5([0-3]|[5-9]))|(8[0,5-9]))\d{8}$/;
            var value=$("#maccount").val();
            var length = value.length;
            if((length==11&&mobile.test(value))||email.test(value)){
                $("#account_error").removeClass("error");
                $("#account_error").addClass("success").attr("title", "");
                $("#account_error").attr("data-original-title", "");

                accountflag=true;
            }
            else{
                $("#account_error").removeClass("success");
                $("#account_error").addClass("error");

                $("#account_error").attr("data-original-title", "格式错误");
                $("#account_error").tooltip();

                accountflag=false;
            }
        });

        $("#muser_name").blur(function(){
            if($("#muser_name").val().length<=0){
                $("#muser_name_img").removeClass("success");
                $("#muser_name_img").addClass("error");

                $("#muser_name_img").attr("data-original-title", "用户名不能为空");
                $("#muser_name_img").tooltip();

                usernameflag=false;

                return false;
            }
            $.ajax({
                url:"checkUsernameAction",
                data:{username:$("#muser_name").val()},
                dataType:"json",
                success:function(data){
                    if(data.result=="success") {
                        $("#muser_name_img").removeClass("error");
                        $("#muser_name_img").addClass("success");

                        $("#muser_name_img").attr("title", "");
                        $("#muser_name_img").attr("data-original-title", "");

                        usernameflag=true;
                    }
                    else{
                        $("#muser_name_img").removeClass("success");
                        $("#muser_name_img").addClass("error");

                        $("#muser_name_img").attr("data-original-title", "用户名已存在");
                        $("#muser_name_img").tooltip();

                        usernameflag=false;
                    }
                },
                error:function (data) {
                   alert(data.message);
                }
            });
        });

        $("#password").keyup(function(){
            var value=$("#password").val();
            if(value.length<3||value.length>16){
                $("#password_img").removeClass("success");
                $("#password_img").addClass("error");

                $("#password_img").attr("data-original-title", "密码长度在3-16之间");
                $("#password_img").tooltip();
                passwordflag=false;
            }
            else{
                $("#password_img").removeClass("error");
                $("#password_img").addClass("success");

                $("#password_img").attr("title", "");
                $("#password_img").attr("data-original-title", "");

                passwordflag=true;
            }
        });

        $("#rpassword").keyup(function(){
            var pv=$("#password").val();
            var rpv=$("#rpassword").val();
            if(pv!=rpv){
                $("#rpassword_img").removeClass("success");
                $("#rpassword_img").addClass("error");

                $("#rpassword_img").attr("data-original-title", "两次密码不相同");
                $("#rpassword_img").tooltip();

                rpasswordflag=false;
            }
            else{
                $("#rpassword_img").removeClass("error");
                $("#rpassword_img").addClass("success");

                $("#rpassword_img").attr("title", "");
                $("#rpassword_img").attr("data-original-title", "");

                rpasswordflag=true;
            }
        });

        $("#adress").keyup(function(){
            var value=$("#adress").val();
            if(value.length<=0){
                $("#adress_img").removeClass("success");
                $("#adress_img").addClass("error");

                $("#adress_img").attr("data-original-title", "请填写地址");
                $("#adress_img").tooltip();

                adressflag=false;
            }
            else{
                $("#adress_img").removeClass("error");
                $("#adress_img").addClass("success");

                $("#adress_img").attr("title", "");
                $("#adress_img").attr("data-original-title", "");

                adressflag=true;
            }
        });
    };

</script>
</body>

</html>

