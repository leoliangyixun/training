<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div style="margin-left:7px; border:#E97B56 solid 1px;">
	<form action="AlterBlogInfo" method="post">
	  <table width="570" border="0">
	    <tr>
	      <td width="100">博客名称：</td>
	      <td>
	      <input name="blog_name" type="text"  size="45" id="text_border"/></td>
	    </tr>
	    <tr>
	      <td>博客LOGO：</td>
	      <td><textarea name="blog_logo" cols="55" rows="5" id="text_border"></textarea></td>
	    </tr>
	    <tr>
	      <td>&nbsp;</td>
	      <td><input type="submit" name="button" id="button" value="修改" />
	      <input type="reset" name="button2" id="button2" value="取消" /></td>
	    </tr>
	  </table>
	</form>
</div>