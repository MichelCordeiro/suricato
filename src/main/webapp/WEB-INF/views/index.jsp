<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/index.css'/>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
</head>
<body>
	<form:form role="form" commandName="lousa" servletRelativeAction="/lousa/nova">
	<c:forEach items="${lousas}" var="lousa">
		<input type="radio" name="endereco" value="${lousa.endereco}"/>
		<p>${lousa.nome}</p>
		<img src="<c:url value="${lousa.endereco}"/>">
	</c:forEach>
		<button type="submit">Nova lousa</button>
	</form:form>
</body>
</html>