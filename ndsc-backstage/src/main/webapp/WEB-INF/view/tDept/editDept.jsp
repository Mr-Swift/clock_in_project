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

<body>

<form id="myfrm" action="<%=request.getContextPath()%>/tDept/saveDept.do">
    <input type="hidden" value="${entity.id}" name="id">
    <input type="hidden" value="${entity.deptName}" id="oldName">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bd-tc-s" >
            <tr>
                <td class="title"><span>*</span>部门名称</td>
                <td><input type="text" id="newDeptName" name="deptName" style="width:200px"  maxlength="20" title="最大字符长度20位" value="${entity.deptName}"></td>
            </tr>

            <tr>
                <td class="title"><span>*</span>部门说明</td>
                <td><textarea id="newDeptRemark" name="deptRemark" maxlength="100" title="最大字符长度100位" style="width:92%; height:70px">${entity.deptRemark}</textarea></td>
            </tr>
        </table>

    <!--easyui弹窗按钮加载-->
        <table cellpadding="0" cellspacing="0" style="width:100%">
            <tr>
                <td align="center" height="30">
                    <a href="#" id="bt1" class="dailog-bt" onclick="saveBnt()">确 定</a>
                    <a href="#" class="dailog-bt" onclick="javascript:$('#dlg2').dialog('close')">关 闭</a>
                </td>
            </tr>
        </table>

</form>

<script type="text/javascript">

            function saveBnt() {
                var newDeptName=$("#newDeptName").val();
                var oldName=$("#oldName").val();
                var newDeptRemark=$("#newDeptRemark").val();
                var patrn = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、]/;
                if(newDeptName==''){
                    alert("部门名称必填！");
                    $("#newDeptName").focus();
                    return false;
                }
                if (patrn.test(newDeptName)) {// 如果包含特殊字符返回false
                    alert("输入内容包含特殊字符，请重新输入！");
                    $("#newDeptName").focus();
                    return false;
                }
                if(newDeptName.length>20){
                    alert("部门名称不得超过20个字符！");
                    $("#newDeptName").focus();
                    return false;
                }
                if(newDeptRemark==''){
                    alert("部门说明必填！");
                    $("#newDeptRemark").focus();
                    return false;
                }
                if(newDeptRemark.length>100){
                    alert("部门说明不得超过100个字符！");
                    return false;
                }

                if (patrn.test(newDeptRemark)) {// 如果包含特殊字符返回false
                    alert("输入内容包含特殊字符，请重新输入！");
                    $("#newDeptRemark").focus();
                    return false;
                }





                if(confirm("确认保存？")){
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/tDept/verifyDeptName.do",
                data:{"deptName":$("#newDeptName").val(),"oldName":oldName},
                dataType:"json",
                success:function (data) {
                    if(data.code=='0'){
                        if(data.data.isExist=="1"){
                            alert("部门名称已存在！");
                        }else{
                            $("#myfrm").submit();
                        }
                    }
                }
            });
        }
    }
</script>



</body>
</html>
