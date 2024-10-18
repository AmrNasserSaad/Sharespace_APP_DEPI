package com.example.shareworkspace

import com.example.shareworkspace.presentation.main.confirmation.calculateTimeDifference
import org.junit.Assert.*
import org.junit.Test

class TimeDifferenceTest {


    @Test
    fun testValidTimeDifference() {
        val from = "10:00"
        val to = "12:30"
        val result = calculateTimeDifference(from, to)
        assertEquals("02 hours, 30 minutes", result)
    }

    @Test
    fun testInvalidTimeDifferenceEqualTimes() {
        val from = "10:00"
        val to = "10:00"
        val result = calculateTimeDifference(from, to)
        assertEquals("Invalid: Time difference cannot be zero or negative", result)
    }

    @Test
    fun testInvalidTimeDifferenceToBeforeFrom() {
        val from = "14:00"
        val to = "12:00"
        val result = calculateTimeDifference(from, to)
        assertEquals("Invalid: End time must be after start time", result)
    }

    @Test
    fun testValidEdgeCase() {
        val from = "23:59"
        val to = "00:30"
        val result = calculateTimeDifference(from, to)
        assertEquals("00 hours, 31 minutes", result)
    }

    @Test
    fun testInvalidTimeInput() {
        val from = "Select Time"
        val to = "12:00"
        val result = calculateTimeDifference(from, to)
        assertNull(result)
    }

}
