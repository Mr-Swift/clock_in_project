<%@ page import="com.njusc.npm.metadata.entity.TUserEntity" %>
<%@ page import="com.njusc.npm.app.util.SessionUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.njusc.npm.service.TLogService" %>
<%@ page import="com.njusc.npm.service.impl.TLogServiceImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<c:set var="ctx" value='${"/" eq pageContext.request.contextPath ? "" : pageContext.request.contextPath}'/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>在线打卡系统</title>
    <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/jquery-accordion-menu.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-accordion-menu.js"></script>
    <script type="text/javascript">

        jQuery(document).ready(function () {
            jQuery("#jquery-accordion-menu").jqueryAccordionMenu();
        });
        // $(function () {
        //     //顶部导航切换
        //     $("#demo-list").find("li").click(function () {
        //         $("#demo-list").children().removeClass("active")
        //         $(this).addClass("active");
        //     });
        //     $(".submenu li").click(function () {
        //         $(".submenu li.active").removeClass("active")
        //         $(this).addClass("active");
        //     })
        // })
        // $(function() {
        //     $(".submenu li").find("li").click(function () {
        //         $(".submenu li.active").removeClass("active");
        //         $(this).addClass("active");
        //     });
        // })

    </script>

    <script type="text/javascript">
        function exit() {
            if (!confirm("是否退出该系统？")) {
                window.event.returnValue = true;
            } else {

                $.ajax({
                    url:"<%=request.getContextPath()%>/login/gobank.do",
                    type:"post",
                    dataType:"json",
                    success:function (data){
                        if(data.code=='0'){
                            window.location.href = "<%=request.getContextPath()%>/login/unlogin.do";
                        }
                    }
                })
            }
        }
        function a(funUrl) {
            $.ajax({
                url:funUrl,
                statusCode: {
                    404: function() {
                        alert( "page not found" );
                    }
                },
            });
        }
    </script>






</head>

<body class="easyui-layout">

<div data-options="region:'north',split:false,border:false" id="north">
    <div class="head-button">
        <a href="javaScript:;" onclick="exit()"><i class="fa fa-sign-out pd-r06"></i>退出登录</a>

        <a href="JavaScript:;" style="color:#fff" onclick="$('#dlg').window('open')"><i class="fa fa-user pd-r06"></i>修改密码</a>
        <a href="JavaScript:;" style="color:#fff;cursor: default;"><i class="fa fa-user pd-r06"></i>${user.userName}</a><!--获取登录账号的用户名-->
    </div>
<%--    <div class="head-title"><img src="<%=request.getContextPath()%>/frame/images/logo.png" height="70" alt=""/></div>--%>
    <div class="head-title"><div style="font-size: 30px">在<span>&nbsp;&nbsp;</span>线<span>&nbsp;&nbsp;</span>打<span>&nbsp;&nbsp;</span>卡<span>&nbsp;&nbsp;</span>系<span>&nbsp;&nbsp;</span>统</div></div>
<%--    <span><font size='7'>打卡</font></span>--%>
</div>

<div data-options="region:'west',split:false,border:false" id="west">
    <div id="jquery-accordion-menu" class="jquery-accordion-menu blue"  >
        <ul id="demo-list" >
            <c:forEach var="function" items="${functionNewList}" >
                <li>
                    <a href="${ctx}${function.funUrl}" target="frame" onclick="a(${function.funUrl})" >
                        <c:if test="${function.funName=='员工信息管理'}"><i class="fa fa-edit"></i></c:if>
                        <c:if test="${function.funName=='打卡管理'}"><i class="fa fa-eye"></i></c:if>
                        <c:if test="${function.funName=='考勤管理'}"><i class="fa fa-forumbee"></i></c:if>
                        <c:if test="${function.funName=='请假管理'}"><i class="fa fa-server"></i></c:if>
                        <c:if test="${function.funName=='平台管理'}"><i class="fa fa-desktop"></i></c:if>
                            ${function.funName}</a>
                    <ul <c:if test="${fn:length(function.childs) > 0}">class="submenu"</c:if> >
                        <c:forEach var="chils" items="${function.childs}">
                            <li>
                                <a
                                        <c:if test="${chils.funUrl eq '' || chils.funUrl == null}"> href="javascript:;" </c:if>
                                        <c:if test="${chils.funUrl ne '' && chils.funUrl != null}"> href="${ctx}${chils.funUrl}" </c:if>
                                >${chils.funName}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>


