package com.DS.config;
import com.jfinal.plugin.activerecord.generator.MetaBuilder;
import javax.sql.DataSource;

public class MyMetaBulider extends MetaBuilder{
	public MyMetaBulider(DataSource dataSource) {
        super(dataSource);
    }
    
    @Override
    protected boolean isSkipTable(String tableName) {
    	//此处填入需要生成model的表
    	String[] tableArray = {
    			"user",
    			"menu",
    			"ds_remind",
    			"notification"
    			};
    	for(int i=0;i<tableArray.length;i++){
    		if(tableName.equals(tableArray[i])){
    			return false;
    		}
    	}
        return true;
    }
}
