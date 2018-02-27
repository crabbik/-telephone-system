package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.web.dto.PhoneNumberDTO;

@Component
public class PhoneNumberToDTOConverter implements Function<PhoneNumber, PhoneNumberDTO> {

	@Override
	public PhoneNumberDTO apply(PhoneNumber dbModel) {
		PhoneNumberDTO dto = new PhoneNumberDTO();
		dto.setId(dbModel.getId());
		dto.setNumber(dbModel.getNumber());
		dto.setCreated(dbModel.getCreated());
		dto.setModified(dbModel.getModified());

		dto.setAccountId(dbModel.getAccount().getId());
		dto.setTariffId(dbModel.getTariff().getId());

		return dto;
	}
}
