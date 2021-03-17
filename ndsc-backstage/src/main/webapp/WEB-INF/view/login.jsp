<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <title>在线打卡系统系统</title>
    <link href="<%=request.getContextPath()%>/frame/images/login.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
</head>
<script>
    function convertPwd(initPwd) {
        var convertPwd = escape(initPwd);//编码
        return convertPwd;
    }

    var proCodeType = 0;

    function login() {
        var account = $("#account").val().trim();
        var pwd = $("#pwd").val().trim();
        var proCode = $("#picCode").val().trim();

        if (!account.length > 0) {
            alert("请输入账号！");
        } else if (!pwd.length > 0) {
            alert("请输入密码！");
        } else if (proCode == null || proCode == "") {
            alert("请输入4位有效验证码！");
        } else if (proCode.length != 4 && proCodeType == 0) {
            alert("请输入4位有效验证码！");
        } else
        {
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/login/loginIn.do?r=" + new Date().getTime(),
                data: {
                    "account": convertPwd(account),
                    "password": convertPwd(pwd),
                    "proCode": proCode
                },
                dataType: "json",
                success: function (data) {

                    if (data.code == 0) {
                        window.location.href = "<%=request.getContextPath()%>/main/index.do";
                    }
                    else {
                        alert(data.msg);
                        getPicCode();
                    }
                },
                error: function () {
                    alert("网络连接出错！");
                }
            });
        }
    }

    // 验证码
    function getPicCode() {
        $("#pic").attr("src", "<%=request.getContextPath()%>/login/exeImg.do?r=" + new Date().getTime());
        $('#picCode').val("");
    }

    // 校验验证码
    function valiPicCode(obj) {
        var picCode = $.trim(obj.value);
        if (picCode == '' || picCode == null) {
            return;
        }
        proCodeType = 1;
        if (picCode != '' && picCode.length == 4) {
            $.ajax({
                async: false,
                type: "post",
                url: "<%=request.getContextPath()%>/login/valiPicCode.do?r=" + new Date().getTime(),
                data: {"picCode": picCode},
                dataType: "json",
                success: function (data) {
                    if (data.code != 0) {
                        alert(data.msg);
                        getPicCode();
                    } else {
                        proCodeType = 0;
                    }
                },
                error: function () {
                    alert("系统异常！");
                    proCodeType = 1;
                }
            });
        }
        else {
            // alert("请输入4位有效验证码");
            return false;
        }
    }

    document.onkeydown = function (event) {
        e = event ? event : (window.event ? window.event : null);
        if (e.keyCode == 13) {
            document.getElementById("loginBtn").click();

        }
    };


    var days = new Array("日", "一", "二", "三", "四", "五", "六");

    function showDT() {
        var currentDT = new Date();
        var y, m, date, day, hs, ms, ss, theDateStr;
        y = currentDT.getFullYear(); //四位整数表示的年份
        m = currentDT.getMonth() + 1; //月
        date = currentDT.getDate(); //日
        day = currentDT.getDay(); //星期
        hs = currentDT.getHours(); //时
        if (hs < 10) {
            hs = "0" + hs;
        }
        ms = currentDT.getMinutes(); //分
        if (ms < 10) {
            ms = "0" + ms;
        }
        ss = currentDT.getSeconds(); //秒
        if (ss < 10) {
            ss = "0" + ss;
        }
        $("#dqn").html(y);
        $("#dqy").html(m);
        $("#dqr").html(date);
        $("#dqxq").html(days[day]);
        $("#dqsj").html(hs + ":" + ms + ":" + ss);
        // setTimeout 在执行时,是在载入后延迟指定时间后,去执行一次表达式,仅执行一次
        window.setTimeout(showDT, 1000);
    }
</script>
<style>
    .djdl {
        width: 500px;
        height: 400px;
        float: left;
        position: absolute
    }
</style>
</head>

<body>
<input type="hidden" name="picCodeType" id="picCodeType" value="1">
<table width="1100" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tbody>
    <tr>
        <td align="right" height="100%" style="background:url(<%=request.getContextPath()%>/frame/images/background.jpg) center center no-repeat">
            <a href=#" target="_blank"
            >
                <div class="djdl"></div>
            </a>
            <table width="412" border="0" cellspacing="0" cellpadding="0" style="margin-top:90px">
                <tbody>
                <tr>
                    <td width="54" height="32">&nbsp;</td>
                    <td colspan="2" align="left"><input type="text" name="account" id="account" autocomplete="off"></td>
                </tr>
                <tr>
                    <td height="21" colspan="3">&nbsp;</td>
                </tr>
                <tr>
                    <td height="32">&nbsp;</td>
                    <td height="32" colspan="2" align="left"><input type="password" name="password" id="pwd" autocomplete="off"></td>
                </tr>
                <tr>
                    <td height="21" colspan="3">&nbsp;</td>
                </tr>

                <tr>
                    <td height="32">&nbsp;</td>
                    <td width="84" height="32" align="left">
                        <input type="text" name="picCode" id="picCode" maxlength="4" onblur="valiPicCode(this)"
                               style="width:80px"></td>
                    <td width="263" align="left">
                        <img id="pic" src="<%=request.getContextPath()%>/login/exeImg.do" onclick="getPicCode();" width="64"
                             height="28" style="cursor:hand;border:1px solid #ccc;vertical-align:top;"></td>
                </tr>

                <tr>
                    <td height="22" colspan="3"><span id="msg" style="color: red;"></span></td>
                </tr>
                <tr>
                    <td height="32" colspan="3" align="left"><a href="#" id="loginBtn" onclick="login()"><img src="<%=request.getContextPath()%>/frame/loginpage_files/bt.png"
                                                                                          width="235" height="32"
                                                                                          alt=""></a></td>
                </tr>
                </tbody>
            </table>

        </td>
    </tr>
    </tbody>
</table>


</body>
</html>