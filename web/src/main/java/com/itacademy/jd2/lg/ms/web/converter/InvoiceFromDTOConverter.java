package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.ms.web.dto.InvoiceDTO;

@Component
public class InvoiceFromDTOConverter implements Function<InvoiceDTO, Invoice> {

	@Override
	public Invoice apply(InvoiceDTO dto) {
		Invoice dbModel = new Invoice();
		dbModel.setId(dto.getId());
		dbModel.setMonth(dto.getMonth());
		dbModel.setQuantity(dto.getQuantity());
		dbModel.setSum(dto.getSum());
		dbModel.setType(dto.getType());
		dbModel.setYear(dto.getYear());
		dbModel.setCreated(dto.getCreated());
		dbModel.setModified(dto.getModified());

		return dbModel;
	}
}
