package com.spring.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO{

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}



	@Override
	public List selectAllMembers() throws DataAccessException {
		
		String query = "select id,pwd,name,email,joinDate" + " from t_member " + " order by joinDate desc";
	
		List membersList = new ArrayList();
		
		//RowMapper : 행 단위로 ResultSet의 행을 매핑하기 위해 JdbcTemplate에서 사용하는 인터페이스
		membersList = this.jdbcTemplate.query(query, new RowMapper(){

		@Override
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			MemberVO memberVO = new MemberVO();
			memberVO.setId(rs.getString("id"));
			memberVO.setPwd(rs.getString("pwd"));
			memberVO.setName(rs.getString("name"));
			memberVO.setEmail(rs.getString("email"));
			memberVO.setJoinDate(rs.getDate("joinDate"));
			
			return memberVO;
		}
		
	});
		
		return membersList;
	}



	
}
