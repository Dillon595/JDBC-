
package test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import DAO.CustomerDAOImpl;
import DAO.CustomersDAO;
import bean.Customer;

public class Test {

	/**
	 * 1、通过分层方式实现商品的添加、修改、删除、查询 2、扩展作业： 通过商品表实现分页查询。 mysql -- > limit关键字
	 */

	/**
	 * 
	 * @Description: 通用的增删改操作
	 * @author LYL
	 * @date 2021-03-15 17:45:06
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CustomersDAO cd = new CustomerDAOImpl();
		for (;;) {
			System.out.println("1.添加、2.修改、3.删除、4.查询、5.退出");
			int index = sc.nextInt();
			if (1 == index) {
				int id = 1;
				System.out.print("输入入用户姓名：");
				String name = sc.next();
				System.out.print("输入入用户邮箱：");
				String email = sc.next();
				System.out.print("输入入用户生日：");
				String date = sc.next();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date parse = null;
				try {
					long num = sdf.parse(date).getTime();
					parse = new Date(num);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Customer cust = new Customer(id, name, email, parse);
				cd.insert(cust);
			} else if (2 == index) {
				System.out.println("请输入需要更改的用户id");
				int id = sc.nextInt();
				System.out.print("输入入用户姓名：");
				String name = sc.next();
				System.out.print("输入入用户邮箱：");
				String email = sc.next();
				System.out.print("输入入用户生日：");
				String date = sc.next();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date parse = null;
				try {
					long num = sdf.parse(date).getTime();
					parse = new Date(num);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Customer cust = new Customer(id, name, email, parse);

				cd.alter(cust);
			} else if (3 == index) {
				System.out.println("请输入需要删除的用户id");
				int id = sc.nextInt();

				Customer cust = new Customer();
				cust.setId(id);

				cd.delete(cust);
			} else if (4 == index) {
				System.out.println("请输入需要查询第几页（每页5位用户）");
				int start = 0;
				int num = sc.nextInt();
				if (1 == num) {

				} else {
					start = num * 5 - 6;
				}

				List<Customer> list = cd.query(start);
				for (Customer cust : list) {
					System.out.println(cust);
				}

			} else if(5 == index){
				System.out.println("程序结束~");
				System.exit(0);
			}
			else {
				System.out.println("输入错误，请重新输入！");
			}

		}
	}

}
