package com.DS.task.service;

import java.util.List;

import com.DS.common.model.ProjectTree;

public interface ProjectTreeService {
	List<ProjectTree> getTodayTreeTask(String userId);
}
