<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<c:set var="pageListHolder" value="${account}" scope="session" />
<spring:url value="/account" var="pageurl" />

<h4>List of Accounts</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="id">ID</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="width">Email</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="material">Password</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="material">Created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="material">Modified</mytaglib:sort-link></th>
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
