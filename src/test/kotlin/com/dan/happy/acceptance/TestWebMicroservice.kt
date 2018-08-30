package com.dan.happy.acceptance

import com.oneeyedmen.okeydoke.junit.ApprovalsRule
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.junit.Rule
import org.junit.Test
import java.io.BufferedReader


class TestWebMicroservice {

    @Rule
    @JvmField
    val approval: ApprovalsRule = ApprovalsRule.fileSystemRule("src/test/resources/approvals")

    @Test
    fun `can get list of matches from webservice`() {
        approval.assertApproved(runMatches(Request(Method.GET, "/findmatches")).bodyString())
    }

    fun runMatches(r: Request): Response {
        val data = Any().javaClass.getResourceAsStream("/matches.json").bufferedReader().use(BufferedReader::readText)
        return Response(Status.OK).body(data)
    }

}