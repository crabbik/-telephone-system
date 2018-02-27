<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<footer class="page-footer  teal">
	<div class="container">
		<div class="row">
			<div class="col l3 s12 ">
				<p class="grey-text text-lighten-4">
					Current User is:
					<sec:authentication property="name" />
				</p>
			</div>
			<div class="col l3  s12">
				<ul>
					<li><a class="grey-text text-lighten-4 right" target="_blank"
						href="https://raw.githubusercontent.com/crabbik/mobile_system/master/docs/db/mobile_system.png">Image
							DB Model</a></li>
				</ul>
			</div>
			<div class="col l3 s12">
				<ul>
					<li><a class="grey-text text-lighten-4 right" target="_blank"
						href="https://raw.githubusercontent.com/crabbik/mobile_system/master/docs/db/mobile_system.png">Image
							DB Model</a></li>
				</ul>
			</div>
			<div class="col l3  s12">
				<ul>
					<li><a class="grey-text text-lighten-4 right" target="_blank"
						href="https://raw.githubusercontent.com/crabbik/mobile_system/master/docs/db/mobile_system.png">Image
							DB Model</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="footer-copyright">
		<div class="container">© 2018 Copyright Text</div>
					<p class="grey-text text-lighten-4">
					
					<sec:authentication property="authorities" var="roles" scope="page" />
					<c:forEach var="role" items="${roles}">
						<li>${role}</li>
					</c:forEach>
				</p>
	</div>
</footer>