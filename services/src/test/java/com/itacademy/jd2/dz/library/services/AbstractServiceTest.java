package com.itacademy.jd2.dz.library.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static IPhoneNumberService servicePhoneNumberService = PhoneNumberServiceImpl.PHONENUMBER_SERVICE;
	private static IServiceHistoryService serviceServiceHistory = ServiceHistoryServiceImpl.SERVICEHISTORY_SERVICE;
	private static IServiceService serviceService = ServiceServiceImpl.SERVICE_SERVICE;
	private static ITariffItemService serviceTariffItem = TariffItemServiceImpl.TARIFFITEM_DAO;
	private static ITariffService serviceTariff = TariffServiceImpl.TARIFF_DAO;

}
