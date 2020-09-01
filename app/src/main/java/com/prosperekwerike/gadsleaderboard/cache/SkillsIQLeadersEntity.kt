package com.prosperekwerike.gadsleaderboard.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skills_iq_leaders_table")
data class SkillsIQLeadersEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "leader_name")
    val leaderName: String,
    @ColumnInfo(name = "leader_badge_url")
    val leaderBadgeUrl: String,
    @ColumnInfo(name = "leader_iq_score_and_country")
    val leaderIQScoreAndCountry: String
)