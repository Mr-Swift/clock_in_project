/*************************************************
	code by bicashy
*************************************************/
function check()
{ 
    var flag = true; 
    if(document.all["depart"].value=="")
	{
		document.getElementById("chk_depart").innerHTML = "<font color='red'>��ѡ���û����ڲ���</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_depart").innerHTML = "";
	}
    if(document.getElementById("landname").value=="")
	{
		document.getElementById("chk_landname").innerHTML = "<font color='red'>�������½����</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_landname").innerHTML = "";
	}
	if(document.getElementById("landname").value=="")
	{
		document.getElementById("chk_landname").innerHTML = "<font color='red'>�������½����</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_landname").innerHTML = "";
	}
	if(document.getElementById("pass").value=="")
	{
		document.getElementById("chk_pass").innerHTML = "<font color='red'>�������¼����</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_pass").innerHTML = "";
	}
	if(document.getElementById("newpass").value=="")
	{
		document.getElementById("chk_newpass").innerHTML = "<font color='red'>������ȷ������</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_newpass").innerHTML = "";
	}
	if(document.all["pass"].value != document.all["newpass"].value)
    {
    	document.all["chk_pass"].innerHTML = "<font color='red'><font color='red'>�����������벻һ�£�����������</font>";
    	flag = false;
    }
	if(document.getElementById("chinesename").value=="")
	{
		document.getElementById("chk_chinesename").innerHTML = "<font color='red'>����������</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_chinesename").innerHTML = "";
	}
	if(document.getElementById("sfz").value=="")
	{
		document.getElementById("chk_sfz").innerHTML = "<font color='red'>���������֤��</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_sfz").innerHTML = "";
	}
	if(document.getElementById("phone").value=="")
	{
		document.getElementById("chk_phone").innerHTML = "<font color='red'>������绰����</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_phone").innerHTML = "";
	}
	if(document.getElementById("zhiwu").value=="")
	{
		document.getElementById("chk_zhiwu").innerHTML = "<font color='red'>������ְλ</font>";
		flag = false;
	}else
	{
		document.getElementById("chk_zhiwu").innerHTML = "";
	}
	return flag;
}
