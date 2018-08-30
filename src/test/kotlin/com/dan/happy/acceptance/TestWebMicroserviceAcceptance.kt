package com.dan.happy.acceptance

import com.dan.happy.City
import com.dan.happy.Match
import com.dan.happy.MatchService
import com.dan.happy.Restrictions
import com.dan.happy.WebServer
import com.oneeyedmen.okeydoke.junit.ApprovalsRule
import org.hamcrest.CoreMatchers.equalTo
import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestWebMicroserviceAcceptance {

    private val port = 9090
    private val client = ApacheClient()
    private val matcherService = DummyMatchService()
    val server = WebServer(matcherService).create(port)

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
        assertThat(matcherService.restrictedWith, equalTo(Restrictions()))
    }

    @Test
    fun `marshalls hasPhoto=true parameter to find service`() {
        client(Request(Method.GET, "http://localhost:9090/findmatches?hasPhoto=true"))
        assertThat(matcherService.restrictedWith, equalTo(
            Restrictions(
            hasPhoto = true)
        ))
    }

    @Test
    fun `marshalls hasPhoto=false parameter to find service`() {
        client(Request(Method.GET, "http://localhost:9090/findmatches?hasPhoto=false"))
        assertThat(matcherService.restrictedWith, equalTo(
            Restrictions(
                hasPhoto = false)
        ))
    }

    @Test
    fun `marshalls inContact=true parameter to find service`() {
        client(Request(Method.GET, "http://localhost:9090/findmatches?inContact=true"))
        assertThat(matcherService.restrictedWith, equalTo(
            Restrictions(
                inContact = true)
        ))
    }

    @Test
    fun `marshalls inContact=false parameter to find service`() {
        client(Request(Method.GET, "http://localhost:9090/findmatches?inContact=false"))
        assertThat(matcherService.restrictedWith, equalTo(
            Restrictions(
                inContact = false)
        ))
    }

    @Test
    fun `marshalls distance parameter to find service`() {
        client(Request(Method.GET, "http://localhost:9090/findmatches?distance=102"))
        assertThat(matcherService.restrictedWith, equalTo(
            Restrictions(
                distance = 102)
        ))
    }

    class DummyMatchService : MatchService {
        lateinit var restrictedWith: Restrictions
        override fun findMatches(restrictions: Restrictions, originCity: City?): List<Match> {
            restrictedWith = restrictions
            return listOf(
                Match(
                    "Caroline",
                    41,
                    "Corporate Lawyer",
                    153,
                    City("Leeds", 53.801277, -1.548567),
                    "http://thecatapi.com/api/images/get?format=src&type=gif",
                    0.76,
                    2,
                    true,
                    "Atheist"
                ),
                Match(
                    "Natalia",
                    38,
                    "Project Manager",
                    144,
                    City("Cardiff", 51.481583, -3.17909),
                    "http://thecatapi.com/api/images/get?format=src&type=gif",
                    0.47,
                    5,
                    false,
                    "Christian"
                    )
                )
        }

    }

}