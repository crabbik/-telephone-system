<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="margin: 10px;">
	<h4>List of Operators</h4>
	<table style="width: 600px" class="reference">
		<tbody>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Deleted</th>
				<th>Created</th>
				<th>Modified</th>
			</tr>
			<c:forEach var="cover" items="${requestScope.operators}"
				varStatus="loopCounter">
				<tr>
					<td><c:out value="${operator.id}" /></td>
					<td><c:out value="${operator.title}" /></td>
					<td><c:out value="${operator.deleted}" /></td>
					<td><c:out value="${operator.created}" /></td>
					<td><c:out value="${operator.modified}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>