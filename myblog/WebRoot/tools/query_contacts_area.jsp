<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<form name="contactsQueryForm" method="post" >
  <table width="490" border="0">
    <tr>
      <td width="100">姓名关键字：</td>
      <td width="200"><input name="name" type="text" id="text_border" size="30" /></td>
      <td width="190"><input type="button" name="button" id="button" value="查询"  onclick="queryContacts(document.contactsQueryForm.name.value)"/></td>
    </tr>
  </table>
</form>
