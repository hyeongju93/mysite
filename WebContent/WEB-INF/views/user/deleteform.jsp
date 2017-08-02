<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<title>방명록</title>
</head>
<body>
<div id="container">

	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	
	<c:import url="/WEB-INF/views/includes/navigataion.jsp"></c:import>

	<div id="wrapper">
		<div id="content">
			<div id="guestbook">
				
					<form method="post" action="/mysite/gs?a=deleteform">
						<input type="hidden" name="id" value=${requestScope.num}>
						<%--27라인 고침  --%>
						<table>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="pass"></td>
								<td><input type="submit" value="확인"></td>
								<td><a href="list.jsp"></a>메인으로돌아가기</td>
							</tr>
						</table>
					</form>

				
			</div>
			<!-- /user -->
		</div>
		<!-- /content -->
	</div>
	<!-- /wrapper -->

	<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

</div>
</body>
<!-- /container -->
</html>