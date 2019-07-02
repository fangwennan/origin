package com.fang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTest {

	public static void main(String[] args) {
		try {
			Class<?> forName = Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://rm-bp1elvih65s8397k1lo.mysql.rds.aliyuncs.com:3306/authc-sub-centers", "root",
					"Fwn414911!@#");
			PreparedStatement prepareStatement = connection.prepareStatement("select * from sys_user where id = 1");
			ResultSet rs = prepareStatement.executeQuery();

			int len = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				
				Member member = new Member();
				Class clazz = Member.class;
				for (int i = 0; i < len; i++) {
					String Columns = rs.getMetaData().getColumnName(i);
					long long1 = rs.getLong("id");
				}
				System.out.println(rs.getLong("id"));
				System.out.println(rs.getString("name"));
			}
			rs.close();
			prepareStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
