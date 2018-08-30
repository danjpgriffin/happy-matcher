package com.dan.happy

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test


class TestFileBackedMatcherService {

    @Test
    fun `can return unrestricted set of data`() {
        val unit = FileBackedMatchService("/matches.json")

        val matches = unit.findMatches()
        assertThat(matches.size, equalTo(25))
    }

    @Test
    fun `can filter by photo`() {
        val unit = FileBackedMatchService("/matches.json")

        val matchesWith = unit.findMatches(must.havePhoto())
        assertThat(matchesWith.size, equalTo(22))

        val matchesWithout = unit.findMatches(must.notHavePhoto())
        assertThat(matchesWithout.size, equalTo(3))
    }

    @Test
    fun `can filter by in contact`() {
        val unit = FileBackedMatchService("/matches.json")

        val matchesWith = unit.findMatches(must.beInContact())
        assertThat(matchesWith.size, equalTo(12))

        val matchesWithout = unit.findMatches(must.notBeInContact())
        assertThat(matchesWithout.size, equalTo(13))
    }

}