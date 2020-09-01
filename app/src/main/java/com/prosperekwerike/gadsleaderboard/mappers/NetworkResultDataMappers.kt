package com.prosperekwerike.gadsleaderboard.mappers

import com.prosperekwerike.gadsleaderboard.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.network.LearningLeadersNetworkModel
import com.prosperekwerike.gadsleaderboard.domain.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.network.SkillsIQLeadersNetworkModel

fun List<LearningLeadersNetworkModel>.convertToLearningLeadersCustomModel() :
      List<LearningLeadersCustomModel>{
    return map{
        LearningLeadersCustomModel(
            leaderName = it.name,
            learningBadgeUrl = it.badgeUrl,
            hoursAndCountry = "${it.hours} learning hours, ${it.country}."
        )
    }
}

fun List<SkillsIQLeadersNetworkModel>.convertToSkillsIQLeadersCustomModel() :
        List<SkillsIQLeadersCustomModel>{
    return map{
        SkillsIQLeadersCustomModel(
            leaderName = it.name,
            leaderBadgeUrl = it.badgeUrl,
            leaderIQScoreAndCountry =  "${it.score} skill IQ Score, ${it.country}"
        )
    }
}

