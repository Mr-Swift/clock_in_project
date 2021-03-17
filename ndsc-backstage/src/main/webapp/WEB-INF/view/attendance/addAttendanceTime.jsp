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
    <script>
        alert("111");
        $(document).ready(function (){
            $("#attendanceType").val(${tAttendance.clockInType});
        })
    </script>
</head>
<body>

<form action="<%=request.getContextPath()%>/attendanceTime/save.do>" id="myForm">
    <input type="hidden" id="id" value="${id}" name="id">
    <table border="0" width="100%" cellpadding="0" cellspacing="0" class="bd-tc-s">
        <tr>
            <td align="right" class="titley" colspan="4">基础信息</td>
        </tr>

        <tr>
            <td align="right" class="title"><span>*</span>考勤名称：</td>
            <td align="left"><input type="text" style="width: 193px;" value="${tAttendance.clockInName}" id="clockInName" name="clockInName"/></td>
            <td width="130" align="right" class="title"><span>*</span>考勤类型：</td>
            <td align="left" colspan="1">
                <select name="attendanceType" id="attendanceType" style="width: 193px">
                <option value="">全部</option>
                <c:forEach items="${attendanceTypeList}" var="type" varStatus="attendanceTypeStatus">
                    <option value="${type.id}" <c:if test="${tAttendance.clockInType==type.id}">selected="selected"</c:if>>${type.dataValue1}</option>


                </c:forEach>
            </select>
            </td>
        </tr>

        <tr>
            <td width="130" align="right" class="title"><span>*</span>重复：</td>
            <td align="left" colspan="1">
                <select name="repeat" id="repeat" style="width: 193px">
                    <option value="重复">重复</option>
                    <option value="单次">单次</option>
                </select>

                <script>
                    repeat.addEventListener('change',function (){
                        if ($("#repeat").val()== '单次') {
                            $("#date").css("display", "block");
                            // $("#date1").css("display", "block");
                        } else {
                            $("#date").css("display","none" );
                            // $("#date1").css("display","none" );
                        }
                    });
                </script>
            </td>
            <td align="right" class="title">描<span>&nbsp;&nbsp;&nbsp;</span>述：</td>
            <td align="left">
          <textarea rows="5" cols="40" id="clockInRemark" name="clockInRemark">${tAttendance.clockInRemark}</textarea>
            </td>
        </tr>
        <tr>
            <td align="right" class="titley" colspan="4">考勤时间</td>
        </tr>
        <tr>
            <td align="right" class="title" id="">日期：</td>
            <td align="left">

                <input
                        type="text" style="width: 180px;"
                        readonly class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '%y-%M-%d'})" id="date" name="date" />
            </td>
            <td align="right" class="title"><span>*</span>时：</td>
            <td align="left" colspan="1"><select name="hour" id="hour" style="width: 193px">
                <option value="0">0</option>

                <%for (int i = 1; i <= 24; i++) {%>
                <option value="<%=i%>>"><%=i%>
                </option>
                <%}%>

            </select></td>

        </tr>
        <td align="right" class="title"><span>*</span>分：</td>
        <td align="left" colspan="1"><select name="minute" id="minute" style="width: 185px">
            <option value="0">0</option>

            <%for (int i = 1; i <= 60; i++) {%>
            <option value="<%=i%>>"><%=i%>
            </option>
            <%}%>

        </select></td>

        <td align="right" class="title"><span>*</span>秒：</td>
        <td align="left" colspan="1"><select name="second" id="second" style="width: 193px">
            <option value="0">0</option>

            <%for (int i = 1; i <= 60; i++) {%>
            <option value="<%=i%>>"><%=i%>
            </option>
            <%}%>

        </select></td>
    </table>
</form>
<div id="dlg-buttons">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td align="center" height="30">
                <a href="javascript:;" class="dailog-bt" onclick="javascript:$('#myForm').submit()">保 存</a>
                <a href="javascript:;" class="dailog-bt" onclick="javascript:$('#dlg').dialog('close')">取 消</a>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
