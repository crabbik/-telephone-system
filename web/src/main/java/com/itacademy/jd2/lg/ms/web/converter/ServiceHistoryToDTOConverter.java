package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.ms.web.dto.ServiceHistoryDTO;

@Component
public class ServiceHistoryToDTOConverter implements Function<ServiceHistory, ServiceHistoryDTO> {
	@Override
	public ServiceHistoryDTO apply(ServiceHistory dbModel) {
		ServiceHistoryDTO dto = new ServiceHistoryDTO();
		dto.setId(dbModel.getId());
		dto.setQuantity(dbModel.getQuantity());
		dto.setSum(dbModel.getSum());
		dto.setCreated(dbModel.getCreated());
		dto.setModified(dbModel.getModified());
		dto.setPhoneNumberId(dbModel.getPhone_number().getId());
		dto.setTariffItemId(dbModel.getTariff_item().getId());

		return dto;
	}
}
