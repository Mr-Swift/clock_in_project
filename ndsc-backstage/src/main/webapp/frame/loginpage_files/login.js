var msg100="";

var isCanLogin = true;
function login(){
		if(isCanLogin){
			isCanLogin = false;
		
			var account = $.trim($("#account").val());
			var pwd = $("#password").val();
			if(account==''||account=='账号'){
				isCanLogin = true;
				$("#msg").html("登陆账号不能为空");
		  		
		  	}else{
			  	if(pwd==''){
			  		isCanLogin = true;
			  		$("#msg").html("密码不能为空");
			  	}else{
			  		var picCode = $.trim($("#picCode").val());
			  		var picCodeType = $.trim($("#picCodeType").val());
			  			if(picCode==''){
				  			isCanLogin = true;
				  			$("#msg").html("验证码不能为空");
							
			  			}else{
			  				$.post(domainurl+'/login/valiPicCode.htm',{'picCode':picCode,'picCodeType':picCodeType},function(data){
				  				var result = JSON.parse(data);
				  				if(result.code=='0'){
				  					loginSubmit();
				  					$("#msg").html('');
				  				}else{
				  					isCanLogin = true;
//				  					loginSubmit();
				  					$("#msg").html("验证码输入错误");
				  					$("#picCode").val('');
				  					getPicCode();
				  				}
				  			});
			  			}
			  	}
		  	}
		}
	}
	function loginSubmit(){
		
		$.post(domainurl+'/login/keyPair.htm?key='+Math.random(),function(data){
			
			          var result = JSON.parse(data);
		              var modulus = result.modulus, exponent = result.exponent;
                      var publicKey = RSAUtils.getKeyPair(exponent, '', modulus);
	                  var password = encodeURI($("#password").val());
	                  password = RSAUtils.encryptedString(publicKey, password);
	                  var  account= $.trim($("#account").val());
	                  var picCodeType = $.trim($("#picCodeType").val());
	                  var picCode = "";
	                  		picCode = $.trim($("#picCode").val());
	                  $.post(domainurl+'/login/logIn.htm?picCodeType=1',{'account':account,'password':password,'picCodeType':picCodeType,'picCode':picCode},function(data){
						var result = JSON.parse(data);
						if(result.code=='0'){
							window.location.href=result.data;//"/loanMng/myAppList.htm"
						}else if(result.code=='1100'){
							alert("企业已注销或已吊销！");
							window.location.reload(true);
						}else if(result.code=='1000'){
							$("#msg").html("您输入的账号或密码有误，请重新输入");
							isCanLogin = true;
							$("#password").val('');
							$("#picCode").val('');
							$.post(domainurl+'/login/getLoginTimes.htm',function(data){
					    		var result = JSON.parse(data);
					    		if(result.code=='0'){
					    			document.getElementById("valiCode").style.display="none";
					    		}else{
					    			document.getElementById("valiCode").style.display="block";
					    			getPicCode();
					    		}
							});
						}else{
							$("#msg").html("您输入的账号或密码有误，请重新输入");
							isCanLogin = true;
		  					$("#picCode").val('');
		  					getPicCode();
						}
					});
		});  
	}

	/****获取验证码****/
	function getPicCode(){
		$("#pic").attr("src",domainurl+"/login/getPicCode.htm?picCodeType=1&qq="+Math.random());
	}
	/********验证图片验证码***********/
	function valiPicCode(obj){
		var picCode = $.trim(obj.value);
		if(picCode!='' && picCode.length==4){
			$.post(domainurl+'/login/valiPicCode.htm',{'picCode':picCode,'picCodeType':'1'},function(data){
				var result = JSON.parse(data);
				if(result.code=='0'){
				}else{
					$("#msg").html("验证码输入错误");
				}
			});
		}
	}

//
//function login(){
//	var account =$.trim($("#username").val());
//	var pwd = $("#password").val();
//	if(account==''){
//		alert("请输入用户名");
//	}else if(pwd==''){
//		alert("请输入密码");
//	}else{
//		loginSubmit();
//	}			
//		
//	}
//	function loginSubmit(){
//		var account =$.trim($("#username").val());
//		var pwd = $("#password").val();
//		$.ajax({
//			  type: "post",
//			  url: domainurl+"/login/logIn.htm",
//			  dataType: "json",
//			  data: {'account':account,'pwd':pwd},
//			  success: function(_data) {
//					if(_data.code==0){
//						 window.top.location.href=domainurl+'/login/index.htm';
//					}else {
//						alert("您输入的账号或密码有误，请重新输入");
//					}
//			  },
//			  error: function(e) {
//					alert("系统异常，请稍后再试！");
//			  }
//			});
//			/**
//	    $.post(domainurl+'/login/logIn.htm?',{'account':account,'pwd':pwd},function(data){
//			var result = JSON.parse(data);
//			if(result.code==0){
//				 window.top.location.href=domainurl+'/login/index.htm';
//			}else {
//				alert("您输入的账号或密码有误，请重新输入");
//			}
//		});*/
//	}
