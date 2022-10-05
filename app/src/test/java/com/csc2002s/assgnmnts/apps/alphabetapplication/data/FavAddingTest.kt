package com.csc2002s.assgnmnts.apps.alphabetapplication.data

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.MONTH
import java.util.Calendar.YEAR

class FavAddingTest {

    @Test
    fun testDefaultValues() {
        val favAdding = FavAdding("1")
        val cal = Calendar.getInstance()
        assertYMD(cal, favAdding.alphabetDate)
        assertYMD(cal, favAdding.lastAddingDate)
        assertEquals(0L, favAdding.favAddingId)
    }

    // Only Year/Month/Day precision is needed for comparing GardenPlanting Calendar entries
    private fun assertYMD(expectedCal: Calendar, actualCal: Calendar) {
        assertThat(actualCal.get(YEAR), equalTo(expectedCal.get(YEAR)))
        assertThat(actualCal.get(MONTH), equalTo(expectedCal.get(MONTH)))
        assertThat(actualCal.get(DAY_OF_MONTH), equalTo(expectedCal.get(DAY_OF_MONTH)))
    }
}
