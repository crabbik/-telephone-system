package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.ms.web.dto.OperatorDTO;

@Component
public class OperatorToDTOConverter implements Function<Operator, OperatorDTO> {
	@Override
	public OperatorDTO apply(Operator dbModel) {
		OperatorDTO dto = new OperatorDTO();
		dto.setId(dbModel.getId());
		dto.setTitle(dbModel.getTitle());
		dto.setDeleted(dbModel.isDeleted());
		dto.setCreated(dbModel.getCreated());
		dto.setModified(dbModel.getModified());

		
		return dto;
	}

}
