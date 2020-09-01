package com.prosperekwerike.gadsleaderboard.mappers

import com.prosperekwerike.gadsleaderboard.models.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.models.LearningLeadersModel
import com.prosperekwerike.gadsleaderboard.models.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.models.SkillsIQLeadersModel

fun List<LearningLeadersModel>.convertToLearningLeadersCustomModel() :
      List<LearningLeadersCustomModel>{
    return map{
        LearningLeadersCustomModel(
            leaderName = it.name,
            learningBadgeUrl = it.badgeUrl,
            hoursAndCountry = "${it.hours} learning hours, ${it.country}."
        )
    }
}

fun List<SkillsIQLeadersModel>.convertToSkillsIQLeadersCustomModel() :
        List<SkillsIQLeadersCustomModel>{
    return map{
        SkillsIQLeadersCustomModel(
            leaderName = it.name,
            leaderBadgeUrl = it.badgeUrl,
            leaderIQScoreAndCountry =  "${it.score} skill IQ Score, ${it.country}"
        )
    }
}

