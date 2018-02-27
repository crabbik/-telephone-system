package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneService;
import com.itacademy.jd2.lg.ms.web.dto.PhoneServiceDTO;

@Component
public class PhoneServiceFromDTOConverter implements Function<PhoneServiceDTO, PhoneService> {

	@Override
	public PhoneService apply(PhoneServiceDTO dto) {
		PhoneService dbModel = new PhoneService();
		dbModel.setId(dto.getId());
		dbModel.setType(dto.getType());
		dbModel.setUnit(dto.getUnit());
		dbModel.setDeleted(dto.isDeleted());
		dbModel.setCreated(dto.getCreated());
		dbModel.setModified(dto.getModified());

		return dbModel;
	}
}
