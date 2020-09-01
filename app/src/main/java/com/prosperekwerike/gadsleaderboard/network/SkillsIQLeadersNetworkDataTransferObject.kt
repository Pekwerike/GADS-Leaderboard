package com.prosperekwerike.gadsleaderboard.network

data class SkillsIQLeadersNetworkDataTransferObject(
    val name : String,
    val score : Int,
    val country : String,
    val badgeUrl : String
)