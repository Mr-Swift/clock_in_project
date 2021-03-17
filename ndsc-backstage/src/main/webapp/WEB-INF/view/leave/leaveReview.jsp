<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>请假审批</title>
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

        function modify() {
            $('#dlg').window({
                draggable: true,
                title: "修改请假信息"
            });
            $('#dlg').window('open');

        };

        function add() {
            $('#dlg').window({
                draggable: true,
                title: "新增请假信息"
            });
            $('#dlg').window('open');
        };
    </script>
    <script type="text/javascript">
        function
        del() {
            if (!confirm(
                "是否确认删除该条请假信息？"
            )) {
                window.event.returnValue = false;
            }
        }
    </script>
</head>

<body>
<!--数据展示区域外框，用于周边留空padding不可删除-->
<div class="pd-zy20">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
        <tr>
            <td width="46%">当前位置：请假审批</td>
        </tr>
    </table>
    <div class="cx-bt">
        <!--功能按钮-->
        <div class="btn-bt">
            <ul>
                <li><a href="#" onclick="toExe()">导出</a>
                    <%--                    <a href="#" onclick=add()>新增</a></li>--%>
            </ul>
        </div>
        <!--end-->
        <!--搜索-->
        <div class="search1">
            <ul>
                <li>请假人：</li>
                <li><input name="" type="text" style="width:70px;"></li>
                <li>操作人：</li>
                <li><input name="" type="text" style="width:70px;"></li>
                <li>请假类型：</li>
                <li><select name="select2" style="width: 100px">
                    <option value="">全部</option>
                    <c:forEach items="${leaveTypeList}" var="leaveTypeList" varStatus="leaveTypeListStatus">
                        <option value="${leaveTypeList.id}">${leaveTypeList.dataValue1}</option>
                    </c:forEach>
                </select></li>
                <li>审批状态：</li>
                <li><select name="select2" style="width: 100px">
                    <option value="">全部</option>
                    <option value="1">已审批</option>
                    <option value="0">未审批</option>
                </select></li>
            </ul>
        </div>
    </div>

    <div id="shadow">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:8px;">

        <tr>
            <%--            <th width="30"><span id="allCheck">全选</span></th>--%>
                <th width="20">序号</th>
                <th width="50">申请人</th>
                <th width="100">请假时间</th>
                <th width="100">销假时间</th>
                <th width="30">请假类型</th>
                <th width="100">请假理由</th>
                <th width="100">申请时间</th>
                <th width="50">审批人</th>
                <th width="100">审批时间</th>
                <th width="20">是否审批</th>
                <th width="40">操作</th>
        </tr>

        <c:forEach items="${result.data}" var="data" varStatus="dataStatus">
            <tr <c:if test="${dataStatus.count%2==0}">class="ol"</c:if>>
                <td>${dataStatus.count}</td>
                <td>${data.leaveUser}</td>
                <td><fmt:formatDate value="${data.leaveStartDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <td><fmt:formatDate value="${data.leaveEndDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <td>${data.leaveType}</td>
                <td>${data.leaveReason}</td>
                <td><fmt:formatDate value="${data.insertDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <td>${data.checkUser}</td>
                <td><fmt:formatDate value="${data.checkDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <td>${data.checkStatusStr}</td>
                <td class="cz-a">
                    <a><i class="fa fa-edit pd-r06" onclick="check('${data.id}')"></i>审批</a>
                </td>
                <script>
                    function check(id) {
                        if (!confirm("是否同意该条请假申请？")) {
                            window.event.returnValue = false;
                        } else {
                            $.ajax({
                                type: "post",
                                url: "<%=request.getContextPath()%>/leaveReview/check.do",
                                data: {"id": id},
                                dataType: "json",
                                success: function (data) {
                                    if (data.code == 0) {
                                        alert("审批成功！");
                                        window.reload();
                                    } else {
                                        alert("审批失败！")
                                    }
                                },
                                error: function (data) {
                                    alert("系统故障，请联系管理员")
                                }
                            })
                        }
                    }
                </script>
            </tr>
        </c:forEach>
    </table>
    </div>

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="fy">
        <tr>
            <td height="40" align="right" >
                当前第 <span>${page}</span> 页 共 <span>${totalPage}</span> 页
                <a style="width: 30px" href="<%=request.getContextPath()%>/goOut/toGoOut.do?page=1">首页</a>&nbsp;|&nbsp;
                <a style="width: 45px" href="javaScript:;" onclick="toPreviousPage('${page-1}')">上一页</a>&nbsp;|&nbsp;
                <a style="width: 45px" href="javaScript:;" onclick="toNextPage('${page+1}')">下一页</a>&nbsp;|&nbsp;
                <a style="width: 30px" href="<%=request.getContextPath()%>/goOut/toGoOut.do?page=${totalPage}">尾页</a> 跳转到</td>
            <td width="40" align="center"><input name="textfield8" type="text" class="inp_w4" id="pageNum"/></td>
            <td width="35" align="left"><img src="<%=request.getContextPath()%>/frame/images/bt_go.gif" width="26"
                                             height="24" onclick="goToPage()"/></td>
        </tr>
    </table>



    <div id="dlg-buttons2">
        <table cellpadding="0" cellspacing="0" style="width:1300px; margin-top:30px">
            <tr>
                <td align="center" height="30">
<%--                    <a href="javaScript:confirmButton()" class="dailog-bt">确 认</a>--%>
                </td>
            </tr>
        </table>
    </div>
</div>


<div id="dlg" class="easyui-dialog" title="新增员工" style="width:790px; height:400px;" closed="true"
     buttons="#dlg-buttons">

    <table border="0" width="100%" cellpadding="0" cellspacing="0" class="bd-tc-s">
        <tr>
            <td align="right" class="titley" colspan="4">基础信息</td>
        </tr>

        <tr>
            <td width="130" align="right" class="title"><span>*</span>请假类型：</td>
            <td align="left" colspan="1"><select name="select2" style="width: 193px">
                <option>请选择</option>
            </select>
            </td>
            <td align="right" class="title"><span>*</span>理<span>&nbsp;&nbsp;&nbsp;</span>由：</td>
            <td align="left">
          <textarea rows="5" cols="40">
            </textarea>
            </td>
        </tr>

        <tr>
            <td align="right" class="titley" colspan="4">请假时间</td>
        </tr>
        <tr>
            <td align="right" class="title"><span>*</span>日期：</td>
            <td align="left">

                <input
                        type="text" style="width: 180px;"
                        readonly class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '%y-%M-%d'})"/>
            </td>
            <td align="right" class="title"><span>*</span>时：</td>
            <td align="left" colspan="1"><select name="select2" id="select2" style="width: 193px">
                <option>请选择</option>

                <%for (int i = 1; i <= 24; i++) {%>
                <option><%=i%>
                </option>
                <%}%>

            </select></td>

        </tr>
        <td align="right" class="title"><span>*</span>分：</td>
        <td align="left" colspan="1"><select name="select2" id="select2" style="width: 193px">
            <option>请选择</option>

            <%for (int i = 1; i <= 60; i++) {%>
            <option><%=i%>
            </option>
            <%}%>

        </select></td>

        <td align="right" class="title"><span>*</span>秒：</td>
        <td align="left" colspan="1"><select name="select2" id="select2" style="width: 193px">
            <option>请选择</option>

            <%for (int i = 1; i <= 60; i++) {%>
            <option><%=i%>
            </option>
            <%}%>

        </select></td>


        <tr>
            <td align="right" class="titley" colspan="4">销假时间</td>
        </tr>
        <tr>
            <td align="right" class="title"><span>*</span>日期：</td>
            <td align="left">

                <input
                        type="text" style="width: 180px;"
                        readonly class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '%y-%M-%d'})"/>
            </td>
            <td align="right" class="title"><span>*</span>时：</td>
            <td align="left" colspan="1"><select name="select2" id="select2" style="width: 193px">
                <option>请选择</option>

                <%for (int i = 1; i <= 24; i++) {%>
                <option><%=i%>
                </option>
                <%}%>

            </select></td>

        </tr>
        <td align="right" class="title"><span>*</span>分：</td>
        <td align="left" colspan="1"><select name="select2" id="select2" style="width: 193px">
            <option>请选择</option>

            <%for (int i = 1; i <= 60; i++) {%>
            <option><%=i%>
            </option>
            <%}%>

        </select></td>

        <td align="right" class="title"><span>*</span>秒：</td>
        <td align="left" colspan="1"><select name="select2" id="select2" style="width: 193px">
            <option>请选择</option>

            <%for (int i = 1; i <= 60; i++) {%>
            <option><%=i%>
            </option>
            <%}%>

        </select></td>


    </table>
