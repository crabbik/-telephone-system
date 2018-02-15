<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<c:set var="pageListHolder" value="${operator}" scope="session" />
<spring:url value="/operator" var="pageurl" />

<h4>List of Operators</h4>
<table class="striped">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="id">ID</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="title">Title</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="deleted">Deleted</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="created">Created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="modified">Modified</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="operator" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${operator.id}" /></td>
				<td><c:out value="${operator.title}" /></td>
				<td><c:out value="${operator.deleted}" /></td>
				<td><c:out value="${operator.created}" /></td>
				<td><c:out value="${operator.modified}" /></td>
				<td class="right"><a class="btn-floating"
					href="/operator/${operator.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="/operator/${operator.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/operator/${operator.id}/delete"><i
						class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/operator/add"><i
	class="material-icons">add</i></a>
