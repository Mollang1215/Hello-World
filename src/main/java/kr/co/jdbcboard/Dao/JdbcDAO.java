package kr.co.jdbcboard.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import kr.co.jdbcboard.Bean.BoardBean;
import kr.co.jdbcboard.util.Constant;

public class JdbcDAO {

	JdbcTemplate template;

	public JdbcDAO() {
		this.template = Constant.template;
	}

	// 리스트 가져오기
	public ArrayList<BoardBean> getBoardList(final int PAGE, final int LIMIT) {
		String sql = "select * from " + "(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"
				+ "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"
				+ "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "
				+ "(select * from board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc)) " + "where rnum>=" + PAGE
				+ " and rnum<=" + LIMIT;

		return (ArrayList<BoardBean>) template.query(sql, new BeanPropertyRowMapper<BoardBean>(BoardBean.class));
	}

	// 조회수 가져오기
	public int getListCount() {
		String sql = "SELECT COUNT(*) FROM BOARD";
		return this.template.queryForInt(sql);
	}

	// 글 쓰기 등록
	public void Write(final BoardBean BOARDBEAN) {
		String sql = "insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
				+ "BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF," + "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"
				+ "BOARD_DATE) values(BOARD_SEQ.NEXTVAL,?,?,?,?,?,BOARD_SEQ.CURRVAL,0,0,0,sysdate)";

		this.template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, BOARDBEAN.getBoard_Name());
				ps.setString(2, BOARDBEAN.getBoard_Pass());
				ps.setString(3, BOARDBEAN.getBoard_Subject());
				ps.setString(4, BOARDBEAN.getBoard_Content());
				ps.setString(5, BOARDBEAN.getBoard_File());
			}
		});
	}

	// 값 가져오기
	public BoardBean Detail(int cnt) {
		String sql = "select * from board where BOARD_NUM = " + cnt;
		return template.queryForObject(sql, new BeanPropertyRowMapper<BoardBean>(BoardBean.class));
	}

	// 수정 완료
	public void ModifyAction(final BoardBean BOARDBEAN) {
		String sql = "UPDATE BOARD SET BOARD_NAME= ?, BOARD_PASS = ?, BOARD_SUBJECT = ?, BOARD_CONTENT =? WHERE BOARD_NUM = ?";
		this.template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, BOARDBEAN.getBoard_Name());
				ps.setString(2, BOARDBEAN.getBoard_Pass());
				ps.setString(3, BOARDBEAN.getBoard_Subject());
				ps.setString(4, BOARDBEAN.getBoard_Content());
				ps.setInt(5, BOARDBEAN.getBoard_Num());
			}
		});
	}

	public void Delete(int num) {
		String sql = "DELETE FROM BOARD WHERE BOARD_NUM = " + num;
		this.template.update(sql);

	}

	public void ReplyUpdate(int cnt) {
		String sql = "UPDATE BOARD SET BOARD_RE_SEQ = BOARD_RE_SEQ+1 WHERE BOARD_RE_REF =" + cnt;
		this.template.update(sql);
	}

	public void ReplyAction(final BoardBean BOARDBEAN) {
		String sql = "insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
				+ "BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF," + "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"
				+ "BOARD_DATE) values(BOARD_SEQ.NEXTVAL,?,?,?,?,\'\',?,?,?,?,sysdate)";

		this.template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, BOARDBEAN.getBoard_Name());
				ps.setString(2, BOARDBEAN.getBoard_Pass());
				ps.setString(3, BOARDBEAN.getBoard_Subject());
				ps.setString(4, BOARDBEAN.getBoard_Content());
				ps.setInt(5, BOARDBEAN.getBoard_Re_Ref());
				ps.setInt(6, BOARDBEAN.getBoard_Re_Lev()+1);
				ps.setInt(7, BOARDBEAN.getBoard_Re_Seq());
				ps.setInt(8, 0);
			}
		});
	}
}
