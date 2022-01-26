package com.example.cal.service

import com.example.cal.controller.CalendarizationController
import com.example.cal.model.Calendarization
import com.example.cal.service.exception.CalendarizationNotFoundException
import kotlin.jvm.Throws

interface CalendarizationService {

    @Throws(CalendarizationNotFoundException::class)
    fun getCalendarization(id: String): Calendarization

    fun getAllCalendarizations(): List<Calendarization>

    @Throws(CalendarizationNotFoundException::class)
    fun updateCalendarization(id: String, calendarizationDto: CalendarizationController.CalendarizationDto): Calendarization

    fun createCalendarization(calendarizationDto: CalendarizationController.CalendarizationDto): Calendarization

    @Throws(CalendarizationNotFoundException::class)
    fun deleteCalendarization(id: String)
}
