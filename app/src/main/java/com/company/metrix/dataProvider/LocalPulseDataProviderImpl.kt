package com.company.metrix.dataProvider

import com.company.metrix.data.model.Pulse
import com.company.metrix.db.dao.PulseDao
import com.company.metrix.db.entity.PulseEntity
import javax.inject.Inject

class LocalPulseDataProviderImpl @Inject constructor(val dao: PulseDao) : LocalPulseDataProvider {
    override suspend fun insertPulse(item: Pulse) {
        dao.insertPulse(
            PulseEntity(
                id = item.id,
                question_id = item.question_id,
                team_id = item.team_id,
                companyName = item.companyName,
                votesOne = item.votesOne,
                votesTwo = item.votesTwo,
                votesThree = item.votesThree,
                votesFour = item.votesFour
            )
        )
    }

    override suspend fun getAllPulse(): List<Pulse> = dao.getAllPulse().map {
        Pulse(
            id = it.id,
            question_id = it.question_id,
            team_id = it.team_id,
            companyName = it.companyName,
            votesOne = it.votesOne,
            votesTwo = it.votesTwo,
            votesThree = it.votesThree,
            votesFour = it.votesFour
        )
    }

    override suspend fun getPulseByCompany(companyName: String): Pulse {
        val item = dao.getPulseByCompany(companyName)

        return Pulse(
            id = item.id,
            question_id = item.question_id,
            team_id = item.team_id,
            companyName = item.companyName,
            votesOne = item.votesOne,
            votesTwo = item.votesTwo,
            votesThree = item.votesThree,
            votesFour = item.votesFour
        )
    }

    override suspend fun deletePulse(item: Pulse) = dao.deletePulse(
        PulseEntity(
            id = item.id,
            question_id = item.question_id,
            team_id = item.team_id,
            companyName = item.companyName,
            votesOne = item.votesOne,
            votesTwo = item.votesTwo,
            votesThree = item.votesThree,
            votesFour = item.votesFour
        )
    )

    override suspend fun updatePulse(item: Pulse) =
        dao.updatePulse(
            PulseEntity(
                id = item.id,
                question_id = item.question_id,
                team_id = item.team_id,
                companyName = item.companyName,
                votesOne = item.votesOne,
                votesTwo = item.votesTwo,
                votesThree = item.votesThree,
                votesFour = item.votesFour
            )
        )

    override suspend fun getPulseByCompanyAndIdQuestion(companyName: String, question_id: Long, team_id : Long): Pulse {
        val item = dao.getPulseByCompanyAndIdQuestion(companyName, question_id, team_id)

        return Pulse(
            id = item.id,
            question_id = item.question_id,
            team_id = item.team_id,
            companyName = item.companyName,
            votesOne = item.votesOne,
            votesTwo = item.votesTwo,
            votesThree = item.votesThree,
            votesFour = item.votesFour
        )
    }

}