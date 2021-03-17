/*************************************************
	code by bicashy
*************************************************/
function check()
{ 
    var flag = true; 
    if(document.all["depart"].value=="")
	{
		document.getElementById("chk_depart").innerHTML = "<font color='red'>请选择用户所在部门</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_depart").innerHTML = "";
	}
    if(document.getElementById("landname").value=="")
	{
		document.getElementById("chk_landname").innerHTML = "<font color='red'>请输入登陆名称</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_landname").innerHTML = "";
	}
	if(document.getElementById("landname").value=="")
	{
		document.getElementById("chk_landname").innerHTML = "<font color='red'>请输入登陆名称</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_landname").innerHTML = "";
	}
	if(document.getElementById("pass").value=="")
	{
		document.getElementById("chk_pass").innerHTML = "<font color='red'>请输入登录密码</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_pass").innerHTML = "";
	}
	if(document.getElementById("newpass").value=="")
	{
		document.getElementById("chk_newpass").innerHTML = "<font color='red'>请输入确认密码</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_newpass").innerHTML = "";
	}
	if(document.all["pass"].value != document.all["newpass"].value)
    {
    	document.all["chk_pass"].innerHTML = "<font color='red'><font color='red'>两次密码输入不一致，请重新输入</font>";
    	flag = false;
    }
	if(document.getElementById("chinesename").value=="")
	{
		document.getElementById("chk_chinesename").innerHTML = "<font color='red'>请输入姓名</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_chinesename").innerHTML = "";
	}
	if(document.getElementById("sfz").value=="")
	{
		document.getElementById("chk_sfz").innerHTML = "<font color='red'>请输入身份证号</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_sfz").innerHTML = "";
	}
	if(document.getElementById("phone").value=="")
	{
		document.getElementById("chk_phone").innerHTML = "<font color='red'>请输入电话号码</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_phone").innerHTML = "";
	}
	if(document.getElementById("zhiwu").value=="")
	{
		document.getElementById("chk_zhiwu").innerHTML = "<font color='red'>请输入职位</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_zhiwu").innerHTML = "";
	}
	return flag;
}
