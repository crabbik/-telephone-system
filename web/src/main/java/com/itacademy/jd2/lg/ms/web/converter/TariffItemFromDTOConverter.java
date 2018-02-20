package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Service;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.ms.web.dto.TariffItemDTO;

@Component
public class TariffItemFromDTOConverter implements Function<TariffItemDTO, TariffItem> {

	@Override
	public TariffItem apply(TariffItemDTO dto) {
		TariffItem dbModel = new TariffItem();
		dbModel.setId(dto.getId());
		dbModel.setCost(dto.getCost());
		dbModel.setCreated(dto.getCreated());
		dbModel.setModified(dto.getModified());
		/*
		 * Service service = new Service(); service.setId(dto.getId());
		 * service.setType(dto.getService().getType());
		 * service.setUnit(dto.getService().getUnit());
		 * service.setDeleted(dto.getService().isDeleted());
		 * dbModel.setService(service);
		 */

		return dbModel;
	}
}
