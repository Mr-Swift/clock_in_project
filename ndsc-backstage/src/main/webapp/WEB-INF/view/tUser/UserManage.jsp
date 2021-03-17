<%--
  Created by IntelliJ IDEA.
  User: LiYang
  Date: 2021/1/20
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户管理</title>
    <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.table2excel.js"></script>
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
        });

    </script>
    <script type="text/javascript">
        function del(id) {
            if (!confirm("是否重置该账号密码？")) {
                window.event.returnValue = false;
            } else {
                console.log(id);
                $.ajax({
                    type: "POST",
                    url: "<%=request.getContextPath()%>/tUser/resetPwd.do",
                    data: {"id": id},
                    dataType: "json",
                    async: false,
                    success: function (result) {
                        if (result.code == 0) {
                            alert("密码已重置成功！");
                        } else {
                            alert(result.msg);
                            return false;
                        }
                    },
                    error: function () {
                        alert("系统异常！");
                    }
                });
            }
        }
    </script>
    <script type="text/javascript">
        function goToPage() {
            var page = $("#pageNum").val().trim();
            var deptName = $("#deptName").val().trim();
            var personNo = $("#personNo").val().trim();
            var userName = $("#userName").val().trim();
            var postName = $("#postName").val().trim();
            var userStatus = $("#userStatus option:selected").val();
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
                window.location.href = "<%=request.getContextPath()%>/tUser/getUsers.do?page=" + page +"&deptName=" + deptName +"&personNo=" + personNo +"&userName=" + userName +"&postName=" + postName +"&userStatus=" + userStatus;
            }else{
                alert("请输入要跳转的页数！");
            }
        }

        function toSelect() {
            var deptName = $("#deptName").val().trim();
            $("#deptName").val(deptName);
            var personNo = $("#personNo").val().trim();
            $("#personNo").val(personNo);
            var userName = $("#userName").val().trim();
            $("#userName").val(userName);
            var postName = $("#postName").val().trim();
            $("#postName").val(postName);
            $("#Frm").submit();
        }

        function setTxt1CursorPosition(i){
            var oTxt1 = document.getElementById("deptName");
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

        function setTxt1CursorPosition1(i){
            var oTxt1 = document.getElementById("personNo");
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

        function setTxt1CursorPosition2(i){
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

        function setTxt1CursorPosition3(i){
            var oTxt1 = document.getElementById("postName");
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

    </script>
    <script type="text/javascript">
        function toGrantRole(id) {
            $('#dlg1').dialog({
                title: '授权管理',
                width: 500,
                height: 390,
                closed: false,
                cache: false,
                href: '<%=request.getContextPath()%>/tUser/toGrantRole.do?id='+id,
                modal: true
            });
        }
    </script>
    <script type="text/javascript">
        $(function() {
            var checkAll = $("#checkAll");
            var childC = $(":checkbox[name='childCheck']");
            checkAll.click(function() {
                if (childC.data("checked")) {
                    childC.prop("checked", false);
                    childC.data("checked", false);
                    $("#hi").val(0);
                } else {
                    childC.prop("checked", true);
                    childC.data("checked", true);
                    $("#hi").val(1);
                }
            });
        });

        function isSelected(checkedId) {
            if(checkedId.checked == true){
                $($(checkedId).closest("tr")).removeClass('noExl');
            }else {
                $($(checkedId).closest("tr")).addClass('noExl');
            }
        }
    </script>
    <script type="text/javascript">
        function toExport(){
            if($("input:checkbox:checked").length == 0){
                var returned = confirm("您没有选择任何数据，确认导出吗？");
                if(!returned){
                    window.event.returnValue = false;
                }else{
                    $(".ss_table").table2excel({
                        exclude: ".noExl",
                        filename: "用户管理-" + new Date().getFullYear()+"."+(new Date().getMonth()+1)+"."+new Date().getDay()+".xls",
                        bootstrap: false
                    });
                }
            }else{
                if($("#checkAll").click() && $("#hi").val() == 0){
                    $("#exceltableHidden").table2excel({
                        exclude: ".noExl",
                        filename: "用户管理-" + new Date().getFullYear()+"."+(new Date().getMonth()+1)+"."+new Date().getDay()+".xls",
                        bootstrap: false
                    });
                }else{
                    $(".ss_table").table2excel({
                        exclude: ".noExl",
                        filename: "用户管理-" + new Date().getFullYear()+"."+(new Date().getMonth()+1)+"."+new Date().getDay()+".xls",
                        bootstrap: false
                    });
                }
            }

        }
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".ss_table tr:even").css("background-color","#edf6fe");
        });
    </script>
    <script type="text/javascript">
        function checkDeptName(){
            var deptName = $("#deptName").val().trim();
            if(deptName.length>20){
                alert("字段超长")
                return false;
            }

            if (!/^([\u4e00-\u9fa5\s])*$/.test(deptName)) {
                alert("输入内容包含非法字符,请重新输入")
                for (var i = 0; i < deptName.length; i++) {
                    if(!/^([\u4e00-\u9fa5])*$/.test(deptName[i])){
                        setTxt1CursorPosition(i+1);
                    }
                }
                return false;
            }
        }
        function checkPersonNo(){
            var personNo = $("#personNo").val().trim();
            if(personNo.length>20){
                alert("字段超长")
                return false;
            }

            if (!/^([A-Za-z0-9*_\s])*$/.test(personNo)) {
                alert("输入内容包含非法字符,请重新输入")
                for (var i = 0; i < personNo.length; i++) {
                    if(!/^([A-Za-z0-9*_])*$/.test(personNo[i])){
                        setTxt1CursorPosition1(i+1);
                    }
                }
                return false;
            }
        }
        function checkUserName(){
            var userName = $("#userName").val().trim();
            if(userName.length>20){
                alert("字段超长")
                return false;
            }

            if (!/^([\u4e00-\u9fa5A-Za-z*_\s])*$/.test(userName)) {
                alert("输入内容包含非法字符,请重新输入")
                for (var i = 0; i < userName.length; i++) {
                    if(!/^([\u4e00-\u9fa5*_])*$/.test(userName[i])){
                        setTxt1CursorPosition2(i+1);
                    }
                }
                return false;
            }
        }
        function checkPostName(){
            var postName = $("#postName").val().trim();
            if(postName.length>20){
                alert("字段超长")
                return false;
            }

            if (!/^([\u4e00-\u9fa5A-Za-z\s])*$/.test(postName)) {
                alert("输入内容包含非法字符,请重新输入")
                for (var i = 0; i < postName.length; i++) {
                    if(!/^([\u4e00-\u9fa5A-Za-z0-9,.?!;，。？！、；])*$/.test(postName[i])){
                        setTxt1CursorPosition3(i+1);
                    }
                }
                return false;
            }
        }
    </script>
    <script type="text/javascript">
        function toPreviousPage(page){
            if (Number(page) == 0) {
                alert("已经是第一页。");
                return false;
            }else{
                location.href="<%=request.getContextPath()%>/tUser/getUsers.do?page=${page-1}&deptName=${result.params.get('deptName')}&personNo=${result.params.get('personNo')}&userName=${result.params.get('userName')}&postName=${result.params.get('postName')}&userStatus=${result.params.get('userStatus')}"
            }
        }

        function toNextPage(page){
            var total = '${totalPage}';
            if (Number(page) > Number(total)) {
                alert("已经是最后一页。");
                return false;
            }else{
            location.href="<%=request.getContextPath()%>/tUser/getUsers.do?page=${page+1}&deptName=${result.params.get('deptName')}&personNo=${result.params.get('personNo')}&userName=${result.params.get('userName')}&postName=${result.params.get('postName')}&userStatus=${result.params.get('userStatus')}"
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
<!--数据展示区域外框，用于周边留空padding不可删除-->
<div class="pd-zy20">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
        <tr>
            <td width="46%">当前位置：平台管理 ->用户管理</td>
        </tr>
    </table>
    <div class="cx-bt">
        <!--功能按钮-->
        <div class="btn-bt">
            <ul>
                <li class="search_bt"><a href="" onclick="toExport()">导出</a></li>
            </ul>
        </div><!--end-->
        <!--搜索-->
        <form action="<%=request.getContextPath()%>/tUser/getUsers.do" id="Frm" method="post">
            <div class="search1">
                <ul>
                    <li>部门：</li>
                    <li><input name="deptName" id="deptName" type="text" style="width:120px;" value="${result.params.get('deptName')}"
                               onblur="checkDeptName()"></li>
                    <li>工号：</li>
                    <li><input name="personNo" id="personNo" type="text" style="width:120px;" value="${result.params.get('personNo')}"
                               onblur="checkPersonNo()"></li>
                    <li>姓名：</li>
                    <li><input name="userName" id="userName" type="text" style="width:120px;" value="${result.params.get('userName')}"
                               onblur="checkUserName()"></li>
                    <li>岗位：</li>
                    <li><input name="postName" id="postName" type="text" style="width:120px;" value="${result.params.get('postName')}"
                               onblur="checkPostName()"></li>
                    <li>账号状态：</li>
                    <li>
                        <select name="userStatus" style="width: 80px" id="userStatus">
                            <option value="0"<c:if test="${0 eq result.params.get('userStatus')}"> selected="selected"</c:if>>全部</option>
                            <option value="1"<c:if test="${1 eq result.params.get('userStatus')}"> selected="selected"</c:if>>启用</option>
                            <option value="2"<c:if test="${2 eq result.params.get('userStatus')}"> selected="selected"</c:if>>禁用</option>
                        </select>
                    </li>
                    <li class="search_bt"><a href="javaScript:;" onclick="toSelect()">查 询</a></li>
                </ul>
            </div>
        </form>
    </div>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:8px; table-layout: fixed">
        <tr>
            <th width="30" class="noExl">
                <span id="checkAll" style="cursor:pointer;">全选</span>
                <input type="hidden" id="hi"/>
            </th>
            <th width="40">序号</th>
            <th width="120">工号</th>
            <th width="120">姓名</th>
            <th width="120">部门</th>
            <th width="120">岗位</th>
            <th width="120">联系方式</th>
            <th width="120">账号状态</th>
            <th width="120">角色名称</th>
            <th width="220" class="noExl">操作</th>
        </tr>
        <c:forEach items="${result.data}" var="user" varStatus="status">
            <tr class="noExl">
                <td class="noExl">
                    <input id="checked-${status.count}" name="childCheck" type="checkbox" onclick="isSelected(this)"/>
                </td>
                <td>${status.count+(result.page-1)*result.limit}</td>
                <td>${user.personNo}</td>
                <td>${user.userName}</td>
                <td>${user.deptName}</td>
                <td title="${user.postName}">${user.postName}</td>
                <td>${user.telephone}</td>
                <td>
                    <c:if test="${user.userStatus eq 1}">启用</c:if>
                    <c:if test="${user.userStatus eq 2}">禁用</c:if>
                </td>
                <td title="${user.roleName}">${user.roleName}</td>
                <td class="cz-a noExl">
                    <a href="#" onclick="return del('${user.id}')">
                        <i class="fa fa-warning pd-r06"></i>重置密码
                    </a>
                    <a href="#" onclick="toGrantRole('${user.id}')">
                        <i class="fa fa-edit pd-r06"></i>授权
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="fy">
        <tr>
            <td height="40" align="right" valign="bottom">

            当前第 <span>${page}</span> 页
                共 <span>${totalPage}</span> 页
                <a style="width: 30px" href="<%=request.getContextPath()%>/tUser/getUsers.do?page=1&deptName=${result.params.get('deptName')}&personNo=${result.params.get('personNo')}&userName=${result.params.get('userName')}&postName=${result.params.get('postName')}&userStatus=${result.params.get('userStatus')}">首页</a>&nbsp;|&nbsp;
                <a style="width: 45px" href="javaScript:;" onclick="toPreviousPage('${page-1}')">上一页</a>&nbsp;|&nbsp;
                <a style="width: 45px" href="javaScript:;" onclick="toNextPage('${page+1}')">下一页</a>&nbsp;|&nbsp;
                <a style="width: 30px" href="<%=request.getContextPath()%>/tUser/getUsers.do?page=${totalPage}&deptName=${result.params.get('deptName')}&personNo=${result.params.get('personNo')}&userName=${result.params.get('userName')}&postName=${result.params.get('postName')}&userStatus=${result.params.get('userStatus')}">尾页</a> 跳转到 </td>
            <td width="40" align="center" valign="bottom">
                <input name="textfield8" type="text" class="inp_w4" id="pageNum"/>
            </td>
            <td width="35" align="left" valign="bottom">
                <img src="<%=request.getContextPath()%>/frame/images/bt_go.gif" width="26" height="24" onclick="goToPage()"/>
            </td>
        </tr>
    </table>
</div>

<table id="exceltableHidden" hidden>
    <th>序号</th>
    <th>工号</th>
    <th>姓名</th>
    <th>部门</th>
    <th>岗位</th>
    <th>联系方式</th>
    <th>账号状态</th>
    <th>角色名称</th>
        <c:forEach items="${noPage.data}" var="u" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${u.personNo}</td>
                <td>${u.userName}</td>
                <td>${u.deptName}</td>
                <td>${u.postName}</td>
                <td>${u.telephone}</td>
                <td>
                    <c:if test="${u.userStatus eq 1}">在职</c:if>
                    <c:if test="${u.userStatus eq 2}">离职</c:if>
                </td>
                <td>${u.roleName}</td>
            </tr>
        </c:forEach>
</table>

<div id="dlg1" class="easyui-dialog" title="授权管理" style="width:500px; height:390px; padding:8px" buttons="#dlg-buttons" closed="true">
</div>
</body>
</html>