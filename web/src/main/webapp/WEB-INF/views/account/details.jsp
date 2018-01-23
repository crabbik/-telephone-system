<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="account" value="${requestScope.account}" />
<table>
    <tr>
        <td>ID</td>
        <td><c:out value="${account.id}" /></td>
    </tr>
    <tr>
        <td>Почта</td>
        <td><c:out value="${account.email}" /></td>
    </tr>
    <tr>
        <td>Пароль</td>
        <td><c:out value="${account.password}" /></td>
    </tr>
        <tr>
        <td>Дата создания</td>
        <td><c:out value="${account.created}" /></td>
    </tr>
        <tr>
        <td>Дата изменения</td>
        <td><c:out value="${account.modified}" /></td>
    </tr>
</table>