package com.prosperekwerike.gadsleaderboard.mappers

import com.prosperekwerike.gadsleaderboard.cache.LearningLeadersEntity
import com.prosperekwerike.gadsleaderboard.cache.SkillsIQLeadersEntity
import com.prosperekwerike.gadsleaderboard.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.domain.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.network.LearningLeadersNetworkModel
import com.prosperekwerike.gadsleaderboard.network.SkillsIQLeadersNetworkModel

//function to map network result of learning leaders to database entity for caching
fun List<LearningLeadersNetworkModel>.convertToLearningLeadersEntity():
        List<LearningLeadersEntity> {
    return map {
        LearningLeadersEntity(
            leaderName = it.name,
            learningBadgeUrl = it.badgeUrl,
            hoursAndCountry = "${it.hours} learning hours, ${it.country}."
        )
    }
}

//function to map network result of skills IQ leaders to database entity for caching
fun List<SkillsIQLeadersNetworkModel>.convertToSkillsIQLeadersEntity():
        List<SkillsIQLeadersEntity> {
    return map {
        SkillsIQLeadersEntity(
            leaderName = it.name,
            leaderBadgeUrl = it.badgeUrl,
            leaderIQScoreAndCountry = "${it.score} skill IQ Score, ${it.country}"
        )
    }
}

fun List<LearningLeadersNetworkModel>.convertToLearningLeadersCustomModel():
        List<LearningLeadersCustomModel> {
    return map {
        LearningLeadersCustomModel(
            leaderName = it.name,
            learningBadgeUrl = it.badgeUrl,
            hoursAndCountry = "${it.hours} learning hours, ${it.country}."
        )
    }
}

fun List<SkillsIQLeadersNetworkModel>.convertToSkillsIQLeadersCustomModel():
        List<SkillsIQLeadersCustomModel> {
    return map {
        SkillsIQLeadersCustomModel(
            leaderName = it.name,
            leaderBadgeUrl = it.badgeUrl,
            leaderIQScoreAndCountry = "${it.score} skill IQ Score, ${it.country}"
        )
    }
}




