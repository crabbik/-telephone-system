package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Service;
import com.itacademy.jd2.lg.ms.web.dto.ServiceDTO;

@Component
public class ServiceFromDTOConverter implements Function<ServiceDTO, Service> {

	@Override
	public Service apply(ServiceDTO dto) {
		Service dbModel = new Service();
		dbModel.setId(dto.getId());
		dbModel.setType(dto.getType());
		dbModel.setUnit(dto.getUnit());
		dbModel.setDeleted(dto.isDeleted());
		dbModel.setCreated(dto.getCreated());
		dbModel.setModified(dto.getModified());

		return dbModel;
	}
}
