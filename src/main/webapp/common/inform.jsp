<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<c:if test="${not empty message }">
	<div class="row">
		<div class="col">
			<div class="alert alert-success">${message}</div>
		</div>
	</div>
</c:if>
<c:if test="${not empty error }">
	<div class="row">
		<div class="col">
			<div class="alert alert-danger">${error}</div>
		</div>
	</div>
</c:if>