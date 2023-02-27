package com.hwq.company.department.service;

import com.hwq.company.department.model.DepartmentModel;
/*import com.situ.company.department.model.DepartmentModel;*/

import java.util.List;

public interface IDepartmentService {
    String insert(DepartmentModel model);

    String delete(DepartmentModel model);

    String deleteByCode(DepartmentModel model);

    String update(DepartmentModel model);

    List<DepartmentModel> selectList(DepartmentModel model);

    DepartmentModel selectModel(DepartmentModel model);

    Integer selectCount(DepartmentModel model);
}
