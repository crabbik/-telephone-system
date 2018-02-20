package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.web.dto.AccountDetailsDTO;

@Component
public class AccountDetailsFromDTOConverter implements Function<AccountDetailsDTO, AccountDetails> {

	@Override
	public AccountDetails apply(AccountDetailsDTO dto) {
		AccountDetails dbModel = new AccountDetails();
		dbModel.setId(dto.getId());
		dbModel.setFirstName(dto.getFirstName());
		dbModel.setLastName(dto.getLastName());
		dbModel.setCreated(dto.getCreated());
		dbModel.setModified(dto.getModified());

		return dbModel;
	}
}
