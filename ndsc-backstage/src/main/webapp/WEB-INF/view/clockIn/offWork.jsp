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
  <title>下班打卡</title>
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


    function add() {
      $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/offWork/add.do',
        traditional: true,
        dataType:"json",
        success: function (data) {
          window.location.reload();
        },
        error: function () {
          alert("网络连接出错！");
        }
      });
    }

    function select() {
      $("#select").submit();
    }

    /**
     * 点击年选择框，则清空 月、日选择框
     */
    function monthAndDayClear() {
      $("#month").val('');
      $("#day").val('');
      WdatePicker({dateFmt: 'yyyy', maxDate: '%y-%M-%d'})
    }

    /**
     * 点击月选择框，则清空 年、日 选择框
     */
    function yearAndDayClear() {
      $("#year").val('');
      $("#day").val('');
      WdatePicker({dateFmt: 'yyyy-MM', maxDate: '%y-%M-%d'})
    }

    /**
     * 点击日选择框，则清空 年、月 选择框
     */
    function yearAndMonthClear() {
      $("#year").val('');
      $("#month").val('');
      WdatePicker({dateFmt: 'yyyy-MM-dd', maxDate: '%y-%M-%d'})
    }


    /**
     * 导出
     */
    function toExe() {
      $("#shadow").table2excel({
        exclude: ".noExl",
        name: "Excel Document Name",
        filename: "下班打卡" + new Date().getFullYear() + "年" + new Date().getMonth() + 1 + "月" + new Date().getDate() + "日" + ".xls",
        exclude_img: true,
        exclude_links: true,
        exclude_inputs: true
      });
    }
  </script>

  <script type="text/javascript">
    function goToPage() {
      var page = $("#pageNum").val();
      var year = $("#year").val();
      var month = $("#month").val();
      var day = $("#day").val();
      var remark = $("#remark").val();

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
        window.location.href = "<%=request.getContextPath()%>/offWork/toOffWork.do?page=" + page+"&year"+year+"&month"+month+"&day"+day+"&remark"+remark;
      } else {
        alert("跳转失败，请重新输入页数");
      }
    }

    function toPreviousPage(page){
      if (Number(page) == 0) {
        alert("已经是第一页。");
        return false;
      }else{
        location.href="<%=request.getContextPath()%>/offWork/toOffWork.do?page=${page-1}&year=${result.params.get('year')}&month=${result.params.get('month')}&day=${result.params.get('day')}&remark=${result.params.get('remark')}"
      }
    }


    function toNextPage(page) {
      var sumCount = '${totalPage}';
      if (Number(page) > Number(sumCount)) {
        alert("已是最后一页。");
        return false;
      }else {
        window.location.href = "<%=request.getContextPath()%>/offWork/toOffWork.do?page=${page+1}&year=${result.params.get('year')}&month=${result.params.get('month')}&day=${result.params.get('day')}&remark=${result.params.get('remark')}"
      }
    }
  </script>
</head>

