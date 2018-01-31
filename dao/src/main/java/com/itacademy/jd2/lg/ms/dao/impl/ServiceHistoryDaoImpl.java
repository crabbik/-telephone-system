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

import com.itacademy.jd2.lg.ms.dao.IServiceHistoryDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceHistoryFilter;

@Repository
public class ServiceHistoryDaoImpl extends AbstractHibernateDaoImpl<ServiceHistory, Integer>
		implements IServiceHistoryDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHistoryDaoImpl.class);

	protected ServiceHistoryDaoImpl() {
		super(ServiceHistory.class);
	}

	@Override
	public Long count(ServiceHistoryFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ServiceHistory> from = cq.from(ServiceHistory.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<ServiceHistory> find(ServiceHistoryFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ServiceHistory> cq = cb.createQuery(ServiceHistory.class);
		Root<ServiceHistory> from = cq.from(ServiceHistory.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<ServiceHistory> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
