<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form method="POST" action="/account" modelAttribute="accountForm">
	<table>
		<tr>
			<td>Почта</td>
			<td><form:input path="email" type="text" /></td>
		</tr>
		<tr>
			<td>Пароль</td>
			<td><form:input path="password" type="text" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Сохранить" /></td>
		</tr>
	</table>
</form:form>