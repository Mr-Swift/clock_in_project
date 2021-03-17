<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="<%=request.getContextPath()%>/frame/images/default.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/frame/images/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/js/jquery.table2excel.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/frame/end/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#search").click(function(){
                $(".search-hide").slideToggle(400)
            })
            $("#close-s").click(function(){
                $(".search-hide").slideToggle(400)
            });
            $("#search1").click(function(){
                $(".search-hide1").slideToggle(400)
            })
            $("#close-s1").click(function(){
                $(".search-hide1").slideToggle(400)
            })
            //标签切换
            var $li = $(".sw_b li");
            var $div = $(".swb_b");
            $li.click(function(){
                var $t = $(".sw_b li").index($(this));
                $li.removeClass();
                $(this).addClass("active");
                $div.css("display","none");
                $div.eq($t).css("display","block");
            })

        });

        $(document).ready(function () {
            $("#userStatus").val(${userStatus});
            $("#dept").val('${dept}');
        });


        $(function () {
            $('#ddlLine').combobox({
                valueField: "id", //Value字段
                textField: "text", //Text字段
                multiple: true,
                data:  [{ "id": 1, "text": "研发部" }, { "id": 2, "text": "商务部" }, { "id": 3, "text": "质量管理部" }, { "id": 4, "text": "实施部" }, { "id": 5, "text": "人事部" }, { "id": 6, "text": "财务部" }, { "id": 7, "text": "美工部" }, { "id": 8, "text": "需求部" }] ,
                //  url: "tree_data2.json", //数据源
                onClick: function (node, checked) {
                    //让全选不显示
                    $("#ddlLine").combobox("setText", $("#ddlLine").combobox("getText").toString().replace("全选,", ""));
                }
            });
        })
        $(function () {
            $('#ddlLine1').combobox({
                valueField: "id", //Value字段
                textField: "text", //Text字段
                multiple: true,
                data:  [<c:forEach items="${allPostManage}" var="all">
                   { "id": "${all.id}", "text": "${all.postName}" },
                </c:forEach>],
                onClick: function (node, checked) {
                    //让全选不显示
                    $("#ddlLine1").combobox("setText", $("#ddlLine1").combobox("getText").toString().replace("全选,", ""));
                },
            });
        })
    </script>
    <script type="text/javascript">
        function del(id, postId, userName){
            if (confirm("是否确认删除该员工？")){
                $.ajax({
                    type: "POST",
                    url: "<%=request.getContextPath()%>/tUser/del.do",
                    data: {"id": id, "postId": postId, "userName": userName},
                    dataType:"json",
                    success: function (data) {
                        if (data.toString() == 'ok') {
                            alert("删除成功");
                            window.location.href = "<%=request.getContextPath()%>/tUser/toUserInfo.do";
                        }
                    },
                    error: function () {
                        alert("网络连接出错！");
                    }
                });
            }
        }
    </script>

