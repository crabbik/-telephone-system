<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit account</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="/account" modelAttribute="accountForm">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="email" type="text" disabled="${readonly}" />
                <form:errors path="email" cssClass="red-text" />
                <label for="width">Почта</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="password" type="text" disabled="${readonly}" />
                <form:errors path="password" cssClass="red-text" />
                <label for="width">Пароль</label>
            </div>
        </div>
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right"type="submit">сохранить</button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right" href="/account">к списку<i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>