<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="suricato" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Nova retrospectiva</title>
		<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-impromptu.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-ui.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/retrospectiva/cria.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/header.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/nova.css'/>">
	</head>
	<body>
		<%@include file="../header.jsp" %>
		<form:form role="form" commandName="retrospectiva" servletRelativeAction="/retrospectiva/cria" id="formLousa">
			<input type="hidden" name="criador.nome" value="${pageContext.request.userPrincipal.name}"/>
			<input name="nome" type="text" placeholder="Nome" id="nomeRetrospectiva">
			<suricato:calendario id="dataFim" placeholder="Data fim"/>
			
			<select name="time.id" id="time">
				<option value="" checked>Nenhum time</option>
				<c:forEach var="time" items="${usuario.times}">
					<option value="${time.id}">${time.nome}</option>
				</c:forEach>
			</select>
<!-- 			Pública: <input type="checkbox" name="publica" value="true"> -->
			<select name="lousa.id"  id="idLousa" onchange="mostraLousa()">
				<c:forEach items="${lousas}" var="lousa" varStatus="status">
					<option value="${lousa.id}" id="atividade_${status.index}" data-url="${lousa.endereco}">${lousa.nome}</option>
				</c:forEach>
			</select>
			<img id="imagem" src=""/>
			<button id="botao" type="submit">Criar</button>
		</form:form>
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/nova.js'/>"></script>
</html>