<script type="text/javascript">
    //校验工号唯一性
    function checkPersonNo() {
        var regExp = new RegExp("[`~!！：。、‘’''“”？=【】《》——@#$%^&*()+<>?:\"{},.\\/;'[\\]]");//非法字符校验
        var personNo = $("#personNo").val().trim();
        if (regExp.test(personNo)){
            alert("输入内容包括非法字符，请重新输入");
            $('#personNo').focus();
        }
        if(personNo.length>10){
            alert("工号长度不能超过10位");
            $('#personNo').focus();
        <%--}else {--%>
        <%--    $.ajax({--%>
        <%--        // async: false,--%>
        <%--        type: "POST",--%>
        <%--        url: "<%=request.getContextPath()%>/tUser/checkPersonNo.do",--%>
        <%--        data: {--%>
        <%--            "personNo": personNo--%>
        <%--        },--%>
        <%--        dataType:"json",--%>
        <%--        success: function (data) {--%>
        <%--            if (data.toString() == 'no') {--%>
        <%--                alert("工号已被使用，请重新输入");--%>
        <%--                $('#personNo').focus();--%>
        <%--                isCheck = false;--%>
        <%--            }else {--%>
        <%--                isCheck = true;--%>
        <%--            }--%>
        <%--        },--%>
        <%--        error: function () {--%>
        <%--            alert("网络连接出错！");--%>
        <%--        }--%>
        <%--    });--%>
        }
    }
    //校验字段
    function check() {
        var username = $("#username").val().trim();
        var mobile = $("#mobile").val().trim();
        var idcard = $("#idcard").val().trim();
        var salary = $("#salary").val().trim();
        var minzu = $("#minzu").val().trim();
        var jiguan = $("#jiguan").val().trim();
        var hujiLocation = $("#hujiLocation").val().trim();
        var juzhuLocation = $("#juzhuLocation").val().trim();
        var emergencyContact = $("#emergencyContact").val().trim();
        var emMobile = $("#emMobile").val().trim();
        var university = $("#university").val().trim();
        var major = $("#major").val().trim();
        var salaryFormat = /(^[1-9]([0-9]+)?(\.[0]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        var r = /^\d+$/ //数字
        var mobileFormat = /^1[3|4|5|7|8][0-9]{9}$/;
        var regExp = new RegExp("[`~!！：。、‘’''“”？=【】《》——@#$%^&*()+<>?:\"{},.\\/;'[\\]]");//非法字符校验
        if (username!=""){
            if (username.length > 10) {
                $('#username').focus();
                alert("姓名长度不能超过10位!");
            } else if (regExp.test(username)){
                $('#username').focus();
                alert("输入内容包括非法字符，请重新输入!");
            }
        }
        if (mobile!=""){
            if (mobile.length > 11) {
                $('#mobile').focus();
                alert("联系方式长度不能超过11位!");

            } else if (!mobileFormat.test(mobile)){
                alert("手机号不合法！");
                $('#mobile').val("");
                $('#mobile').focus();
            }
        }
       if (idcard!=""){
           if (idcard.length > 18) {
               alert("身份证号长度不能超过18位");
               $('#idcard').focus();
           } else if (regExp.test(idcard)){
               alert("输入内容包括非法字符，请重新输入!");
               $('#idcard').focus();
           }
       }
        if (salary!=""){
            if (!salaryFormat.test(salary)){
                alert("基本工资必须为整数并且最多保留2位小数！");
                $('#salary').focus();
            }
        }
        if (minzu!=""){
            if (minzu.length > 10) {
                alert("民族字段长度不能超过10位");
                $('#minzu').focus();
            } else if (regExp.test(minzu)){
                alert("输入内容包括非法字符，请重新输入!");
                $('#minzu').focus();
            }
        }
        if (jiguan!=""){
            if (jiguan.length > 20) {
                alert("籍贯字段长度不能超过20位");
                $('#jiguan').focus();
            } else if (regExp.test(jiguan)){
                alert("输入内容包括非法字符，请重新输入!");
                $('#jiguan').focus();
            }
        }
        if (hujiLocation!=""){
            if (hujiLocation.length > 100) {
                alert("户籍地址长度不能超过100位");
                $('#hujiLocation').focus();
            } else if (regExp.test(hujiLocation)){
                alert("输入内容包括非法字符，请重新输入!");
                $('#hujiLocation').focus();
            }
        }
        if (juzhuLocation!=""){
            if (juzhuLocation.length > 100) {
                alert("居住地址长度不能超过100位");
                $('#juzhuLocation').focus();
            } else if (regExp.test(juzhuLocation)){
                alert("输入内容包括非法字符，请重新输入!");
                $('#juzhuLocation').focus();
            }
        }
        if (emergencyContact!=""){
            if (emergencyContact.length > 10) {
                alert("紧急联系人长度不能超过10位");
                $('#emergencyContact').focus();
            } else if (regExp.test(emergencyContact)){
                alert("输入内容包括非法字符，请重新输入！");
                $('#emergencyContact').focus();
            }
        }
        if (emMobile!=""){
            if (emMobile.length > 11) {
                alert("联系方式长度不能超过11位");
                $('#emMobile').focus();
            } else if (!mobileFormat.test(emMobile)){
                alert("手机号不合法！");
                $('#emMobile').focus();
            }
        }
        if (university!=""){
            if (university.length > 30) {
                alert("毕业院校长度不能超过30位!");
                $('#university').focus();
            } else if (regExp.test(university)){
                alert("输入内容包括非法字符，请重新输入!");
                $('#university').focus();
            }
        }
        if (major!=""){
            if (major.length > 20) {
                alert("专业名称长度不能超过20位!");
                $('#major').focus();
            } else if (regExp.test(major)){
                alert("输入内容包括非法字符，请重新输入!");
                $('#major').focus();
            }
        }

    }
    //校验截止日期是不是在开始日期后
    function compareDate(dateTime1,dateTime2){
        if (dateTime1==null){
            return true;
        }
        var formatDate1 = new Date(dateTime1);
        var formatDate2 = new Date(dateTime2);
        if (formatDate1 > formatDate2) {
            return false;
        } else {
            return true;
        }
    }
    var isCheck;
    var porsonNoCheck;
    //添加或修改数据
    function update() {
        var personNo = $("#personNo").val().trim();
        var username = $("#username").val().trim();
        var mobile = $("#mobile").val().trim();
        var sex = $("#sex").val().trim();
        var dept = $("#dept").val().trim();
        var isDeptLeader = $("#isDeptLeader").val().trim();
        var array = $('#ddlLine1').combobox('getValues');
        var hunyu = $("#hunyu").val().trim();
        var university = $("#university").val().trim();
        var workDate = $("#workDate").val().trim();
        var salary = $("#salary").val().trim();
        var zhuangtai = $("#zhuangtai").val().trim();
        var idcard = $("#idcard").val().trim();
        var date = $("#date1").val().trim();
        var minzu = $("#minzu").val().trim();
        var jiguan = $("#jiguan").val().trim();
        var hujiLocation = $("#hujiLocation").val().trim();
        var juzhuLocation = $("#juzhuLocation").val().trim();
        var emergencyContact = $("#emergencyContact").val().trim();
        var emMobile = $("#emMobile").val().trim();
        var major = $("#major").val().trim();
        var graduateDate = $("#graduateDate").val().trim();
        var xueli = $("#xueli").val().trim();
        var zhuanzhengDate = $("#zhuanzhengDate").val().trim();
        var hetong1 = $("#hetong1").val().trim();
        var hetong11 = $("#hetong11").val().trim();
        var hetong2 = $("#hetong2").val().trim();
        var hetong22 = $("#hetong22").val().trim();
        var hetong3 = $("#hetong3").val().trim();
        var hetong33 = $("#hetong33").val().trim();
        var regExp = new RegExp("[`~!！：。、‘’''“”？=【】《》——@#$%^&*()+<>?:\"{},.\\/;'[\\]]");//非法字符校验

        check();
        if (personNo=="") {
            alert("工号不能为空！");
            $('#personNo').focus();
            return false;
        } else if (username=="") {
            alert("姓名不能为空！");
            $('#username').focus();
            return false;
        } else if (mobile=="") {
            alert("联系方式不能为空！");
            $('#mobile').focus();
            return false;
        } else if (array=="") {
            alert("岗位不能为空！");
            $('#ddlLine1').focus();
            return false;
        } else if (university=="") {
            alert("毕业院校不能为空！");
            $('#university').focus();
            return false;
        } else if (workDate=="") {
            alert("入职日期不能为空！");
            $('#workDate').focus();
            return false;
        }
        var getDate = new Date();
        var nowDate = getDate.getFullYear()+"-"+(getDate.getMonth()+1)+"-"+getDate.getDate();
        var nowDateFormat = format(nowDate, "yyyy-MM-dd");
        var dateFormat = format(date, "yyyy-MM-dd")
        if (dateFormat>nowDateFormat){
            alert("出生日期不得大于当前日期！");
            $('#date1').focus();
            return false;
        }
        if (compareDate(hetong1, hetong11)==false){
            alert("合同截止时间不得早于开始时间!")
            $('#hetong11').focus();
            return false;
        }
        if (compareDate(hetong2, hetong22)==false){
            alert("合同截止时间不得早于开始时间!")
            $('#hetong22').focus();
            return false;
        }
        if (compareDate(hetong3, hetong33)==false){
            alert("合同截止时间不得早于开始时间!")
            $('#hetong33').focus();
            return false;
        }
        if ((hetong1!="")||(hetong11!="")){
            if (hetong11==""){
                alert("合同信息填写不完整！");
                $('#hetong11').focus();
                return false;
            }
            if (hetong1==""){
                alert("合同信息填写不完整！");
                $('#hetong1').focus();
                return false;
            }
        }
        if ((hetong2!="")||(hetong22!="")){
            if (hetong22==""){
                alert("合同信息填写不完整！");
                $('#hetong22').focus();
                return false;
            }
            if (hetong2==""){
                alert("合同信息填写不完整！");
                $('#hetong2').focus();
                return false;
            }
        }
        if ((hetong3!="")||(hetong33!="")){
            if (hetong33==""){
                alert("合同信息填写不完整！");
                $('#hetong33').focus();
                return false;
            }
            if (hetong3==""){
                alert("合同信息填写不完整！");
                $('#hetong3').focus();
                return false;
            }

        }
        if (porsonNoCheck!=personNo){
            $.ajax({
                async: false,
                type: "POST",
                url: "<%=request.getContextPath()%>/tUser/checkPersonNo.do",
                data: {
                    "personNo": personNo
                },
                dataType:"json",
                success: function (data) {
                    if (data.toString() == 'no') {
                        isCheck = false;
                    }else {
                        isCheck = true;
                    }
                },
                error: function () {
                    alert("网络连接出错！");
                }
            });
        }

            if (isCheck==false){
                alert("工号已经存在!")
                $('#personNo').focus();
                return false;
            }else
            {
            if (iden==1){
                var aa = window.confirm("您确定新增员工信息吗？");
            }else if (iden==0){
                var aa = window.confirm("您确定修改员工信息吗？");
            }
            if (aa) {
                $.ajax({
                    type: "post",
                    url: '<%=request.getContextPath()%>/tUser/addUserInfo.do',
                    data: { "personNo": personNo, "username": username,
                            "mobile": mobile, "sex": sex,
                            "dept": dept, "isDeptLeader": isDeptLeader,
                            "array": array, "hunyu": hunyu,
                            "university": university, "workDate": workDate,
                            "salary": salary, "zhuangtai": zhuangtai,
                            "idcard": idcard, "date": date,
                            "minzu": minzu, "jiguan": jiguan,
                            "hujiLocation": hujiLocation, "juzhuLocation": juzhuLocation,
                            "emergencyContact": emergencyContact, "emMobile": emMobile,
                            "major": major, "graduateDate": graduateDate,
                            "xueli": xueli, "zhuanzhengDate": zhuanzhengDate,
                            "hetong1": hetong1, "hetong11": hetong11,
                            "hetong2": hetong2, "hetong22": hetong22,
                            "hetong3": hetong3, "hetong33": hetong33,
                            "iden": iden, "uid": userId, "oldPostId": oldPostID},
                    dataType: "json",
                    traditional: true,
                    success: function (data) {
                        console.log(data);
                        console.log(iden);
                        if (data == "1") {
                            if (iden==1){
                                alert("新增员工成功！")
                            }else if (iden==0){
                                alert("修改员工信息成功！")
                            }
                            window.location = "<%=request.getContextPath()%>/tUser/toUserInfo.do";
                        } else {
                            alert("新增或修改员工信息失败！")
                        }
                    }
                });
            }
        }
    }
    $(function(){
        $("#btn").click(function(){
            $("#myForm").submit();
        });
    });
    //校验跳转页数输入是否正确
    function checkPage() {
        var r = /^\d{0,}$/;
        var go = $("#go").val().trim();
        if (!r.test(go)){
            alert("页数只能输入数字！");
            $('#go').focus();
        }
    }
    function goto() {
        var n = $('#go').val().trim();
        var maxPage = ${totalPage}
        if (n==""){
            alert("请输入要跳转的页数");
            return false;
        }
        if (n>maxPage){
            alert("要跳转的页数大于最大页数，请重新输入！")
            return false;
        }else {
            $("#page").val(n);
            $("#myForm").submit();
        }
    }

    //给修改信息框赋值
    function xiugai(personNo,userName,sex,telephone,deptName,isDeptManager,postId,salary,userStatus,cardNo,
                    birthDate,ethnic,nativePlace,censusRegisterAddress,familyAddress,marriageStatus,hurryContractPerson,
                    hurryContractTelephone,school,nameOfMajor,graduateDate,highLevel,entryCompanyDate,
                    regularWorkDate,firstContractStartDate,firstContractEndDate,secondContractStartDate,
                    secondContractEndDate,thirdContractStartDate,thirdContractEndDate,id) {
        userId = id;
        oldPostID = postId;
        var result=format(birthDate,'yyyy-MM-dd');
        var result2=format(graduateDate,'yyyy-MM-dd');
        var result3=format(entryCompanyDate,'yyyy-MM-dd');
        var result4=format(regularWorkDate,'yyyy-MM-dd');
        var result5=format(firstContractStartDate,'yyyy-MM-dd');
        var result6=format(firstContractEndDate,'yyyy-MM-dd');
        var result7=format(secondContractStartDate,'yyyy-MM-dd');
        var result8=format(secondContractEndDate,'yyyy-MM-dd');
        var result9=format(thirdContractStartDate,'yyyy-MM-dd');
        var result10=format(thirdContractEndDate,'yyyy-MM-dd');
        $('#ddlLine1').combobox('clear');
        if ((postId==null)||postId==""){

        } else if ((postId!="")&&(postId!=null)&&(postId.length==1)){
            $('#ddlLine1').combobox('select', postId);
        } else {
            var arrayDeptId = new Array();
            arrayDeptId = postId.split(",");
            for(var i=0;i<arrayDeptId.length;i++){
                $('#ddlLine1').combobox('select', arrayDeptId[i]);
            }
        }
        porsonNoCheck = personNo;
        $('#personNo').val(personNo);
        $('#username').val(userName);
        if (sex==2){ $("#sex").val("女") } else if (sex==1){ $("#sex").val("男") };
        $('#mobile').val(telephone);
        $('#dept').val(deptName);
        $('#salary').val(salary);
        if (userStatus==1){ $("#zhuangtai").val("在职") } else if (userStatus==2){ $("#zhuangtai").val("离职") };
        if (isDeptManager==1){ $(".isDeptManager").val("是") } else if (isDeptManager==0){ $(".isDeptManager").val("否") };
        $('#idcard').val(cardNo);
        $('#date1').val(result);
        $('#minzu').val(ethnic);
        $('#jiguan').val(nativePlace);
        $('#hujiLocation').val(censusRegisterAddress);
        $('#juzhuLocation').val(familyAddress);
        if (marriageStatus==1){ $("#hunyu").val("未婚") } else if (marriageStatus==2){ $("#hunyu").val("已婚未育") }else if (marriageStatus==3){ $("#hunyu").val("已婚已育") }else if (marriageStatus==4){ $("#hunyu").val("离异") };
        $('#emergencyContact').val(hurryContractPerson);
        $('#emMobile').val(hurryContractTelephone);
        $('#university').val(school);
        $('#major').val(nameOfMajor);
        $('#graduateDate').val(result2);
        $('#xueli').val(highLevel);
        $('#workDate').val(result3);
        $('#zhuanzhengDate').val(result4);
        $('#hetong1').val(result5);
        $('#hetong11').val(result6);
        $('#hetong2').val(result7);
        $('#hetong22').val(result8);
        $('#hetong3').val(result9);
        $('#hetong33').val(result10);
        $('#dlg').window({
            draggable: true,
            title: "修改员工信息"
        });
        $('#dlg').window('open');
        iden = 0;//修改功能,设置iden为0
    }
    function add1() {
        //清空编辑信息框的值
        $('#personNo').val("");
        $('#username').val("");
        $('#mobile').val("");
        $('#salary').val("");
        $('#idcard').val("");
        $('#date1').val("");
        $('#minzu').val("");
        $('#jiguan').val("");
        $('#hujiLocation').val("");
        $('#juzhuLocation').val("");
        $('#emergencyContact').val("");
        $('#emMobile').val("");
        $('#university').val("");
        $('#major').val("");
        $('#graduateDate').val("");
        $('#workDate').val("");
        $('#zhuanzhengDate').val("");
        $('#zhuangtai').val("在职");
        $('.isDeptManager').val("否");
        $('#hetong1').val("");
        $('#hetong11').val("");
        $('#hetong2').val("");
        $('#hetong22').val("");
        $('#hetong3').val("");
        $('#hetong33').val("");
        $('#hunyu').val("未婚");
        $('#xueli').val("高中");
        $('#ddlLine1').combobox('clear');
        $('#dlg').window({
            draggable: true,
            title: "新增员工"
        });
        $('#dlg').window('open');
        iden = 1;//添加功能,设置iden为1
        userId = "";
        oldPostID = "";
    }
</script>
<script>
    //封装时间格式
    function format(time, format) {
        if ((time==null)||time==""){
            return "";
        }
        var t = new Date(time);
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    }
</script>
<script>
    // 复选框的全选和全不选
    $(function() {
        // 全选
        var inpcheck = $("td input[type=checkbox]");
        $("#checkAll").click(function () {
            if (inpcheck.data('checked')) {
                inpcheck.prop('checked', false);
                inpcheck.data('checked', false);
                $($(":checkbox[name='ids']").closest("tr")).addClass('noExl');
                $('#isAllExport').val(1);
            } else {
                inpcheck.prop('checked', true);
                inpcheck.data('checked', true);
                $($(":checkbox[name='ids']").closest("tr")).removeClass('noExl');
                $('#isAllExport').val(0);
            }
        })
    });
    //如果选中导出框,给框加样式class="noExl",反之去除样式
    function isSelected(checkedId) {
        if(checkedId.checked == true){
            $($(checkedId).closest("tr")).removeClass('noExl');
        }else {
            $($(checkedId).closest("tr")).addClass('noExl');
        }
    }
    //导出功能
    function Export(){
        //如果是全选，导出隐藏的不分页的所有数据
        if ($("input[name='ids']:checked").length == ${allUser.limit} && $('#isAllExport').val() == 0){
            $("#exceltableHidden").table2excel({            //exceltable为存放数据的table
                // 不被导出的表格行的CSS class类
                exclude: ".noExl",
                // 导出的Excel文档的名称
                name: "员工信息" + new Date().getFullYear()+"年"+(new Date().getMonth()+1)+"月"+new Date().getDate()+"日",
                // Excel文件的名称
                filename: "员工信息" + new Date().getFullYear()+"年"+(new Date().getMonth()+1)+"月"+new Date().getDate()+"日"+".xls",
                bootstrap: false
            });

        } else if ($("input[name='ids']:checked").length == 0) {
            if (confirm("您没有选择到任何数据，确认导出吗？")){
                $("#exceltable").table2excel({            //exceltable为存放数据的table
                    // 不被导出的表格行的CSS class类
                    exclude: ".noExl",
                    // 导出的Excel文档的名称
                    // name: "表格-" + new Date().getTime(),
                    // Excel文件的名称
                    filename: "项目信息" + new Date().getFullYear() + "年" + (new Date().getMonth() + 1) + "月" + new Date().getDate() + "日" + ".xls",
                    bootstrap: false
                });
            }
        } else{
            $("#exceltable").table2excel({            //exceltable为存放数据的table
                // 不被导出的表格行的CSS class类
                exclude: ".noExl",
                // 导出的Excel文档的名称
                name: "员工信息" + new Date().getFullYear()+"年"+(new Date().getMonth()+1)+"月"+new Date().getDate()+"日",
                // Excel文件的名称
                filename: "员工信息" + new Date().getFullYear()+"年"+(new Date().getMonth()+1)+"月"+new Date().getDate()+"日"+".xls",
                bootstrap: false
            });
        }
    }

    function checkSearch() {
        var regExp = new RegExp("[`~!！：。、‘’''“”？=【】《》——@#$%^&*()+<>?:\"{},.\\/;'[\\]]");//非法字符校验
        var username = $('.userName').val().trim();
        var personNo = $('.personNo').val().trim();
        var postManage = $('.postManageId').val().trim();
        if (username!=""){
            if (regExp.test(username)){
                alert("输入内容包括非法字符，请重新输入");
                $('.userName').focus();
            }
        }
        if (personNo!=""){
            if (regExp.test(personNo)){
                alert("输入内容包括非法字符，请重新输入");
                $('.personNo').focus();
            }
        }
        if (postManage!=""){
            if (regExp.test(postManage)){
                alert("输入内容包括非法字符，请重新输入");
                $('.postManage').focus();
            }
        }

    }

</script>
    <style type="text/css">
        .ss_table tr td {
            white-space:nowrap;
            overflow:hidden;
            text-overflow:ellipsis;
        }
    </style>
</head>
<body>
<!--数据展示区域外框，用于周边留空padding不可删除-->
<div class="pd-zy20">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagesl">
        <tr>
            <td width="46%">当前位置：员工信息管理</td>
        </tr>
    </table>
    <div class="cx-bt">
        <!--功能按钮-->
        <div class="btn-bt">
            <ul>
                <li class="search_bt"><a href="javaScript:;" onclick="Export()">导出</a> <a href="#" onclick="add()">新增</a></li>
            </ul></div><!--end-->
        <!--搜索-->
        <div class="search1">
            <form action="<%=request.getContextPath()%>/tUser/toUserInfo.do" id="myForm" method="post">
            <ul>
                <li>姓名：</li>
                <li><input onblur="checkSearch()" name="userName" type="text" style="width:120px;" class="userName" value="${userNameValue}"></li>
                <li>工号：</li>
                <li><input onblur="checkSearch()"  name="personNo" type="text" style="width:120px;" class="personNo" value="${personNoValue}"></li>
                <li>部门：</li>
                <li>
                    <select name="dept" id="dept" style="width: 100px" class="deptId">
                        <option value="">全部</option>
                        <c:forEach items="${deptList}" var="dept">
                            <option value="${dept.id}">${dept.deptName}</option>
                        </c:forEach>
                    </select>
                </li>
                <li>岗位：</li>
                <li><input onblur="checkSearch()" name="postName" id="postName" type="text" style="width:120px;" class="postManageId" value="${postManageIdValue}"></li>
                <li>当前状态：</li>
                <li><select name="userStatus" id="userStatus" style="width: 100px" class="userStatusS">
                    <option value="">全部</option>
                    <option value="1">在职</option>
                    <option value="0">离职</option>
<%--                    <c:forEach items="${userStatusList}" var="userStatusList">--%>
<%--                        <option value="${userStatusList}">${userStatusList}</option>--%>
<%--                    </c:forEach>--%>
                </select></li>
                <input id="page" name="page" type="hidden" value="1">
                <li class="search_bt"><a id="btn" href="#">查 询</a></li>
            </ul>
            </form>
        </div>
    </div>


    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:8px; table-layout: fixed" id="exceltable">

        <tr>
<%--            <th width="30" class="noExl"> <span id="checkAll" style="cursor:pointer;">全选</span></th> <input type="hidden" id="isAllExport" value="" />--%>
            <th width="40">序号</th>
            <th width="80">工号</th>
            <th width="120">姓名</th>
            <th width="120">部门</th>
            <th width="170">岗位</th>
            <th width="70">性别</th>
            <th width="200">毕业院校</th>
            <th width="120">学历</th>
            <th width="120">当前状态</th>
            <th width="120">联系方式</th>
            <th width="120" class="noExl">操作</th>


        <c:forEach items="${result.data}" var="user" varStatus="resultStatus">
            <tr class="noExl">
<%--                <td class="noExl"><input id="checked-${v.count}" name="ids" type="checkbox" onclick="isSelected(this)"/></td>--%>
                <td>${resultStatus.count+(result.page-1)*result.limit}</td>
                <td title="${user.personNo}">${user.personNo}</td>
                <td>${user.userName}</td>
                <td>${user.deptName}</td>
                <td title="${user.postName}">${user.postName}</td>
                <td>${user.sexStr}</td>
                <td>${user.school}</td>
                <td>${user.highLevel}</td>
                <td>${user.userStatusStr}</td>
                <td>${user.telephone}</td>
                <td class="cz-a noExl">
                    <a class="noExl" href="javaScript:;" onclick="modify('${user.id}')">
                        <i class="fa fa-edit pd-r06"></i>修改
                    </a>
                    <a onclick="del('${user.id}')" href="javaScript:;">
                        <i class="fa fa-close pd-r06"></i>删除
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="fy">
        <tr>
            <td height="40" align="right" id="fenye">当前第<span>${result.page}<span/>页 共 <span>${totalPage}</span> 页 <%--class="active"--%>
<%--                <c:forEach var="s" begin="1" end="${allUser.totalpage}" varStatus="v">--%>
<%--                    <a href="javaScript:;" onclick="pageToGo(${s})">${s}</a>&nbsp;--%>
<%--                </c:forEach>--%>
                <a style="width: auto" href="javaScript:;" onclick="pageToGo(1)">首页</a>
                <a style="width: auto" href="javaScript:;" onclick="pageToGo('${page-1}')">上一页</a>
                <a style="width: auto" href="javaScript:;" onclick="pageToGo('${page+1}')">下一页</a>
                <a style="width: auto" href="javaScript:;" onclick="pageToGo('${totalPage}')">尾页</a>
            <td/>
            <td width="40" align="center"><input name="textfield8" type="text" class="inp_w4" id="go" onblur="checkPage()"/></td>
            <td width="35" align="left"><a id="goto" href="#" style="display: inline-block;text-indent:-5px;padding-bottom: 5px" onclick="goto()"><img src="<%=request.getContextPath()%>/frame/images/bt_go.gif" width="26" height="24"/></a></td>
        </tr>
    </table>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ss_table" style="margin-top:8px;" id="exceltableHidden" hidden>
    <tr>
        <th width="30" class="noExl"></th>
        <th width="40">序号</th>
        <th width="40">工号</th>
        <th width="120">姓名</th>
        <th width="120">部门</th>
        <th width="120">岗位</th>
        <th width="120">性别</th>
        <th width="200">毕业院校</th>
        <th width="120">学历</th>
        <th width="120">当前状态</th>
        <th width="120">联系方式</th>
        <th width="120" class="noExl">操作</th>


        <c:forEach items="${allUserNoPage}" var="all" varStatus="v">
    <tr>
        <td class="noExl"></td>
        <td id="id">${v.count+(allUser.page-1)*allUser.limit}</td>
        <td>${all.personNo}</td>
        <td>${all.userName}</td>
        <td>${all.deptName}</td>
        <td>${all.postName}</td>
        <c:if test="${all.sex==1}">
            <td>男</td>
        </c:if>
        <c:if test="${all.sex==2}">
            <td>女</td>
        </c:if>
        <td>${all.school}</td>

        <%--<td>${all.highLevel}</td>--%>
        <c:if test="${all.highLevel==5}">
            <td>高中</td>
        </c:if>
        <c:if test="${all.highLevel==6}">
            <td>大专</td>
        </c:if>
        <c:if test="${all.highLevel==7}">
            <td>本科</td>
        </c:if>
        <c:if test="${all.highLevel==8}">
            <td>研究生</td>
        </c:if>
        <c:if test="${all.highLevel==9}">
            <td>其他</td>
        </c:if>

        <c:if test="${all.userStatus==1}">
            <td>在职</td>
        </c:if>
        <c:if test="${all.userStatus==2}">
            <td>离职</td>
        </c:if>
        <td>${all.telephone}</td>

    </tr>
    </c:forEach>
</table>

<div id="editDlg"></div>

<script type="text/javascript">
    var trs = document.getElementsByTagName("tr");
    for(var i=1;i<trs.length;i++) {
        if (i % 2 == 0) {
            trs[i].style.backgroundColor = "";
        } else {
            trs[i].style.backgroundColor = "#edf6fe";
        }
    }
</script>

<script>
    /**
     *新增
     */
    function add() {
        $("#editDlg").dialog({
            title: "新增",
            bgiframe: true,
            width: 1000,
            height: 600,
            href: '<%=request.getContextPath()%>/tUser/editUser.do',
            modal: true,
            cache: false,
            closed: true,
            maximizable: false
        }).dialog('open')
    }

    function modify(id) {
        $("#editDlg").dialog({
            title: "修改",
            bgiframe: true,
            width: 1000,
            height: 600,
            href: '<%=request.getContextPath()%>/tUser/editUser.do?id='+id,
            modal: true,
            cache: false,
            closed: true,
            maximizable: false
        }).dialog('open')
    }

    function pageToGo(page) {
        var totalPage=Number('${totalPage}');
        if (page>totalPage){
            return false;
        }
        if (page<1){
            return false;
        }
        $("#page").val(page);
        $("#myForm").submit();
    }
</script>

</body>
</html>
