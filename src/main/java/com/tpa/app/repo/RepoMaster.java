package com.tpa.app.repo;

public class RepoMaster {

	private static final RepoMaster instance = new RepoMaster();

	public static RepoMaster getInstance() {
		return instance;
	}
}
