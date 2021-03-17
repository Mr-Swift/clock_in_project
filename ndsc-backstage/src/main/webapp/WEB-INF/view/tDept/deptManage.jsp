<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>部门管理</title>
    <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#search").click(function () {
                $(".search-hide").slideToggle(400)
            })
        });

        function inquire() {
                var reg = /^[\s\u4e00-\u9fa5a-z0-9_-]{0,}$/;
                if (!reg.test($("#deptName").val())) {
                    alert("输入内容包含特殊字符，请重新输入！")
                    $("#deptName").focus();
                }else{
                    $("#formMsg").submit();
                }
        }

        //输入情况判断
        function keyLength(deptName) {
//            var deptName=deptName.replaceAll(/\s*/g,"");
            var patrn = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'、]/;
//            var deptName=$("#deptName").val();

            if(deptName.length>20){
                alert("部门名称不得超过20个字符！");
                return false;
            }
//            if (patrn.test(deptName)) {// 如果包含特殊字符返回false
//                alert("输入内容包含特殊字符，请重新输入！");
// //               $("#deptName").focus();
//                return false;
            }



        function goToPage() {
            var page = $("#pageNum").val();
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
                window.location.href = "<%=request.getContextPath()%>/tDept/toDept.do?page=" + page;
            } else {
                alert("跳转失败，请重新输入页数");
            }
        }

        function goupPage(page){
            if (page >= 1) {
                window.location.href = "<%=request.getContextPath()%>/tDept/toDept.do?page=" + page;
            }else{
                return false;
            }
        }

        function goNextPage(sumPage,page){
            if (page > sumPage) {
                return false;
            }else{
                window.location.href = "<%=request.getContextPath()%>/tDept/toDept.do?page=" + page;
            }
        }
        function go() {
            var pageCount = '${totalPage}';
            var num = $("#textfield8").val();
            if (num == null || num == '') {
                alert("请输入页数！");
                return false;
            } else if (Number(num) < 1 || Number(num) > Number(pageCount)) {
                alert("请输入正确的页数");
            } else {
                window.location.href = "<%=request.getContextPath()%>/tDept/toDept.do?page=" + num;
            }
        }
    </script>
</head>
<body>

<div class="pd-zy20">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
        <tr>
            <td width="46%">当前位置：平台管理 -> 部门管理</td>
        </tr>
    </table>
    <!--查询 按钮容器!-->
    <div class="cx-bt">
        <!--按钮div!-->
        <div class="btn-bt"><li class="search_bt"><a href="javascript:;" onclick="toAddPost()">新 增</a></li></div>
        <!--查询div!-->
        <form action="<%=request.getContextPath()%>/tDept/toDept.do" id="formMsg">
            <div class="search1">
                <ul>
                    <li>部门名称</li>



                    <li><input type="text" style="width:180px" id="deptName" name="deptName" onblur="keyLength(this.value)" value="${result.params.deptName}"
                               autocomplete="off"></li>
                    <li class="search_bt"><a href="javaScript:;" onclick="inquire()">查 询</a></li>




                </ul>
            </div>
        </form>
    </div>
    <div style="height:5px"></div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:6px">
        <tr>
            <th width="220">部门名称</th>
            <th>部门说明</th>
            <th width="180">操作</th>
        </tr>

        <c:forEach items="${result.data}" var="info" varStatus="v">
            <tr <c:if test="${v.count % 2 == 0}">class="ol"</c:if>>
                <td>${info.deptName}</td>
                <td>${info.deptRemark}</td>
                <td class="cz-a">
                    <a href="javaScript:;" onclick="editItems('${info.id}')"><i class="fa fa-edit pd-r06"></i>编辑</a>
                    <a href="javaScript:;" onclick="del('${info.id}')"><i class="fa fa-close pd-r06"></i>删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>




    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="fy">
        <tr>
            <td height="40" align="right">

                <%--<c:forEach var="s" begin="1" end="${totalPage}">--%>
                    <%--<a href="<%=request.getContextPath()%>/tDept/toDept.do?page=${s}">${s}</a>&nbsp;--%>
                <%--</c:forEach>--%>
                当前第 <span>${page}</span> 页 共 <span>${totalPage}</span> 页
                <a href="<%=request.getContextPath()%>/tDept/toDept.do?page=1" style="width: 50px;" >首页</a>
                <span>|</span>
                <a href="#"onclick="goupPage('${page-1}')" style="width: 50px;">上一页</a>
                <span>|</span>
                <a href="#" onclick="goNextPage('${totalPage}','${page+1}')" style="width: 50px;">下一页</a>
                <span>|</span>
                <a href="<%=request.getContextPath()%>/tDept/toDept.do?page=${totalPage}" style="width: 50px;">尾页</a>
                跳转到
            </td>

            <td width="40" align="center"><input name="textfield8" type="text" class="inp_w4" id="pageNum"/></td>
            <td width="35" align="left"><img src="<%=request.getContextPath()%>/frame/images/bt_go.gif" width="26"
                                             height="24" onclick="goToPage()"/></td>
        </tr>
    </table>
</div>

<div id="dlg2"></div>
<script type="text/javascript">

    function toAddPost(){
        $('#dlg2').dialog({
            title: "新增",
            bgiframe: true,
            width: 500,
            height: 400,
            href: '<%=request.getContextPath()%>/tDept/addDept.do',
            modal: true,
            cache: false,
            closed: true,
            maximizable: false
        }).dialog('open');

    }

    function editItems(id) {
        $('#dlg2').dialog({
            title: "编辑",
            bgiframe: true,
            width: 500,
            height: 400,
            href: '<%=request.getContextPath()%>/tDept/addDept.do?id=' + id,
            modal: true,
            cache: false,
            closed: true,
            maximizable: false
        }).dialog('open');
    }


    function del(id) {
        if (confirm("是否删除该部门？")) {
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/tDept/deleteById.do",
                data: {"id": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        alert("删除成功！");
                        window.location.href = "<%=request.getContextPath()%>/tDept/toDept.do"
                    } else {
                        alert("删除失败！")
                    }
                },
                error: function (result) {
                    alert("系统故障，请联系管理员")
                }
            })
        } else {
            window.event.returnValue = false;
        }
    }






</script>

</body>
</html>
