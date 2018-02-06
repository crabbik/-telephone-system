<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit invoice</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="/invoice"
		modelAttribute="invoiceForm">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="type" type="text" disabled="${readonly}" />
				<form:errors path="type" cssClass="red-text" />
				<label for="width">type</label>
			</div>
		</div>
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
				<form:input path="month" type="text" disabled="${readonly}" />
				<form:errors path="month" cssClass="red-text" />
				<label for="width">month</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="year" type="text" disabled="${readonly}" />
				<form:errors path="year" cssClass="red-text" />
				<label for="width">year</label>
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
				<a class="btn waves-effect waves-light right" href="/invoice">к
					списку<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>