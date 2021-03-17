<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/demo/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/demo/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.table2excel.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form action="<%=request.getContextPath()%>/leaveApplication/save.do>" id="myForm">
    <input type="hidden" id="id" name="id" value="${id}">
    <table border="0" width="100%" cellpadding="0" cellspacing="0" class="bd-tc-s">
        <tr>
            <td align="right" class="titley" colspan="4">基础信息</td>
        </tr>

        <tr>
            <td width="130" align="right" class="title"><span>*</span>请假类型：</td>
            <td align="left" colspan="1">
                <select name="leaveType" id="leaveType" style="width: 193px">
                    <option>请选择</option>
                    <c:forEach items="${leaveTypeList}" var="leaveType">
                        <option value="${leaveType.id}" <c:if test="${tLeaveEntity.leaveType==leaveType.id}">selected="selected"</c:if>>${leaveType.dataValue1}</option>
                    </c:forEach>
                </select>
            </td>
            <td align="right" class="title"><span>*</span>理<span>&nbsp;&nbsp;&nbsp;</span>由：</td>
            <td align="left">
                <textarea rows="5" cols="40" id="leaveReason" name="leaveReason">${tLeaveEntity.leaveReason}</textarea>
            </td>
        </tr>

        <tr>
            <td align="right" class="titley" colspan="4">请假时间</td>
        </tr>
        <tr>
            <td align="right" class="title"><span>*</span>日期：</td>
            <td align="left">

                <input
                        type="text" style="width: 180px;" id="startDay" name="startDay"
                        readonly class="Wdate"
                        onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\');}'})"/></td>
            <td align="right" class="title"><span>*</span>时：</td>
            <td align="left" colspan="1"><select name="startHour" id="startHour" style="width: 193px">
                <option>请选择</option>

                <%for (int i = 1; i <= 24; i++) {%>
                <option value="<%=i%>"><%=i%></option>
                <%}%>

            </select></td>

        </tr>
        <td align="right" class="title"><span>*</span>分：</td>
        <td align="left" colspan="1"><select name="startMinute" id="startMinute" style="width: 193px">
            <option>请选择</option>

            <%for (int i = 1; i <= 60; i++) {%>
            <option value="<%=i%>"><%=i%>
            </option>
            <%}%>

        </select></td>

        <td align="right" class="title"><span>*</span>秒：</td>
        <td align="left" colspan="1"><select name="startSecond" id="startSecond" style="width: 193px">
            <option>请选择</option>

            <%for (int i = 1; i <= 60; i++) {%>
            <option value="<%=i%>"><%=i%>
            </option>
            <%}%>

        </select></td>


        <tr>
            <td align="right" class="titley" colspan="4">销假时间</td>
        </tr>
        <tr>
            <td align="right" class="title"><span>*</span>日期：</td>
            <td align="left">

                <input
                        type="text" style="width: 180px;" id="endDay" name="endDay"
                        readonly class="Wdate"
                        onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\');}'})"/></td>
            <td align="right" class="title"><span>*</span>时：</td>
            <td align="left" colspan="1"><select name="endHour" id="endHour" style="width: 193px">
                <option>请选择</option>

                <%for (int i = 1; i <= 24; i++) {%>
                <option value="<%=i%>"><%=i%>
                </option>
                <%}%>

            </select></td>

        </tr>
        <td align="right" class="title"><span>*</span>分：</td>
        <td align="left" colspan="1"><select name="endMinute" id="endMinute" style="width: 193px">
            <option>请选择</option>

            <%for (int i = 1; i <= 60; i++) {%>
            <option value="<%=i%>"><%=i%>
            </option>
            <%}%>

        </select></td>

        <td align="right" class="title"><span>*</span>秒：</td>
        <td align="left" colspan="1"><select name="endSecond" id="endSecond" style="width: 193px">
            <option>请选择</option>

            <%for (int i = 1; i <= 60; i++) {%>
            <option value="<%=i%>"><%=i%>
            </option>
            <%}%>

        </select></td>
    </table>

</form>


<div id="dlg-buttons">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td align="center" height="30">
                <a href="javascript:;" class="dailog-bt" onclick="">保 存</a><a href="javascript:$('#myForm').submit()" class="dailog-bt"
                                                                              onclick="javascript:$('#dlg').dialog('close')">取
                消</a>
            </td>
        </tr>
    </table>
</div>
</body>