package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.List;


public class DepartmentRespone extends BaseRespone {
	private List<DepartmentResultDTO> list;

	public List<DepartmentResultDTO> getList() {
		return list;
	}

	public void setList(List<DepartmentResultDTO> list) {
		this.list = list;
	}
}
