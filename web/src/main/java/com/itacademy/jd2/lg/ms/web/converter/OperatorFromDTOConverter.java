package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.ms.web.dto.OperatorDTO;

@Component
public class OperatorFromDTOConverter implements Function<OperatorDTO,Operator>{

	@Override
	public Operator apply(OperatorDTO dto) {
		Operator dbModel = new Operator();
		dbModel.setId(dto.getId());
		dbModel.setTitle(dto.getTitle());
		dbModel.setDeleted(dto.isDeleted());
		dbModel.setCreated(dto.getCreated());
		dbModel.setModified(dto.getModified());

		return dbModel;
	}

}