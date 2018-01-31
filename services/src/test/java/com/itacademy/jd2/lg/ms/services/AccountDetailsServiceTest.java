package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.ms.dao.dbmodel.AccountDetails;

public class AccountDetailsServiceTest extends AbstractServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDetailsServiceTest.class);

	@Test
	public void testCRUD() {
		LOGGER.info("test CRUD");
		AccountDetails accountDetails = new AccountDetails();
		try {
			serviceAccountDetails.save(accountDetails);
			Assert.fail("AccountDetails not be saved");
		} catch (Exception e) {
			LOGGER.info("impossible to save AccountDetails ");
		}
		// fill the properties
		accountDetails.setId(19);
		accountDetails.setFirstName("Name");
		accountDetails.setLastName("LastName");
		// now save the account
		LOGGER.info("test save accountDetails");
		serviceAccountDetails.save(accountDetails);
		// try to get by ID and check each field
		LOGGER.info("try to get by ID and check each field");
		AccountDetails accountDetailsFromDB = serviceAccountDetails.get(accountDetails.getId());
		Assert.assertEquals(accountDetails.getId(), accountDetailsFromDB.getId());
		Assert.assertNotNull(accountDetails.getCreated());
		Assert.assertNotNull(accountDetails.getModified());

		// update account
		LOGGER.info("test update accountDetails");
		AccountDetails accountDetailsUpdate = serviceAccountDetails.get(accountDetails.getId());
		accountDetailsUpdate.setLastName("New LastName");
		serviceAccountDetails.save(accountDetailsUpdate);

		// get all account
		LOGGER.info("test get all accountDetails");
		List<AccountDetails> listAccountDetails = serviceAccountDetails.getAll();

		// remove account
		LOGGER.info("test remove accountDetails");
		serviceAccountDetails.remove(accountDetails.getId());

		// check that account was removed
		LOGGER.info("check that accountDetails was removed");
		try {
			Assert.assertNull(serviceAccountDetails.get(accountDetails.getId()));
		} catch (Exception e) {
			LOGGER.info("accountDetails with this id does not exist");
		}
	}
}