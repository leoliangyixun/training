var blog_id=null;
var blog_comment_id=null;
var mood_id=null;
var mood_comment_id=null;
var message_id=null;
var request_id=null;
var response_id=null;
var MESSAGE_FOR_USER=0;
var MESSAGE_FOR_OTHER=1;
var MESSAGE_DELETE_FROM_AJAX=0;
var index=null;
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
function sendBlogCommentRequest(blog_id)//只能接受传递过来的整型参数
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
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
        alert("你的浏览器不支持Ajax!!!");
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

function showMoodCommentTextarea(mood_id,currpage,currstep)
{
	
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	this.mood_id=mood_id;
    	xmlhttp.onreadystatechange=getMoodCommentTextarea;
    	var url="tools/mood_comment_textarea.jsp?mood_id="+mood_id+"&page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getMoodCommentTextarea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("mood_comment_textarea"+mood_id).innerHTML=xmlhttp.responseText;				
		}	
	}
}

function showMoodCommentReplyTextarea(mood_comment_id,currpage,currstep)
{
	
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	this.mood_comment_id=mood_comment_id;
    	xmlhttp.onreadystatechange=getMoodCommentReplyTextarea;
    	var url="tools/mood_comment_reply_textarea.jsp?mood_comment_id="+mood_comment_id+"&page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getMoodCommentReplyTextarea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("mood_comment_reply_textarea"+mood_comment_id).innerHTML=xmlhttp.responseText;				
		}	
	}
}
function showMessageReplyTextarea(message_id,currpage,currstep,message_link_mark)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
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
function showUserRequestTextarea(response_id,currpage,currstep)
{
	
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	this.response_id=response_id;
    	xmlhttp.onreadystatechange=getUserRequestTextarea;
    	var url="tools/user_request_textarea.jsp?response_id="+response_id+"&page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    
    }
}
function getUserRequestTextarea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("request_textarea"+response_id).innerHTML=xmlhttp.responseText;
		}	
	}
}
function showUserResponseReplyTextarea(request_id,currpage,currstep)
{
	
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	this.request_id=request_id;
    	xmlhttp.onreadystatechange=getUserResponseReplyTextarea;
    	var url="tools/user_response_textarea.jsp?request_id="+request_id+"&page="+currpage+"&step="+currstep+"";
    	
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    
    }
}
function getUserResponseReplyTextarea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("response_textarea"+request_id).innerHTML=xmlhttp.responseText;
		}	
	}
}
function addBlogClass(blogcomment_id,blog_id,mark)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{			
    	var url="AddBlogClass?blog_class="+encodeURI(encodeURI(document.getElementById("option_value").value))+"&blog_class_mark=0";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
     }
}

function addOption()
{
	var option=document.createElement("option");//创建一个<option>元素
	option.setAttribute("value", document.getElementById("option_value").value);//获取文本框输入的值。
	var option_data=document.createTextNode(document.getElementById("option_value").value);//创建一个文本节点。
	document.getElementById("blog_class").appendChild(option).appendChild(option_data);//将<option>元素添加到指定id元素的末尾，并将创建的文本节点添加到相应的<option>元素的末尾。
	this.addBlogClass();
}

