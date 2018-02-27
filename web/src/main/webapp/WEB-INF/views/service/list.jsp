<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<c:set var="pageListHolder" value="${service}" scope="session" />
<spring:url value="/service" var="pageurl" />

<h4>List of Accounts</h4>
<table class="striped">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="type">type</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="unit">unit</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="deleted">deleted</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="modified">Modified</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="service" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${service.id}" /></td>
				<td><c:out value="${service.type}" /></td>
				<td><c:out value="${service.unit}" /></td>
				<td><c:out value="${service.deleted}" /></td>
				<td><c:out value="${service.created}" /></td>
				<td><c:out value="${service.modified}" /></td>
				<td class="right"><a class="btn-floating"
					href="/service/${service.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="/service/${service.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/service/${service.id}/delete"><i class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/service/add"><i
	class="material-icons">add</i></a>
