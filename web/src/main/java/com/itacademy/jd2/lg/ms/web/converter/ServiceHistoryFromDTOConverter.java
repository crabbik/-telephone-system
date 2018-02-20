package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.ms.web.dto.ServiceHistoryDTO;

@Component
public class ServiceHistoryFromDTOConverter implements Function<ServiceHistoryDTO, ServiceHistory> {

	@Override
	public ServiceHistory apply(ServiceHistoryDTO dto) {
		ServiceHistory dbModel = new ServiceHistory();
		dbModel.setId(dto.getId());
		dbModel.setData(dto.getData());
		dbModel.setQuantity(dto.getQuantity());
		dbModel.setSum(dto.getSum());
		dbModel.setCreated(dto.getCreated());
		dbModel.setModified(dto.getModified());

		return dbModel;
	}
}
