<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul style="list-style: none; line-height: 28px;">
	<li><a href="/">Home</a></li>
	<li><a href="/Operator">Operators List</a></li>
	<li><a href="/account">Accounts List</a></li>


<sec:authorize access="!isAnonymous()">
		<li><a style="color:red" href="/execute_logout">logout</a></li>
	</sec:authorize>
</ul>