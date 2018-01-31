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

import com.itacademy.jd2.lg.ms.dao.IInvoiceDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.ms.dao.filter.InvoiceFilter;

@Repository
public class InvoiceDaoImpl extends AbstractHibernateDaoImpl<Invoice, Integer> implements IInvoiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceDaoImpl.class);

	protected InvoiceDaoImpl() {
		super(Invoice.class);
	}

	@Override
	public Long count(InvoiceFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Invoice> from = cq.from(Invoice.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Invoice> find(InvoiceFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
		Root<Invoice> from = cq.from(Invoice.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Invoice> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}