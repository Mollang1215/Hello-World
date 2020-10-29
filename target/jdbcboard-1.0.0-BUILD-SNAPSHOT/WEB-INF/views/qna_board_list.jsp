<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 게시판 리스트 -->
	<table width=50% border="0" cellpadding="0" cellspacing="0">
		<tr align="center" valign="middle">
			<td colspan="4">MVC 게시판</td>
			<td align=right><font size=2>글 개수 : ${listcount}</font></td>
		</tr>
		<tr align="center" valign="middle" bordercolor="#333333">
			<td style="font-family: Tahoma; font-size: 8pt;" width="8%"
				height="26">
				<div align="center">번호</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="50%">
				<div align="center">제목</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="14%">
				<div align="center">작성자</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="17%">
				<div align="center">날짜</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="11%">
				<div align="center">조회수</div>
			</td>
		</tr>

		<c:forEach items="${boardlist}" var="obj" varStatus="i">
			<tr align="center" valign="middle" bordercolor="#333333"
				onmouseover="this.style.backgroundColor='F8F8F8'"
				onmouseout="this.style.backgroundColor=''">
				<td height="23" style="font-family: Tahoma; font-size: 10pt;">
					${obj.Board_Num}</td>
				<td style="font-family: Tahoma; font-size: 10pt;">
					<div align="left">
						<c:choose>
							<c:when test="${obj.Board_Lev ne 0}">
								<c:forEach items="${obj.Board_Lev *2 }">&nbsp;</c:forEach>
								▶
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
						▶<a href="BoardDetailAction?num=${obj.Board_Num}">${obj.Board_Subject}</a>
					</div>
				</td>

				<td style="font-family: Tahoma; font-size: 10pt;">
					<div align="center">${obj.Board_Name}</div>
				</td>
				<td style="font-family: Tahoma; font-size: 10pt;">
					<div align="center">${obj.Board_Date}</div>
				</td>
				<td style="font-family: Tahoma; font-size: 10pt;">
					<div align="center">${obj.Board_ReadCount}</div>
				</td>
			</tr>
		</c:forEach>


		<tr align=center height=20>
			<td colspan=7 style="font-family: Tahoma; font-size: 10pt;"><c:choose>
					<c:when test="${nowpage <= 1}">
		[이전]&nbsp;
		</c:when>
					<c:otherwise>
						<a href="BoardList?page=${nowpage-1}">[이전]</a>&nbsp;
		</c:otherwise>
				</c:choose> <c:forEach begin="${startpage}" end="${endpage}" varStatus="i">
					<c:choose>
						<c:when test="${i == noepage}">
			${i}
			</c:when>
						<c:otherwise>
							<a href="BoardList?page=${i}">[${i}]</a>&nbsp;
			</c:otherwise>
					</c:choose>
				</c:forEach> <c:choose>
					<c:when test="${nowpage >= maxpage)}">
				[다음]
				</c:when>
					<c:otherwise>
						<a href="BoardList?page=${nowpage+1}">[다음]</a>
					</c:otherwise>
				</c:choose></td>
		</tr>

		<tr align="right">
			<td colspan="5"><a href="./BoardWrite.bo">[글쓰기]</a></td>
		</tr>
</body>
</html>