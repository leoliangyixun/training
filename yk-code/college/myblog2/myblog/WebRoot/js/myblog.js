var blog_id=null;
var blog_comment_id=null;
var mood_id=null;
var mood_comment_id=null;
var MESSAGE_FOR_USER=0;
var MESSAGE_FOR_OTHER=1;
var MESSAGE_DELETE_FROM_AJAX=0;
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
function sendBlogCommentRequest(blog_id)//ֻ�ܽ��ܴ��ݹ��������Ͳ���
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	this.blog_id=blog_id;
    	xmlhttp.onreadystatechange=getBlogComment;
    	var url="AjaxBlogComment?blog_id="+blog_id+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function getBlogComment()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("blog_comment"+blog_id).innerHTML=xmlhttp.responseText;
		}	
	}
}
function showBlogCommentReplyTextarea(blog_comment_id,blog_id,mark)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	this.blog_comment_id=blog_comment_id;
    	xmlhttp.onreadystatechange=getBlogCommentReplyTextarea;
    	var url="tools/blog_comment_reply_textarea.jsp?blog_comment_id="+blog_comment_id+"&blog_id="+blog_id+"&mark="+mark+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getBlogCommentReplyTextarea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("blog_comment_reply_textarea"+blog_comment_id).innerHTML=xmlhttp.responseText;				
		}	
	}
}

function showMessageTextarea(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	this.message_id=message_id;
    	xmlhttp.onreadystatechange=getMessageTextarea;
    	
    	var url="tools/message_reply_textarea.jsp?message_id="+message_id+"&page="+currpage+"&step="+currstep+"&message_link_mark="+message_link_mark;
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getMessageTextarea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("message_reply_textarea"+message_id).innerHTML=xmlhttp.responseText;				
		}	
	}
}

function showMessageReplyTextarea(message_id,currpage,currstep,message_link_mark)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	this.message_id=message_id;
    	xmlhttp.onreadystatechange=getMessageReplyTextarea;
    	
    	var url="tools/message_reply_textarea.jsp?message_id="+message_id+"&page="+currpage+"&step="+currstep+"&message_link_mark="+message_link_mark;
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getMessageReplyTextarea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("message_reply_textarea"+message_id).innerHTML=xmlhttp.responseText;				
		}	
	}
}
function addBlogClass(blogcomment_id,blog_id,mark)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{			
    	var url="AddBlogClass?blog_class="+encodeURI(encodeURI(document.getElementById("option_value").value))+"&blog_class_mark=0";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
     }
}

function addOption()
{
	var option=document.createElement("option");//����һ��<option>Ԫ��
	option.setAttribute("value", document.getElementById("option_value").value);//��ȡ�ı��������ֵ��
	var option_data=document.createTextNode(document.getElementById("option_value").value);//����һ���ı��ڵ㡣
	document.getElementById("blog_class").appendChild(option).appendChild(option_data);//��<option>Ԫ����ӵ�ָ��idԪ�ص�ĩβ�������������ı��ڵ���ӵ���Ӧ��<option>Ԫ�ص�ĩβ��
	this.addBlogClass();
}

