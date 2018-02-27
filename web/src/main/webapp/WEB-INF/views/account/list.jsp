<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib prefix="mylocal"
	uri="https://journaldev.com/jsp/tlds/mylocal"%>

<spring:url value="/account" var="pageurl" />

<h4>List of Accounts</h4>
<table class="striped">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="email">Email</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="password">Password</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">
					<mylocal:message code="created"></mylocal:message>
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="modified">
					<mylocal:message code="modified"></mylocal:message>
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}"
					column="details.lastName">LastName</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}"
					column="details.firstName">FirstName</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="account" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${account.id}" /></td>
				<td><c:out value="${account.email}" /></td>
				<td><c:out value="${account.password}" /></td>
				<td><c:out value="${account.created}" /></td>
				<td><c:out value="${account.modified}" /></td>
				<td><c:out value="${account.details.lastName}" /></td>
				<td><c:out value="${account.details.firstName}" /></td>
				<td class="right"><a class="btn-floating"
					href="/account/${account.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="/account/${account.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/account/${account.id}/delete"><i class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/account/add"><i
	class="material-icons">add</i></a>
