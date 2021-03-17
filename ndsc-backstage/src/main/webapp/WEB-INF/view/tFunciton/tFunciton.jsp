<%@ page import="com.njusc.npm.metadata.entity.TFuncitonEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" %><!--language="java"-->
<html>
<head>
    <title>Title</title>
    <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/easyui.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.ztree.core-3.5.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/zTree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/zTree/js/jquery.ztree.excheck.js"></script>
    <link href="<%=request.getContextPath()%>/frame/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">

    <script type="text/javascript">
        var domainurl='<%=request.getContextPath()%>';
        var id ="";
        var add = false;
        var update = false;

        $(document).ready(function(){
            $("#search").click(function(){
                $(".search-hide").slideToggle(400)
            });
        });

        function insert() {
            if (document.getElementById("funName").value.length<=20) {
                $.ajax({
                    url:domainurl+"/tFunciton/add.do",
                    data:{"funName":$("#funName").val().trim(),"funUrl":$("#funUrl").val().trim(),"funParentName":$("#funParentName").val().trim(),"orderNumber":$("#orderNumber").val().trim(),"parentID":id},
                    beforeSend:function() {
                        document.getElementById("bt1").disabled = true;
                    },
                    complete:function() {
                        document.getElementById("bt1").disabled = false;
                    },
                    success:function(data) {
                        alert(data);
                        if ("\"添加失败，功能模块名称不可为空\"" === data||"\"添加失败，模块名包含非法字符，请重新输入\"" === data||"\"添加失败，该父模块下已有该模块名\"" === data) {
                            document.getElementById("funName").focus();
                            document.getElementById("funNameLength").innerText = data;
                        } else if ("\"添加失败，排序不可为空\"" === data||"\"添加失败，排序内容包括非法字符，请重新输入，排序应为整数\"" === data||"\"该排序已存在，请重新输入\"" === data) {
                            document.getElementById("orderNumber").focus();
                        } else if ("\"添加失败，父模块不存在\"" === data) {
                            document.getElementById("funParentName").focus();
                        } else if ("\"添加成功\"" === data) {
                            $('#dlg2').dialog('close');
                            add = false;
                            document.getElementById("funName").value = "";
                            document.getElementById("funUrl").value = "";
                            document.getElementById("funParentName").value = "";
                            document.getElementById("orderNumber").value = "";
                            window.location.reload();
                        }
                    },
                    error:function() {
                        alert("系统异常");
                    }
                });
            } else {
                alert("名称过长，请重新输入");
            }
        }
        function addTab() {
            document.getElementById("funName").focus();
        }
        function focusJump() {
            $('#dlg2').window('open');
            add = false;
            update = false;
            document.getElementById("funParentName").value = document.getElementById("functionName").value;
        }
        function addLength() {
            if (add) {
                $.ajax({
                    url:domainurl+"/tFunciton/onblur.do",
                    data:{"funName":$("#funName").val().trim(),"method":"insert","id":id},
                    success:function(data) {
                        if ("\"添加失败，功能模块名称不可为空\"" === data||"\"添加失败，模块名包含非法字符，请重新输入\"" === data||"\"添加失败，该父模块下已有该模块名\"" === data||"\"名称过长，限定20位\"" === data) {
                            document.getElementById("funNameLength").innerText = data;
                        } else if("\"名称可用\"" === data){
                            document.getElementById("funNameLength").innerText = data;
                        }
                    },
                    error:function() {
                        alert("系统异常");
                    }
                });
            }
        }
        function addPoint() {
            add = true;
        }

        function select(id1,funName,funUrl,parentId,orderNumber) {
            id = id1;
            var child = document.getElementById(id);
            var img1 = document.getElementById(funName);
            if(child.style.display === "none"){
                child.style.display = "block";
                img1.src = domainurl+"/frame/images/openf.png";
            } else {
                child.style.display = "none";
                img1.src = domainurl+"/frame/images/closef.png";
            }
            $.ajax({
                url:domainurl+"/tFunciton/select.do",
                data:{"id":parentId},
                success:function(data) {
                    document.getElementById("functionParent").value = data;
                    document.getElementById("functionName").focus();
                },
                error:function() {
                    alert("系统异常");
                }
            });
            document.getElementById("functionName").value = funName;
            document.getElementById("funcitonURL").value = funUrl;
            document.getElementById("functionOrder").value = orderNumber;
        }
        function selectNoChild(id1,funName,funUrl,parentId,orderNumber) {
            id = id1;
            $.ajax({
                url:domainurl+"/tFunciton/select.do",
                data:{"id":parentId},
                success:function(data) {
                    document.getElementById("functionParent").value = data;
                    document.getElementById("functionName").focus();
                },
                error:function() {
                    alert("系统异常");
                }
            });
            document.getElementById("functionName").value = funName;
            document.getElementById("funcitonURL").value = funUrl;
            document.getElementById("functionOrder").value = orderNumber;
        }
        function selectchild(id1,funName,funUrl,parentId,orderNumber) {
            id = id1;
            var child = document.getElementById(id);
            if (child.style.display === "none") {
                child.style.display = "block";
            } else {
                child.style.display = "none";
            }
            $.ajax({
                url:domainurl+"/tFunciton/select.do",
                data:{"id":parentId},
                success:function(data) {
                    document.getElementById("functionParent").value = data;
                    document.getElementById("functionName").focus();
                },
                error:function() {
                    alert("系统异常");
                }
            });
            document.getElementById("functionName").value = funName;
            document.getElementById("funcitonURL").value = funUrl;
            document.getElementById("functionOrder").value = orderNumber;
        }
        function selectlastchild(id1,funName,funUrl,parentId,orderNumber) {
            id = id1;
            $.ajax({
                url:domainurl+"/tFunciton/select.do",
                data:{"id":parentId},
                success:function(data) {
                    document.getElementById("functionParent").value = data;
                    document.getElementById("functionName").focus();
                },
                error:function() {
                    alert("系统异常");
                }
            });
            document.getElementById("functionName").value = funName;
            document.getElementById("funcitonURL").value = funUrl;
            document.getElementById("functionOrder").value = orderNumber;
        }

        function deletefun() {
            var se=confirm("是否确认删除本菜单及所有下级菜单");
            if (se === true) {
                $.ajax({
                    url:domainurl+"/tFunciton/delete.do",
                    data:{"id":id,"funName":$("#functionName").val().trim(),"funcitonURL":$("#funcitonURL").val().trim(),"functionParent":$("#functionParent").val().trim(),"functionOrder":$("#functionOrder").val().trim()},
                    beforeSend:function() {
                        document.getElementById("bt3").disabled = true;
                    },
                    complete:function() {
                        document.getElementById("bt3").disabled = false;
                    },
                    success:function(data) {
                        if (data === "\"删除失败，功能模块名称不可为空\""||data === "\"删除失败，模块名包含非法字符，请重新输入\""||data === "\"删除失败，该模块不存在\"") {
                            document.getElementById("functionName").focus();
                            document.getElementById("functionNameLength").innerText = data;
                        } else if (data === "\"删除失败，排序不可为空\""||data === "\"删除失败，排序内容包括非法字符，请重新输入，排序应为整数\"") {
                            document.getElementById("functionOrder").focus();
                        } else if(data === "\"删除成功\"") {
                            alert(data);
                            window.location.reload();
                        }
                    },
                    error:function() {
                        alert("系统异常");
                    }
                });
            }
            else {}
        }
        function updatefun() {
            $.ajax({
                url:domainurl+"/tFunciton/update.do",
                data:{"id":id,"funName":$("#functionName").val().trim(),"funcitonURL":$("#funcitonURL").val().trim(),"functionParent":$("#functionParent").val().trim(),"functionOrder":$("#functionOrder").val().trim()},
                beforeSend:function() {
                    document.getElementById("bt2").disabled = true;
                },
                complete:function() {
                    document.getElementById("bt2").disabled = false;
                },
                success:function(data) {
                    alert(data);
                    if (data === "\"修改失败，功能模块名称不可为空\""||data === "\"修改失败，模块名包含非法字符，请重新输入\""||data === "\"修改失败，该模块不存在\"") {
                        document.getElementById("functionName").focus();
                        document.getElementById("functionNameLength").innerText = data;
                    } else if (data === "\"修改失败，排序不可为空\""||data === "\"修改失败，排序内容包括非法字符，请重新输入，排序应为整数\""||"\"该排序已存在，请重新输入\"" === data) {
                        document.getElementById("functionOrder").focus();
                    } else if (data === "\"已修改\"") {
                        window.location.reload();
                    }
                },
                error:function() {
                    alert("系统异常");
                }
            });
        }
        function updateLength() {
            if (update) {
                $.ajax({
                    url:domainurl+"/tFunciton/onblur.do",
                    data:{"funName":$("#functionName").val().trim(),"method":"a","id":id},
                    success:function(data) {
                        if (data === "\"功能模块名称不可为空\""||data === "\"模块名包含非法字符，请重新输入\""||data === "\"该父模块下已有该模块名\""||data === "\"名称过长，限定20位\"") {
                            document.getElementById("functionNameLength").innerText = data;
                        } else if(data === "\"名称可用\"") {
                            document.getElementById("functionNameLength").innerText = data;
                        }
                        update = false;
                    },
                    error:function() {
                        alert("系统异常");
                    }
                });
            }
        }
        function updatePoint() {
            update = true;
        }

        function tab() {
            document.getElementById("functionName").focus();
        }

