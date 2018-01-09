package com.itacademy.jd2.dz.library.services;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Account;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.PhoneNumber;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Service;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.mobile_system.services.IAccount2RoleService;
import com.itacademy.jd2.lg.mobile_system.services.IAccountDetailsService;
import com.itacademy.jd2.lg.mobile_system.services.IAccountService;
import com.itacademy.jd2.lg.mobile_system.services.IInvoiceService;
import com.itacademy.jd2.lg.mobile_system.services.IOperatorService;
import com.itacademy.jd2.lg.mobile_system.services.IPhoneNumber2InvoiceService;
import com.itacademy.jd2.lg.mobile_system.services.IPhoneNumberService;
import com.itacademy.jd2.lg.mobile_system.services.IServiceHistoryService;
import com.itacademy.jd2.lg.mobile_system.services.IServiceService;
import com.itacademy.jd2.lg.mobile_system.services.ITariffItemService;
import com.itacademy.jd2.lg.mobile_system.services.ITariffService;
import com.itacademy.jd2.lg.mobile_system.services.impl.Account2RoleServiceImpl;
import com.itacademy.jd2.lg.mobile_system.services.impl.AccountDetailsServiceImpl;
import com.itacademy.jd2.lg.mobile_system.services.impl.AccountServiceImpl;
import com.itacademy.jd2.lg.mobile_system.services.impl.InvoiceServiceImpl;
import com.itacademy.jd2.lg.mobile_system.services.impl.OperatorServiceImpl;
import com.itacademy.jd2.lg.mobile_system.services.impl.PhoneNumber2InvoiceServiceImpl;
import com.itacademy.jd2.lg.mobile_system.services.impl.PhoneNumberServiceImpl;
import com.itacademy.jd2.lg.mobile_system.services.impl.ServiceHistoryServiceImpl;
import com.itacademy.jd2.lg.mobile_system.services.impl.ServiceServiceImpl;
import com.itacademy.jd2.lg.mobile_system.services.impl.TariffItemServiceImpl;
import com.itacademy.jd2.lg.mobile_system.services.impl.TariffServiceImpl;

public abstract class AbstractServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServiceTest.class);
	private static IAccount2RoleService serviceAccount2role = Account2RoleServiceImpl.ACCOUNT2ROLE_SERVICE;
	private static IAccountDetailsService serviceAccountDetails = AccountDetailsServiceImpl.ACCOUNTDETAILS_SERVICE;
	private static IAccountService serviceAccount = AccountServiceImpl.ACCOUNT_SERVICE;
	private static IInvoiceService serviceInvoice = InvoiceServiceImpl.INVOICE_SERVICE;
	private static IOperatorService serviceOperator = OperatorServiceImpl.OPERATOR_SERVICE;
	private static IPhoneNumber2InvoiceService servicePhoneNumber2Invoice = PhoneNumber2InvoiceServiceImpl.PHONENUMBER_2_INVOICE_DAO;
	private static IPhoneNumberService servicePhoneNumber = PhoneNumberServiceImpl.PHONENUMBER_SERVICE;
	private static IServiceHistoryService serviceServiceHistory = ServiceHistoryServiceImpl.SERVICEHISTORY_SERVICE;
	private static IServiceService serviceService = ServiceServiceImpl.SERVICE_SERVICE;
	private static ITariffItemService serviceTariffItem = TariffItemServiceImpl.TARIFFITEM_DAO;
	private static ITariffService serviceTariff = TariffServiceImpl.TARIFF_DAO;

	private AccountDetails accountDetails;
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
		accountDetails = new AccountDetails();
		accountDetails.setLastName("Иванов");
		accountDetails.setFirstName("Иван");
		if (accountDetails.getId() == null) {
			return serviceAccountDetails.save(accountDetails);
		} else {
			return accountDetails;
		}
	}

	protected void removeAccountDetails() {
		serviceAccountDetails.remove(accountDetails.getId());

	}

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
