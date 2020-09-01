package com.prosperekwerike.gadsleaderboard.localdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learning_leaders_table")
data class LearningLeadersEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "leader_name")
    val leaderName: String,
    @ColumnInfo(name = "hours_and_country")
    val hoursAndCountry: String,
    @ColumnInfo(name = "learning_badge_url")
    val learningBadgeUrl: String
)