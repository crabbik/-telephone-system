<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit phoneNumber</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="/phoneNumber"
		modelAttribute="phoneNumberForm">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="accountId" type="text" disabled="${readonly}" />
				<form:errors path="accountId" cssClass="red-text" />
				<label for="width">accountId</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="number" type="text" disabled="${readonly}" />
				<form:errors path="number" cssClass="red-text" />
				<label for="width">number</label>
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
				<a class="btn waves-effect waves-light right" href="/phoneNumber">к
					списку<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>