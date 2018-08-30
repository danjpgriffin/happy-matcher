package com.dan.happy

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class TestCityDistanceCalculations {

    @Test
    fun `can tell distance between two cities`() {
        val london = City("London", 51.509865, -0.118092)
        val miltonKeynes = City("MK", 52.04172, -0.75583)
        val bournemouth = City("Bournemouth", 50.720806, -1.904755)

        assertThat(london.distanceFrom(miltonKeynes), equalTo(73))
        assertThat(bournemouth.distanceFrom(miltonKeynes), equalTo(167))
    }
}

