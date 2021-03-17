<%--
  Created by IntelliJ IDEA.
  User: LiYang
  Date: 2021/1/28
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>岗位管理</title>
    <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js?v=1.0"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-accordion-menu.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $("#search").click(function(){
                $(".search-hide").slideToggle(400)
            })
        });
    </script>
    <script type="text/javascript">
        $(window).keydown( function(e) {
            var key = window.event?e.keyCode:e.which;
            if(key.toString() == "13"){
                return false;
            }
        });
        function del(id){
            if (confirm("是否删除该岗位？")){
                $.ajax({
                    type: "post",
                    url:  "<%=request.getContextPath()%>/tPostManage/deleteById.do",
                    data: {"id":id},
                    dataType: "json",
                    success:function (data) {
                        if(data.code == 0){
                            alert("删除成功！");
                            window.location.href = "<%=request.getContextPath()%>/tPostManage/getPosts.do"
                        }else {
                            alert("删除失败！");
                        }
                    },
                    error: function (result) {
                        alert("系统出现异常，请联系管理员！");
                    }
                })
            }else {
                window.event.returnValue = false;
            }
        }
    </script>

    <script type="text/javascript">
        function goToPage() {
            var page = $("#pageNum").val().replace(/\s+/g, "");
            var totalPage = '${totalPage}';
            var reg = /^[1-9]+[0-9]*]*$/; //校验输入是否是数字
            if (page != null && page != '') {
                if (Number(page) > Number(totalPage)) {
                    alert("请输入正确页码");
                    $("#pageNum").focus();
                    return false;
                }
                if(!(reg).test(page)){
                    alert("请输入数字！")
                    $("#pageNum").focus();
                    return false;
                }
                window.location.href = "<%=request.getContextPath()%>/tPostManage/getPosts.do?page=" + page;
            } else {
                alert("请输入要跳转的页数！");
            }
        }

        function toSelect() {
            //查询条件校验
            var deptName = $("#deptName1").val().trim();//存储部门名称
            var postName = $("#postName1").val().trim();//存储岗位名称
            var patrn = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、]/;//特殊字符
            if (patrn.test(deptName)) {// 如果包含特殊字符返回false
                alert("部门名称包含特殊字符！请重新输入")
                $("#deptName1").focus();
            } else if (deptName.length >20) {
                alert("部门名称过长，请重新输入");
                $("#deptName1").focus();
            } else if (patrn.test(postName)) {// 如果包含特殊字符返回false
                alert("岗位名称包含特殊字符！请重新输入")
                $("#postName1").focus();
            } else if (postName.length >20) {
                alert("岗位名称过长，请重新输入");
                $("#postName1").focus();
            } else {
                $("#Frm").submit();
            }
        }

        function toAddPost() {
            $('#dlg2').dialog({
                title: '新增岗位',
                width: 500,
                height: 390,
                closed: false,
                cache: false,
                href: '<%=request.getContextPath()%>/tPostManage/showInfo.do',
                modal: true
            });
        }

        function edit(id){
            $('#dlg2').dialog({
                title: '编辑岗位',
                width: 500,
                height: 390,
                closed: false,
                cache: false,
                href: '<%=request.getContextPath()%>/tPostManage/showInfo.do?id='+id,
                modal: true
            });
        }


        function goupPage(page){
            if (page >= 1) {
                window.location.href = "<%=request.getContextPath()%>/tPostManage/getPosts.do?page=" + page;
            }else{
                return false;
            }
        }

        function goNextPage(sumPage,page){
            if (page > sumPage) {
                return false;
            }else{
                window.location.href = "<%=request.getContextPath()%>/tPostManage/getPosts.do?page=" + page;
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
                window.location.href = "<%=request.getContextPath()%>/tPostManage/getPosts.do?page=" + num;
            }
        }
    </script>


</head>

