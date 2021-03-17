function selectItem(id,tableid){
    
    var obj = document.getElementById(id) ;
  
	var thisColor = obj.style.backgroundColor ;
	if(thisColor==""){
		o= document.getElementById(tableid);   
  for(i=0;i<o.rows.length;i++)   
  {      
  o.rows[i].style.backgroundColor='';  
  }
		obj.style.backgroundColor = "#99FFFF"  ;	
	}else{
		obj.style.backgroundColor = '' ;
	}


}  // end funtion 


function AddSelItem(srcTable,desTable,preFix,CkPreFix,funName){
	
	var srcTableObj = document.getElementById(srcTable) ;
	var rownumber = srcTableObj.rows.length ;	
	for(var i = 1 ; i<rownumber ; i++){
		var id = srcTableObj.rows[i].id  ;				
		
		var TrObj = srcTableObj.rows[i] ;
		var thisColor = TrObj.style.backgroundColor ;	
		
		if( thisColor != "" 
		){		
				
			var cellsObj = srcTableObj.rows[i].cells ;
			var cellsLen = cellsObj.length ;
			var rowObj = srcTableObj.rows[i] ;
			var tdsText ="" ;
			for(var j = 0 ; j < cellsLen ; j++){
				var inText  = rowObj.cells[j].innerHTML ;
				tdsText = tdsText + inText + "|" ; 
			} // end for,cells
			var newTRId = preFix + id   ;

			if(!IsInTheTable(desTable,newTRId)){
				InsertRowInTable(desTable,tdsText,newTRId,CkPreFix,funName) ;
				}
			
		} // end if

		
	} // end for
}

function IsInTheTable(tableName,id){
	var TableObj = document.getElementById(tableName) ;
	var rownumber = TableObj.rows.length ;
	for(var i = 0 ; i<rownumber ; i++){
		var TRId = TableObj.rows[i].id ;
			 if(TRId==id){		
				return true ;
			 } // end if
	} // end for
	return false ;
} // end function  

function InsertRowInTable(tableName, TRTag,TRId,CkPreFix,funName){
    
    
   var TableObj = document.getElementById(tableName) ;
   
   document.getElementById(tableName+'Changed').value=1;
    
	var newRow = TableObj.insertRow(1);
	newRow.id = TRId ;	
		
	newRow.onclick = function selectItem(){var obj = document.getElementById(TRId) ; 
		var thisColor = obj.style.backgroundColor ;
		if(thisColor==""){
			obj.style.backgroundColor = "#99FFFF"  ;	
		}else{
			obj.style.backgroundColor = '' ;
		}
	};
	

	var arrTds = new Array() ;
	arrTds = TRTag.split("|") ;
   var arrLen = arrTds.length ;
  
   for(var i = 0 ; i< arrLen ; i++)  
	newRow.insertCell(i);  	

   	newRow.cells[0].innerHTML = "<input type=checkbox id="+CkPreFix+TRId+">";

   		 
   for(var j = 1 ; j< arrLen ; j++){
	newRow.cells[j].innerHTML = arrTds[j-1];	
   } // end for  

 
}	// end function 


function DeleteSelRowsInTable(tableName){
	var TableObj = document.getElementById(tableName) ;
	var rownumber = TableObj.rows.length ;
	
	var delTRids = "" ;
	var isChanged = false;
    for(var i = 0 ; i<rownumber ; i++){			
			var TrObj = TableObj.rows[i] ;
			if(TrObj && TrObj.style){
			var thisColor = TrObj.style.backgroundColor ;
	
			 if(thisColor != "" )	
			 {
				isChanged = true;
				TableObj.deleteRow(i);	
				i--;
			 }
			}
			 
	} // end for
	if(isChanged)
		document.getElementById(tableName+'Changed').value=1;
	
} // end function 