<body>
  <!--数据展示区域外框，用于周边留空padding不可删除-->
  <div class="pd-zy20">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
      <tr>
        <td width="46%">当前位置：下班打卡</td>
      </tr>
    </table>
    <div class="cx-bt">
      <!--功能按钮-->
      <div class="btn-bt">
        <ul>
          <li>
            <a href="javascript:;" onclick="add()">下班打卡</a>
            <a href="javascript:;" onclick="toExe()">导出</a>
          </li>
        </ul>
      </div>
      <!--end-->
      <!--搜索-->
      <div class="search1">
        <ul>
          <form action="<%=request.getContextPath()%>/offWork/toOffWork.do" id="select">
          <li style="color: red">当前时间：</li>
          <li>
            <div id="thisTime" style="color: red">
              <script>
                setInterval("document.getElementById('thisTime').innerHTML=(new Date()).toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);
              </script>
            </div>

          </li>
          <li>年：</li>
          <li>
          <li>
            <input type="text" id="year" name="year"
                   onclick="monthAndDayClear()">
            >
          </li>
          </li>
          <li>月：</li>

          <li>
            <input type="text"  name="month" id="month"
                   onclick="yearAndDayClear()"
            >
          </li>

          <li>日：</li>
          <li>
            <input type="text" name="day" id="day"
                   onclick="yearAndMonthClear()"
            >
          </li>
          <li>打卡时间：</li>
          <li><select name="remark" id="remark" style="width: 100px">
            <option value="">全部</option>
            <option value="正常">正常</option>
            <option value="迟到">迟到</option>
            </select></li>
          <li class="search_bt"><a href="javascript:;" onclick="select()">查 询</a></li>
          </form>
        </ul>
      </div>
    </div>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:8px;">

      <tr>
<%--        <th width="30"><span id="allCheck">全选</span></th>--%>
        <th width="60">用户</th>
        <th width="60">应打卡时间</th>
        <th width="120">实际打卡时间</th>
        <th width="120">是否早退</th>
        <th width="100">日期</th>
      </tr>

      <c:forEach items="${result.data}" var="info" varStatus="status">
        <tr <c:if test="${status.count%2==0}">class="ol"</c:if>>
          <td title="${info.userName}">${info.userName}</td>
          <td title="${info.clockInTime}">${info.clockInTime}</td>
          <td title="<fmt:formatDate value="${info.actionTime}" pattern="HH:mm:ss"/>"><fmt:formatDate
                  value="${info.actionTime}" pattern="HH:mm:ss"/></td>
          <td title="${info.remark}">${info.remark}</td>
          <td title="<fmt:formatDate value="${info.insertDate}" pattern="yyyy-MM-dd"/>"><fmt:formatDate
                  value="${info.insertDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </c:forEach>

    </table>

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="fy">
      <tr>
        <td height="40" align="right" >
          当前第 <span>${page}</span> 页 共 <span>${totalPage}</span> 页
          <a style="width: 30px" href="<%=request.getContextPath()%>/offWork/toOffWork.do?page=1">首页</a>&nbsp;|&nbsp;
          <a style="width: 45px" href="javaScript:;" onclick="toPreviousPage('${page-1}')">上一页</a>&nbsp;|&nbsp;
          <a style="width: 45px" href="javaScript:;" onclick="toNextPage('${page+1}')">下一页</a>&nbsp;|&nbsp;
          <a style="width: 30px" href="<%=request.getContextPath()%>/offWork/toOffWork.do?page=${totalPage}">尾页</a> 跳转到</td>
        <td width="40" align="center"><input name="textfield8" type="text" class="inp_w4" id="pageNum"/></td>
        <td width="35" align="left"><img src="<%=request.getContextPath()%>/frame/images/bt_go.gif" width="26"
                                         height="24" onclick="goToPage()"/></td>
      </tr>
    </table>

    <div id="shadow" style="display: none">
      <table>
        <tr>
          <c:if test="${not empty year}">
            <script>alert("111")</script>
            <td>年份：${year}</td>
          </c:if>

          <c:if test="${not empty month}">
            <td>月份：${month}</td>
          </c:if>

          <c:if test="${not empty day}">
            <td>日：${day}</td>
          </c:if>
        </tr>
      </table>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:8px;">
        <tr>
          <%--        <th width="30"><span id="allCheck">全选</span></th>--%>
          <th width="60">用户</th>
          <th width="60">应打卡时间</th>
          <th width="120">实际打卡时间</th>
          <th width="120">是否早退</th>
          <th width="100">日期</th>
        </tr>

        <c:forEach items="${result.data}" var="info" varStatus="status">
          <tr <c:if test="${status.count%2==0}">class="ol"</c:if>>
            <td title="${info.userName}">${info.userName}</td>
            <td title="${info.clockInTime}">${info.clockInTime}</td>
            <td title="<fmt:formatDate value="${info.actionTime}" pattern="HH:mm:ss"/>"><fmt:formatDate
                    value="${info.actionTime}" pattern="HH:mm:ss"/></td>
            <td title="${info.remark}">${info.remark}</td>
            <td title="<fmt:formatDate value="${info.insertDate}" pattern="yyyy-MM-dd"/>"><fmt:formatDate
                    value="${info.insertDate}" pattern="yyyy-MM-dd"/></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</body>
</html>