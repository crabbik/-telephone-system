package com.itacademy.jd2.lg.ms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.web.dto.PhoneNumberDTO;

@Component
public class PhoneNumberFromDTOConverter implements Function<PhoneNumberDTO, PhoneNumber> {

	@Override
	public PhoneNumber apply(PhoneNumberDTO dto) {
		PhoneNumber dbModel = new PhoneNumber();
		dbModel.setId(dto.getId());
		dbModel.setNumber(dto.getNumber());
		dbModel.setCreated(dto.getCreated());
		dbModel.setModified(dto.getModified());
		
		Account account =new Account();
		account.setId(dto.getAccountId());
		dbModel.setAccount(account);
		
		Tariff tariff =new Tariff();
		tariff.setId(dto.getTariffId());
		dbModel.setTariff(tariff);;

		return dbModel;
	}
}
