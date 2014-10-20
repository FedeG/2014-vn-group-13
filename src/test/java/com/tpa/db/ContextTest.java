package com.tpa.db;

import org.junit.Test;

import com.tpa.app.db.EntityManagerHelper;

public class ContextTest {

	@Test
	public void contextUp() {
		EntityManagerHelper.getEntityManager();
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		EntityManagerHelper.withTransaction(() -> {});
	}
}
