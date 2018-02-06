<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<nav>
		<div class="nav-wrapper  teal ">
			<sec:authorize access="!isAnonymous()">
				<a class="right" href="/execute_logout"><i
					class="material-icons">arrow_forward</i></a>
			</sec:authorize>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="/">Home</a></li>
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
		</div>
	</nav>
</header>