package com.poly.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.util.JpaUtil;

public class AbstractDao<T>  {

	public static final EntityManager entityManager = JpaUtil.getEntityManager();
	
	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		entityManager.close();
		super.finalize();
	}
	
	public T findById(Class<T> clazz, Integer id) {
		return entityManager.find(clazz, id);
	}
	
	public List<T> findAll(Class<T> clazz, Boolean existIsAactive) {
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o FROM ").append(entityName).append(" o");
		if (existIsAactive == true) {
			sql.append(" WHERE isActive = 1");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
		return query.getResultList();
	}

	public List<T> findAll(Class<T> clazz, Boolean existIsAactive, int pageNumber, int pageSize) {
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o FROM ").append(entityName).append(" o");
		if (existIsAactive == true) {
			sql.append(" WHERE isActive = 1");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
		//	vi du: co 5 phan tu, 1 trang chua toi da 2 phan tu
		//	>> tong so trng bang 3: trang 1 = 2 phan tu [0] [1]
		//							trang 2 = 2 phan tu [2] [3]
		//							trang 3 = 1 phan tu [4] 
		//	* muon lay phan tu trang 3: pageNumber = 3, pageSize = 2 (vi 1 trang chua toi da 2 phan tu)
		//	>>>(3 - 1) * 2 = 4 lay tu phan tu thu 4
	
	// ex: select o from user where o.username = ?0 o.password = ?1 
	public T findOne(Class<T> clazz, String sql, Object...params) {
		TypedQuery<T> query = entityManager.createQuery(sql, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		List<T> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}
	
	public List<T> findMany(Class<T> clazz, String sql, Object...params) {
		TypedQuery<T> query = entityManager.createQuery(sql, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i,params[i]);
		}
		return  query.getResultList();
	}
	
	public T create (T entity) {
		try {
			entityManager.getTransaction().begin();;
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			System.out.println("Create Success");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot insert " + entity.getClass().getSimpleName() + " to database");
			throw new RuntimeException(e);
		}
	}
	
	
	public T update (T entity) {
		try {
			entityManager.getTransaction().begin();;
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			System.out.println("Create Success");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot update " + entity.getClass().getSimpleName() + " to database");
			throw new RuntimeException(e);
		}
	}
	
	public T delete (T entity) {
		try {
			entityManager.getTransaction().begin();;
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			System.out.println("Create Success");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot delete " + entity.getClass().getSimpleName() + " to database");
			throw new RuntimeException(e);
		}
	}
}


