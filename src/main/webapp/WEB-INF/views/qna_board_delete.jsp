<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<html>
<head>
<title>MVC �Խ���</title>
</head>
<body>
	<form name="deleteForm" action="BoardDeleteAction?num=${num}"
		method="post">
		${num}
		<table border=1>
			<tr>
				<td><font size=2>�� ��й�ȣ : </font></td>
				<td><input name="BOARD_PASS" type="password"></td>
			</tr>
			<tr>
				<td colspan=2 align=center><a
					href="javascript:deleteForm.submit()">����</a> &nbsp;&nbsp; <a
					href="javascript:history.go(-1)">���ư���</a></td>
			</tr>
		</table>
	</form>
</body>
</html>