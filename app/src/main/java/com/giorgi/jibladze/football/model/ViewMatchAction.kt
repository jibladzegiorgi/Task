package com.giorgi.jibladze.football.model

import java.io.Serializable

data class ViewMocky(
    val resultCode: Int? = null,
    val match: ViewMatch? = null
)

data class ViewMatch(
    val matchTime: String? = null,
    val team1: ViewTeam? = null,
    val team2: ViewTeam? = null,
    val stadiumAddress: String? = null,
    val matchSummary:ViewMatchSummaries? = null,
    val matchDate: Long? = null
) : Serializable

data class ViewTeam(
    val teamName: String? = null,
    val teamImage: String? = null,
    val score: Int? = null,
    val ballPosition: String? = null
): Serializable

data class ViewMatchSummaries(
    val matchActionList: List<ViewMatchAction>
) : Serializable

data class ViewMatchAction(
    var isHeader: Boolean =false,
    val actionTime: String? = null,
    val team1Action:  List<ViewTeam1Action>? = null,
    val team2Action:  List<ViewTeam1Action>? = null
): Serializable

data class ViewTeam1Action(
    val actionType: MatchActionType? = null,
    val teamType: MatchTeamType? = null,
    val action: ViewAction? = null
): Serializable

data class ViewAction(
    val goalType: GoalType? = null,
    val player: ViewPlayer? = null,
    val player1: ViewPlayer? = null,
    val player2: ViewPlayer? = null
): Serializable

data class ViewPlayer(
    val playerName: String? = null,
    val playerImage: String? = null
): Serializable