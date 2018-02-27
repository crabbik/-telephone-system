<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<c:set var="pageListHolder" value="${invoice}" scope="session" />
<spring:url value="/invoice" var="pageurl" />

<h4>List of Invoice</h4>
<table class="striped">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="type">type</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="quantity">quantity</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="sum">sum</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="month">month</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="year">year</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}" column="modified">Modified</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pageUrl}"
					column="phoneNumberId">Phone Number Id</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="invoice" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${invoice.id}" /></td>
				<td><c:out value="${invoice.type}" /></td>
				<td><c:out value="${invoice.quantity}" /></td>
				<td><c:out value="${invoice.sum}" /></td>
				<td><c:out value="${invoice.month}" /></td>
				<td><c:out value="${invoice.year}" /></td>
				<td><c:out value="${invoice.created}" /></td>
				<td><c:out value="${invoice.modified}" /></td>
				<td><c:out value="${invoice.phoneNumberId}" /></td>
				<td class="right"><a class="btn-floating"
					href="/invoice/${invoice.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="/invoice/${invoice.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/invoice/${invoice.id}/delete"><i class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/invoice/add"><i
	class="material-icons">add</i></a>
