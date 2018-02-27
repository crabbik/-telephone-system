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

import com.itacademy.jd2.lg.ms.dao.IPhoneServiceDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneService;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceFilter;

@Repository
public class PhoneServiceDaoImpl extends AbstractHibernateDaoImpl<PhoneService, Integer> implements IPhoneServiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhoneServiceDaoImpl.class);

	protected PhoneServiceDaoImpl() {
		super(PhoneService.class);
	}

	@Override
	public Long count(ServiceFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PhoneService> from = cq.from(PhoneService.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<PhoneService> find(ServiceFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PhoneService> cq = cb.createQuery(PhoneService.class);
		Root<PhoneService> from = cq.from(PhoneService.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<PhoneService> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
