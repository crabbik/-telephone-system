package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.web.dto.TariffDTO;
@Component
public class TariffToDTOConverter implements Function<Tariff, TariffDTO> {

	@Override
	public TariffDTO apply(Tariff dbModel) {
		TariffDTO dto = new TariffDTO();
		dto.setId(dbModel.getId());
		dto.setDeleted(dbModel.isDeleted());
		dto.setName(dbModel.getName());
		dto.setCreated(dbModel.getCreated());
		dto.setModified(dbModel.getModified());

		return dto;
	}
}
