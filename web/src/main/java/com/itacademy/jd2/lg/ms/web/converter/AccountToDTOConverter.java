package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.web.dto.AccountDTO;

@Component
public class AccountToDTOConverter implements Function<Account, AccountDTO> {

	@Override
	public AccountDTO apply(Account dbModel) {
		AccountDTO dto = new AccountDTO();
		dto.setId(dbModel.getId());
		dto.setEmail(dbModel.getEmail());
		dto.setPassword(dbModel.getPassword());
		dto.setRoles(dbModel.getRoles());
		dto.setCreated(dbModel.getCreated());
		dto.setModified(dbModel.getModified());
		AccountDetails details = dbModel.getDetails();
		if (details != null) {
			dto.getDetails().setFirstName(details.getFirstName());
			dto.getDetails().setLastName(details.getLastName());
		}

		return dto;
	}

}
