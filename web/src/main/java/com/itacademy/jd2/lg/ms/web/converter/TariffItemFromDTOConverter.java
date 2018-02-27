package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneService;
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

		PhoneService phoneService = new PhoneService();
		phoneService.setId(dto.getPhoneServiceId());
		dbModel.setPhoneservice(phoneService);

		Tariff tariff = new Tariff();
		tariff.setId(dto.getTariffId());
		dbModel.setTariff(tariff);

		return dbModel;
	}
}
