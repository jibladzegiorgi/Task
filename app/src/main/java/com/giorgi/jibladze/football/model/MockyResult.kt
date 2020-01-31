package com.giorgi.jibladze.football.model

import com.google.gson.annotations.SerializedName

data class MockyResult(
    @SerializedName("resultCode")
    val resultCode: Int? = null,

    @SerializedName("match")
    val match: Match? = null
)

data class Match(

    @SerializedName("matchTime")
    val matchTime: Double? = null,

    @SerializedName("team2")
    val team2: Team? = null,

    @SerializedName("team1")
    val team1: Team? = null,

    @SerializedName("stadiumAdress")
    val stadiumAddress: String? = null,

    @SerializedName("matchSummary")
    val matchSummary: MatchSummary? = null,

    @SerializedName("matchDate")
    val matchDate: Long? = null
)

data class Team(
    @SerializedName("teamName")
    val teamName: String? = null,

    @SerializedName("teamImage")
    val teamImage: String? = null,

    @SerializedName("score")
    val score: Int? = null,

    @SerializedName("ballPosition")
    val ballPosition: Int? = null
)

data class MatchSummary(
    @SerializedName("summaries")
    val summaries: List<Summaries>? = null
)

data class Summaries(
    @SerializedName("actionTime")
    val actionTime: String? = null,

    @SerializedName("team1Action")
    val team1Action: Team1Action? = null
)

data class Team1Action(
    @SerializedName("actionType")
    val actionType: Int? = null,

    @SerializedName("teamType")
    val teamType: Int? = null,

    @SerializedName("action")
    val action: Action? = null
)

data class Action(
    @SerializedName("goalType")
    val goalType: Int? = null,

    @SerializedName("player")
    val player: Player? = null
)

data class Player(
    @SerializedName("playerName")
    val playerName: String? = null,

    @SerializedName("playerImage")
    val playerImage: String? = null
)