<div data-options="region:'center',split:false,border:false" style="overflow:hidden">
    <iframe width="100%" height="100%" frameborder="0" scrolling="auto" id="frame" name="frame" src="<%=request.getContextPath()%>/main/mainshow.do"></iframe>
</div>


<div id="dlg" class="easyui-dialog" title="重置密码" style="width:500px; height:320px; padding:8px" buttons="#dlg-buttons" closed="true">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bd-tc-s" >
        <tr>
            <td class="title"><span>*</span>原密码</td>
            <td><input type="password" id="oldUserPassword" style="width:200px"></td>
        </tr>
        <tr>
            <td class="title">新密码</td>
            <td><input type="password" id="newUserPassword" style="width:200px"></td>
        </tr>
        <tr>
            <td class="title">确认密码</td>
            <td><input type="password" id="confirmUserPassword" style="width:200px"></td>
        </tr>
        <tr>
            <td colspan="2">密码长度为6~10位，至少需包含大写字母、小写字母、数字、特殊字符中至少三种组合</td>
        </tr>
    </table>
</div>
<!--easyui弹窗按钮加载-->
<div id="dlg-buttons">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td align="center" height="30">
                <a href="javaScript:;" class="dailog-bt" onclick="updatePas()">保 存</a>
                <a href="javaScript:;" class="dailog-bt" onclick="javascript:$('#dlg').dialog('close'),
                        $('#dlg').dialog({
                        onClose: function(){
                        $('#dlg').form('clear');
                        //window.location.reload();
                        }
                        });">关 闭</a>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    //dialog窗口关闭时清空数据
    $(function () {
        $("#dlg").dialog({
            onClose: function(){
                $("#dlg").form('clear');
                //window.location.reload();
            }
        });
    })
    //修改密码
    function updatePas() {
        var oldUserPassword = $("#oldUserPassword").val().trim();
        var newUserPassword = $("#newUserPassword").val().trim();
        var confirmUserPassword = $("#confirmUserPassword").val().trim();
        if (oldUserPassword == '') {
            alert("原密码不能为空！");
            return false;
        } else if (newUserPassword== '') {
            alert("新密码不能为空！");
            return false;
        } else if (confirmUserPassword== '') {
            alert("确认密码不能为空!");
            return false;
        } else if (oldUserPassword == newUserPassword) {
            alert("新旧密码一致，密码修改失败!");
            clear() ;
            return false;
        } else if(newUserPassword!= confirmUserPassword){
            alert("两次密码不一致!");
            clear() ;
            return false;
        }
        else {
            var pwd = newUserPassword.replace(/ /g, "*");   //将" "全部替换成"*"，中间的空格可以作为密码，但存储的过程中将空格存储为*
            var passTest = /((^(?=.*[a-z])(?=.*[A-Z])(?=.*\W)[\da-zA-Z\W]{6,10}$)|(^(?=.*\d)(?=.*[A-Z])(?=.*\W)[\da-zA-Z\W]{6,10}$)|(^(?=.*\d)(?=.*[a-z])(?=.*\W)[\da-zA-Z\W]{6,10}$)|(^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[\da-zA-Z\W]{6,10}$))/;
            if (!passTest.test(pwd)) {
                alert("密码长度为6~10位，至少需包含大写字母、小写字母、数字、特殊字符中至少三种组合");
                clear();
                return false;
            } else {
                $.ajax({
                    async: false,
                    type: "post",
                    url: '<%=request.getContextPath()%>/login/checkBpwd.do',
                    data: {"bpwd": oldUserPassword},
                    dataType: "json",
                    success: function (data1) {
                        if (data1.code=="0") {
                            var aa = window.confirm("您确定修改密码吗？");
                            if (aa) {
                                $.ajax({
                                    async: false,
                                    type: "post",
                                    url: '<%=request.getContextPath()%>/login/passwordUpdate.do',
                                    data: {"bpwd": oldUserPassword, "npwd": newUserPassword},
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.code == "0") {
                                            alert("密码修改成功");
                                            clear() ;
                                            // 跳转登录页面
                                            window.location.href = "<%=request.getContextPath()%>/login/unlogin.do";
                                        }
                                    }
                                });
                            }
                        }else{
                            alert("原密码不正确");
                        }
                    }
                });
            }
        }
    }
    <!--清空操作-->
    function clear() {
        $("#oldUserPassword").val("");
        $("#newUserPassword").val("");
        $("#confirmUserPassword").val("");
    }
</script>
</body>


</html>
