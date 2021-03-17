<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>考勤统计管理</title>
  <link href="<%=request.getContextPath()%>/frame/demo/images/default.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/frame/demo/images/default/easyui.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.table2excel.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/frame/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript">
    $(document).ready(function () {
      $("#search").click(function () {
        $(".search-hide").slideToggle(400)
      })
      $("#close-s").click(function () {
        $(".search-hide").slideToggle(400)
      });
      $("#search1").click(function () {
        $(".search-hide1").slideToggle(400)
      })
      $("#close-s1").click(function () {
        $(".search-hide1").slideToggle(400)
      })
      //标签切换
      var $li = $(".sw_b li");
      var $div = $(".swb_b");
      $li.click(function () {
        var $t = $(".sw_b li").index($(this));
        $li.removeClass();
        $(this).addClass("active");
        $div.css("display", "none");
        $div.eq($t).css("display", "block");
      })

      // 全选
      var inpcheck = $("td input[type=checkbox]");
      $("#allCheck").click(function () {
        if (inpcheck.data('checked')) {
          inpcheck.prop('checked', false);
          inpcheck.data('checked', false);
        } else {
          inpcheck.prop('checked', true);
          inpcheck.data('checked', true);
        }
      })
    });



    /**
     * 点击年选择框，则清空 月 选择框
     */
    function monthAndDayClear() {
        $("#month").val('');
        WdatePicker({dateFmt: 'yyyy', maxDate: '%y-%M-%d'})
    }

    /**
     * 点击月选择框，则清空 年、日 选择框
     */
    function yearAndDayClear() {
        $("#year").val('');
        WdatePicker({dateFmt: 'yyyy-MM', maxDate: '%y-%M-%d'})
    }

  </script>
</head>

<body>
<!--数据展示区域外框，用于周边留空padding不可删除-->
<div class="pd-zy20">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
    <tr>
      <td width="46%">当前位置：考勤统计管理</td>
    </tr>
  </table>

  <div class="cx-bt">
    <!--功能按钮-->
    <div class="btn-bt">
      <ul>
        <li>
<%--          <a href="#"></a>--%>
          <a href="#" onclick=add()>导出</a>
        </li>
      </ul>
    </div>
    <!--end-->
    <!--搜索-->


    <div class="search1">
        <form action="<%=request.getContextPath()%>/attendanceStatistics/search.do" id="myForm">
            <input type="hidden" value="1" id="hit" name="hit">
        <li>用户：</li>

        <li>
          <input type="text"  name="userName" id="userName">
        </li>

        <li id="clockInType1" style="display: none">考勤类型：</li>
        <li><select name="clockInType" id="clockInType" style="width: 100px;display: none">
          <option value="">全部</option>
            <c:forEach items="${attendanceTypeList}" var="attendanceType">
                <option value="${attendanceType.id}">${attendanceType.dataValue1}</option>
            </c:forEach>
        </select></li>

        <li id="clockInName1" style="display: none">考勤名称：</li>
          <li><select name="clockInName" id="clockInName" style="width: 100px;display: none">
              <option value="">全部</option>
              <c:forEach items="${attendanceNameList}" var="attendance">
                  <option value="${attendance.id}">${attendance.clockInName}</option>
              </c:forEach>
          </select></li>



        <li>年：</li>

        <li>
          <input type="text" id="year" name="year"
                 onclick="monthAndDayClear()">
        </li>
        <li>月：</li>

        <li>
          <input type="text"  name="month"
          <%--                                   value="${year}-${month}"--%>
                 onclick="yearAndDayClear()">
        </li>

        <li class="search_bt"><a href="JavaScript:;" onclick="javascript:$('#myForm').submit()">查 询</a></li>
        <li class="search_bt"><a href="javaScript:;" id="detail">明 细</a></li>
      </form>
          <script>
              $("#detail").click(function (){
                  var str=$("#detail").text();
                  if(str=='明 细'){
                      $("#table1").css("display","none");
                      $("#table2").css("display","block");
                      $("#clockInName").css("display","block");
                      $("#clockInName1").css("display","block");
                      $("#clockInType").css("display","block");
                      $("#clockInType1").css("display","block");
                      $("#detail").text('统 计');
                      $('#hit').val(1);
                  }else{
                      $("#table1").css("display","block");
                      $("#table2").css("display","none");
                      $("#clockInName").css("display","none");
                      $("#clockInName1").css("display","none");
                      $("#clockInType").css("display","none");
                      $("#clockInType1").css("display","none");
                      $("#detail").text('明 细');
                      $('#hit').val(2);
                  }

              });

              $(document).ready(function (){
                  if('${hit}'==1){
                      $("#table1").css("display","none");
                      $("#table2").css("display","block");
                      $("#clockInName").css("display","block");
                      $("#clockInName1").css("display","block");
                      $("#clockInType").css("display","block");
                      $("#clockInType1").css("display","block");
                      $("#detail").text('统 计');
                      $('#hit').val(1);
                  }else{
                      $("#table1").css("display","block");
                      $("#table2").css("display","none");
                      $("#clockInName").css("display","none");
                      $("#clockInName1").css("display","none");
                      $("#clockInType").css("display","none");
                      $("#clockInType1").css("display","none");
                      $("#detail").text('明 细');
                      $('#hit').val(2);
                  }
              });
          </script>
      </ul>
    </div>
  </div>

  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:8px;display: none" id="table2">

    <tr>
<%--      <th width="30"><span id="allCheck">全选</span></th>--%>
      <th width="200">职工</th>
      <th width="200">考勤名称</th>
      <th width="200">考勤类型</th>
      <th width="200">考勤描述</th>
      <th width="200">考勤时间</th>
      <th width="200">打卡时间</th>
      <th width="200">备注</th>
      <th width="200">日期</th>
    <tr>
      <c:forEach items="${detailList}" var="detail" varStatus="detailStatus">
      <tr <c:if test="${detailStatus.count%2==0}">class="ol"</c:if>>
          <td>${detail.userName}</td>
          <td>${detail.clockInName}</td>
          <td>${detail.clockInType}</td>
          <td>${detail.clockInRemark}</td>
          <td>${detail.clockInTime}</td>
          <td><fmt:formatDate value="${detail.actionTime}" pattern="hh:mm:ss"/></td>
          <td>${detail.remark}</td>
          <td><fmt:formatDate value="${detail.insertDate}" pattern="yyyy-MM-dd"/></td>
      </tr>
      </c:forEach>

    </tr>
  </table>

  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:8px;" id="table1">
    <tr>
        <th width="200">职工</th>
        <th width="200">迟到次数</th>
        <th width="200">早退次数</th>
        <th width="200">缺卡次数</th>
        <th width="200">外勤次数</th>
        <th width="200">出勤班次</th>
        <th width="200">旷工</th>
    </tr>
      <c:forEach items="${statisticsList}" var="statistics" varStatus="statisticsStatus">
          <tr <c:if test="${statisticsStatus.count%2==0}">class="ol"</c:if>>
              <td>${statistics.userName}</td>
              <td>${statistics.late}</td>
              <td>${statistics.leaveEarly}</td>
              <td>${statistics.missAttendance}</td>
              <td>${statistics.workOutSide}</td>
              <td>${statistics.attendanceTimes}</td>
              <td>${statistics.absenteeism}</td>
          </tr>
      </c:forEach>
  </table>
</div>
</body>
</html>