<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mylocal"
	uri="https://journaldev.com/jsp/tlds/mylocal"%>
<header>
	<nav>
		<div class="nav-wrapper  teal ">


			<sec:authorize access="isAnonymous()">
				<a href="/" class="brand-logo rigth"></a>
			</sec:authorize>
			<sec:authorize access="!(isAnonymous())">
				<a href="/home" class="brand-logo left"></a>
			</sec:authorize>
			<ul class="right">
				<sec:authorize access="isAnonymous()">
					<li><a href="/login"><mylocal:message code="sign_in"></mylocal:message></a></li>
					<li><a href="/registration"><mylocal:message code="sign_up"></mylocal:message></a></li>
				</sec:authorize>
				<sec:authorize access="!(isAnonymous())">
					<sec:authorize access="hasRole('ROLE_admin')">
						<ul id="nav-mobile" class="right hide-on-med-and-down">

							<li><a href="/account">Accounts List</a></li>
							<li><a href="/operator">Operators</a></li>
							<li><a href="/accountDetails">Account Details</a></li>
							<li><a href="/invoice">Invoice</a></li>
							<li><a href="/phoneNumber">Phone Numbers</a></li>
							<li><a href="/service">Services List</a></li>
							<li><a href="/serviceHistory">Service History</a></li>
							<li><a href="/tariff">Tariffs </a></li>
							<li><a href="/tariffItem">Tariff Items</a></li>
							<li><a href="/role">Role</a></li>
						</ul>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_user')">

						<li><a href="/invoice">Invoice</a></li>
						<li><a href="/serviceHistory">Service History</a></li>
						<li><a href="/accountDetails">Account Details</a></li>
					</sec:authorize>
					<li><a class="right" href="/execute_logout"><i
							class="material-icons">arrow_forward</i></a></li>
				</sec:authorize>

			</ul>
		</div>

	</nav>

	<div style="position: absolute; top: 20; left: 20">
		<a
			href="${requestScope['javax.servlet.forward.request_uri']}?language=en">en</a>|<a
			href="${requestScope['javax.servlet.forward.request_uri']}?language=ru">ru</a>
	</div>
</header>