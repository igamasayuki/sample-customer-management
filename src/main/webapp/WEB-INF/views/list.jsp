<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>顧客管理システム</h1>
<div>
	<form:errors path="customerForm.*"/>
	<form:form modelAttribute="customerForm" action="${pageContext.request.contextPath}/customers/create">
		<dl>
			<dt>性</dt>
			<dd>
				<form:input path="firstName" />
				<form:errors path="firstName" />
			</dd>
			<dt>名</dt>
			<dd>
				<form:input path="lastName" />
				<form:errors path="lastName" />
			</dd>
		</dl>
		<input type="submit" value="作成" />
	</form:form>
</div>
<table>
    <tr>
        <th>ID</th>
        <th>姓</th>
        <th>名</th>
        <th colspan="2">編集</th>
    </tr>
	<c:forEach var="customer" items="${customers}">
    <tr>
        <td><c:out value="${customer.id}"/></td>
        <td><c:out value="${customer.firstName}"/></td>
        <td><c:out value="${customer.lastName}"/></td>
        <td>
            <form action="${pageContext.request.contextPath}/customers/toedit" method="get">
                <input type="submit" name="form" value="編集"/>
                <input type="hidden" name="id" value="<c:out value="${customer.id}"/>"/>
            </form>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/customers/delete" method="post">
                <input type="submit" value="削除"/>
                <input type="hidden" name="id" value="<c:out value="${customer.id}"/>"/>
            </form>
        </td>
    </tr>
	</c:forEach>
</table>
</body>
</html>