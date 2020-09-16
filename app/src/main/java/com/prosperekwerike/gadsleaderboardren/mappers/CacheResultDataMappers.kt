package com.prosperekwerike.gadsleaderboardren.mappers

import com.prosperekwerike.gadsleaderboardren.cache.LearningLeadersEntity
import com.prosperekwerike.gadsleaderboardren.cache.SkillsIQLeadersEntity
import com.prosperekwerike.gadsleaderboardren.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboardren.domain.SkillsIQLeadersCustomModel

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