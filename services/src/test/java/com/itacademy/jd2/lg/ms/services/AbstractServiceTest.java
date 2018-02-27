package com.itacademy.jd2.lg.ms.services;

import java.util.Date;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneService;
import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.ms.services.IAccountDetailsService;
import com.itacademy.jd2.lg.ms.services.IAccountService;
import com.itacademy.jd2.lg.ms.services.IInvoiceService;
import com.itacademy.jd2.lg.ms.services.IOperatorService;
import com.itacademy.jd2.lg.ms.services.IPhoneNumberService;
import com.itacademy.jd2.lg.ms.services.IServiceHistoryService;
import com.itacademy.jd2.lg.ms.services.IPhoneServiceService;
import com.itacademy.jd2.lg.ms.services.ITariffItemService;
import com.itacademy.jd2.lg.ms.services.ITariffService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public abstract class AbstractServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServiceTest.class);
	@Autowired
	protected IAccountDetailsService serviceAccountDetails;
	@Autowired
	protected IAccountService serviceAccount;
	@Autowired
	protected IInvoiceService serviceInvoice;
	@Autowired
	protected IOperatorService serviceOperator;
	@Autowired
	protected IPhoneNumberService servicePhoneNumber;
	@Autowired
	protected IServiceHistoryService serviceServiceHistory;
	@Autowired
	protected IPhoneServiceService serviceService;
	@Autowired
	protected ITariffItemService serviceTariffItem;
	@Autowired
	protected ITariffService serviceTariff;

	protected Account createAccount() {
		LOGGER.info("prepare data for AccountServiceTest");
		Account account = new Account();
		account.setEmail("test@mail.ru");
		account.setPassword("123456");
		serviceAccount.save(account);
		return account;

	}

	protected AccountDetails createAccountDetails() {
		LOGGER.info("prepare data for AccountDetailsServiceTest");
		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setId(1);
		accountDetails.setLastName("Иванов");
		accountDetails.setFirstName("Иван");
		if (accountDetails.getId() == null) {
			return serviceAccountDetails.save(accountDetails);
		} else {
			return accountDetails;
		}
	}

	protected Invoice createInvoice() {
		LOGGER.info("prepare data for InvoiceServiceTest");
		Invoice invoice = new Invoice();
		invoice.setMonth(1);
		invoice.setQuantity(10);
		invoice.setSum(990);
		invoice.setType("mb");
		invoice.setYear(2018);
		return invoice;

	}

	protected Operator createOperator() {
		LOGGER.info("prepare data for OperatorServiceTest");
		Operator operator = new Operator();
		operator.setTitle("MTS");
		operator.setDeleted(false);
		if (operator.getId() == null) {
			return serviceOperator.save(operator);
		} else {
			return operator;
		}
	}

	protected PhoneNumber createPhoneNumber() {
		LOGGER.info("prepare data for PhoneNumberServiceTest");
		PhoneNumber phoneNumber = new PhoneNumber();
		if (phoneNumber.getId() == null) {
			return servicePhoneNumber.save(phoneNumber);
		} else {
			return phoneNumber;
		}
	}

	protected ServiceHistory createServiceHistory() {
		LOGGER.info("prepare data for ServiceHistoryServiceTest");
		ServiceHistory serviceHistory = new ServiceHistory();
		serviceHistory.setQuantity(10);
		serviceHistory.setSum(990);
		return serviceHistory;

	}

	protected PhoneService createService() {
		LOGGER.info("prepare data for ServiceServiceTest");
		PhoneService service = new PhoneService();
		service.setType("звонки");
		service.setUnit("мин");
		service.setDeleted(false);
		if (service.getId() == null) {
			return serviceService.save(service);
		} else {
			return service;
		}
	}

	protected TariffItem createTariffItem() {
		LOGGER.info("prepare data for TariffItemServiceTest");
		TariffItem tariffItem = new TariffItem();
		tariffItem.setCost(10);
		tariffItem.setDeleted(false);
		if (tariffItem.getId() == null) {
			return serviceTariffItem.save(tariffItem);
		} else {
			return tariffItem;
		}
	}

	protected Tariff createTariff() {
		LOGGER.info("prepare data for TariffServiceTest");
		Tariff tariff = new Tariff();
		tariff.setName("Отличный");
		tariff.setOperator(createOperator());
		tariff.setDeleted(false);
		if (tariff.getId() == null) {
			return serviceTariff.save(tariff);
		} else {
			return tariff;
		}
	}

}
