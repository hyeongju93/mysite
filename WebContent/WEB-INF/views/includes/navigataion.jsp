<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="navigation">
	<ul>
		<li><a href="/mysite/main">메인화면</a></li>
		<li><a href="/mysite/gs">방명록</a></li>
		<c:if test="${!(empty sessionScope.authUser) }">
			<li><a href="/mysite/bs">게시판</a></li>
		</c:if>
	</ul>
</div>
<!-- /navigation -->