function showText()
{
	document.getElementById("create_class").innerHTML="<div><input type='text' id='option_value'/><input type='button' value='���' onClick='addOption()'/></div>";
}
function showMoreBlogClassItems()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
	 	xmlhttp.onreadystatechange=getMoreBlogClassItems;
	 	//var url="AjaxShowMoreBlogClassItems";
	 	var url="tools/show_more_blogclass_items.jsp";
	 	xmlhttp.open("GET",url,true);
	 	xmlhttp.send(null);
     }
}
function getMoreBlogClassItems()
{
	if(xmlhttp.readyState==4)
   	{
			if(xmlhttp.status==200 || xmlhttp.status==0)
			{
				document.getElementById("more_blogclass_items").innerHTML=xmlhttp.responseText;
			}	
   	}
}
/*
function checkAlbumName()
{
	var album_name=document.albumForm.album_name.value;
	//var album_name=document.getElementById("album_name").value;
	if(album_name=="޹�²�")
	{
		//document.getElementById("exsistalbum_name").firstChild.data="������Ѿ�����!";
		document.getElementById("exsistalbum_name").innerHTML="������Ѿ�����!";
	}
	else{
		document.getElementById("exsistalbum_name").innerHTML="";
	}
}
*/
function sendCheckAlbumNameRequest()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	 	var album_name=document.albumForm.album_name.value;
    	 	if(album_name!="")
    	 	{
	    	 	xmlhttp.onreadystatechange=getCheckAlbumName;
	    	 	var url="AjaxCheckAlbumName?album_name="+encodeURI(encodeURI(album_name))+"";
	    	 	xmlhttp.open("GET",url,true);
	    	 	xmlhttp.send(null);
     		}
    	 	else{
    	 		document.getElementById("exsist_album_name").innerHTML="<a style='color:red'>*�����������</a>";
    	 	}
         }
}
function getCheckAlbumName()
{
	if(xmlhttp.readyState==4)
   	{
			if(xmlhttp.status==200 || xmlhttp.status==0)
			{
				document.getElementById("exsist_album_name").innerHTML=xmlhttp.responseText;
			}	
   	}
}

function sendCheckBlogClassRequest()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	 var blog_class=document.blogclassForm.blog_class.value;
    	 if(blog_class!="")
    	 {
	    	 xmlhttp.onreadystatechange=getCheckBlogClass;
	    	 var url="AjaxCheckBlogClass?blog_class="+encodeURI(encodeURI(blog_class))+"";
	    	 xmlhttp.open("GET",url,true);
	    	 xmlhttp.send(null);
     	 }
    	 else{
    	 	document.getElementById("exsist_blog_class").innerHTML="<a style='color:red'>*���������������</a>";
    	 }
    }
}
function getCheckBlogClass()
{
	if(xmlhttp.readyState==4)
   	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("exsist_blog_class").innerHTML=xmlhttp.responseText;
			var value=document.getElementById("ajax_exsist_blog_class").firstChild.data;
			if(value!="*����������Ѿ�����!")
			{
				document.blogclassForm.action="AddBlogClass";
				document.blogclassForm.submit();
			}
		}	
   	}
}
function sendCheckUsernameRequest()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	 var username=document.Form.username.value;
    	 if(username!="")
    	 {
	    	 xmlhttp.onreadystatechange=getCheckUsername;
	    	 var url="../AjaxCheckUsername?username="+encodeURI(encodeURI(username))+"";
	    	 xmlhttp.open("GET",url,true);
	    	 xmlhttp.send(null);
     	 }
    	 else{
    	 	document.getElementById("username").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*���������û���</a>";
    	 }
    }
}
function getCheckUsername()
{
	if(xmlhttp.readyState==4)
   	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("username").innerHTML=xmlhttp.responseText;	
		}	
   	}
}
function checkLogin()
{
	document.getElementById("fail").innerHTML="<a style='color:red'>�û������������!!!</a>";
}
function checkRegist()
{
	var qualified_sex=false;
	var qualified_address=false;
	if(!document.Form.sex[0].checked && !document.Form.sex[1].checked)
	{
		document.getElementById("sex").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*��û��ѡ���Ա�</a>";
		qualified_sex=false;
	}else{
		document.getElementById("sex").innerHTML="";
		qualified_sex=true;
	}
	if(document.Form.address.value=="")
	{
		document.getElementById("address").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*��û����д���ڵ�</a>";
		qualified_address=false;
	}else{
		document.getElementById("address").innerHTML="";
		qualified_address=true;
	}
	if(qualified_sex==true && qualified_address==true)
	{
		document.Form.action="UserRegist";
		document.Form.submit();
	}
}
function checkNum(num)
{
	if(num!=document.Form.num.value)
	{
		document.getElementById("num").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*��֤�����</a>";
	}else{
		document.getElementById("num").innerHTML="";
	}
}
function checkPassword()
{
	if(document.Form.confirmpassword.value!=document.Form.password.value)
	{
		document.getElementById("confirmpassword").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*����������벻һ��</a>";
	}else{
		document.getElementById("confirmpassword").innerHTML="";
	}
}
function checkMail()
{
	if(document.registForm.mail.value=="")
	{
		document.getElementById("mail").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*����д����</a>";
	}else{
		document.getElementById("mail").innerHTML="";
	}
}
function checkAlbumSelected()
{
	var album_name=document.photouploadForm.album_name.value;
	//var album_name=document.getElementById("albumselected");
	if(album_name!=null && album_name=="default")
	{
		document.getElementById("albumselected").innerHTML="<a style='color:red'>*��û��ѡ�����</a>";
	}
	else{
		var pic1=document.getElementById("pic1");
		var pic2=document.getElementById("pic2");
		var pic3=document.getElementById("pic3");
		if(pic1.value=="" && pic2.value=="" && pic3.value=="")
		{
			document.getElementById("photoupload").innerHTML="<a style='color:red'>*���������ϴ�һ����Ƭ<a>";
		}else{
			document.photouploadForm.action="UploadPhoto";
			document.photouploadForm.submit();
		}
	}
}
function login()
{
	document.loginForm.action="LoginCheck";
	document.loginForm.submit();
}
function reset()
{
	document.loginForm.reset();
}
function clearSexText()
{
	if(document.Form.sex[0].checked || document.Form.sex[1].checked)
	{
		document.getElementById("sex").innerHTML="";
	}
}
function clearAddressText()
{
	if(document.Form.address.value!="")
	{
		document.getElementById("address").innerHTML="";
	}
}
function clearUsernameText()
{
	document.getElementById("username").innerHTML="";
}
function clearAlbumSelectedText()
{
	document.getElementById("albumselected").innerHTML="";
}
function submitReply()
{
	document.replyForm.action="AddBlogCommentReply";
	document.replyForm.submit();
}
function addDraftBlog()
{
	document.addblogForm.action="AddDraftBlog";
	document.addblogForm.submit();
}
function addPrivateBlog()
{
	document.addblogForm.action="AddPrivateBlog";
	document.addblogForm.submit();
}
function addMessaage()
{
	
}
function alterBlogState(blog_id)
{
	window.location.href="AlterBlogState?blog_id="+blog_id;
}

