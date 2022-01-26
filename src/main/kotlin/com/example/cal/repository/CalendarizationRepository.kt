package com.example.cal.repository

import com.example.cal.model.Calendarization
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CalendarizationRepository :CrudRepository<Calendarization, String>
