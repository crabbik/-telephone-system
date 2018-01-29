<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<nav>
		<div class="nav-wrapper container">
			<sec:authorize access="!isAnonymous()">
				<a class="right" href="/execute_logout"><i
					class="material-icons">arrow_forward</i></a>
			</sec:authorize>
			<ul id="nav-mobile" class="left hide-on-med-and-down">
				<li><a href="/">Home</a></li>
				<li><a href="/account">Accounts List</a></li>
				<li><a href="/operator">Operators List</a></li>
			</ul>
		</div>
	</nav>
</header>