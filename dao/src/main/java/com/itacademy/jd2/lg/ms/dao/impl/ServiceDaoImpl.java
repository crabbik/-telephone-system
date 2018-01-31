package com.itacademy.jd2.lg.ms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.lg.ms.dao.IServiceDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Service;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceFilter;

@Repository
public class ServiceDaoImpl extends AbstractHibernateDaoImpl<Service, Integer> implements IServiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDaoImpl.class);

	protected ServiceDaoImpl() {
		super(Service.class);
	}

	@Override
	public Long count(ServiceFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Service> from = cq.from(Service.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Service> find(ServiceFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Service> cq = cb.createQuery(Service.class);
		Root<Service> from = cq.from(Service.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Service> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
