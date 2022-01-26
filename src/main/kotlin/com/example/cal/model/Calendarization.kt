package com.example.cal.model

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.time.LocalDate
import java.util.*

@RedisHash("Calendarization")
data class Calendarization(
    val version: String,
    @Indexed val region: String,
    val distributionCenter: String,
    @Indexed val countyCode: String?,
    val salesOrganizationType: String,
    @Indexed val shippingDate: LocalDate,
    val cycle: String,
    val shippingSequence: Int,
){
    @get:Id
    var id: String = "$region"+"_*_$shippingDate"
}
