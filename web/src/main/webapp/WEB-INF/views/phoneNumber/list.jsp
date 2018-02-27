<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<c:set var="pageListHolder" value="${phoneNumber}" scope="session" />
<spring:url value="/phoneNumber" var="pageurl" />

<h4>List of PhoneNumber</h4>
<table class="striped">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="accountId">accountId</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="number">number</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="modified">Modified</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="tariffId">tariffId</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="invoices">invoices</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="phoneNumber" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${phoneNumber.id}" /></td>
				<td><c:out value="${phoneNumber.accountId}" /></td>
				<td><c:out value="${phoneNumber.number}" /></td>
				<td><c:out value="${phoneNumber.created}" /></td>
				<td><c:out value="${phoneNumber.modified}" /></td>
				<td><c:out value="${phoneNumber.tariffId}" /></td>
				<td><c:out value="${phoneNumber.invoices}" /></td>
				<td class="right"><a class="btn-floating"
					href="/account/${phoneNumber.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="/account/${phoneNumber.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/account/${phoneNumber.id}/delete"><i
						class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/phoneNumber/add"><i
	class="material-icons">add</i></a>