function GetSelTRIndex(tableName){

	var TableObj = document.getElementById(tableName) ;	
	var rownumber = TableObj.rows.length ;
	var sel = "" ;
    for(var i = 0 ; i<rownumber ; i++){
			var id = TableObj.rows[i].id  ;
			var TrObj = document.getElementById(id) ;
			var thisColor = TrObj.style.backgroundColor ;
			 if(thisColor != "" )		sel = i;
			
	} // end for
	
	return sel;

} // end function 


function Exchange(src, dec){

		var iDecId =  dec.id;
		var iDecHtml =  dec.innerHTML;

		dec.id = src.id ;			
		dec.innerHTML = src.innerHTML ;		
		dec.style.backgroundColor = "#99FFFF"  ;
		dec.onclick = function select(){	
			var obj = document.getElementById(dec.id);
			var thisColor = obj.style.backgroundColor ;
			if(thisColor=="")		obj.style.backgroundColor = "#99FFFF"  ;	
			else	obj.style.backgroundColor = '' ;			
		};						
			
		src.id = iDecId ;		
		src.innerHTML = iDecHtml ;
		src.style.backgroundColor = ""  ;
		src.onclick = function select(){
			var obj = document.getElementById(src.id);
			var thisColor = obj.style.backgroundColor ;
			if(thisColor=="")		obj.style.backgroundColor = "#99FFFF"  ;	
			else	obj.style.backgroundColor = '' ;			
		};	

}

function Move(tableName,dirc){
    
	var TableObj = document.getElementById(tableName) ;
	
	var rownumber = TableObj.rows.length ;
	var RowIndex = GetSelTRIndex(tableName) ;	
	if(  RowIndex == 0) return;
	var dec;	
	switch(dirc)
	{
		case "up":
		if(  RowIndex == 1) return ;		
		dec = RowIndex-1;
		break;
		
		case "down":
		if(  RowIndex == rownumber-1) return ;
		dec = RowIndex+1;			
		break;
		
		case "first":
		if(  RowIndex == 1) return ;
		dec = 1;	
		break;
		
		case "last":
		if(  RowIndex == rownumber-1) return ;
		dec = rownumber-1;	
		break;
	}

	document.getElementById(tableName+'Changed').value=1;
	var myString = new String(window.navigator.appName.toUpperCase());	
	if(myString.indexOf("EXPLORER") > -1){
		TableObj.moveRow(RowIndex,dec) ;	
	}else{		
		Exchange(TableObj.rows[RowIndex], TableObj.rows[dec]);
	}
}	//	end function 
function Movenum(tableName,dirc,num){
    if(!validUnsignedInt(num))
	{
		alert("只能输入数字！");
		return false;
	}
	var TableObj = document.getElementById(tableName) ;
	var rownumber = TableObj.rows.length ;
	var RowIndex = GetSelTRIndex(tableName) ;	
	if(  RowIndex == 0) return;
	
	var dec;	
	
	switch(dirc)
	{
		case "up":
		if(  RowIndex == 1) return ;		
		if( RowIndex>=num)
		{
		dec = RowIndex-num;
		}else{
		dec =1;
		}
		break;
		
		case "down":
		if(  RowIndex == rownumber-1) return ;
		if( RowIndex<=rownumber-1-num)
		{
			
		dec =  parseInt(RowIndex)+parseInt(num);
		}else{
		dec = rownumber-1;
		}
					
		break;
		
		case "first":
		if(  RowIndex == 1) return ;
		dec = 1;	
		break;
		
		case "last":
		if(  RowIndex == rownumber-1) return ;
		dec = rownumber-1;	
		break;
	}


	document.getElementById(tableName+'Changed').value=1;
	
	var myString = new String(window.navigator.appName.toUpperCase());	
	if(myString.indexOf("EXPLORER") > -1){
		TableObj.moveRow(RowIndex,dec) ;	
		
	}else{		
	
		Exchange(TableObj.rows[RowIndex], TableObj.rows[dec]);
	}

}	//	end function 
 function validUnsignedInt (str) {
                    var patrn = /^\d+$/;
                   return validAll(patrn, str);
                    }
                   function validAll(patrn, str) {
                    if (patrn.test(str)) {
                      return true;
                     } else {
                    return false;
                      }
                    }
