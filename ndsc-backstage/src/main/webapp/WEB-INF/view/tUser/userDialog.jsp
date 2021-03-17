<%--
  Created by IntelliJ IDEA.
  User: LiYang
  Date: 2021/1/27
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>授权管理</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>

</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bd-tc-s">
    <tr>
        <td class="title" style="text-align: center">工号</td>
        <td>${user.personNo}</td>
    </tr>
    <tr>
        <td class="title" style="text-align: center">姓名</td>
        <td>${user.userName}</td>
    </tr>
    <tr>
        <td class="title" style="text-align: center">角色名称</td>
        <td>
            <c:forEach items="${roles}" var="role" varStatus="status">
                <label id="lab"><input type="checkbox" name="cbox" id="cb" <c:if test="${fn:contains(roleId,role.id)}">checked</c:if> value="${role.id}"/>
                    &nbsp;&nbsp; ${role.roleName}</label><br/>
            </c:forEach>
        </td>
    </tr>
</table>
</div>
<!--easyui弹窗按钮加载-->
<div id="dlg-buttons" style="padding-top: 12px">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td align="center" height="30">
                <a class="dailog-bt" href="javaScript:;" onclick="grant('${user.id}')">确 定</a>
                <a class="dailog-bt" href="javaScript:;" onclick="javascript:$('#dlg1').dialog('close')">关 闭</a>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    function grant(userId){
        var roleId = [];
        $('input[name="cbox"]:checked').each(function () {
            roleId.push($(this).val());
        });
        $.ajax({
            type:"post",
            url:"<%=request.getContextPath()%>/tUser/grant.do",
            data:{"userId":userId,"roleIds":roleId.toString()},
            dataType:"json",
            async:false,
            success:function(result){
                if (result.code == 0) {
                    alert("授权成功！");
                    $('#dlg1').dialog('close');
                    location.reload();
                } else {
                    alert(result.msg);
                    return false;
                }
            }
        });
    }
</script>
</body>
</html>