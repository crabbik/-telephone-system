package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.web.dto.TariffDTO;

@Component
public class TariffFromDTOConverter implements Function<TariffDTO, Tariff> {

	@Override
	public Tariff apply(TariffDTO dto) {
		Tariff dbModel = new Tariff();
		dbModel.setId(dto.getId());
		dbModel.setName(dto.getName());
		dbModel.setDeleted(dto.isDeleted());
		dbModel.setCreated(dto.getCreated());
		dbModel.setModified(dto.getModified());

		return dbModel;
	}
}
