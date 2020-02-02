package com.giorgi.jibladze.football.network.data

import com.giorgi.jibladze.football.model.*
import com.giorgi.jibladze.football.network.MediatorUseCase
import javax.inject.Inject

class LoadMockyUseCase @Inject constructor(
    private val mockyRepository: MockyRepository
) : MediatorUseCase<Unit, ViewMocky>() {

    override fun execute(parameters: Unit) {
        result.addSource(mockyRepository.getMocky()) {
            (it as? Result.Success)?.data?.let { data ->
                result.postValue(Result.Success(convertDataToMainUIModel(data)))
            }
        }
    }

    private fun convertDataToMainUIModel(mockyResult: MockyResult?): ViewMocky {
        val matchActionList = ArrayList<ViewMatchAction>()
        mockyResult?.match?.matchSummary?.summaries?.forEach { action ->
            val team1Action = ArrayList<ViewTeam1Action>()
            action.team1Action?.forEach { tm ->
                team1Action.add(
                    ViewTeam1Action(
                        tm.actionType,
                        tm.teamType,
                        ViewAction(
                            tm.action?.goalType,
                            ViewPlayer(
                                tm.action?.player?.playerName,
                                tm.action?.player?.playerImage
                            )
                        )
                    )
                )
            }

            val team1Action2 = ArrayList<ViewTeam1Action>()
            action.team2Action?.forEach { teamAction ->
                team1Action2.add(
                    ViewTeam1Action(
                        teamAction.actionType,
                        teamAction.teamType,
                        ViewAction(
                            teamAction.action?.goalType,
                            ViewPlayer(
                                teamAction.action?.player?.playerName,
                                teamAction.action?.player?.playerImage
                            )
                        )
                    )
                )
            }

            matchActionList.add(
                ViewMatchAction(
                    actionTime = action.actionTime,
                    team1Action = team1Action,
                    team2Action = team1Action2
                )
            )
        }

        return ViewMocky(
            mockyResult?.resultCode,
            ViewMatch(
                mockyResult?.match?.matchTime,
                ViewTeam(
                    mockyResult?.match?.team1?.teamName,
                    mockyResult?.match?.team1?.teamImage,
                    mockyResult?.match?.team1?.score,
                    mockyResult?.match?.team1?.ballPosition
                ),
                ViewTeam(
                    mockyResult?.match?.team2?.teamName,
                    mockyResult?.match?.team2?.teamImage,
                    mockyResult?.match?.team2?.score,
                    mockyResult?.match?.team2?.ballPosition
                ),
                mockyResult?.match?.stadiumAddress,
                ViewMatchSummaries(matchActionList),
                mockyResult?.match?.matchDate
            )
        )
    }

}