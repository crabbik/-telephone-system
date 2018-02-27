<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit tariffItem</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="/tariffItem"
		modelAttribute="tariffItemForm">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="phoneServiceId" type="text" disabled="${readonly}" />
				<form:errors path="phoneServiceId" cssClass="red-text" />
				<label for="width">phoneServiceId</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="cost" type="text" disabled="${readonly}" />
				<form:errors path="cost" cssClass="red-text" />
				<label for="width">cost</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="deleted" type="text" disabled="${readonly}" />
				<form:errors path="deleted" cssClass="red-text" />
				<label for="width">deleted</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="tariffId" type="text" disabled="${readonly}" />
				<form:errors path="tariffId" cssClass="red-text" />
				<label for="width">tariffId</label>
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
				<a class="btn waves-effect waves-light right" href="/tariffItem">к
					списку<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>