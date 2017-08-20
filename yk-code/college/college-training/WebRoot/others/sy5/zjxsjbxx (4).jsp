<%--网页编码采用简体中文--%>
<%@page contentType="text/html;charset=GB2312"%>

<%--包含连接数据库的文件--%>
<%@include file="jdbc-xsjbxx.jsp"%>

<%
//设置中文编码方式  
request.setCharacterEncoding("gb2312");

//取得提交的姓名、学号、性别、出生日期、邮箱、血型和备注信息  
String xingming=request.getParameter("xingming");
String xuehao=request.getParameter("xuehao");
String xingbie=request.getParameter("xingbie");
String chushengriqi=request.getParameter("chushengriqi");
String youxiang=request.getParameter("youxiang");
String xuexing=request.getParameter("xuexing");
 String beizhu=request.getParameter("beizhu");
 
//取得提交的兴趣爱好信息，先保存在数组中，然后转换为用","连接的字符串	   
  String aihao[]=request.getParameterValues("aihao");
  String aihao2="";
  if (aihao!=null){
	for (int i=0;i<aihao.length;i++){
	    aihao2=aihao2+aihao[i]+",";
		}
  }
  
//取得提交的喜欢的体育运动信息，先保存在数组中，然后转换为用","连接的字符串
  String tiyu[]=request.getParameterValues("tiyuyundong");
  String tiyu2="";
  if (tiyu!=null){
	for (int i=0;i<tiyu.length;i++){
	    tiyu2=tiyu2+tiyu[i]+",";
		}
  }

//判断要增加的学号是否已经存在
 String sql = "select * from xsjbxx where xuehao='"+xuehao+"'";
 ResultSet rs = s.executeQuery(sql);
 String id =null;
 while (rs.next())
   id = rs.getString("xuehao");
 rs.close();
 
 //如果已经存在，则给出相应的提示
 if(id != null) {
  out.print("<script language='javascript'>alert('学号"+xuehao+"的信息已存在，请输入其他学号！');onclick=history.back();</script>");
 }//history对象是window对象的一个属性，它包含了最近访问过的网址列表。history.back()即回退到上一个网址。
 
 else {
  //如果不存在，进行增加操作
 String sql1="insert into xsjbxx(xingming,xuehao,xingbie,chushengriqi,youxiang,xuexing,aihao,tiyuyundong,beizhu)";
  sql1=sql1+" values ('"+xingming+"','"+xuehao+"','"+xingbie+"','"+chushengriqi+"','";
  sql1=sql1+youxiang+"','"+xuexing+"','"+aihao2+"','"+tiyu2+"','"+beizhu+"')";
 
  s.executeUpdate(sql1);
  s.close();
  c.close();

 out.print("<script language='javascript'>alert('学号"+xuehao+"的基本信息已成功增加！');window.navigate('index.htm');</script>"); 
 }//window.navigate()将网页转向到指定的网页
 %>