 var xmlhttp=null;
 function createXMLHttpRequest()
    {
    	var objxmlhttp=null;
    	var xmlhttpTypes=["Microsoft.XMLHTTP","Msxml2.XMLHTTP.3.0","Msxml2.XMLHTTP.4.0","Msxml2.XMLHTTP.5.0","Msxml2.XMLHTTP.6.0"];
    	if(window.ActiveXObject)
    		{
    		for(var i=0;i<xmlhttpTypes.length;i++)
    		{
    			try{
    					objxmlhttp=new ActiveXObject(xmlhttpTypes[i]);
    					break;
    			   }catch(e){continue;}
    		}
    	}
    	else if(window.XMLHttpRequest)
    	{
    		objxmlhttp=new XMLHttpRequest();
    	}
    	return objxmlhttp;
    }
	
    function sendHttpRequest(userclass)
    {
		xmlhttp=createXMLHttpRequest();
    	if(xmlhttp==null)
    	{
    		alert("你的浏览器不支持Ajax!!!");
    	}
    	else{
				//alert("xmlhttp创建成功");
    			//var userclass=document.classForm.userclass.value;
				xmlhttp.onreadystatechange=getHttpResponse;
    			var url="ajaxfriendslistresult.jsp?userclass="+encodeURI(encodeURI(userclass));
				//var url="ajaxfriendslist.jsp?userclass="+encodeURI(encodeURI(userclass));
    			
    			xmlhttp.open("GET",url,true);
    			xmlhttp.send(null);
    			//window.location.href="ajaxfriendslistresult.jsp";
    		}
    }
	
	/*
    function getHttpResponse()
    {
		//alert(xmlhttp.readyState);
		
    	if(xmlhttp.readyState==4 )
    	{
			//alert("数据发送成功");
			if(xmlhttp.status==200 || xmlhttp.status==0)
			{
				document.getElementById("content").innerHTML=xmlhttp.responseText;
				document.getElementById("content2").innerHTML=xmlhttp.responseText;
				//alert("恭喜你，Ajax成功");
			}
			else{
    				alert("很遗憾，Ajax失败");
    			}
    	}		
    }
	*/