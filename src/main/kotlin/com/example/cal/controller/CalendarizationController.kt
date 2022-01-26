package com.example.cal.controller

import com.example.cal.model.Calendarization
import com.example.cal.service.CalendarizationService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/calendarization")
@Validated
class CalendarizationController(val calendarizationService: CalendarizationService) {

    @RequestMapping(value = ["/create"], method = [RequestMethod.GET, RequestMethod.POST])
    private fun createCalendarization(
        @RequestParam(name = "region")
        @NotBlank
        region: String,
        @RequestParam(name = "distributionCenter")
        @NotBlank
        distributionCenter: String,
        @RequestParam(name = "cycle")
        @NotBlank
        cycle: String,
        @RequestParam(name = "shippingSequence")
        @NotBlank
        shippingSequence: Int,
    ): Calendarization {
        val calendarization: CalendarizationDto = CalendarizationDto(
            "403",
            region,
            distributionCenter,
            "01",
            "BOT",
            LocalDate.now(),
            cycle,
            shippingSequence
        )
        return calendarizationService.createCalendarization(calendarization)
    }

    @RequestMapping(value = ["/getById"])
    private fun getCalendarizationById(
        @RequestParam(name = "id")
        @NotBlank
        id: String,
    ): Calendarization = calendarizationService.getCalendarization(id)

    @RequestMapping(value = ["/getAll"])
    private fun getCalendarizations(): List<Calendarization> = calendarizationService.getAllCalendarizations()

    @RequestMapping(value = ["/updateCalendarization"])
    private fun updateCalendarization(
        @RequestParam(name = "id")
        @NotBlank
        id: String,
        @RequestParam(name = "region")
        region: String,
        @RequestParam(name = "distributionCenter")
        distributionCenter: String,
        @RequestParam(name = "cycle")
        cycle: String,
        @RequestParam(name = "shippingSequence")
        shippingSequence: Int,
    ): Calendarization {
        val calendarization: CalendarizationDto = CalendarizationDto(
            "403",
            region,
            distributionCenter,
            "01",
            "BOT",
            LocalDate.now(),
            cycle,
            shippingSequence
        )
        return calendarizationService.updateCalendarization(id, calendarization)
    }

    @RequestMapping(value = ["/deleteCalendarization"])
    private fun deleteCalendarization(
        @RequestParam(name = "id")
        @NotBlank
        id: String,
    ) = calendarizationService.deleteCalendarization(id)

    data class CalendarizationDto(
        val version: String,
        val region: String,
        val distributionCenter: String,
        val countyCode: String?,
        val salesOrganizationType: String,
        val shippingDate: LocalDate,
        val cycle: String,
        val shippingSequence: Int,
    )
}
