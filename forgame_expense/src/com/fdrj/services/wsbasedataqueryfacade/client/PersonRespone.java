package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.List;

public class PersonRespone extends BaseRespone {
	
	private List<PersonResultDTO> list;

	public List<PersonResultDTO> getList() {
		return list;
	}

	public void setList(List<PersonResultDTO> list) {
		this.list = list;
	}

}
