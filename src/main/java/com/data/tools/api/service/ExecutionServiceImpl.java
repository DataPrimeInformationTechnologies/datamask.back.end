package com.data.tools.api.service;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExecutionServiceImpl implements ExecutionService {


	@PersistenceContext
	private EntityManager entityManager;


	@Override
	@Transactional
	public List<Object> runSQLCommand() {

		String sql = """
CREATE TABLE accounts (
	user_id serial PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	password VARCHAR ( 50 ) NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL,
	created_on TIMESTAMP NOT NULL,
        last_login TIMESTAMP
);
""";
		Query query = entityManager.createNativeQuery(sql);
		System.out.println(query.executeUpdate());
		return null;
	}



}
