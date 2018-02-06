<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit account details</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="/accountDetails"
		modelAttribute="accountDetailsForm">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="lastName" type="text" disabled="${readonly}" />
				<form:errors path="lastName" cssClass="red-text" />
				<label for="width">Имя</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="firstName" type="text" disabled="${readonly}" />
				<form:errors path="firstName" cssClass="red-text" />
				<label for="width">Фамилия</label>
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
				<a class="btn waves-effect waves-light right" href="/accountDetails">к
					списку<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>