<%@ page language="java" contentType="text/html; charset=EUC-KR"%>

<html>
<head>
<title>MVC �Խ���</title>
</head>

<body>
	<!-- �Խ��� ���� -->
	<table cellpadding="0" cellspacing="0">
		<tr align="center" valign="middle">
			<td colspan="5">MVC �Խ���</td>
		</tr>

		<tr>
			<td style="font-family: ����; font-size: 12" height="16">
				<div align="center">�� ��&nbsp;&nbsp;</div>
			</td>

			<td style="font-family: ����; font-size: 12">${board.board_Subject}
			</td>
		</tr>

		<tr bgcolor="cccccc">
			<td colspan="2" style="height: 1px;"></td>
		</tr>

		<tr>
			<td style="font-family: ����; font-size: 12">
				<div align="center">�� ��</div>
			</td>
			<td style="font-family: ����; font-size: 12">
				<table border=0 width=490 height=250 style="table-layout: fixed">
					<tr>
						<td valign=top style="font-family: ����; font-size: 12">${board.board_Content}
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="font-family: ����; font-size: 12">
				<div align="center">÷������</div>
			</td>
			<td style="font-family: ����; font-size: 12">
				<c:if test=${board.board_File ne null}>
				<a href="./boardupload/${board.board_File}">${board.board_File}</a>
				</c:if>
			</td>
		</tr>

		<tr bgcolor="cccccc">
			<td colspan="2" style="height: 1px;"></td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>

		<tr align="center" valign="middle">
			<td colspan="5"><font size=2> <a
					href="BoardReplyAction?num=${board.board_Num}">
						[�亯] </a>&nbsp;&nbsp; <a
					href="BoardModify?num=${board.board_Num}">[����] </a>&nbsp;&nbsp;
					<a href="BoardDelete?num=${board.board_Num}"> [����]
				</a>&nbsp;&nbsp; <a href="BoardList">[���]</a>&nbsp;&nbsp;
			</font></td>
		</tr>
	</table>
	<!-- �Խ��� ���� -->
</body>
</html>