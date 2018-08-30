package com.dan.happy

import com.dan.happy.PPJackson.auto
import org.http4k.core.Body
import org.http4k.core.Response
import org.http4k.core.Status
import java.io.BufferedReader

class MatchService {

    private val lens = Body.auto<MatchResult>().toLens()

    fun findMatches(): List<Match> {
        val data = Any().javaClass.getResourceAsStream("/matches.json").bufferedReader().use(BufferedReader::readText)
        return lens.extract(Response(Status.OK).body(data)).matches
    }

}
