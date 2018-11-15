package com.DS.Model;

import com.jfinal.plugin.activerecord.Model;
/**
 * 
 * @author jeff
 * 调度器任务类数据
 */
@SuppressWarnings("serial")
public class QrtzJobDetailsModel extends Model<QrtzJobDetailsModel>{
	public static final QrtzJobDetailsModel dao = new QrtzJobDetailsModel();
}
