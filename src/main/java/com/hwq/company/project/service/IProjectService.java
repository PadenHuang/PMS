package com.hwq.company.project.service;

/*import com.situ.company.project.model.ProjectModel;*/

import com.hwq.company.project.model.ProjectModel;

import java.util.List;

public interface IProjectService {
    String insert(ProjectModel model);

    String delete(ProjectModel model);

    String deleteByCode(ProjectModel model);

    String update(ProjectModel model);

    List<ProjectModel> SelectList(ProjectModel model);

    ProjectModel SelectModel(ProjectModel model);

    Integer selectCount(ProjectModel model);
}
