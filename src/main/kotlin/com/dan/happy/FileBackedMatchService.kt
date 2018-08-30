package com.dan.happy

import com.dan.happy.PPJackson.auto
import org.http4k.core.Body
import org.http4k.core.Response
import org.http4k.core.Status
import java.io.BufferedReader

class FileBackedMatchService(filename: String): MatchService {

    private val json = Any().javaClass.getResourceAsStream(filename).bufferedReader().use(BufferedReader::readText)
    private val allData = Body.auto<MatchResult>().toLens().extract(Response(Status.OK).body(json)).matches

    override fun findMatches(restrictions: Restrictions): List<Match> {

        fun Match.photoRestriction(): Boolean = restrictions.hasPhoto?.let {
           if (it) this.main_photo != null else this.main_photo == null
        } ?: true

        fun Match.contactRestriction(): Boolean = restrictions.inContact?.let {
            if (it) this.contacts_exchanged > 0 else this.contacts_exchanged == 0
        } ?: true

        return allData.filter {
            it.photoRestriction() && it.contactRestriction()
        }

    }

}