//        var zTreeObj;
//        var setting = {
//            async: {
//                enable:true,
//                url:domainurl+"/tFunciton/ztree.do",
//                autoParam:["id","name"]
//            },
//            callback :{
//                onClick: zTreeOnClick
//            }
//        };
//        function zTreeOnClick(event, treeId, treeNode) {
//            var index = treeNode.id;
//            alert(treeNode.tId + ", " + treeNode.name);
//            alert(index);
//            selectf(index);
//        }
//        var zNodes = [];
//        $(document).ready(function(){
//            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting,zNodes);
//        });
//        function selectf(id2) {
//            $.ajax({
//                url:domainurl+"/tFunciton/selectf.do",
//                data:{"id":id2},
//                success:function() {
//                },
//                error:function() {
//                    alert("系统异常");
//                }
//            });
//        }

    </script>

    <style type="text/css">
        .co{
            color: #ff0800;
        }
        .co:hover{
            border-bottom: 1px #ff0800 solid;
            cursor:pointer;
        }
        .im{
            cursor:pointer;
        }
    </style>
</head>
<body>
<!--留白div，不能用margin padding控制!-->
<!--数据展示区域外框，用于周边留空padding不可删除-->

<div class="pd-zy20">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
        <tr>
            <td width="46%" height="50px">当前位置：平台管理 -> 菜单管理</td>
        </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
        <tr>
            <td width="200"  style="vertical-align:top;"><strong>菜单列表</strong>
                <ul>
                    <c:forEach items="${newList}" var="all">
                        <li>
                            &nbsp;&nbsp;
                            <c:if test="${fn:length(all.childs) == 0}">
                                &nbsp;<img name="${all.funName}" src="<%=request.getContextPath()%>/frame/images/zi.PNG" class="im" onclick="selectNoChild('${all.id}','${all.funName}','${all.funUrl}','${all.parentId}','${all.orderNumber}')"/>
                                <a onclick="selectNoChild('${all.id}','${all.funName}','${all.funUrl}','${all.parentId}','${all.orderNumber}')" class="co">
                                    ${all.funName}
                                </a>
                            </c:if>
                            <c:if test="${fn:length(all.childs) > 0}">
                                <img id="${all.funName}" class="im" src="<%=request.getContextPath()%>/frame/images/closef.png" onclick="select('${all.id}','${all.funName}','${all.funUrl}','${all.parentId}','${all.orderNumber}')">
                                <img class="im" name="${all.funName}" src="<%=request.getContextPath()%>/frame/images/fu.png" onclick="select('${all.id}','${all.funName}','${all.funUrl}','${all.parentId}','${all.orderNumber}')">
                                <a onclick="select('${all.id}','${all.funName}','${all.funUrl}','${all.parentId}','${all.orderNumber}')" class="co">
                                        ${all.funName}
                                </a>
                                <dl  style="display: none" id="${all.id}">
                                    <c:forEach var="childs" items="${all.childs}">
                                        <dd>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <c:if test="${fn:length(childs.childs) > 0}">
                                                <img id="${childs.funName}" class="im" src="<%=request.getContextPath()%>/frame/images/closef.png" onclick="select('${childs.id}','${childs.funName}','${childs.funUrl}','${childs.parentId}','${childs.orderNumber}')">
                                                <img class="im" name="${childs.funName}" src="<%=request.getContextPath()%>/frame/images/fu.png" onclick="select('${childs.id}','${childs.funName}','${childs.funUrl}','${childs.parentId}','${childs.orderNumber}')">
                                                <a onclick="select('${childs.id}','${childs.funName}','${childs.funUrl}','${childs.parentId}','${childs.orderNumber}')" class="co">
                                                        ${childs.funName}
                                                </a>
                                                <ul style="display: none" id="${childs.id}"><!---->
                                                    <c:if test="${fn:length(childs.childs) > 0}">
                                                        <c:forEach var="child" items="${childs.childs}">
                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <img src="<%=request.getContextPath()%>/frame/images/zi.PNG" class="im" onclick="selectchild('${child.id}','${child.funName}','${child.funUrl}','${child.parentId}','${child.orderNumber}')">
                                                            <a onclick="selectlastchild('${child.id}','${child.funName}','${child.funUrl}','${child.parentId}','${child.orderNumber}')" class="co">
                                                                    ${child.funName}
                                                            </a>
                                                        </c:forEach>
                                                    </c:if>
                                                </ul>
                                            </c:if>
                                            <c:if test="${fn:length(childs.childs) == 0}">
                                                <img src="<%=request.getContextPath()%>/frame/images/zi.PNG" class="im" onclick="selectNoChild('${childs.id}','${childs.funName}','${childs.funUrl}','${childs.parentId}','${childs.orderNumber}')">
                                                <a onclick="selectNoChild('${childs.id}','${childs.funName}','${childs.funUrl}','${childs.parentId}','${childs.orderNumber}')" class="co">
                                                        &nbsp;
                                                        ${childs.funName}
                                                </a>
                                            </c:if>
                                        </dd>
                                    </c:forEach>
                                </dl>
                            </c:if>
                        </li>
                    </c:forEach>
                </ul>
            </td>
            <td><table width="100%" style="height: 250px" border="0" cellspacing="0" cellpadding="0" class="bd-tc-s">
                <tr>
                    <td class="title" width="150"><span>*</span>功能模块名称</td>
                    <td>
                        <input id="functionName" style="width:400px"  type="text"  value="" onblur="updateLength()" onfocus="updatePoint()" placeholder=""/>
                        <span id="functionNameLength">长度限定20位</span>
                    </td>
                </tr>
                <tr>
                    <td class="title" width="150">URL地址</td>
                    <td><input id="funcitonURL" style="width:400px"  type="text"  value="" placeholder=""/></td>
                </tr>
                <tr>
                    <td class="title">父类功能模块</td>
                    <td><input id="functionParent" style="width:400px"  type="text"  value="" disabled placeholder=""/></td>
                </tr>
                <tr>
                    <td class="title"><span>*</span>排序</td>
                    <td><input id="functionOrder" style="width:400px"  type="text"  value=" " placeholder="" onblur="tab()"/></td>
                </tr>
            </table>
            <div id="dlg-buttons2" style="margin-top:15px;">
                <table cellpadding="0" cellspacing="0" style="width:100%">
                    <tr>
                        <td align="center" height="30"><a href="#" onclick="focusJump()" class="dailog-bt" onfocus="">添加子模块</a>
                            <a href="#" id="bt2" onclick="updatefun()" class="dailog-bt">保存修改</a>
                            <a href="#" id="bt3" onclick="deletefun()" class="dailog-bt">删除模块</a>
                        </td>
                    </tr>
                </table>
            </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<%--<div>--%>
    <%--<ul id="treeDemo" class="ztree"></ul>--%>
