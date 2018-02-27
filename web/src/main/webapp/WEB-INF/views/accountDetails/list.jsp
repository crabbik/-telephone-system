<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<c:set var="pageListHolder" value="${accountDetails}" scope="session" />
<spring:url value="/accountDetails" var="pageurl" />

<h4>List of Account Details</h4>
<table class="striped">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="lastName">lastName</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="firstName">firstName</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="modified">Modified</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="accountDetails" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${accountDetails.id}" /></td>
				<td><c:out value="${accountDetails.lastName}" /></td>
				<td><c:out value="${accountDetails.firstName}" /></td>
				<td><c:out value="${accountDetails.created}" /></td>
				<td><c:out value="${accountDetails.modified}" /></td>
				<td class="right"><a class="btn-floating"
					href="/accountDetails/${accountDetails.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="/accountDetails/${accountDetails.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/accountDetails/${accountDetails.id}/delete"><i
						class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/accountDetails/add"><i
	class="material-icons">add</i></a>