function sendUserMessageRequest(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getUserMessage;
    	var url="me_message.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function getUserMessage()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("message").innerHTML=xmlhttp.responseText;
		}	
	}
}
function sendOtherMessageRequest(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getOtherMessage;
    	var url="other_message.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function getOtherMessage()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("message").innerHTML=xmlhttp.responseText;
		}	
	}
}
function sendAjaxMessageMark(ajax_message_mark,currpage,currstep)
{
	if(ajax_message_mark==MESSAGE_FOR_USER)
	{
		sendUserMessageRequest(currpage,currstep);
	}
	if(ajax_message_mark==MESSAGE_FOR_OTHER)
	{
		sendOtherMessageRequest(currpage,currstep);
	}
}
/********************************************Ajax���Դ���******************************************************/
function findMyMessage(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getMessage;
    	var url="ajax_my_message.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}

function findMyLeaveMessage(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getMessage;
    	var url="ajax_my_leave_message.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function manageMessageBox(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{	
    	xmlhttp.onreadystatechange=getMessage;
    	var url="ajax_message_list.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function getMessage()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("message").innerHTML=xmlhttp.responseText;
		}	
	}
}
function deleteBlogBox(blog_id,blog_mark)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		

    }
}
function deleteMessageBox(message_id,currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("����������֧��Ajax!!!");
     }
     else{		
    	var url="DeleteMessage?message_id="+message_id+"&message_mark="+MESSAGE_DELETE_FROM_AJAX+"&page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
      
    }
}

/*
function sendAjaxMessageRequest(ajax_message_mark)
{
	
}
*/
/**************************************************************************************************************/