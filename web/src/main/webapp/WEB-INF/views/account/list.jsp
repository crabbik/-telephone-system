<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="margin: 10px;">
	<h4>List of Accounts</h4>
	<table style="width: 600px" class="reference">
		<tbody>
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th>Password</th>
				<th>Created</th>
				<th>Modified</th>
			</tr>
			 <c:forEach var="account" items="${requestScope.accounts}" varStatus="loopCounter">
                <tr>
                    <td><c:out value="${account.id}" /></td>
                    <td><c:out value="${account.email}" /></td>
                    <td><c:out value="${account.password}" /></td>
                    <td><c:out value="${account.created}" /></td>
                    <td><c:out value="${account.modified}" /></td>
                    <td><a href="/account/${account.id}">Подробности</a>|<a href="/account/${account.id}/edit">изменить</a>|<a href="/account/${account.id}/delete">удалить</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/account/add">Создать новый</a>
</div>