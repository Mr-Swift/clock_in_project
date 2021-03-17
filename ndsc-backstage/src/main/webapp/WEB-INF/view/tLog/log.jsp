<%--
  Created by IntelliJ IDEA.
  User: My
  Date: 2021/1/21
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>日志管理</title>
    <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/frame/My97DatePicker/skin/WdatePicker.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/end/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#search").click(function () {
                $(".search-hide").slideToggle(400)
            })
        });
    </script>

    <script type="text/javascript">
        function checkUserName(){
            var userName = $("#userName").val().trim();
            if(userName.length>20){
                alert("字段超长")
                return false;
            }

            if (!/^([\u4e00-\u9fa5A-Za-z\s])*$/.test(userName)) {
                alert("输入内容包含非法字符,请重新输入")
                for (var i = 0; i < userName.length; i++) {
                    if(!/^([\u4e00-\u9fa5])*$/.test(userName[i])){
                        setTxt1CursorPosition(i+1);
                    }
                }
                return false;
            }
        }
    </script>
    <script type="text/javascript">
        function goToPage() {
            var page = $("#pageNum").val();
            var userName=$("#userName").val();
            var endTime=$("#endTime").val();
            var startTime=$("#startTime").val();
            var totalPage = '${totalPage}';
            if (page != null && page != ''){
                if(!/^[1-9]+[0-9]*]*$/.test(page)){
                    alert("跳转失败，请重新输入页数!")
                    return false;
                }
                if(Number(page) > Number(totalPage)||Number(page)==0){
                    alert("跳转失败，请重新输入页数!")
                    return false;
                }
                window.location.href = "<%=request.getContextPath()%>/tLog/getLogs.do?page=" + page +"&userName=" + userName +"&startTime=" + startTime +"&endTime=" + endTime;
            } else {
                alert("跳转失败，请重新输入页数");
            }
        }

        function toSelect() {
            var userName = $("#userName").val().trim();
            $("#userName").val(userName);
            $("#Frm").submit();
        }

        function setTxt1CursorPosition(i){
            var oTxt1 = document.getElementById("userName");
            var cursurPosition=-1;
            if(oTxt1.createTextRange){//IE
                range.move("character",i);
                range.select();
            }
            else{//非IE
                oTxt1.setSelectionRange("character",i);
                oTxt1.focus();
                oTxt1.selectionStart=i;
            }
        }
            function toPreviousPage(page){
                if (Number(page) == 0) {
                    alert("已经是第一页。");
                    return false;
                }else{
                    location.href="<%=request.getContextPath()%>/tLog/getLogs.do?page=${page-1}&userName=${result.params.get('userName')}&startTime=${result.params.get('startTime')}&endTime=${result.params.get('endTime')}"
                }
            }


        function toNextPage(page) {
            var sumCount = '${totalPage}';
            if (Number(page) > Number(sumCount)) {
                alert("已是最后一页。");
                return false;
            }else {
                window.location.href = "<%=request.getContextPath()%>/tLog/getLogs.do?page=${page+1}&userName=${result.params.get('userName')}&startTime=${result.params.get('startTime')}&endTime=${result.params.get('endTime')}"
            }
        }
    </script>
    <style type="text/css">
        .ss_table tr td {
            overflow:hidden;
            white-space:nowrap;
            text-overflow:ellipsis;
        }
    </style>
</head>

<body>
<!--留白div，不能用margin padding控制!-->
<!--数据展示区域外框，用于周边留空padding不可删除-->
<div class="pd-zy20">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
        <tr>
            <td width="46%">当前位置：平台管理 -> 日志管理</td>
        </tr>
    </table>
    <!--查询 按钮容器!-->
    <div class="cx-bt">
        <!--功能按钮-->

        <!--查询-->
        <div class="search1">
        <form action="<%=request.getContextPath()%>/tLog/getLogs.do" id="Frm" method="post">
                <ul>
                    <li>操作人：</li>
                    <li><input id="userName" name="userName" type="text" style="width:140px;" value="${result.params.get('userName')}"
                               onblur="checkUserName()" ></li>

                    <li><label>操作时间</label></li>
                    <li>
                        <input type="text" id="startTime" name="startTime" readonly class="Wdate"
                           onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\');}'})"
                            value="${result.params.get('startTime')}">
                    </li>
                    <li> -</li>

                    <li><input type="text" id="endTime" name="endTime" readonly class="Wdate"
                           onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\');}'})"
                            value="${result.params.get('endTime')}">
                    </li>
                    <li class="search_bt"><a  onclick="toSelect()">查 询</a></li>
                </ul>
        </form>
            </div>
        </form>
    </div>


    <div style="height:5px"></div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:6px;table-layout: fixed">
        <thead>
        <tr>
            <th width="100">操作人</th>
            <th>信息</th>
            <th width="200">操作时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${result.data}" var="logs" varStatus="status">
            <tr <c:if test="${status.count % 2 == 0}">class="ol"</c:if>>
                <td title="${logs.userName}">${logs.userName}</td>
                <td title="${logs.operationRemark}">${logs.operationRemark}</td>
                <td><fmt:formatDate value="${logs.insertDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </c:forEach>
        </tbody>
     </table>


    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="fy">
        <tr>
            <td height="40" align="right" >
                当前第 <span>${page}</span> 页 共 <span>${totalPage}</span> 页
               <a style="width: 30px" href="<%=request.getContextPath()%>/tLog/getLogs.do?page=1&userName=${result.params.get('userName')}&startTime=${result.params.get('startTime')}&endTime=${result.params.get('endTime')}">首页</a>&nbsp;|&nbsp;
                <a style="width: 45px" href="javaScript:;" onclick="toPreviousPage('${page-1}')">上一页</a>&nbsp;|&nbsp;
                <a style="width: 45px" href="javaScript:;" onclick="toNextPage('${page+1}')">下一页</a>&nbsp;|&nbsp;
                <a style="width: 30px" href="<%=request.getContextPath()%>/tLog/getLogs.do?page=${totalPage}&userName=${result.params.get('userName')}&startTime=${result.params.get('startTime')}&endTime=${result.params.get('endTime')}">尾页</a> 跳转到</td>
            <td width="40" align="center"><input name="textfield8" type="text" class="inp_w4" id="pageNum"/></td>
            <td width="35" align="left"><img src="<%=request.getContextPath()%>/frame/images/bt_go.gif" width="26"
                                             height="24" onclick="goToPage()"/></td>
        </tr>
    </table>
</div>
<!--easyui弹窗，参数buttons根据id调用操作按钮，参数title显示弹窗标题-->


</body>
</html>
