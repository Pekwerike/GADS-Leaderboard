package com.prosperekwerike.gadsleaderboard.mappers

import com.prosperekwerike.gadsleaderboard.models.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.models.LearningLeadersModel

fun List<LearningLeadersModel>.convertToLearningLeadersCustomModel() :
      List<LearningLeadersCustomModel>{
    return map{
        LearningLeadersCustomModel(
            leaderName = it.name,
            learningBadgeUrl = it.badgeUrl,
            hoursAndCountry = "${it.hours} learning hours, ${it.country}"
        )
    }
}

