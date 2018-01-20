package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Account;
public class AccountServiceTest extends AbstractServiceTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AccountServiceTest.class);

	@Test
	public void testCRUD() {
		LOGGER.info("test CRUD");
		Account account = new Account();
		try {
			serviceAccount.save(account);
			Assert.fail("account without email, pass not be saved");
		} catch (Exception e) {
			LOGGER.info("impossible to save account ");
		}
		// fill the properties
		account.setEmail("email");
		account.setPassword("pass");
		// now save the account
		LOGGER.info("test save account");
		serviceAccount.save(account);
		// try to get by ID and check each field
		LOGGER.info("try to get by ID and check each field");
		Account accountFromDB = serviceAccount.get(account.getId());
		Assert.assertEquals(account.getId(), accountFromDB.getId());
		Assert.assertNotNull(account.getCreated());
		Assert.assertNotNull(account.getModified());

		// update account
		LOGGER.info("test update account");
		Account accountUpdate = serviceAccount.get(account.getId());
		accountUpdate.setEmail("emailNew");
		serviceAccount.save(accountUpdate);

		// get all account
		LOGGER.info("test get all account");
		List<Account> listAccount = serviceAccount.getAll();

		// remove account
		LOGGER.info("test remove account");
		serviceAccount.remove(account.getId());

		// check that account was removed
		LOGGER.info("check that account was removed");
		try {
			Assert.assertNull(serviceAccount.get(account.getId()));
		} catch (Exception e) {
			LOGGER.info("account with this id does not exist");
		}
	}
}