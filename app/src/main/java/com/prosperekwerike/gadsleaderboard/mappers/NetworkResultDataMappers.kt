package com.prosperekwerike.gadsleaderboard.mappers

import com.prosperekwerike.gadsleaderboard.cache.LearningLeadersEntity
import com.prosperekwerike.gadsleaderboard.cache.SkillsIQLeadersEntity
import com.prosperekwerike.gadsleaderboard.network.LearningLeadersNetworkDataTranferObject
import com.prosperekwerike.gadsleaderboard.network.SkillsIQLeadersNetworkDataTransferObject

//function to map network result of learning leaders to database entity for caching
fun List<LearningLeadersNetworkDataTranferObject>.convertToLearningLeadersEntity():
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
fun List<SkillsIQLeadersNetworkDataTransferObject>.convertToSkillsIQLeadersEntity():
        List<SkillsIQLeadersEntity> {
    return map {
        SkillsIQLeadersEntity(
            leaderName = it.name,
            leaderBadgeUrl = it.badgeUrl,
            leaderIQScoreAndCountry = "${it.score} skill IQ Score, ${it.country}"
        )
    }
}





