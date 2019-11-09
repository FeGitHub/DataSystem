package com.DS.pams.vo;

public class QuartzTransferVo {
    private String dataStr;//时间字符串
    private String period;//周期
    private String weekType;//具体的星期
	public String getDataStr() {
		return dataStr;
	}
	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getWeekType() {
		return weekType;
	}
	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}
}
