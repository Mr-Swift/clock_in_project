<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/20
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>无标题文档</title>
    <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">


    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/zTree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/zTree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/zTree/js/jquery.ztree.excheck.js"></script>


    <script type="text/javascript">
        $(document).ready(function(){
            $("#search").click(function(){
                $(".search-hide").slideToggle(400)
            })
        });
    </script>
    <script type="text/javascript">
       //设置一个名字保存修改前的旧名
        var oldName
       //删除操作
        function
        del(delName){
            if
            (!confirm(
                "是否删除该角色？")
            ){
                window.event.returnValue = false;
            }else{
                $.ajax({
                    async: false,
                    type:"post",
                    url:"<%=request.getContextPath()%>/tRoleFunction/delete.do?r="+new Date().getTime(),
                    dataType: "json",
                    data:{"delName": delName},
                    success: function (data) {
                        alert("删除成功");
                        window.location.reload();
                    }
                })
            }
        }
        //上一页操作
        function topPage(page) {
            if(Number(page)==0){
                alert("已经是第一页了。");
                return false;
            }else {
                var roleName='${roleName}';
                roleName=encodeURI(encodeURI(roleName));
                window.location.href = "<%=request.getContextPath()%>/tRole/toRoleManage.do?page="+page+"&roleName="+roleName;
            }
        }
        //下一页操作
        function  nextPage(page) {
            var total = '${totalPage}'
            if(Number(page) > Number(total)){
                alert("已经是最后一页了。");
                return false;
            }else{
                // var roleName = $("#roleName").val().trim();
                // roleName=encodeURI(encodeURI(roleName));
                var roleName='${roleName}';
                roleName=encodeURI(encodeURI(roleName));
                window.location.href = "<%=request.getContextPath()%>/tRole/toRoleManage.do?page="+page+"&roleName="+roleName;
            }
        }
        //跳转到首页或尾页操作
        function  goToPage2(s) {
            var roleName='${roleName}';
            roleName=encodeURI(encodeURI(roleName));
            window.location.href = "<%=request.getContextPath()%>/tRole/toRoleManage.do?page="+s+"&roleName="+roleName;
        }
        //输入页码进行跳转
        function goToPage() {
            var page = $("#pageNum").val();
            var totalPage = '${totalPage}';
            var roleName='${roleName}';
            if (page != null && page != ''){
                if(!/^[1-9]+[0-9]*]*$/.test(page)){
                    alert("请输入正确页码")
                    return false;
                }
                if(Number(page) > Number(totalPage)||Number(page)==0){
                    alert("请输入正确页码")
                    return false;
                }
                roleName=encodeURI(encodeURI(roleName));
                window.location.href = "<%=request.getContextPath()%>/tRole/toRoleManage.do?page="+page+"&roleName="+roleName;
            }else{
                alert("请输入要跳转的页数！");
            }
        }
        //定位到非法字符
        function setTxt1CursorPosition(i){
            var oTxt1 = document.getElementById("roleName");
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
        //定位到非法字符
        function setTxt1CursorPosition2(i){
            var oTxt1 = document.getElementById("addRoleName");
            var cursurPosition=-1;
            if(oTxt1.createTextRange){
                range.move("character",i);
                range.select();
            }
            else{
                oTxt1.setSelectionRange("character",i);
                oTxt1.focus();
                oTxt1.selectionStart=i;
            }
        }
        //定位到非法字符
        function setTxt1CursorPosition3(i){
            var oTxt1 = document.getElementById("updateName");
            var cursurPosition=-1;
            if(oTxt1.createTextRange){
                range.move("character",i);
                range.select();
            }
            else{
                oTxt1.setSelectionRange("character",i);
                oTxt1.focus();
                oTxt1.selectionStart=i;
            }
        }
        //查询操作
        function toSelect() {
            var roleName = $("#roleName").val().trim();
            if(roleName == null || roleName==""){
                alert("查询字段不能为空")
                roleName=encodeURI(encodeURI(roleName));
                window.location.href = "<%=request.getContextPath()%>/tRole/toRoleManage.do?roleName="+roleName;
                return false;
            }
            if(roleName.length>20){
                alert("字段超长")
                return false;
            }
            if (!/^([\u4e00-\u9fa5A-Za-z0-9 ])*$/.test(roleName)) {
                alert("输入内容包含非法字符,请重新输入")
                for (var i = 0; i < roleName.length; i++) {
                    if(!/^([\u4e00-\u9fa5A-Za-z0-9])*$/.test(roleName[i])){
                        setTxt1CursorPosition(i+1)
                    }
                }
                return false;
            }
            //var addRoleName = $("#addRoleName").val().trim();
            $.ajax({
                async: false,
                type:"post",
                url:"<%=request.getContextPath()%>/tRoleFunction/selectOnly.do?r="+new Date().getTime(),
                dataType: "json",
                data:{"roleName": roleName},
                // beforeSend: function(){
                //     alert("正在处理，请稍等!")
                // },
                success: function (data) {
                    if(data ==""){
                        alert("查询内容不存在")
                        return false;
                    }else {
                        roleName=encodeURI(encodeURI(roleName));
                        window.location.href = "<%=request.getContextPath()%>/tRole/toRoleManage.do?roleName="+roleName;
                    }
                }
            })

        }
        //点击修改按钮将后台数据传到页面
        function update(point1) {

            $('#dlg1').window('open')
            oldName=point1
            $.ajax({
                async:false,
                type:"post",
                url:"<%=request.getContextPath()%>/tRoleFunction/roleUpdate.do?r="+new Date().getTime(),
                data:{"point1" :point1},
                success: function (data) {
                    var m = data.split(',');
                    var reg = /^[\'\"]+|[\'\"]+$/g;
                    var str = m[0];
                    str=str.replace(reg,"");
                    $("#updateName").val(str)
                    document.getElementById("updateRemark").value=m[1]
                    var setting = {
                        // view:{
                        //     selectedMulti:true,
                        // },
                        data: {
                            simpleData: {
                                enable: true,
                                idKey: "id",
                                pIdKey: "pid",
                                //   rootPId: 0
                            },
                        },
                        check:{
                            enable: true,
                            nochechInherit:true
                        }
                    };

                    data1=${jsonTree};
                    var zNodes = data1;
                    $(function(){
                        zTreeObj2=$.fn.zTree.init($("#treeDemo2"),setting,zNodes);
                        var treeObj = $.fn.zTree.getZTreeObj("treeDemo2");
                        var s= data.split(',');
                        var reg2 = /^[\'\"]+|[\'\"]+$/g;
                        var str2 = m[s.length-1];
                        str2=str2.replace(reg,"");
                        treeObj.expandAll(true)
                        for(var i = 2; i < s.length; i++) {
                            //     //zTree.checkNode(node, true, true);
                            if(i==s.length-1){
                                var n = treeObj.getNodeByParam("id", str2)
                            }else{
                                var n = treeObj.getNodeByParam("id",s[i])
                            }

                            //treeObj.selectNode(n)
                            treeObj.checkNode(n,true,true);
                        }
                    })


                    // var treeObj = $.fn.zTree.getZTreeObj("treeDemo2");
                    // treeObj.expandAll(true)
                    // treeObj.selectNode(1)
                    // for(var i = 2; i < m.length; i++) {
                    //     //     //zTree.checkNode(node, true, true);
                    //
                    //     var n = treeObj.getNodesByParam("id",m[i])
                    //     //treeObj.selectNode(n)
                    //     treeObj.checkNode(n,true,true);
                    // }
                    // alert("保存成功！");
                }
            })
            <%--point1=encodeURI(encodeURI(point1));--%>
            <%--window.location.href = "<%=request.getContextPath()%>/tRoleFunction/roleUpdate.do?point1="+point1;--%>

        }
        //判断添加的名字是否唯一，在执行添加操作
        function isOnly() {
            var addRoleName = $("#addRoleName").val().trim();
            $.ajax({
                async: false,
                type:"post",
                url:"<%=request.getContextPath()%>/tRoleFunction/isOnly.do?r="+new Date().getTime(),
                dataType: "json",
                data:{"addRoleName": addRoleName},
                success: function (data) {
                    var addRoleName = $("#addRoleName").val().trim();
                    var addRemark = $("#addRemark").val();
                    var nodes = zTreeObj.getCheckedNodes(true);
                    var ids = [];
                    for (var i = 0; i < nodes.length; i++){
                        ids.push(nodes[i].id);
                    }
                    //console.log(ids)
                    // var treeObj=$.fn.zTree.getZTreeObj("tree");
                    // var nodes = treeObj.getCheckedNodes(true);
                    if(addRoleName.length>20){
                        alert("角色名字段超长")
                        return false;
                    }
                    if(addRemark.length>100){
                        alert("角色说明字段超长")
                        return false;
                    }
                    if ("" == addRoleName || null == addRoleName) {
                        alert("角色名称不能为空！");
                        return false;
                    }

                    if ("" == addRemark || null == addRemark) {
                        alert("角色说明不能为空！");
                        return false;
                    }
                    if(0 == nodes.length){
                        alert("请选择权限");
                        return false;
                    }
                    if (!/^([\u4e00-\u9fa5A-Za-z0-9])*$/.test(addRoleName)) {
                        alert("输入内容包含非法字符,请重新输入")
                        for (var i = 0; i < addRoleName.length; i++) {
                            if(!/^([\u4e00-\u9fa5A-Za-z0-9])*$/.test(addRoleName[i])){
                                setTxt1CursorPosition2(i+1)
                            }
                        }
                        return false;
                    }
                    //var name=isOnly(addRoleName);
                    if(data == addRoleName){
                        alert("角色名已存在");
                        return false;
                    }
                    updateOrAddRole(ids);
                }
            })
        }
        //判断修改的名字是否重复在执行修改
        function isOnly2() {
            var updateRoleName = $("#updateName").val().trim();
            var s = oldName
            $.ajax({
                async: false,
                type:"post",
                url:"<%=request.getContextPath()%>/tRoleFunction/isOnly2.do?r="+new Date().getTime(),
                dataType: "json",
                data:{"updateRoleName": updateRoleName,"oldName": s},
                success: function (data) {
                    var updateName = $("#updateName").val().trim();
                    var updateRemark = $("#updateRemark").val();
                    var nodes = zTreeObj2.getCheckedNodes(true);
                    var ids = [];
                    for (var i = 0; i < nodes.length; i++){
                        ids.push(nodes[i].id);
                    }
                    console.log(ids)
                    // var treeObj=$.fn.zTree.getZTreeObj("tree");
                    // var nodes = treeObj.getCheckedNodes(true);
                    if(updateName.length>20){
                        alert("角色名字段超长")
                        return false;
                    }
                    if(updateRemark.length>100){
                        alert("角色说明字段超长")
                        return false;
                    }
                    if ("" == updateName || null == updateRemark) {
                        alert("角色名称不能为空！");
                        return false;
                    }

                    if ("" == updateRemark || null == updateRemark) {
                        alert("角色说明不能为空！");
                        return false;
                    }
                    if(0 == nodes.length){
                        alert("请选择权限");
                        return false;
                    }
                    if (!/^([\u4e00-\u9fa5A-Za-z0-9])*$/.test(updateName)) {
                        alert("输入内容包含非法字符,请重新输入")
                        for (var i = 0; i < updateName.length; i++) {
                            if(!/^([\u4e00-\u9fa5A-Za-z0-9])*$/.test(updateName[i])){
                                setTxt1CursorPosition3(i+1)
                            }
                        }
                        return false;
                    }
                    if(data == updateName){
                        alert("角色名已存在");
                        return false;
                    }
                    $.ajax({
                        async: false,
                        type:"post",
                        url:"<%=request.getContextPath()%>/tRoleFunction/roleUpdate2.do?r="+new Date().getTime(),
                        dataType: "json",
                        data:{"menuIds":  ids.toLocaleString(), "roleName": updateName, "remark": updateRemark, "oldName": oldName},
                        success: function (data) {
                            alert("修改成功");
                        }
                    })
                    //alert("修改成功！");
                    window.location.reload();
                }
            })
        }

        //添加的方法
        function updateOrAddRole(menuIds) {
            var addRoleName = $("#addRoleName").val().trim();
            var addRemark = $("#addRemark").val();
            var params = {
                menuIds: menuIds,
                roleName: addRoleName,
                remark: addRemark
            };
            $.ajax({
                async: false,
                type:"post",
                url:"<%=request.getContextPath()%>/tRoleFunction/roleSaveOrUpdate.do?r="+new Date().getTime(),
                dataType: "json",
                data:{"menuIds":  menuIds.toLocaleString(), "roleName": addRoleName, "remark": addRemark, "oldName":oldName},
                success: function (data) {
                    alert(data);
                    //window.location.reload();
                }
            })

            //var url = "<%=request.getContextPath()%>/tRoleFunction/roleSaveOrUpdate";
            //alert("保存成功！");
            window.location.reload();
        }
        $(function () {
            $("#dlg2").dialog({
                onClose: function(){
                    $("#dlg2").form('clear');
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    treeObj.checkAllNodes(false);
                    //window.location.reload();
                }
            });
        })

    </script>

    <script type="text/javascript">
        //生成菜单树
        var setting = {
            // view:{
            //     selectedMulti:true,
            // },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    //   rootPId: 0
                },
            },
            check:{
                enable: true,
                nochechInherit:true
            }
        };

        data=${jsonTree};
        var zNodes = data;

        $(function(){

            zTreeObj=$.fn.zTree.init($("#treeDemo"),setting,zNodes);
            var tree1 = $.fn.zTree.getZTreeObj("treeDemo");
            var  nodes1 = tree1.getNodes();
            tree1.expandNode(nodes1[0],  true ,  false ,  true );
        })

    </script>
</head>
<body>
<!--留白div，不能用margin padding控制!-->
<!--数据展示区域外框，用于周边留空padding不可删除-->
<div class="pd-zy20">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
        <tr>
            <td width="46%">当前位置：平台管理 -> 角色管理</td>
        </tr>
    </table>
    <!--查询 按钮容器!-->
    <div class="cx-bt">



        <!--按钮div!-->
        <div class="btn-bt"><li class="search_bt"><a href="#" onclick="$('#dlg2').window('open')">新 增</a></li></div>
        <!--查询div!-->

        <div class="search1">
            <ul>

                <li>角色名称</li>
                <li><input id="roleName" type="text" style="width:180px" name="selectRoleName" value="${roleName}"></li>
                <li class="search_bt" onclick="toSelect()"><a href="#">查 询</a></li>
            </ul>
        </div>
    </div>
    <div style="height:5px"></div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" id="list_table_data" style="margin-top:6px ;font-size: 12px;">
        <thead>
        <th width="220">角色名称</th>
        <th>角色说明</th>
        <th width="180">操作</th>
        </thead>
        <tbody>
        <c:forEach  items="${infoList.data}" var="proInfo" varStatus="status">
            <tr <c:if test="${status.count%2 == 0}">class="ol"</c:if>>
                <td>${proInfo.roleName}</td>
                <td>${proInfo.remark}</td>
                <td class="cz-a"><a href="#" onclick="update('${proInfo.roleName}')"><i class="fa fa-edit pd-r06"></i>编辑</a><a href="#" onclick="return del('${proInfo.roleName}')"><i class="fa fa-close pd-r06"></i>删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="fy" style="font-size: 12px;">
        <tr>
            <td height="40" align="right" valign="bottom">当前第 <span>${infoList.page}</span> 页 共 <span>${totalPage}</span> 页
                <%--<c:forEach var="s"   begin="1" end="${totalPage}"><a  href="#" onclick="goToPage2(${s})">${s}</a>&nbsp;&nbsp;</c:forEach>--%>
                <a style="width: 30px" href="#" onclick="goToPage2(1)">首页</a>&nbsp;|&nbsp;
                <a style="width: 45px" href="#" onclick="topPage(${page-1})">上一页</a>&nbsp;|&nbsp;
                <a style="width: 45px" href="#" onclick="nextPage(${page+1})">下一页</a>&nbsp;|&nbsp;
                <a style="width: 30px" href="#" onclick="goToPage2(${totalPage})">尾页</a>

                跳转到 </td>
            <td width="40" align="center" valign="bottom"><input name="textfield8" type="text" class="inp_w4" id="pageNum" /></td>
            <td width="35" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/frame/images/bt_go.gif" width="26" height="24" onclick="goToPage()"></td>
        </tr>
    </table>
</div>
<!--easyui弹窗，参数buttons根据id调用操作按钮，参数title显示弹窗标题，编辑-->
<div id="dlg1" class="easyui-dialog" title="角色管理" style="width:500px; height:390px; padding:8px " modal="true"  closed="true" buttons="#dlg-buttons" >
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bd-tc-s" >
            <tr>
                <td class="title"><span>*</span>角色名称</td>
                <td><input type="text" style="width:92%"  maxlength="21" id="updateName"></td>
            </tr>
            <tr>
                <td class="title"><span>*</span>角色说明</td>
                <td><textarea style="width:92%; height:70px ; resize:none" maxlength="101" id="updateRemark"></textarea></td>
            </tr>
            <tr>
                <td class="title"><span>*</span>功能模块</td>
                <td width="356px" height="173px"><div id="roleTree2" width="174px" height="113px">
                    <ul id="treeDemo2" class="ztree">
                    </ul>
                </div></td>
            </tr>
        </table>
        <div id="dlg-buttons">
            <table cellpadding="0" cellspacing="0" style="width:100%">
                <tr>
                    <td align="center" height="30">
                        <a href="#" class="dailog-bt"  onclick="isOnly2()">确 定</a><a href="#" class="dailog-bt" onclick="javascript:$('#dlg1').dialog('close')">关 闭</a>
                    </td>
                </tr>
            </table>
        </div>
</div>
<!--easyui弹窗按钮加载，新增-->

<div id="dlg2" class="easyui-dialog" title="新增角色" style="width:500px; height:390px; padding:8px" modal="true" buttons="#dlg-buttons1" closed="true">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bd-tc-s" >
            <tr>
                <td class="title"><span>*</span>角色名称</td>
                <td><input id="addRoleName"  type="text" style="width:92%" maxlength="21" ></td>
            </tr>
            <tr>
                <td  class="title"><span>*</span>角色说明</td>
                <td><textarea id="addRemark" style="width:92%; height:70px; resize:none" maxlength="101" placeholder="请输入内容" ></textarea></td>
            </tr>
            <tr>
                <td id="addRoleFunction" class="title"><span>*</span>功能模块</td>
                <td width="356px" height="173px"><div id="roleTree" width="174px" height="113px">
                    <ul id="treeDemo" class="ztree">
                    </ul>
                </div></td>
            </tr>
        </table>

        <div id="dlg-buttons1">
            <table cellpadding="0" cellspacing="0" style="width:100%">
                <tr>
                    <td align="center" height="30">
                        <a href="#" class="dailog-bt" onclick="isOnly()" id="checked">保 存</a><a href="#" class="dailog-bt" onclick="javascript:$('#dlg2').dialog('close') , $('#dlg2').dialog({
                        onClose: function(){
                        $('#dlg2').form('clear');
                        var treeObj = $.fn.zTree.getZTreeObj('treeDemo');
                        treeObj.checkAllNodes(false);
                        //window.location.reload();
                        }
                        });">关 闭</a>
                    </td>
                </tr>
            </table>
        </div>
</div>

<!--easyui弹窗按钮加载-->

</body>
</html>
