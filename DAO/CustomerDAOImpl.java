
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.JDBCUtils;
import bean.Customer;

public class CustomerDAOImpl implements CustomersDAO {

	Connection conn = JDBCUtils.getConnection();

	@Override
	public int insert(Customer cust) {
		String sql = "insert into customers(name,email,birth) values(?,?,?)";
		String name = cust.getName();
		String email = cust.getEmail();
		Date birth = cust.getBirth();
		int index = update(sql, name, email, birth);
		if (index > 0) {
			System.out.println("插入成功~");
		} else {
			System.out.println("插入失败~");
		}

		return index;
	}

	@Override
	public int delete(Customer cust) {
		String sql = "delete from customers where id = ?";
		int id = cust.getId();
		int index = update(sql, id);
		if (index > 0) {
			System.out.println("删除成功~");
		} else {
			System.out.println("删除失败~");
		}
		return index;
	}

	@Override
	public int alter(Customer cust) {
		String sql = "update customers set name=?,email=?,birth=? where id=?";
		int id = cust.getId();
		String name = cust.getName();
		String email = cust.getEmail();
		Date birth = cust.getBirth();
		int index = update(sql, name, email, birth, id);
		if (index > 0) {
			System.out.println("修改成功~");
		} else {
			System.out.println("修改失败~");
		}
		return index;
	}

	@Override
	public List<Customer> query(int start) {
		List<Customer> list = new ArrayList();
		String sql = "select * from customers limit ?,5";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date birth = rs.getDate("birth");
				Customer cust = new Customer(id, name, email, birth);
				list.add(cust);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(null, ps, rs);
		}

		return list;
	}

	/**
	 * 
	 * @Description:通用的增删改操作 
	 * @author LYL
	 * @date 2021-03-15 17:47:42
	 */
	private int update(String sql, Object... args) {
		PreparedStatement ps = null;
		int index = 0;
		try {
			ps = conn.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}

			index = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(ps);
		}
		return index;

	}

}
