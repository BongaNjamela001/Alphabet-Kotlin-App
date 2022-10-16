package com.csc2002s.assgnmnts.apps.alphabetapplication.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.Calendar
import java.util.Calendar.DAY_OF_YEAR

class AlphabetTest {

    private lateinit var alphabet: Alphabet

    @Before fun setUp() {
        alphabet = Alphabet("Aa", "Aa", "Apple", 1, 2, "")
    }

    @Test fun test_default_values() {
        val defaultAlphabet = Alphabet("Aa", "Aa", "Apple", 1)
        assertEquals(7, defaultAlphabet.addingInterval)
        assertEquals("", defaultAlphabet.imageUrl)
    }

    @Test fun test_shouldBeAdded() {
        Calendar.getInstance().let { now ->
            val lastAddingDate = Calendar.getInstance()

            lastAddingDate.time = now.time
            assertFalse(alphabet.shouldBeAdded(now, lastAddingDate.apply { add(DAY_OF_YEAR, -0) }))

            // Test for lastAddingDate is yesterday.
            lastAddingDate.time = now.time
            assertFalse(alphabet.shouldBeAdded(now, lastAddingDate.apply { add(DAY_OF_YEAR, -1) }))

            // Test for lastAddingDate is the day before yesterday.
            lastAddingDate.time = now.time
            assertFalse(alphabet.shouldBeAdded(now, lastAddingDate.apply { add(DAY_OF_YEAR, -2) }))

            // Test for lastAddingDate is some days ago, three days ago, four days ago etc.
            lastAddingDate.time = now.time
            assertTrue(alphabet.shouldBeAdded(now, lastAddingDate.apply { add(DAY_OF_YEAR, -3) }))
        }
    }

    @Test fun test_toString() {
        assertEquals("Apple", alphabet.toString())
    }
}
