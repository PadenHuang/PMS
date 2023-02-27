package com.hwq.company.score.service;

/*import com.situ.company.score.model.ScoreModel;*/

import com.hwq.company.score.model.ScoreModel;

import java.util.List;

public interface IScoreService {
    String insert(ScoreModel model);

    String delete(ScoreModel model);

    String deleteByCode(ScoreModel model);

    String update(ScoreModel model);

    List<ScoreModel> selectList(ScoreModel model);

    ScoreModel selectModel(ScoreModel model);

    Integer selectCount(ScoreModel model);
}