<%--</div>--%>

<!--easyui弹窗，参数buttons根据id调用操作按钮，参数title显示弹窗标题--><!---->
<div id="dlg2" class="easyui-dialog" title="新增模块" style="width:550px; height:390px; padding:8px;" buttons="#dlg-buttons1" closed="true" >
    <table width="100%" style="height: 250px" border="0" cellspacing="0" cellpadding="0" class="bd-tc-s" >
        <tr>
            <td width="32%" class="title"><span>*</span>功能模块名称</td>
            <td width="68%">
                <input  id="funName" type="text" style="width:200px" onblur="addLength()" value="" onfocus="addPoint()" placeholder=""/>
                <span id="funNameLength">长度限定20位</span>
            </td>
        </tr>
        <tr>
            <td class="title">URL地址</td>
            <td><input type="text" id="funUrl" style="width:200px" value="" placeholder=""/></td>
        </tr>
        <tr>
            <td class="title">父类功能模块</td>
            <td><input style="width:200px" id="funParentName" type="text"  value="" disabled placeholder=""/></td>
        </tr>
        <tr>
            <td class="title"><span>*</span>排序</td>
            <td><input style="width:200px" id="orderNumber"  type="text"  value="" placeholder="" onblur="addTab()"/></td>
        </tr>
    </table>
</div>

<!--easyui弹窗按钮加载-->
<div id="dlg-buttons1">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td align="center" height="30">
                <a href="#" id="bt1" onclick="insert()" class="dailog-bt" onfocus="">确定</a>
                <a href="#" class="dailog-bt" onclick="$('#dlg2').dialog('close');add=false;document.getElementById('funName').value = '';document.getElementById('funUrl').value = '';document.getElementById('funParentName').value = '';document.getElementById('orderNumber').value = '';document.getElementById('funNameLength').innerText = '长度限定20位';">
                    关 闭
                </a>
            </td>
        </tr>
    </table>
</div>
</body>
</html>