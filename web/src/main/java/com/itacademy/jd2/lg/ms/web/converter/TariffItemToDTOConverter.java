package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.ms.web.dto.TariffItemDTO;

@Component
public class TariffItemToDTOConverter implements Function<TariffItem, TariffItemDTO> {

	@Override
	public TariffItemDTO apply(TariffItem dbModel) {
		TariffItemDTO dto = new TariffItemDTO();
		dto.setId(dbModel.getId());
		dto.setDeleted(dbModel.isDeleted());
		dto.setCost(dbModel.getCost());
		dto.setCreated(dbModel.getCreated());
		dto.setModified(dbModel.getModified());

		dto.setPhoneServiceId(dbModel.getPhoneservice().getId());
		dto.setTariffId(dbModel.getTariff().getId());

		return dto;
	}
}
