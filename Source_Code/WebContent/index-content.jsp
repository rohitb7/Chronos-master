<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
<c:when test="${error }">
		<div class="alert alert-warning alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Login Failed!</strong>${message }
		</div>

</c:when>
<c:when test="${empty sessionScope.user }">
	<c:redirect url="login"></c:redirect>
</c:when>
<c:otherwise>
			<%-- <jsp:include page="message.jsp"/> --%>
			<a href="/chronos/projects?action=view">Projects</a> 
</c:otherwise>
</c:choose>