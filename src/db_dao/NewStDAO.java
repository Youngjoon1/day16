package db_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.attribute.standard.PresentationDirection;

import db_common.DBconnect;
import db_dto.NewStDTO;
import oracle.jdbc.driver.Representation;

public class NewStDAO {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	public NewStDAO() {
		con = DBconnect.getConnect();
	}
	
	public ArrayList<NewStDTO> getList() {
		
		ArrayList<NewStDTO> list = new ArrayList<>();
		
		String sql = "select * from newst";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
//			System.out.println("=== 회원 정보 ===");
			while(rs.next()) {
				NewStDTO dto = new NewStDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				
				list.add(dto);
//				System.out.println(rs.getString("id"));
//				System.out.println(rs.getString("name"));
//				System.out.println(rs.getInt("age"));
//				System.out.println("-----------------");
				
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public NewStDTO search(String id) {
		NewStDTO dto = null;
		// "select * from newst where id = '아이디'
		String sql = "select * from newst where id = '"+id+"'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) { // while(rs.next())
				dto = new NewStDTO(rs.getString("id"),rs.getString("name"),rs.getInt("age"));
				
//				dto = new NewStDTO();
//				dto.setId(rs.getString("id"));
//				dto.setName(rs.getString("name"));
//				dto.setAge(rs.getInt("age"));
				
				
//				System.out.println("id : " + rs.getString("id"));
//				System.out.println("name : " + rs.getString("name"));
//				System.out.println("age : " + rs.getInt("age"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("dto : " +dto);
		return dto;
	}
	public int insert(NewStDTO dto) {
		String sql = "insert into newst values(?, ?, ?)";
		// ? : 나중에 값 채움
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());// (순번, )
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getAge());
			/*
			 * Query: select에서만 Query를 사용한다. 결과값이 ResultSet
			 * Update : select를 제외한 모든 명령어에서 사용한다.
			 */
			result = ps.executeUpdate();
//			ps.executeQuery();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(String id) {
		int result = 0;
		String sql = "delete from newst where id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
		} catch (Exception e) {

		}
		return result;
	}

	public int modify(NewStDTO d) {
		
		String sql = "update newst set name = ?, age = ? where id = ?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, d.getName());
			ps.setInt(2, d.getAge());
			ps.setString(3, d.getId());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
