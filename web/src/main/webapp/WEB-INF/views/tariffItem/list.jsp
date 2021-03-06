<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<c:set var="pageListHolder" value="${tariffItem}" scope="session" />
<spring:url value="/tariffItem" var="pageurl" />

<h4>List of tariffItem</h4>
<table class="striped">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}"
					column="phoneServiceId">phoneServiceId</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="cost">cost</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="deleted">deleted</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="tariffId">tariffId</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="modified">Modified</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="tariffItem" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${tariffItem.id}" /></td>
				<td><c:out value="${tariffItem.phoneServiceId}" /></td>
				<td><c:out value="${tariffItem.cost}" /></td>
				<td><c:out value="${tariffItem.deleted}" /></td>
				<td><c:out value="${tariffItem.cost}" /></td>
				<td><c:out value="${tariffItem.tariffId}" /></td>
				<td><c:out value="${tariffItem.modified}" /></td>
				<td class="right"><a class="btn-floating"
					href="/tariffItem/${tariffItem.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="/tariffItem/${tariffItem.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/tariffItem/${tariffItem.id}/delete"><i
						class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/tariffItem/add"><i
	class="material-icons">add</i></a>
