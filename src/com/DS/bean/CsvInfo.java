package com.DS.bean;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public class CsvInfo {
    private List<Record> view;//用于页面展示的数据
    private String msg="";//提示信息
    private List<String> heads=null;
    private int rows=0;//数据行数
    private int cols=0;//列数
	public List<Record> getView() {
		return view;
	}
	public void setView(List<Record> view) {
		this.view = view;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<String> getHeads() {
		return heads;
	}
	public void setHeads(List<String> heads) {
		this.heads = heads;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
    
}
