package com.giorgi.jibladze.football.model

import com.google.gson.annotations.SerializedName

//GOAL (1);
//YELLOW_CARD (2);
//RED_CARD (3);
//SUBSTITUTION (4);

enum class MatchActionType {
    @SerializedName("1")
    GOAL,
    @SerializedName("2")
    YELLOW_CARD,
    @SerializedName("3")
    RED_CARD,
    @SerializedName("4")
    SUBSTITUTION;
}