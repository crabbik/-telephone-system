package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.ms.dao.filter.InvoiceFilter;

public interface IInvoiceDao extends IHibernateDao<Invoice, Integer> {

	Long count(InvoiceFilter filter);

	List<Invoice> find(InvoiceFilter filter);

}