<body>
<!--留白div，不能用margin padding控制!-->
<!--数据展示区域外框，用于周边留空padding不可删除-->
<div class="pd-zy20">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
        <tr>
            <td width="46%">当前位置：平台管理 -> 岗位管理</td>
        </tr>
    </table>
            <div class="cx-bt">
                <!--功能按钮-->
                <div class="btn-bt">
                    <ul>
                        <li class="search_bt">
                            <a href="javaScript:;" onclick="toAddPost()">新 增</a>
                        </li>
                    </ul>
                </div>

                <form action="<%=request.getContextPath()%>/tPostManage/getPosts.do" id="Frm" method="post">
                <div class="search1" style="margin-bottom: 14px">
                    <ul>
                        <li>部门名称</li>
                        <li><input type="text" style="width:180px" name="deptName" value="${deptName}" id="deptName1" ></li>
                        <li>岗位名称</li>
                        <li><input type="text" style="width:180px" name="postName"  value="${postName}" id="postName1" ></li>
                        <li class="search_bt"><a href="javaScript:;" onclick="toSelect()">查 询</a></li>
                    </ul>
                </div>
    </form>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:6px">
        <tr>
            <th width="220">部门名称</th>
            <th>岗位名称</th>
            <th width="180">操作</th>
        </tr>
        <c:forEach items="${result.data}" var="res" varStatus="status">
            <tr <c:if test="${status.count % 2 == 0}">class="ol"</c:if>>
                <td>${res.deptName.replace(' ','&nbsp;')}</td>
                <td>${res.postName.replace(' ','&nbsp;')}</td>
                <td class="cz-a">
                    <a href="#" onclick="edit('${res.id}')">
                        <i class="fa fa-edit pd-r06"></i>编辑
                    </a>
                    <a href="#" onclick="del('${res.id}')">
                        <i class="fa fa-close pd-r06"></i>删除
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="fy">
        <tr>
            <td height="32" align="right" valign="bottom">
                当前第 <span>${page}</span> 页 共 <span>${totalPage}</span> 页
                <a style="width: 30px" href="<%=request.getContextPath()%>/tPostManage/getPosts.do?page=1">首页</a>&nbsp;|&nbsp;
                <a style="width: 45px" href="#" onclick="goupPage('${page-1}')">上一页</a>&nbsp;|&nbsp;
                <a style="width: 45px" href="#" onclick="goNextPage('${totalPage}','${page+1}')">下一页</a>&nbsp;|&nbsp;
                <a style="width: 30px" href="<%=request.getContextPath()%>/tPostManage/getPosts.do?page=${totalPage}">尾页</a> 跳转到
            </td>
            <td width="40" align="center" valign="bottom">
                <input name="textfield8" type="text" style="width:16px; height:16px;" id="textfield8" oninput="value=value.replace(/[^\d]/g,'')"/>
            </td>
            <td width="40" align="left" valign="bottom">
                <img src="<%=request.getContextPath()%>/frame/images/bt_go.gif" width="26" height="24" onclick="go()"/>
                <%--<input type="button" value="Go" onclick="go()" class="go"/>--%>
            </td>
        </tr>
    </table>
    <%--    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="fy">--%>
    <%--        <tr>--%>
    <%--            <td height="40" align="right" valign="bottom">--%>
    <%--                当前第 <span>${page}</span> 页--%>
    <%--                共 <span>${totalPage}</span> 页--%>
    <%--                <c:forEach var="s" begin="1" end="${totalPage-1}">--%>
    <%--                    <a href="<%=request.getContextPath()%>/tPostManage/getPosts.do?page=${s}">${s}</a>&nbsp;--%>
    <%--                    <a href="<%=request.getContextPath()%>/tPostManage/getPosts.do?page=${totalPage}">${totalPage}</a>--%>
    <%--                </c:forEach> 跳转到--%>
    <%--            </td>--%>
    <%--            <td width="40" align="center" valign="bottom">--%>
    <%--                <input name="textfield8" type="text" class="inp_w4" id="pageNum"/>--%>
    <%--            </td>--%>
    <%--            <td width="35" align="left" valign="bottom">--%>
    <%--                <a id="goto" href="#" style="display: inline-block;text-indent:-5px;padding-bottom: 5px">--%>
    <%--                <img src="<%=request.getContextPath()%>/frame/images/bt_go.gif" width="26" height="24" onclick="goToPage()"/>--%>
    <%--                </a>--%>
    <%--            </td>--%>
    <%--        </tr>--%>
    <%--    </table>--%>
</div>
<%--<!--easyui弹窗，参数buttons根据id调用操作按钮，参数title显示弹窗标题-->
<div id="dlg1" class="easyui-dialog" title="部门管理" style="width:500px; height:390px; padding:8px" buttons="#dlg-buttons" closed="true">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bd-tc-s" >
        <tr>
            <td class="title">部门名称</td>
            <td>质量部</td>
        </tr>
        <tr>
            <td class="title">岗位类型</td>
            <td>质量部</td>
        </tr>
        <tr>
            <td class="title">岗位</td>
            <td>质量管理</td>
        </tr>

    </table>

</div>
<!--easyui弹窗按钮加载-->
<div id="dlg-buttons">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td align="center" height="30">
                <a href="#" class="dailog-bt">保 存</a><a href="#" class="dailog-bt" onclick="javascript:$('#dlg1').dialog('close')">关 闭</a>
            </td>
        </tr>
    </table>
</div>--%>
<%--<form action="<%=request.getContextPath()%>/tPostManage/addPost.do" id="add" method="post">--%>
<div id="dlg2" class="easyui-dialog" title="新增岗位" style="width:500px; height:390px; padding:8px" buttons="#dlg-buttons1" closed="true">
    <%-- <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bd-tc-s" >
         <tr>
             <td class="title"><span>*</span>部门名称</td>
             <td><select name="select1" id="select1" style="width: 200px">

             </select></td>

         </tr>
         <tr>
             <td class="title"><span>*</span>岗位类型</td>
             <td><select name="select2" id="select2" style="width: 200px">

             </select></td>
         </tr>
         <tr>
             <td class="title"><span>*</span>岗位名称</td>
             <td><input type="text" style="width:200px" id="postName"></td>
         </tr>
     </table>--%>
</div>
<!--easyui弹窗按钮加载-->
<div id="dlg-buttons1">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td align="center" height="30">
                <a  href="#" class="dailog-bt" onclick="saveOrUpdate()">确 定</a><a href="#" class="dailog-bt" onclick="javascript:$('#dlg2').dialog('close')">关 闭</a>
            </td>
        </tr>
    </table>
</div>
<%--</form>--%>


</body>
</html>
