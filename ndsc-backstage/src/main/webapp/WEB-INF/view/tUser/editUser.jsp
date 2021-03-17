<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
  <title></title>
  <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/frame/images/default/easyui.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.table2excel.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/frame/end/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form action="<%=request.getContextPath()%>/tUser/save.do">

    <table border="0" width="100%" cellpadding="0" cellspacing="0" class="bd-tc-s">
      <tr>
        <td align="right" class="titley" colspan="4">基础信息</td>
      </tr>
      <tr>

        <td align="right" class="title"><span>*</span>工号：</td>
        <td align="left"><input type="text" style="width: 193px;" onblur="checkPersonNo()" id="personNo" name="personNo"/></td>
        <td width="130" align="right" class="title"><span>*</span>姓名：</td>
        <td align="left"><input type="text" style="width: 193px;" name="textfield14" id="userName" name="userName" onblur="check()"/></td>
      </tr>
      <tr>

        <td width="132" align="right" class="title"><span>*</span>性别：</td>
        <td align="left"><select name="sex" id="sex" style="width: 200px">
          <option value="1">男</option>
          <option value="0">女</option>
        </select></td>
        <td align="right" class="title"><span>*</span>联系方式：</td>
        <td align="left"><input type="text" style="width: 193px;"  id="telephone" name="telephone"/></td>
      </tr>
      <tr>

        <td align="right" class="title"><span>*</span>部门：</td>
        <td align="left"><select name="dept" id="dept" style="width: 200px">
          <option value="">请选择</option>
          <c:forEach items="${deptList}" var="dept">
            <option value="${dept.id}">${dept.deptName}</option>
          </c:forEach>
        </select></td>
        <td align="right" class="title"><span>*</span>是否部门经理：</td>
        <td align="left">
          <select name="isDeptManager" id="isDeptManager" style="width: 200px" class="isDeptManager">
            <option value="0">否</option>
            <option value="1">是</option>
          </select>
        </td>
      </tr>
      <tr>
        <td align="right" class="title"><span>*</span>岗位：</td>
        <td align="left" colspan="3">
          <select id="post" name="post" class="easyui-combobox" style="width:578px;" data-options="editable:false,panelHeight:180"></select>
        </td>


        <script type="text/javascript">
          $(document).ready(function (){
            $('#post').combobox({
              valueField: "id", //Value字段
              textField: "text", //Text字段
              multiple: true,
              data:  [<c:forEach items="${postList}" var="post">
                { "id": "${post.id}", "text": "${post.postName}" },
                </c:forEach>],
              onClick: function (node, checked) {
                //让全选不显示
                $("#post").combobox("setText", $("#post").combobox("getText").toString().replace("全选,", ""));
              },
            });
          })
        </script>


      </tr>
      <tr>

      </tr>
      <tr>

        <td align="right" class="title">基本工资：</td>
        <td align="left"><input type="text" style="width: 193px;" onblur="check()" id="salary" name="salary"/></td>
        <td align="right" class="title">当前状态：</td>
        <td align="left"><select name="select2" id="zhuangtai" style="width: 200px">
          <option value="1">在职</option>
          <option value="0">离职</option>
        </select></td>
      </tr>

      <tr>
        <td align="right" class="titley" colspan="4">身份信息</td>
      </tr>
      <tr>
        <td align="right" class="title">身份证号：</td>
        <td align="left"><input type="text" style="width: 193px;" onblur="check()" id="cardNo" name="cardNo"/></td>
        <td align="right" class="title">出生日期：</td>
        <td align="left"><input type="text" style="width: 193px;" id="birthDate" name="birthDate" readonly class="Wdate"
                                onclick="WdatePicker()"/></td>
      </tr>
      <tr>
        <td align="right" class="title">民族：</td>
        <td align="left"><input type="text" style="width: 193px;" onblur="check()" id="ethnic" name="ethnic"/></td>
        <td align="right" class="title">籍贯：</td>
        <td align="left"><input type="text" style="width: 193px;" onblur="check()" id="nativePlace" name="nativePlace"/></td>
      </tr>
      <tr>
        <td align="right" class="title">户籍地址：</td>
        <td align="left" colspan="3"><input type="text" style="width: 570px;" onblur="check()" id="censusRegisterAddress" name="censusRegisterAddress"/></td>
      </tr>
      <tr>
        <td align="right" class="title">居住地址：</td>
        <td align="left" colspan="3"><input type="text" style="width: 570px;" onblur="check()" id="familyAddress" name="familyAddress"/></td>
      </tr>
      <tr>
        <td align="right" class="title"><span>*</span>婚育状态：</td>
        <td align="left" colspan="3"><select name="marriageStatus" id="marriageStatus" style="width: 200px">
          <option value="">请选择</option>
          <c:forEach items="${marriageList}" var="marriage">
            <option value="${marriage.id}">${marriage.dataValue1}</option>
          </c:forEach>

        </select></td>
      </tr>
      <tr>
        <td align="right" class="title">紧急联系人：</td>
        <td align="left"><input type="text" style="width: 193px;" onblur="check()" id="hurryContractPerson" name="hurryContractPerson"/></td>
        <td align="right" class="title">紧急联系人电话：</td>
        <td align="left"><input type="text" style="width: 193px;" onblur="check()" id="hurryContractTelephone" name="hurryContractTelephone"/></td>
      </tr>
      <tr>
        <td align="right" class="titley" colspan="4">学历信息</td>
      </tr>
      <tr>
        <td align="right" class="title"><span>*</span>毕业院校：</td>
        <td align="left"><input type="text" style="width: 193px;" onblur="check()" id="school" name="school"/></td>
        <td align="right" class="title">专业名称：</td>
        <td align="left"><input type="text" style="width: 193px;" onblur="check()" id="nameOfMajor" name="nameOfMajor"/></td>
      </tr>
      <tr>
        <td align="right" class="title">毕业时间：</td>
        <td align="left"><input type="text" style="width: 193px;" id="graduateDate" name="graduateDate" readonly class="Wdate"
                                onclick="WdatePicker()"/></td>
        <td align="right" class="title">最高学历：</td>
        <td align="left"><select name="highLevel" id="highLevel" style="width: 200px">
          <c:forEach items="${levelList}" var="level">
            <option value="${level.id}">${level.dataValue1}</option>
          </c:forEach>
        </select></td>
      </tr>
      <tr>
        <td align="right" class="title"><span>*</span>入职日期：</td>
        <td align="left"><input type="text" style="width: 193px;" id="entryDate" name="entryDate" readonly class="Wdate"
                                onclick="WdatePicker()"/></td>
        <td align="right" class="title">转正日期：</td>
        <td align="left"><input type="text" style="width: 193px;" id="regularWorkDate" name="regularWorkDate" readonly class="Wdate"
                                onclick="WdatePicker()"/></td>
      </tr>
      <tr>
        <td align="right" class="title">第一期合同期限：</td>
        <td align="left"><input type="text" style="width: 89px;"
                                id="firstContractStartDate" name="firstContractStartDate" readonly class="Wdate" onclick="WdatePicker()"/>~<input
                type="text" style="width: 89px;"  id="firstContractEndDate" name="firstContractEndDate"
                readonly class="Wdate" onclick="WdatePicker()"/></td>
        <td align="right" class="title">第二期合同期限：</td>
        <td align="left"><input type="text" style="width: 89px;"
                                id="secondContractStartDate" name="secondContractStartDate" readonly class="Wdate" onclick="WdatePicker()"/>~<input
                type="text" style="width: 89px;"  id="secondContractEndDate" name="secondContractEndDate"
                readonly class="Wdate" onclick="WdatePicker()"/></td>
      </tr>
      <tr>
        <td align="right" class="title">第三期合同期限：</td>
        <td align="left" colspan="3"><input type="text" style="width: 89px;"
                                            id="thirdContractStartDate" name="thirdContractStartDate"  readonly class="Wdate" onclick="WdatePicker()"/>~<input
                type="text" style="width: 89px;"  id="thirdContractEndDate" name="thirdContractEndDate"
                readonly class="Wdate" onclick="WdatePicker()"/></td>
      </tr>
    </table>


    <table cellpadding="0" cellspacing="0" style="width:100%">
      <tr>
        <td align="center" height="30">
          <a href="#" class="dailog-bt" id="saveOrUpdate" onclick="update()">保 存</a><a href="#" class="dailog-bt" onclick="javascript:$('#editDlg').dialog('close')">取 消</a>
        </td>
      </tr>
    </table>


</form>



</body>
</html>