</div>
<div id="dlg-buttons">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td align="center" height="30">
                <a href="#" class="dailog-bt" onclick="$('#dlg').window('close')">保 存</a><a href="#" class="dailog-bt"
                                                                                            onclick="javascript:$('#dlg').dialog('close')">取
                消</a>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    function toExe() {
        $("#shadow").table2excel({
            exclude: ".noExl",
            name: "Excel Document Name",
            filename: "请假审批" + new Date().getFullYear() + "年" + new Date().getMonth() + 1 + "月" + new Date().getDate() + "日" + ".xls",
            exclude_img: true,
            exclude_links: true,
            exclude_inputs: true
        });
    }


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
            window.location.href = "<%=request.getContextPath()%>/goOut/toGoOut.do?page=" + page+"&year"+year+"&month"+month+"&day"+day+"&remark"+remark;
        } else {
            alert("跳转失败，请重新输入页数");
        }
    }

    function toPreviousPage(page){
        if (Number(page) == 0) {
            alert("已经是第一页。");
            return false;
        }else{
            location.href="<%=request.getContextPath()%>/goOut/toGoOut.do?page=${page-1}&year=${result.params.get('year')}&month=${result.params.get('month')}&day=${result.params.get('day')}&remark=${result.params.get('remark')}"
        }
    }


    function toNextPage(page) {
        var sumCount = '${totalPage}';
        if (Number(page) > Number(sumCount)) {
            alert("已是最后一页。");
            return false;
        }else {
            window.location.href = "<%=request.getContextPath()%>/goOut/toGoOut.do?page=${page+1}&year=${result.params.get('year')}&month=${result.params.get('month')}&day=${result.params.get('day')}&remark=${result.params.get('remark')}"
        }
    }
</script>
</body>
</html>