function showText()
{
	document.getElementById("create_class").innerHTML="<div><input type='text' id='option_value'/><input type='button' value='添加' onClick='addOption()'/></div>";
}
function showMoreBlogClassItems()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
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
	if(album_name=="薰衣草")
	{
		//document.getElementById("exsistalbum_name").firstChild.data="该相册已经存在!";
		document.getElementById("exsistalbum_name").innerHTML="该相册已经存在!";
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
        alert("你的浏览器不支持Ajax!!!");
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
    	 		document.getElementById("exsist_album_name").innerHTML="<a style='color:red'>*请输入相册名</a>";
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
        alert("你的浏览器不支持Ajax!!!");
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
    	 	document.getElementById("exsist_blog_class").innerHTML="<a style='color:red'>*请输入文章类别名</a>";
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
			if(value!="*该文章类别已经存在!")
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
        alert("你的浏览器不支持Ajax!!!");
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
    	 	document.getElementById("username").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*请先输入用户名</a>";
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
	document.getElementById("fail").innerHTML="<a style='color:red'>用户名或密码错误!!!</a>";
}
function clearLoginText()
{
	document.getElementById("fail").innerHTML="";
}
function checkRegist()
{
	var qualified_sex=false;
	var qualified_address=false;
	if(!document.Form.sex[0].checked && !document.Form.sex[1].checked)
	{
		document.getElementById("sex").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*你没有选择性别</a>";
		qualified_sex=false;
	}else{
		document.getElementById("sex").innerHTML="";
		qualified_sex=true;
	}
	if(document.Form.address.value=="")
	{
		document.getElementById("address").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*你没有填写所在地</a>";
		qualified_address=false;
	}else{
		document.getElementById("address").innerHTML="";
		qualified_address=true;
	}
	if(qualified_sex==true && qualified_address==true)
	{
		document.Form.action="../UserRegist";
		document.Form.submit();
	}
}
function checkNum(num)
{
	if(num!=document.Form.num.value)
	{
		document.getElementById("num").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*验证码错误</a>";
	}else{
		document.getElementById("num").innerHTML="";
	}
}
function checkPassword()
{
	if(document.Form.confirmpassword.value!=document.Form.password.value)
	{
		document.getElementById("confirmpassword").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*你输入的密码不一致</a>";
	}else{
		document.getElementById("confirmpassword").innerHTML="";
	}
}
function checkMail()
{
	if(document.registForm.mail.value=="")
	{
		document.getElementById("mail").innerHTML="<a style='color:red;font-size:14px;margin-left:10px'>*请填写邮箱</a>";
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
		document.getElementById("albumselected").innerHTML="<a style='color:red'>*你没有选择相册</a>";
	}
	else{
		var pic1=document.getElementById("pic1");
		var pic2=document.getElementById("pic2");
		var pic3=document.getElementById("pic3");
		if(pic1.value=="" && pic2.value=="" && pic3.value=="")
		{
			document.getElementById("photoupload").innerHTML="<a style='color:red'>*请你至少上传一张照片<a>";
		}else{
			document.photouploadForm.action="UploadPhoto";
			document.photouploadForm.submit();
		}
	}
}
function login()
{
	document.loginForm.action="../LoginCheck";
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
function alterBlogState(blog_id,type)
{
	window.location.href="AlterBlogState?blog_id="+blog_id+"&type="+type+"";
}

function sendUserMessageRequest(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
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
        alert("你的浏览器不支持Ajax!!!");
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
/********************************************Ajax测试代码******************************************************/
function findMyMessage(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
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
        alert("你的浏览器不支持Ajax!!!");
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
        alert("你的浏览器不支持Ajax!!!");
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
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		

    }
}
function deleteMessageBox(message_id,currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	var url="DeleteMessage?message_id="+message_id+"&message_mark="+MESSAGE_DELETE_FROM_AJAX+"&page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function showFriendImage(username)
{
	if(username!="default" )
	{
		document.getElementById("friend_image").innerHTML="<img src='upload/相册/"+username+"/image/me.jpg' width='80' height='80'/>";
		//document.getElementById("friend_image").innerHTML="<img src='upload/image.jpg' width='80' height='80'/>";
	}else{
		document.getElementById("friend_image").innerHTML="<img src='upload/default.jpg' width='80' height='80'/>";
	}
}
function getMyBlog(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{	
    	xmlhttp.onreadystatechange=getDynamic;
    	var url="tools/my_blog.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function getMyMood(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getDynamic;
    	var url="tools/my_mood.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function getFriendsMood(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getDynamic;
    	var url="tools/my_friends_mood.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function getMyMessage(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getDynamic;
    	var url="tools/my_message.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function getMyAlbum(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getDynamic;
    	var url="tools/my_album.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
}
function getDynamic()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("my_dynamic").innerHTML=xmlhttp.responseText;
		}	
	}
}
function showFriendDetails(index,currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	this.index=index;
    	xmlhttp.onreadystatechange=getFriendDetails;
    	var url="tools/details.jsp?index="+index+"&page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getFriendDetails()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("details"+index).innerHTML=xmlhttp.responseText;
		}	
	}
}
function showContacts(index,currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getContacts;
    	var url="tools/my_contacts.jsp?index="+index+"&page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getContacts()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("contacts_list_area").innerHTML=xmlhttp.responseText;
			document.getElementById("contacts_function_area").innerHTML="";
			//document.getElementById("contacts_query_area").innerHTML="";
			document.getElementById("contacts_alter_area").innerHTML="";
		}	
	}
}
function showQueryContacts(name,currpage,currstep)//name的值来自于text文本域。
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getQueryContactsResult;
    	var url="AjaxQueryContacts?name="+encodeURI(encodeURI(name))+"&page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
   
}
function getQueryContactsResult()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("contacts_list_area").innerHTML=xmlhttp.responseText;
			document.getElementById("contacts_alter_area").innerHTML="";
		}	
	}
}
function showContactsAddArea()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getContactsAddArea;
    	var url="tools/add_contacts_area.jsp";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getContactsAddArea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("contacts_function_area").innerHTML=xmlhttp.responseText;
			//document.getElementById("contacts_query_area").innerHTML="";
			document.getElementById("contacts_alter_area").innerHTML="";
		}	
	}
}
function showContactsClassAddArea()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getContactsClassAddArea;
    	var url="tools/add_contacts_class_area.jsp";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getContactsClassAddArea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("contacts_function_area").innerHTML=xmlhttp.responseText;
			//document.getElementById("contacts_query_area").innerHTML="";
			document.getElementById("contacts_alter_area").innerHTML="";
		}	
	}
}
function showContactsQueryArea()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getContactsQueryArea;
    	var url="tools/query_contacts_area.jsp";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getContactsQueryArea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("contacts_function_area").innerHTML=xmlhttp.responseText;
			//document.getElementById("contacts_query_area").innerHTML="";
			document.getElementById("contacts_alter_area").innerHTML="";
		}	
	}
}
function queryContacts(name)
{
	
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getContactsQueryResult;
    	var url="AjaxQueryContacts?name="+encodeURI(encodeURI(name))+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getContactsQueryResult()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("contacts_list_area").innerHTML=xmlhttp.responseText;
			document.getElementById("contacts_alter_area").innerHTML="";
		}	
	}
}
function showContactsAlterArea(contacts_id)
{

	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getContactsAlterArea;
    	var url="tools/alter_contacts_area.jsp?contacts_id="+contacts_id+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getContactsAlterArea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("contacts_alter_area").innerHTML=xmlhttp.responseText;
			document.getElementById("contacts_function_area").innerHTML="";
			//document.getElementById("contacts_query_area").innerHTML="";
			
		}	
	}
}
function showBlogInfoAlterArea()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getUserAlterArea;
    	var url="tools/alter_blog_info_area.jsp";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function showUserImageAlterArea()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getUserAlterArea;
    	var url="tools/alter_user_image_area.jsp";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}

