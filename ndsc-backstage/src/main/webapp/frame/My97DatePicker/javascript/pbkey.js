  //��������Ҽ���Ctrl+N��Shift+F10��F11��F5ˢ�¡��˸�� 
function window.onhelp(){return false} //����F1���� 
function  var y=$dp.cal.date.y;
  var m=$dp.cal.date.M;
  alert(y)
  alert(m)document.onkeydown()
{ 
  if(event.keyCode==13 && event.srcElement.type!='button' && event.srcElement.type!='submit' && event.srcElement.type!='reset' && event.srcElement.type!='textarea' && event.srcElement.type!='')
     event.keyCode=9;
 //����ƶ�
  if ((window.event.altKey)&& 
      ((window.event.keyCode==37)||   //���� Alt+ ����� �� 
       (window.event.keyCode==39)))   //���� Alt+ ����� �� 
  { 
     alert("��׼��ʹ��ALT+�����ǰ���������ҳ��"); 
     event.returnValue=false; 
  } 
  if ((event.keyCode==8&&event.srcElement.type!='textarea'&&event.srcElement.type!='input'&&event.srcElement.type!='file'))
  {
	  event.keyCode=0;
  } //�����˸�ɾ����

  if (                 
      (event.keyCode==114)||(event.keyCode==116)||                 //���� F5 ˢ�¼� 
      (event.ctrlKey && event.keyCode==82)){ //Ctrl + R 
     event.keyCode=0; 
     event.returnValue=false; 
     } 
  if (event.keyCode==122){event.keyCode=0;event.returnValue=false;}  //����F11 
  if (event.ctrlKey && event.keyCode==78) event.returnValue=false;   //���� Ctrl+n 
  if (event.shiftKey && event.keyCode==121)event.returnValue=false;  //���� shift+F10 
  if (window.event.srcElement.tagName == "A" && window.event.shiftKey) 
      window.event.returnValue = false;             //���� shift ���������¿�һ��ҳ 
  if ((window.event.altKey)&&(window.event.keyCode==115))             //����Alt+F4 
  { 
      window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px"); 
      return false; 
  } 
} 


     float_init	= 1;
		function DHTML_Init(Object) { 
 	 if (navigator.userAgent.match(/Mozilla\/5\../) && float_init) { 
 	 SetObjectOffsetTop(Object, undefined);
 	} }
  	function All (ID) { 
		if (document.all)	{  return document.all[ID];   } 
		else if (document.documentElement){
     return document.getElementById (ID); } 
 	else if (document.layers)	{ return document.layers[ID]; }}
		function GetWindowOffsetTop() {
 	if (window.innerHeight)	{ return window.pageYOffset; }
		 else if (document.body)	{ return document.body.scrollTop; }} 
		function GetWindowHeight() { 
		if (window.innerHeight)	{ return window.innerHeight; } 
      else if (document.body)	{ return document.body.clientHeight; }} 
  	function GetObjectHeight(Object) {
		DHTML_Init(Object);
 	if (document.all || document.documentElement)	{
		Clip = Object.style.clip; 
     if (! Clip) { return Object.offsetHeight; }
     else	{ return GetClipElement (Clip, 'Bottom'); }} 
     else if (document.layers)	{ return Object.clip.height; }} 
 	function GetClipElement (Clip, Element) {
		Clip = Clip.substr(Clip.indexOf('(') + 1); 
     Clip = Clip.substr(0, Clip.length - 1);
		Clippers = Clip.split (" "); 
     for (i = 0; i < Clippers.length; i++) { 
     if (Clippers[i] != 'auto') 
      { Clippers[i] = Clippers[i].replace (/D/g, ""); }} 
     ClipTop = Number(Clippers[0]); 
     ClipRight = Number(Clippers[1]); 
     ClipBottom = Number(Clippers[2]);
     ClipLeft = Number(Clippers[3]); 
     if (Element == 'Top')		{ return ClipTop; } 
      else if (Element == 'Right')	{ return ClipRight; } 
     else if (Element == 'Bottom')	{ return ClipBottom; } 
     else if (Element == 'Left')	{ return ClipLeft; } 
      else				{ return undefined; }} 
     function GetObjectOffsetTop(Object) { 
     DHTML_Init(Object); 
     if (Object.offsetTop)	{ return Object.offsetTop; } 
      else if (document.layers)	{ return Object.top; }} 
     function SetObjectOffsetTop(Object, Offset) { 
     if (Object.style)	{ Object.style.top = Offset; } 
      else if (Object.top)	{ Object.top = Offset; }} 
     CenterMenu = 1;	 
     MenuBorder = 100; 
     TimeCheck  = 250;	 
     TimeUpdate = 15; 
     DivUpdate  = 15;	 
     Minimum    = 50;	 
     AddHeight  = -4; 
     function ScrollMenu() { 
     Menu		= All('persistMenu'); 
     WinTop	= GetWindowOffsetTop(); 
     WinHeight	= GetWindowHeight() + AddHeight;
     MenuTop	= GetObjectOffsetTop(Menu); 
     MenuHeight = GetObjectHeight (Menu); 
     MenuNew	= (CenterMenu) ? Math.round (WinTop + (WinHeight - MenuHeight) / 3) : WinTop + MenuBorder; 
     if (MenuNew < Minimum) 
     { MenuNew = Minimum; }
     if (MenuTop != MenuNew) { 
     if ( (MenuTop + MenuHeight) < WinTop || MenuTop > (WinTop + WinHeight) ) { 
     SetObjectOffsetTop (Menu, (MenuTop < MenuNew) ? (WinTop - MenuHeight) : (WinTop + WinHeight)); 
     } else { 
     Add = (MenuTop < MenuNew) ? 1 : -1; 
     SetObjectOffsetTop (Menu, MenuTop + Math.round((MenuNew - MenuTop) / DivUpdate) + Add); 
     } 
     } 
         window.setTimeout('ScrollMenu()', (GetObjectOffsetTop(Menu) == MenuNew) ? TimeCheck : TimeUpdate); 
     } 

function killErrors() { 
return true; 
} 
window.onerror = killErrors; 
