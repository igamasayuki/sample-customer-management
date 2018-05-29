<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Edit</title>
</head>
<body>
	<form:errors path="customerForm.*" cssStyle="color:red"/>
	<form:form modelAttribute="customerForm" action="${pageContext.request.contextPath}/customers/edit">
		<input type="hidden" name="id" value="${param.id}">
		<dl>
			<dt>性</dt>
			<dd>
				<form:input path="firstName" />
				<form:errors path="firstName" cssStyle="color:red"/>
			</dd>
			<dt>名</dt>
			<dd>
				<form:input path="lastName" />
				<form:errors path="lastName" cssStyle="color:red"/>
			</dd>
		</dl>
		<input type="submit" value="更新" />
		<input type="submit" name="goToTop" value="戻る" />
	</form:form>
</body>
</html>