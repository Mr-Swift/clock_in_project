<%--
  Created by IntelliJ IDEA.
  User: LiYang
  Date: 2021/1/29
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/frame/My97DatePicker/My97DatePicker/WdatePicker.js"></script>
<head>
    <title>Title</title>
</head>
<body>
<script>
    $(window).keydown( function(e) {
        var key = window.event?e.keyCode:e.which;
        if(key.toString() == "13"){
            return false;
        }
    });
    function saveOrUpdate(){
        var deptId = $("#select1 option:selected").val();//存储部门Id
        var postTypeId = $("#select2 option:selected").val();//存储岗位类型Id
        var postName = $("#input1").val().trim();//存储岗位名称
        var id = $("#id").val().replace(/\s+/g, "");//存储获取到的列id
        var patrn = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、]/;//特殊字符

        if(deptId == ''){
            alert("部门名称必选！")
            return false;
        }
        if(postTypeId == ''){
            alert("岗位类型必选！")
            return false;
        }
        if(postName == ''){
            alert("岗位名称必填！")
            $("#input1").focus();
            return false;
        }
        if(postName.length > 20){
            alert("岗位名称长度不能超过20个字符！")
            $("#input1").focus();
            return false;
        }
        if((patrn.test(postName))){
            alert("岗位名称包含特殊字符，请重新输入！")
            $("#input1").focus();
            return false;
        }
        if(confirm("确认操作？")){

            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/tPostManage/verifyPostName.do",
                data:{
                    "deptId": deptId,
                    "postTypeId": postTypeId,
                    "postName": postName
                },
                dataType: "json",
                success: function (result) {
//                    debugger
                    if(result.data.INFO == 0){
                        alert("当前岗位已存在当前部门,请做查询后再新增！");
                        $("#input1").focus();
                    }
                    if (result.data.INFO == 1){
                        $.ajax({
                            type: "post",
                            url: "<%=request.getContextPath()%>/tPostManage/saveOrUpdate.do?id="+id,
                            data:{
                                "deptId": deptId,
                                "postTypeId": postTypeId,
                                "postName": postName
                            },
                            dataType: "json",
                            success: function (result) {
                                if (result.code == 0) {
                                    alert("操作成功!");
                                    window.location.href = "<%=request.getContextPath()%>/tPostManage/getPosts.do";
                                }
                            },
                            error: function (data) {
                                alert("系统异常");
                            }
                        })
                    }
                }
            })
        }
    }
</script>
<form action="<%=request.getContextPath()%>/tPostManage/saveOrUpdate.do" method="post" id="frm">

<input id="id" name="id" type="hidden" value="${tPostManageEntity.id}">

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bd-tc-s" >
    <tr>
        <td class="title"><span>*</span>部门名称</td>
        <td><select name="select1" id="select1" style="width: 200px">
            <option value="">请选择</option>
            <c:forEach items="${depts}" var="dept" varStatus="status">
            <option value="${dept.deptId}" <c:if test="${tPostManageEntity.deptId eq dept.deptId}">selected</c:if>>${dept.deptName}</option>
            </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td class="title"><span>*</span>岗位类型</td>
        <td><select name="select2" id="select2" style="width: 200px">
            <option value="">请选择</option>
            <c:forEach items="${baseCodes}" var="baseCode" varStatus="status">
                <option value="${baseCode.id}" <c:if test="${tPostManageEntity.postTypeId eq baseCode.id}">selected</c:if>>${baseCode.dataValue1}</option>
            </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td class="title"><span>*</span>岗位名称</td>
        <td><input type="text" style="width:200px" id="input1" value="${tPostManageEntity.postName}" ></td>
    </tr>
</table>
</form>
</body>
</html>
