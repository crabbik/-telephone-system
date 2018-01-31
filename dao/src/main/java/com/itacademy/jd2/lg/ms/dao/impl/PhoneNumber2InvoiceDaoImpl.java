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

import com.itacademy.jd2.lg.ms.dao.IPhoneNumber2InvoiceDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber2Invoice;
import com.itacademy.jd2.lg.ms.dao.filter.PhoneNumber2InvoiceFilter;

@Repository
public class PhoneNumber2InvoiceDaoImpl extends AbstractHibernateDaoImpl<PhoneNumber2Invoice, Integer>
		implements IPhoneNumber2InvoiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumber2InvoiceDaoImpl.class);

	protected PhoneNumber2InvoiceDaoImpl() {
		super(PhoneNumber2Invoice.class);
	}

	@Override
	public Long count(PhoneNumber2InvoiceFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PhoneNumber2Invoice> from = cq.from(PhoneNumber2Invoice.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<PhoneNumber2Invoice> find(PhoneNumber2InvoiceFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PhoneNumber2Invoice> cq = cb.createQuery(PhoneNumber2Invoice.class);
		Root<PhoneNumber2Invoice> from = cq.from(PhoneNumber2Invoice.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<PhoneNumber2Invoice> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}