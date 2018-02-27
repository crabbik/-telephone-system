<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit ServiceHistory</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="/serviceHistory"
		modelAttribute="serviceHistoryForm">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="quantity" type="text" disabled="${readonly}" />
				<form:errors path="quantity" cssClass="red-text" />
				<label for="width">quantity</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="sum" type="text" disabled="${readonly}" />
				<form:errors path="sum" cssClass="red-text" />
				<label for="width">sum</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="phoneNumberId" type="text" disabled="${readonly}" />
				<form:errors path="phoneNumberId" cssClass="red-text" />
				<label for="width">phoneNumberId</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="tariffItemId" type="text" disabled="${readonly}" />
				<form:errors path="tariffItemId" cssClass="red-text" />
				<label for="width">tariffItemId</label>
			</div>
		</div>
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">сохранить</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="/serviceHistory">к
					списку<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>