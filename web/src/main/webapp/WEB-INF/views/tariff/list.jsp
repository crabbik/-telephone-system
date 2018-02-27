<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<c:set var="pageListHolder" value="${tariff}" scope="session" />
<spring:url value="/tariff" var="pageurl" />

<h4>List of tariff</h4>
<table class="striped">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="deleted">deleted</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="numberId">numberId</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="operatorId">operatorId</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="modified">Modified</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="tariff" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${tariff.id}" /></td>
				<td><c:out value="${tariff.name}" /></td>
				<td><c:out value="${tariff.deleted}" /></td>
				<td><c:out value="${tariff.numberId}" /></td>
				<td><c:out value="${tariff.operatorId}" /></td>
				<td><c:out value="${tariff.created}" /></td>
				<td><c:out value="${tariff.modified}" /></td>
				<td class="right"><a class="btn-floating"
					href="/tariff/${tariff.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="/tariff/${tariff.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/tariff/${tariff.id}/delete"><i class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/tariff/add"><i
	class="material-icons">add</i></a>
