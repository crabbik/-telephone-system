package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber2Invoice;
import com.itacademy.jd2.lg.ms.dao.filter.PhoneNumber2InvoiceFilter;

public interface IPhoneNumber2InvoiceDao extends IHibernateDao<PhoneNumber2Invoice, Integer> {
	Long count(PhoneNumber2InvoiceFilter filter);

	List<PhoneNumber2Invoice> find(PhoneNumber2InvoiceFilter filter);

}
