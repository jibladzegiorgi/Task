package com.giorgi.jibladze.football.model

import com.google.gson.annotations.SerializedName

enum class GoalType {
    @SerializedName("1")
    GOAL,
    @SerializedName("2")
    OWN_GOAL;
}