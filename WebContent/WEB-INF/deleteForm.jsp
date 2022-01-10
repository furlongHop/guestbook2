<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	//int no = (int)request.getAttribute("num");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 루트(/)부터 시작하는 절대 경로 -->
	<form action="/guestbook2/gbc" method="get">
		비밀번호 <input type="password" name="pass" value="">
		<button type="submit">확인</button>
		
		<input type="hidden" name="action" value="delete">
		<input type="hidden" name="no" value="<%=request.getParameter("no")%>">
	</form>
	<!-- a태그를 이용한 인터넷 주소(http://localhost:8088는 생략 가능) -->
	<a href="/guestbook2/gbc?action=addList">메인으로 돌아가기</a>

</body>
</html>