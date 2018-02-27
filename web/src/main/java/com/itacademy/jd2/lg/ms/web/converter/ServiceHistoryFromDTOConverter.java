package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.ms.web.dto.ServiceHistoryDTO;

@Component
public class ServiceHistoryFromDTOConverter implements Function<ServiceHistoryDTO, ServiceHistory> {

	@Override
	public ServiceHistory apply(ServiceHistoryDTO dto) {
		ServiceHistory dbModel = new ServiceHistory();
		dbModel.setId(dto.getId());
		dbModel.setQuantity(dto.getQuantity());
		dbModel.setSum(dto.getSum());
		dbModel.setCreated(dto.getCreated());
		dbModel.setModified(dto.getModified());

		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setId(dto.getPhoneNumberId());
		dbModel.setPhone_number(phoneNumber);

		TariffItem tariffItemId = new TariffItem();
		tariffItemId.setId(dto.getTariffItemId());
		dbModel.setTariff_item(tariffItemId);

		return dbModel;
	}
}
