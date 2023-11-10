package com.data.tools.api.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

@Service
public class ExecutionServiceImpl implements ExecutionService {

	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public List<Object> runSQLCommand() {

		Session currentSession =  getCurrentSession();
		Transaction tx = currentSession.beginTransaction();
		// currentSession.query("select emp_id, emp_name, emp_salary from Employee");
		// currentSession.createNativeQuery(
        //     "select * from dados_usuario where fk_usuario = :id_usuario","");
		// // Get All Employees
		
		// SQLQuery query = session.createSQLQuery();
		// List<Object[]> rows = query.list();
		// for (Object[] row : rows) {
		// 	Employee emp = new Employee();
		// 	emp.setId(Long.parseLong(row[0].toString()));
		// 	emp.setName(row[1].toString());
		// 	emp.setSalary(Double.parseDouble(row[2].toString()));
		// 	System.out.println(emp);
		// }
		return null;
	}

	protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
