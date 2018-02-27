package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.ms.web.dto.InvoiceDTO;

@Component
public class InvoiceToDTOConverter implements Function<Invoice, InvoiceDTO> {

	@Override
	public InvoiceDTO apply(Invoice dbModel) {
		InvoiceDTO dto = new InvoiceDTO();
		dto.setId(dbModel.getId());
		dto.setMonth(dbModel.getMonth());
		dto.setQuantity(dbModel.getQuantity());
		dto.setSum(dbModel.getSum());
		dto.setType(dbModel.getType());
		dto.setYear(dbModel.getYear());
		dto.setCreated(dbModel.getCreated());
		dto.setModified(dbModel.getModified());
		dto.setPhoneNumberId(dbModel.getPhoneNumber().getId());

		return dto;
	}
}
