package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.web.dto.AccountDetailsDTO;

@Component
public class AccountDetailsToDTOConverter implements Function<AccountDetails, AccountDetailsDTO> {

	@Override
	public AccountDetailsDTO apply(AccountDetails dbModel) {
		AccountDetailsDTO dto = new AccountDetailsDTO();
		dto.setId(dbModel.getId());
		dto.setFirstName(dbModel.getFirstName());
		dto.setLastName(dbModel.getLastName());
		dto.setCreated(dbModel.getCreated());
		dto.setModified(dbModel.getModified());
		dto.setAccountId(dbModel.getAccount().getId());

		return dto;
	}
}
