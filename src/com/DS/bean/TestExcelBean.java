package com.DS.bean;

import com.DS.utils.excelutil.ExcelCell;

public class TestExcelBean {
	@ExcelCell(index = 0)
    private String column1;
   
    @ExcelCell(index = 1)
    private String column2;	
    
	@ExcelCell(index = 2)
    private String column3;
	
	@ExcelCell(index = 3)
    private String column4;
    
    public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public Double getColumn5() {
		return column5;
	}

	public void setColumn5(Double column5) {
		this.column5 = column5;
	}

	public Double getColumn6() {
		return column6;
	}

	public void setColumn6(Double column6) {
		this.column6 = column6;
	}

	public Double getColumn7() {
		return column7;
	}

	public void setColumn7(Double column7) {
		this.column7 = column7;
	}

	public Double getColumn8() {
		return column8;
	}

	public void setColumn8(Double column8) {
		this.column8 = column8;
	}

	@ExcelCell(index = 4)
    private Double column5;
   
    @ExcelCell(index = 5)
    private Double column6;   

	@ExcelCell(index = 6)
    private Double column7;
    
    @ExcelCell(index = 7)
    private Double column8;
}
