package com.DS.common.model;
import com.DS.Model.QrtzJobDetailsModel;
import com.DS.Model.RemindModel;
import com.DS.Model.TestModel;
import com.DS.Model.UserModel;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
/****
 * 表的映射
 * @author jeff
 *  
 */
public class _MappingKit {
    public static void mapping(ActiveRecordPlugin arp) {
    	 arp.addMapping("blog", TestModel.class);//用于测试
    	 arp.addMapping("user", UserModel.class);//用户信息
    	 arp.addMapping("qrtz_job_details", QrtzJobDetailsModel.class);//调度器任务信息   	  
    	 arp.addMapping("ds_remind", RemindModel.class);//备忘信息
    }
}
