package com.itacademy.jd2.dz.library.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Client;
import com.itacademy.jd2.lg.mobile_system.services.IClientServices;
import com.itacademy.jd2.lg.mobile_system.services.impl.ClientServiceImpl;

public class ClientServiceTest {

	private IClientServices service = new ClientServiceImpl();

	@Test
	public void testGetById() {
		Client client = service.get(2);
		Assert.assertNotNull(client);
	}

	@Test
	public void testRemove() {
		service.remove(2);
		Assert.assertNull(service.get(2));
	}
}
