package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.List;


public class JobRespone extends BaseRespone {
	
	private List<JobResultDTO> list;

	public List<JobResultDTO> getList() {
		return list;
	}

	public void setList(List<JobResultDTO> list) {
		this.list = list;
	}
}
