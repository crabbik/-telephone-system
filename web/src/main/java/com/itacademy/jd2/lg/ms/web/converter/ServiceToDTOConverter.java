package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Service;
import com.itacademy.jd2.lg.ms.web.dto.ServiceDTO;

@Component
public class ServiceToDTOConverter implements Function<Service, ServiceDTO> {

	@Override
	public ServiceDTO apply(Service dbModel) {
		ServiceDTO dto = new ServiceDTO();
		dto.setId(dbModel.getId());
		dto.setDeleted(dbModel.isDeleted());
		dto.setType(dbModel.getType());
		dto.setUnit(dbModel.getUnit());
		dto.setCreated(dbModel.getCreated());
		dto.setModified(dbModel.getModified());

		return dto;
	}
}
