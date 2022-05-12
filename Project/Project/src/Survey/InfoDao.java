package Survey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoDao {
private JdbcTemplate jt;
	
	public InfoDao() {
		jt = JdbcTemplate.getInstance();
	}
	
	//정보 입력
	public void insertInfo(InfoVo iv) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "insert into \"INFO\" values (SEQ_INFO.nextval, ?, ?)";
		
		try {
			conn = jt.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, iv.getAge());
			ps.setString(2, iv.getReligion());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//종교 나열
	public List<String> viewRel(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> ls = new ArrayList<>();
		
		String sql = "select \"RELIGION\" from \"INFO\" group by \"RELIGION\"\r\n" + 
				"order by (CASE WHEN \"RELIGION\" = '무교' THEN 2 ELSE 1 END), \"RELIGION\" ASC";
		
		try {
			conn = jt.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			while(rs.next()) {
				ls.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}
	
	//종교 중복 확인
	public boolean checkRel(String rel) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from \"INFO\" where \"RELIGION\"=?";
		
		try {
			conn = jt.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, rel);
			
			rs = ps.executeQuery();	
			
			if(rs.next()) {
				return true;
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	//전체적인 종교 통계 
	public List<Count> viewCount(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Count> ls = new ArrayList<>();
		
		String sql = "select \"RELIGION\",COUNT(\"RELIGION\") from \"INFO\" group by \"RELIGION\"\r\n" + 
				"order by COUNT(\"RELIGION\") desc";
		
		try {
			conn = jt.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			while(rs.next()) {
				Count count = new Count(rs.getString(1), rs.getInt(2));
				ls.add(count);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}
	
	//나이별 종교 통계
	public List<Count> ageCount(int age){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Count> ls = new ArrayList<>();
		
		String sql = "select \"RELIGION\", COUNT(\"RELIGION\") from \"INFO\"\r\n" + 
				"where \"AGE\" between ? and ?\r\n" + 
				"group by \"RELIGION\"\r\n" + 
				"order by COUNT(\"RELIGION\") desc";
		
		try {
			conn = jt.getConn();
			ps = conn.prepareStatement(sql);
			switch(age) {			
			case 1 :ps.setInt(1, 0);
					ps.setInt(2, 10);break;
			case 2 :ps.setInt(1, 10);
					ps.setInt(2, 20);break;
			case 3 :ps.setInt(1, 20);
					ps.setInt(2, 30);break;
			case 4 :ps.setInt(1, 30);
					ps.setInt(2, 40);break;
			case 5 :ps.setInt(1, 40);
					ps.setInt(2, 50);break;
			case 6 :ps.setInt(1, 50);
					ps.setInt(2, 60);break;
			case 7 :ps.setInt(1, 60);
					ps.setInt(2, 70);break;
			case 8 :ps.setInt(1, 70);
					ps.setInt(2, 80);break;
			case 9 :ps.setInt(1, 80);
					ps.setInt(2, 90);break;
			case 10 :ps.setInt(1, 90);
					ps.setInt(2, 100);break;
			}
			rs = ps.executeQuery();	
			
			while(rs.next()) {
				Count count = new Count(rs.getString(1), rs.getInt(2));
				ls.add(count);
			}
			
		} catch (SQLException e) {
			System.out.println("잘못 입력!");
		}finally{
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}
}
