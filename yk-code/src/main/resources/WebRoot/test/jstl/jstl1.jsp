<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="customer" class="com.yangkai.bean.jstl.Customer" scope="application"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'jstl1.jsp' starting page</title>
  </head>
  
  <body>
    <!--name=<c:out value="${name}" default="fuck"></c:out><br>-->
    <!---------------------------------ͨ��EL���ʽֱ�����---------------------->
    <!--something=${something }<br>-->
    <!---------------------------------ͨ��JSTL��ǩ���-------------------------->
    <!--something=<c:out value="${something }"></c:out><br>--><!--escapeXmlĬ��Ϊtrue-->
    <!--something=<c:out value="${something }" escapeXml="true"></c:out><br>-->
    <!--something=<c:out value="${something }" escapeXml="false"></c:out><br>-->
    <!--  
    customer1.name=${Customer1["name"] }<br>
    customer1.age=${Customer1["age"] }<br>
    customer1.sex=${Customer1["sex"] }<br>
    customer2.name=${Customer2["name"] }<br>
    customer2.age=${Customer2["age"] }<br>
    customer2.sex=${Customer2["sex"] }<br>
    -->
    <!--  
    <c:choose>
    <c:when test="${a gt b }">
    a����b
    </c:when>
    <c:when test="${a eq b }">
    a����b
    </c:when>
    <c:otherwise>
    aС��b
    </c:otherwise>
    </c:choose>
    -->
  <!--  
    <c:choose>
    <c:when test="${!empty list }">
    <c:forEach var="s" items="${list }" varStatus="step">
    /******************<c:out value="${step.getIndex() }"></c:out>����********************/
    <c:out value="${step.index }"></c:out>: name=${s.name } age=${s.age } sex=${s.sex }<br>
    </c:forEach>
    </c:when>
    <c:otherwise>listΪ��</c:otherwise>
    </c:choose>
   -->
   <!--  
   <c:forEach begin="1" end="10" var="i" step="1">
   ${i }
   </c:forEach>
   -->
   <!--  
    <h3>���µ�JSTL���ʽ��JavaBean�޹�</h3><br>
   <c:set var="name" value="����޹"></c:set>
   <c:out value="${name}"></c:out><br>
   <c:set var="password">
   lavender19880905
   </c:set>
   <c:out value="${password}"></c:out><br>
   <h3>ͨ�����µ�JSTL���ʽJ����avaBean����</h3><br> 
   <c:set value="���紨" target="${customer}" property="name"></c:set>
   <c:set value="lavender" target="${customer}" property="password"></c:set>
   customer.name=<c:out value="${customer['name']}"></c:out><br>
   customer.password=<c:out value="${customer['password']}"></c:out><br>
   
   <c:set value="����" target="${customer}" property="name"></c:set>
   <c:set value="5201314" target="${customer}" property="password"></c:set>
   customer.name=<c:out value="${customer['name']}"></c:out><br>
   customer.password=<c:out value="${customer['password']}"></c:out><br>
   <h3>��������</h3>
   <c:set var="page" value="page" scope="page"></c:set>
   <c:set var="request" value="request" scope="request"></c:set>
   <c:set var="session" value="session" scope="session"></c:set>
   <c:set var="application" value="application" scope="application"></c:set>
   <c:out value="${page}"></c:out><br>
   <c:out value="${request}"></c:out><br>
   <c:out value="${session}"></c:out><br>
   <c:out value="${application}"></c:out><br>
   -->
   <c:if test="${param.username ne null && param.username ne ''}"  var="result">
   <c:out value="${result}"></c:out>
   </c:if>
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
  </body>
</html>
