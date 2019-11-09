package com.DS.config;
import com.jfinal.plugin.activerecord.generator.MetaBuilder;
import javax.sql.DataSource;
/****
 *  用于配置要生成的表的model
 *
 */
public class MyMetaBulider extends MetaBuilder{
	public MyMetaBulider(DataSource dataSource) {
        super(dataSource);
    }
    
    @Override
    protected boolean isSkipTable(String tableName) {
    	//此处填入需要生成model的对应表
    	String[] tableArray = {
    			"user",
    			"menu",
    			"remind",
    			"project_tree",
    			"project",
    			"notification",
    			"task",
    			"analyse"
    			};
    	for(int i=0;i<tableArray.length;i++){
    		if(tableName.equals(tableArray[i])){
    			return false;
    		}
    	}
        return true;
    }
}
