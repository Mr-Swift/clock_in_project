<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>考勤时间管理</title>
    <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/demo/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/demo/js/jquery.easyui.min.js"></script>
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

        function modify(id) {
            $('#dlg').window({
                title: '修改考勤时间',
                bgiframe: false,
                width: 900,
                height: 400,
                href: '<%=request.getContextPath()%>/attendanceTime/add.do?id='+id,
                modal: true,
                cache: false,
                maximizable: false,
            });
            $('#dlg').window('open');

        };

        function add() {
            $("#dlg").dialog({
                title: '新增考勤时间',
                bgiframe: false,
                width: 900,
                height: 400,
                href: '<%=request.getContextPath()%>/attendanceTime/add.do',
                modal: true,
                cache: false,
                maximizable: false,
            }).dialog('open');
        };


    </script>
    <script type="text/javascript">
        function del(id) {
            if (!confirm("是否确认删除该员工？")) {
                window.event.returnValue = false;
            }else{
                $.ajax({
                    type: "post",
                    url: "<%=request.getContextPath()%>/attendanceTime/delete.do",
                    data: {"id": id},
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            alert("删除成功！");
                            window.reload();
                        } else {
                            alert("删除失败！")
                        }
                    },
                    error: function (data) {
                        alert("系统故障，请联系管理员")
                    }
                })
            }
        }
    </script>
</head>

<body>
<!--数据展示区域外框，用于周边留空padding不可删除-->
<div class="pd-zy20">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
        <tr>
            <td width="46%">当前位置：考勤时间管理</td>
        </tr>
    </table>
    <div class="cx-bt">
        <!--功能按钮-->
        <div class="btn-bt">
            <ul>
<%--                <li><a href="#">导出</a>--%>
                    <a href="#" onclick=add()>新增</a></li>
            </ul>
        </div>
        <!--end-->
        <!--搜索-->
<%--        <div class="search1">--%>
<%--            <ul>--%>
<%--                <li>考勤名称：</li>--%>
<%--                <li><input name="" type="text" style="width:70px;" id="attendanceName" name="attendanceName"></li>--%>
<%--                <li>操作人：</li>--%>
<%--                <li><input name="" type="text" style="width:70px;" id="insertUser" name="insertUser"></li>--%>
<%--                <li>考勤类型：</li>--%>
<%--                <li><select name="attendanceType" id="attendanceType" style="width: 100px" name="attendanceType">--%>
<%--                    <option>全部</option>--%>
<%--                    <c:forEach items="${attendanceTypeList}" var="attendanceType" varStatus="attendanceTypeStatus">--%>
<%--                        <option value="${attendanceType}">${attendanceType}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select></li>--%>
<%--            </ul>--%>
<%--        </div>--%>
    </div>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:8px;">

        <tr>
<%--            <th width="30"><span id="allCheck">全选</span></th>--%>
            <th width="60">序号</th>
            <th width="60">考勤名称</th>
            <th width="60">考勤时间</th>
            <th width="120">考勤类型</th>
            <th width="120">考勤描述</th>
            <th width="120">操作人</th>
            <th width="40">操作时间</th>
            <th width="40">操作</th>


<%--        <tr>--%>
<%--            <td><input name="" type="checkbox" value=""/></td>--%>
<%--            <td>*</td>--%>
<%--            <td>*</td>--%>
<%--            <td>*</td>--%>
<%--            <td>*</td>--%>
<%--            <td>*</td>--%>
<%--            <td>*</td>--%>
<%--            <td class="cz-a"><a onclick=modify()><i class="fa fa-edit pd-r06"></i>修改</a> <a--%>
<%--                    onclick="return del()"><i class="fa fa-close pd-r06"></i>删除</a></td>--%>
<%--        </tr>--%>


        <c:forEach items="${dataList}" var="data" varStatus="dataStatus">
            <tr <c:if test="${dataStatus.count%2==0}">class="ol"</c:if>>
                <td>${dataStatus.count}</td>
                <td>${data.clockInName}</td>
                <td>${data.clockInTime}</td>
                <td>${data.clockInTypeStr}</td>
                <td>${data.clockInRemark}</td>
                <td>${data.insertUser}</td>
                <td><fmt:formatDate value="${data.insertDate}" pattern="yyyy-MM-dd"/></td>
                <td class="cz-a"><a onclick=modify('${data.id}')><i class="fa fa-edit pd-r06"></i>修改</a> <a
                        onclick="return del('${data.id}')"><i class="fa fa-close pd-r06"></i>删除</a></td>
            </tr>
        </c:forEach>


    </table>

<%--    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="fy">--%>
<%--        <tr>--%>
<%--            <td height="40" align="right">当前第 1 页 共 14 页 <a href="#" class="active">1</a> <a href="#">2</a> <a--%>
<%--                    href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a> <a href="#">7</a> <a--%>
<%--                    href="#">8</a> <a--%>
<%--                    href="#">9</a> <a href="#">10</a> 跳转到--%>
<%--            </td>--%>
<%--            <td width="40" align="center"><input name="textfield8" type="text" class="inp_w4" id="textfield8"/></td>--%>
<%--            <td width="35" align="left"><img src="<%=request.getContextPath()%>/frame/images/bt_go.gif" width="26"--%>
<%--                                             height="24"/></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
</div>

<div id="dlg"></div>
</body>
</html>