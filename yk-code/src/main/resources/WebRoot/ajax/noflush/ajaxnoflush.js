var xmlhttp=null;
var name=null;
var countpage=null;
var curr_page=1;
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
function send()
{
	curr_page=1;
    xmlhttp=createXMLHttpRequest();
    if(xmlhttp==null)
    {
        alert("你的浏览器不支持Ajax!!!");
    }
    else{		
    	    name=document.getElementById("nav").firstChild.value;
    		xmlhttp.onreadystatechange=show;
    		var URL="multipages.jsp?name="+encodeURI(encodeURI(name));
    		xmlhttp.open("GET",URL,true);
    		xmlhttp.send(null);
        }
}
function sendlink(currpage)
{
    xmlhttp=createXMLHttpRequest();
    if(xmlhttp==null)
    {
        alert("你的浏览器不支持Ajax!!!");
    }
    else{
    		if(currpage==countpage)
    		{
    			curr_page=countpage;
    		}
    		if(currpage==1)
    		{
    			curr_page=1;
    		}	
    	    name=document.getElementById("nav").firstChild.value;
    		xmlhttp.onreadystatechange=show;
    		var URL="multipages.jsp?name="+encodeURI(encodeURI(name))+"&page="+currpage;
    		xmlhttp.open("GET",URL,true);
    		xmlhttp.send(null);
        }
}
function show()
{	 
    if(xmlhttp.readyState==4 )
    {	
    	//alert("send success");
    	if(xmlhttp.status==200 || xmlhttp.status==0)
    	{
    		//alert("response success");
    		 var xmldoc=xmlhttp.responseXML;
    		 countpage=xmldoc.getElementsByTagName("page-record")[0].firstChild.firstChild.data;
    		 var xmldisplay=document.getElementById("xmldisplay");
    		 var display=xmldoc.getElementsByTagName("display");
    		 xmldisplay.innerHTML="";
    		 var table=document.createElement("table");
    		 //table.setAttribute("width", "600");
    		 table.setAttribute("border", "1");
    		 table.setAttribute("borderColor", "#000000");
    		 table.setAttribute("cellSpacing", "0");
    		 table.setAttribute("cellPadding", "2");
    		 table.setAttribute("id", "tab");
    		 table.insertRow(0);
    		 table.style.fontSize="12px";
    		 table.rows[0].align="center";
    		 table.rows[0].bgColor="#FF9966";
    		 table.rows[0].setAttribute("id","row");
 			 table.rows[0].insertCell(0);
 			 table.rows[0].cells[0].innerHTML="编号";
 			 table.rows[0].insertCell(1);
			 table.rows[0].cells[1].innerHTML="ISBN";
			 table.rows[0].insertCell(2);
 			 table.rows[0].cells[2].innerHTML="图书名称";
 			 table.rows[0].insertCell(3);
			 table.rows[0].cells[3].innerHTML="出版社";
			 table.rows[0].insertCell(4);
 			 table.rows[0].cells[4].innerHTML="价格";
 			 table.rows[0].insertCell(5);
			 table.rows[0].cells[5].innerHTML="库存量";
    		 for(var i=1;i<=display.length;i++)
    		 {
    			table.insertRow(i);
    			for(var j=0;j<display[i-1].childNodes.length;j++)
    			{
    				var data=display[i-1].childNodes[j].firstChild.data;
    				table.rows[i].insertCell(j);
    				table.rows[i].cells[j].innerHTML=data;
    			}	
    		}
    		xmldisplay.appendChild(table);
    		changtabborderstyle();	
    		var link=document.getElementById("link");
    		if(curr_page<2)
    		{
    			link.innerHTML="<a href=\"javascript:sendlink(curr_page+=1)\">下一页</a><a href=\"javascript:sendlink(countpage)\">尾页</a>";
    		}
    		if(curr_page>=2 && curr_page<countpage)
    	    {
    			link.innerHTML="<a href=\"javascript:sendlink(1)\">首页</a><a href=\"javascript:sendlink(curr_page-=1)\">上一页</a><a href=\"javascript:sendlink(curr_page+=1)\">下一页</a><a href=\"javascript:sendlink(countpage)\">尾页</a>";
    	    }
    		if(curr_page>=countpage)
    		{
    			link.innerHTML="<a href=\"javascript:sendlink(1)\">首页</a><a href=\"javascript:sendlink(curr_page-=1)\">上一页</a>";
    		}
    	
    	}
    	/*
    	else{
    			alert("response failed");
    		}
    		*/		
    }
}
function changtabborderstyle()
{
    var tab=document.getElementById("tab");
    //var row=document.getElementById("row");
    //row.style.backgroundColor="#FF9966";//这里应该是background-color，不应该是bgcolor。
    tab.style.borderCollapse="collapse";
}