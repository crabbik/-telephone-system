package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;

public class OperatorServiceTest extends AbstractServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(OperatorServiceTest.class);

	@Test
	public void testCRUD() {
		LOGGER.info("test CRUD");
		Operator operator = new Operator();
		try {
			serviceOperator.save(operator);
			Assert.fail("operator without email, pass not be saved");
		} catch (Exception e) {
			LOGGER.info("impossible to save operator ");
		}
		// fill the properties
		operator.setTitle("mts");
		// now save the account
		LOGGER.info("test save account");
		serviceOperator.save(operator);
		// try to get by ID and check each field
		LOGGER.info("try to get by ID and check each field");
		Operator operatorFromDB = serviceOperator.get(operator.getId());
		Assert.assertEquals(operator.getId(), operatorFromDB.getId());
		Assert.assertNotNull(operator.getCreated());
		Assert.assertNotNull(operator.getModified());

		// update account
		LOGGER.info("test update account");
		Operator operatorUpdate = serviceOperator.get(operator.getId());
		operatorUpdate.setTitle("new titel");
		;
		serviceOperator.save(operatorUpdate);

		// get all account
		LOGGER.info("test get all account");
		List<Operator> listOperator = serviceOperator.getAll();

		// remove account
		LOGGER.info("test remove account");
		serviceOperator.remove(operator.getId());

		// check that account was removed
		LOGGER.info("check that account was removed");
		try {
			Assert.assertNull(serviceOperator.get(operator.getId()));
		} catch (Exception e) {
			LOGGER.info("account with this id does not exist");
		}
	}
}