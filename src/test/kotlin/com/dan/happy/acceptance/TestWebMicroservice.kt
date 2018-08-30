package com.dan.happy.acceptance

import com.dan.happy.makeServer
import com.oneeyedmen.okeydoke.junit.ApprovalsRule
import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.BufferedReader


class TestWebMicroservice {

    private val port = 9090
    private val client = ApacheClient()

    val server = makeServer(port)

    @Rule
    @JvmField
    val approval: ApprovalsRule = ApprovalsRule.fileSystemRule("src/test/resources/approvals")

    @Before
    fun startUp() {
        server.start()
    }

    @After
    fun tearDown() {
        server.stop()
    }

    @Test
    fun `can get list of matches from webservice`() {
        approval.assertApproved(client(Request(Method.GET, "http://localhost:9090/findmatches")).bodyString())
    }

}