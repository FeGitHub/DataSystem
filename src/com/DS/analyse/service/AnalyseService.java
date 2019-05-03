package com.DS.analyse.service;

import java.util.List;

import com.DS.common.model.User;

public interface AnalyseService {
    List<String>  customAnalyse(String heads,String customfile);	
    
    
    boolean  updateTaskAnalyse(User user);
    
    String getAnalyseResult(String functionArgs,List<String> param);
}
