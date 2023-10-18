<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
welcome : Member

<hr>

<%-- <c:if test="${not empty pageContext.request.userPrincipal }">
<p> is Log=In</p>
</c:if>

<c:if test="${empty pageContext.request.userPrincipal }">
<p> is Log=Out</p>
</c:if> --%>

<sec:authorize access="isAuthenticated()">
<p> is Log=In</p>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
<p> is Log=Out</p>
</sec:authorize>

<%-- USER ID : ${pageContext.request.userPrincinal.name}<br/> --%>
USER ID : <sec:authentication property="name"/><br/>

<c:url value="/logout" var="logoutUrl" />
<a href="${logoutUrl}">Log Out</a> <br />

</body>
</html>