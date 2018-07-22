<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  	<head>
  	<script type="text/javascript">
			
	var js = {
		XMLHttp:null,
		//发送请求函数
		sendRequest:function(url,responseFun,callback){
			//创建XMLHTTPRequest对象
			(function(){
				
				//根据浏览器类型创建XMLHTTPRequest对象
				if(window.XMLHttpRequest){
					js.XMLHttp = new XMLHttpRequest();
				}
				else{
					try{
						js.XMLHttp = new ActionXObject("Msxml2.XMLHTTP");
					}catch (e){
						try{
							js.XMLHttp = new ActiveXObject("Microsoft.XMLHTTP");
						} catch (e0){alert("Microsoft"+e0);}
					}
				}
				
				})();
			
			js.XMLHttp.open("POST", url, true);
			js.XMLHttp.onreadystatechange = function(responseFunction){
				if(js.XMLHttp.readyState == 4){
					if(js.XMLHttp.status == 200){
						responseFun(js.XMLHttp);
					}else{
						document.getElementById("div").innerHTML = "<font color='red'>连接服务器异常...</font>"	;
					}
				}
				else{
					//document.getElementById("div").innerHTML = "<font color='red'>数据加载中...</font>"	;	
				}
			};//指定响应函数
			js.XMLHttp.send(null);
			return js.XMLHttp;
		}
	}
	
		
		
		
		
		//响应函数
		function responseFunction(xmlhttp){
			var xmlDOM = xmlhttp.responseXML; //接受服务器返回的xml文档
			parse(xmlDOM);//解析XML文档
		}
		
		//解析XML文档
		function parse(xmlDOM){
			var person = xmlDOM.getElementsByTagName("person");
			var page = xmlDOM.getElementsByTagName("page")[0];
			var currpage = page.getElementsByTagName("currpage")[0].firstChild.data;
			var pagecount = page.getElementsByTagName("pagecount")[0].firstChild.data; 
			var prevpagehtml; 
			var nextpagehtml;
			if((currpage-0)<=1){
				prevpagehtml = "<a>上一页</a>";
			}else{
				prevpagehtml = "<a onclick='AjaxTest("+(currpage-1)+");' href='javascript:void(0);'>上一页</a>";
			}
			if((currpage-0)<(pagecount-0)){
				nextpagehtml = "<a onclick='AjaxTest("+(currpage-0+1)+");' href='javascript:void(0);'>下一页</a>";
			}else{
				nextpagehtml = "<a>下一页</a>";
			}
  			var html = "<table style='font-size: 12px; color: red'><tr><td width='80'>编号</td><td width='100'>姓名</td><td width='80'>年龄</td></tr>";
  			
  			for(var i=0;i<person.length;i++){
  					html = html+ "<tr><td>"
  							   +person[i].getElementsByTagName("pid")[0].firstChild.data+"</td><td>"
  							   +person[i].getElementsByTagName("pname")[0].firstChild.data+"</td><td>"
  							   +person[i].getElementsByTagName("age")[0].firstChild.data
  							   +"</td></tr>";
  			}
  			html = html + "<tr ><td width='50'>"+prevpagehtml+"</td><td width='100'>共"+pagecount+"页&nbsp;当前第"+currpage+"页</td><td width='80'>"+nextpagehtml+"</td></tr>";
  			html = html+"</table>";
  			document.getElementById("div").innerHTML=html;
		}
		
		//主调函数,以当前页作为参数
		function AjaxTest(currpage) {
			js.sendRequest("AjaxServlet?currpage="+currpage,responseFunction,null);
		}
		
  	</script>
  	</head>

  	<body onload="AjaxTest(1);">
    <center>
      <div id="div"> </div>
    </center>
</body>
</html>