function showUserInfoAlterArea()
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getUserAlterArea;
    	var url="tools/alter_user_info_area.jsp";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getUserAlterArea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("user_info_setting").innerHTML=xmlhttp.responseText;	
		}	
	}
}
function showUserFriendsArea(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getUserFriendsArea;
    	var url="tools/user_friends_area.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function showUserRequestArea(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getUserFriendsArea;
    	var url="tools/user_request_area.jsp?page="+currpage+"&step="+currstep+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function showUserResponseArea(currpage,currstep)
{
	 xmlhttp=createXMLHttpRequest();
	 if(xmlhttp==null)
     {
        alert("你的浏览器不支持Ajax!!!");
     }
     else{		
    	xmlhttp.onreadystatechange=getUserFriendsArea;
    	var url="tools/user_response_area.jsp?page="+currpage+"&step="+currpage+"";
    	xmlhttp.open("GET",url,true);
    	xmlhttp.send(null);
    }
}
function getUserFriendsArea()
{
	if(xmlhttp.readyState==4)
	{
		if(xmlhttp.status==200 || xmlhttp.status==0)
		{
			document.getElementById("user_friends_info_area").innerHTML=xmlhttp.responseText;	
		}	
	}
}
/*
function sendAjaxMessageRequest(ajax_message_mark)
{
	
}
*/
/**************************************************************************************************************/