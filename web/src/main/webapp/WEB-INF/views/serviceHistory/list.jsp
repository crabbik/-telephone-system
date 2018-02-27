<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<c:set var="pageListHolder" value="${serviceHistory}" scope="session" />
<spring:url value="/serviceHistory" var="pageurl" />

<h4>List of ServiceHistory</h4>
<table class="striped">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="quantity">quantity</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="sum">sum</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}"
					column="phoneNumberId">phoneNumberId</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}"
					column="tariffItemId">tariffItemId</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="modified">Modified</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="serviceHistory" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${serviceHistory.id}" /></td>
				<td><c:out value="${serviceHistory.quantity}" /></td>
				<td><c:out value="${serviceHistory.sum}" /></td>
				<td><c:out value="${serviceHistory.phoneNumberId}" /></td>
				<td><c:out value="${serviceHistory.tariffItemId}" /></td>
				<td><c:out value="${serviceHistory.created}" /></td>
				<td><c:out value="${serviceHistory.modified}" /></td>
				<td class="right"><a class="btn-floating"
					href="/serviceHistory/${serviceHistory.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="/serviceHistory/${serviceHistory.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/serviceHistory/${serviceHistory.id}/delete"><i
						class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/serviceHistory/add"><i
	class="material-icons">add</i></a>
