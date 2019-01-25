package com.DS.bean;

import java.util.Date;

import com.DS.utils.excelutil.ExcelCell;

public class FinanceExcelBean {
	@ExcelCell(index = 0)
    private Double monenyValue;
	@ExcelCell(index = 1)
	private Date  financeTime;
	@ExcelCell(index = 2)
    private String weekType;
    
   
	public Double getMonenyValue() {
		return monenyValue;
	}

	public void setMonenyValue(Double monenyValue) {
		this.monenyValue = monenyValue;
	}


	public String getWeekType() {
		return weekType;
	}

	public Date getFinanceTime() {
		return financeTime;
	}

	public void setFinanceTime(Date financeTime) {
		this.financeTime = financeTime;
	}

	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}

	
}
