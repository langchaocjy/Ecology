package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.List;


public class BudgetRespone extends BaseRespone {
	
	private List<BudgetResultDTO> list;

	public List<BudgetResultDTO> getList() {
		return list;
	}

	public void setList(List<BudgetResultDTO> list) {
		this.list = list;
	}
}
