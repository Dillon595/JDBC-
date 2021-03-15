
package DAO;

import java.util.List;

import bean.Customer;

public interface CustomersDAO {

	public int insert(Customer cust);

	public int delete(Customer cust);

	public int alter(Customer cust);

	public List<Customer> query(int start);

}
