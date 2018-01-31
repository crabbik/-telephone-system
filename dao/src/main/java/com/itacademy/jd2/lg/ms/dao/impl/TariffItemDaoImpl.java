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

import com.itacademy.jd2.lg.ms.dao.ITariffItemDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.ms.dao.filter.TariffItemFilter;

@Repository
public class TariffItemDaoImpl extends AbstractHibernateDaoImpl<TariffItem, Integer> implements ITariffItemDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(TariffItemDaoImpl.class);

	protected TariffItemDaoImpl() {
		super(TariffItem.class);
	}

	@Override
	public Long count(TariffItemFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TariffItem> from = cq.from(TariffItem.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<TariffItem> find(TariffItemFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TariffItem> cq = cb.createQuery(TariffItem.class);
		Root<TariffItem> from = cq.from(TariffItem.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<TariffItem> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
