package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneService;
import com.itacademy.jd2.lg.ms.web.dto.PhoneServiceDTO;

@Component
public class PhoneServiceToDTOConverter implements Function<PhoneService, PhoneServiceDTO> {

	@Override
	public PhoneServiceDTO apply(PhoneService dbModel) {
		PhoneServiceDTO dto = new PhoneServiceDTO();
		dto.setId(dbModel.getId());
		dto.setDeleted(dbModel.isDeleted());
		dto.setType(dbModel.getType());
		dto.setUnit(dbModel.getUnit());
		dto.setCreated(dbModel.getCreated());
		dto.setModified(dbModel.getModified());

		return dto;
	}
}
