<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Include Core Tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="common.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Projects</title>
</head>
<body>
	<div class="body-div">
	<jsp:include page="chronos-header.jsp" />
	<c:choose>
    <c:when test="${not empty display}">    
    	<div class="side-nav" style="background-color:white;">    		
    	</div>   
    </c:when>    
    <c:otherwise>
   		<jsp:include page="chronos-sidenav.jsp" />
    </c:otherwise>
	</c:choose>
	
	
	
	<div class="main-div" ng-app="chronos">
		<jsp:include page="${content}"/>
	</div>
	<jsp:include page="chronos-footer.jsp" />
	</div>
</body>
</html>