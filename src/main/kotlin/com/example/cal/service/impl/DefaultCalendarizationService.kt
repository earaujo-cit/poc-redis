package com.example.cal.service.impl

import com.example.cal.controller.CalendarizationController
import com.example.cal.model.Calendarization
import com.example.cal.repository.CalendarizationRepository
import com.example.cal.service.CalendarizationService
import com.example.cal.service.exception.CalendarizationNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DefaultCalendarizationService(val calendarizationRepository: CalendarizationRepository) : CalendarizationService {

    private val logger: Logger = LoggerFactory.getLogger(DefaultCalendarizationService::class.java)

    override fun getCalendarization(id: String): Calendarization = calendarizationRepository.findById(id).orElseThrow {
        CalendarizationNotFoundException("Unable to find calendarization for $id id")
    }

    override fun getAllCalendarizations(): List<Calendarization> = calendarizationRepository.findAll().toList()

    override fun updateCalendarization(
        id: String,
        calendarizationDto: CalendarizationController.CalendarizationDto
    ): Calendarization {
        val calendarization: Calendarization = calendarizationRepository.findById(id).orElseThrow {
            CalendarizationNotFoundException("Unable to find calendarization for $id id")
        }
        val newRegion: String =
            if (calendarizationDto.region.isEmpty()) calendarization.region else calendarizationDto.region
        val newDistributionCenter: String =
            if (calendarizationDto.distributionCenter.isEmpty()) calendarization.distributionCenter else calendarizationDto.distributionCenter
        val newCycle: String =
            if (calendarizationDto.cycle.isEmpty()) calendarization.cycle else calendarizationDto.cycle
        val newShippingSequence: Int =
            if (calendarizationDto.shippingSequence == null) calendarization.shippingSequence else calendarizationDto.shippingSequence
        val updatedCalendarization = calendarization.copy(
            version = calendarizationDto.version,
            region = newRegion,
            distributionCenter = newDistributionCenter,
            countyCode = calendarizationDto.countyCode.orEmpty(),
            salesOrganizationType = calendarizationDto.salesOrganizationType,
            shippingDate = calendarizationDto.shippingDate,
            cycle = newCycle,
            shippingSequence = newShippingSequence,
        )
        updatedCalendarization.id = calendarization.id
        return calendarizationRepository.save(updatedCalendarization)
    }

    override fun createCalendarization(calendarizationDto: CalendarizationController.CalendarizationDto): Calendarization {
        return calendarizationRepository.save(
            Calendarization(
                version = calendarizationDto.version,
                region = calendarizationDto.region,
                distributionCenter = calendarizationDto.distributionCenter,
                countyCode = calendarizationDto.countyCode.orEmpty(),
                salesOrganizationType = calendarizationDto.salesOrganizationType,
                shippingDate = calendarizationDto.shippingDate,
                cycle = calendarizationDto.cycle,
                shippingSequence = calendarizationDto.shippingSequence,
            )
        )
    }

    override fun deleteCalendarization(id: String) = calendarizationRepository.delete(getCalendarization(id))

}
