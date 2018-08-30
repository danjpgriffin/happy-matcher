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

        val matches = unit.findMatches(must.havePhoto())
        assertThat(matches.size, equalTo(22))
    }

}