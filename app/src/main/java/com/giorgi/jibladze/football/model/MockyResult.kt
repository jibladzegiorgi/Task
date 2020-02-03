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
    val matchTime: String? = null,

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
    val ballPosition: String? = null
)

data class MatchSummary(
    @SerializedName("summaries")
    val summaries: List<Summaries>? = null
)

data class Summaries(
    @SerializedName("actionTime")
    val actionTime: String? = null,

    @SerializedName("team1Action")
    val team1Action: List<Team1Action>? = null,

    @SerializedName("team2Action")
    val team2Action: List<Team1Action>? = null
)

data class Team1Action(
    @SerializedName("actionType")
    val actionType: MatchActionType? = null,

    @SerializedName("teamType")
    val teamType: MatchTeamType? = null,

    @SerializedName("action")
    val action: Action? = null
)

data class Action(
    @SerializedName("goalType")
    val goalType: GoalType? = null,

    @SerializedName("player")
    val player: Player? = null,

    @SerializedName("player1")
    val player1: Player? = null,

    @SerializedName("player2")
    val player2: Player? = null
)

data class Player(
    @SerializedName("playerName")
    val playerName: String? = null,

    @SerializedName("playerImage")
    val playerImage: String? = null
)