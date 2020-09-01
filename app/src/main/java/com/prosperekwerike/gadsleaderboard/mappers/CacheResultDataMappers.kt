package com.prosperekwerike.gadsleaderboard.mappers

import com.prosperekwerike.gadsleaderboard.cache.LearningLeadersEntity
import com.prosperekwerike.gadsleaderboard.cache.SkillsIQLeadersEntity
import com.prosperekwerike.gadsleaderboard.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.domain.SkillsIQLeadersCustomModel

//function to map database learningLeadersEntity to custom domain models
fun List<LearningLeadersEntity>.convertToLearningLeadersCustomModel()
        : List<LearningLeadersCustomModel> {
    return map {
        LearningLeadersCustomModel(
            learningBadgeUrl = it.learningBadgeUrl,
            leaderName = it.leaderName,
            hoursAndCountry = it.hoursAndCountry
        )
    }
}

//function to map database skillIQLeaders to custom domain models
fun List<SkillsIQLeadersEntity>.convertToSkillsIQLeadersCustomModel()
        : List<SkillsIQLeadersCustomModel> {
    return map {
        SkillsIQLeadersCustomModel(
            leaderName = it.leaderName,
            leaderBadgeUrl = it.leaderBadgeUrl,
            leaderIQScoreAndCountry = it.leaderIQScoreAndCountry
        )
    }
}