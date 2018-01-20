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
import com.itacademy.jd2.lg.ms.dao.dbmodel.Service;
import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.ms.services.IAccount2RoleService;
import com.itacademy.jd2.lg.ms.services.IAccountDetailsService;
import com.itacademy.jd2.lg.ms.services.IAccountService;
import com.itacademy.jd2.lg.ms.services.IInvoiceService;
import com.itacademy.jd2.lg.ms.services.IOperatorService;
import com.itacademy.jd2.lg.ms.services.IPhoneNumber2InvoiceService;
import com.itacademy.jd2.lg.ms.services.IPhoneNumberService;
import com.itacademy.jd2.lg.ms.services.IServiceHistoryService;
import com.itacademy.jd2.lg.ms.services.IServiceService;
import com.itacademy.jd2.lg.ms.services.ITariffItemService;
import com.itacademy.jd2.lg.ms.services.ITariffService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public abstract class AbstractServiceTest {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractServiceTest.class);
	@Autowired
	protected IAccountDetailsService serviceAccountDetails;
	@Autowired
	protected IAccount2RoleService serviceAccount2role;
	@Autowired
	protected IAccountService serviceAccount;
	@Autowired
	protected IInvoiceService serviceInvoice;
	@Autowired
	protected IOperatorService serviceOperator;
	@Autowired
	protected IPhoneNumber2InvoiceService servicePhoneNumber2Invoice;
	@Autowired
	protected IPhoneNumberService servicePhoneNumber;
	@Autowired
	protected IServiceHistoryService serviceServiceHistory;
	@Autowired
	protected IServiceService serviceService;
	@Autowired
	protected ITariffItemService serviceTariffItem;
	@Autowired
	protected ITariffService serviceTariff;

	// private AccountDetails accountDetails;
	private Account account;
	private Invoice invoice;
	private Operator operator;
	private PhoneNumber phoneNumber;
	private ServiceHistory serviceHistory;
	private Service service;
	private TariffItem tariffItem;
	private Tariff tariff;

	protected AccountDetails createdAccountDetails() {
		LOGGER.info("prepare data for AccountDetailsServiceTest");
		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setLastName("Иванов");
		accountDetails.setFirstName("Иван");
		if (accountDetails.getId() == null) {
			return serviceAccountDetails.save(accountDetails);
		} else {
			return accountDetails;
		}
	}

	/*
	 * protected void removeAccountDetails() {
	 * serviceAccountDetails.remove(accountDetails.getId());
	 */

	protected Account createdAccount() {
		LOGGER.info("prepare data for AccountServiceTest");
		account = new Account();
		account.setEmail("test@mail.ru");
		account.setPassword("123456");
		if (account.getId() == null) {
			return serviceAccount.save(account);
		} else {
			return account;
		}
	}

	protected void removeAccount() {
		serviceAccount.remove(account.getId());

	}

	protected Invoice createdInvoice() {
		LOGGER.info("prepare data for InvoiceServiceTest");
		invoice = new Invoice();
		invoice.setMonth(1);
		invoice.setQuantity(10);
		invoice.setSum(990);
		invoice.setType("mb");
		invoice.setYear(2018);
		return invoice;

	}

	protected void removeInvoice() {
		serviceInvoice.remove(invoice.getId());

	}

	protected Operator createdOperator() {
		LOGGER.info("prepare data for OperatorServiceTest");
		operator = new Operator();
		operator.setTitle("MTS");
		operator.setDeleted(false);
		if (operator.getId() == null) {
			return serviceOperator.save(operator);
		} else {
			return operator;
		}
	}

	protected void removeOperator() {
		serviceOperator.remove(operator.getId());

	}

	protected PhoneNumber createdPhoneNumber() {
		LOGGER.info("prepare data for PhoneNumberServiceTest");
		phoneNumber = new PhoneNumber();
		phoneNumber.setAccountId(account.getId());
		phoneNumber.setNumber("375297777777");
		phoneNumber.setTariffId(tariff.getId());
		if (phoneNumber.getId() == null) {
			return servicePhoneNumber.save(phoneNumber);
		} else {
			return phoneNumber;
		}
	}

	protected void removePhoneNumber() {
		servicePhoneNumber.remove(phoneNumber.getId());

	}

	protected ServiceHistory createdServiceHistory() {
		LOGGER.info("prepare data for ServiceHistoryServiceTest");
		serviceHistory = new ServiceHistory();
		serviceHistory.setData(new Date());
		serviceHistory.setPhoneNumberId(phoneNumber.getId());
		serviceHistory.setQuantity(10);
		serviceHistory.setSum(990);
		serviceHistory.setTariffItemId(tariffItem.getId());
		return serviceHistory;

	}

	protected void removeServiceHistory() {
		serviceServiceHistory.remove(serviceHistory.getId());

	}

	protected Service createdService() {
		LOGGER.info("prepare data for ServiceServiceTest");
		service = new Service();
		service.setType("звонки");
		service.setUnit("мин");
		service.setDeleted(false);
		if (service.getId() == null) {
			return serviceService.save(service);
		} else {
			return service;
		}
	}

	protected void removeService() {
		serviceService.remove(service.getId());

	}

	protected TariffItem createdTariffItem() {
		LOGGER.info("prepare data for TariffItemServiceTest");
		tariffItem = new TariffItem();
		tariffItem.setCost(10);
		tariffItem.setServiceId(service.getId());
		tariffItem.setTariffId(tariff.getId());
		tariffItem.setDeleted(false);
		if (tariffItem.getId() == null) {
			return serviceTariffItem.save(tariffItem);
		} else {
			return tariffItem;
		}
	}

	protected void removeTariffItem() {
		serviceTariffItem.remove(tariffItem.getId());

	}

	protected Tariff createdTariff() {
		LOGGER.info("prepare data for TariffServiceTest");
		tariff = new Tariff();
		tariff.setName("Отличный");
		tariff.setOperatorId(operator.getId());
		tariff.setDeleted(false);
		if (tariff.getId() == null) {
			return serviceTariff.save(tariff);
		} else {
			return tariff;
		}
	}

	protected void removeTariff() {
		serviceTariff.remove(tariff.getId());

	}
}
