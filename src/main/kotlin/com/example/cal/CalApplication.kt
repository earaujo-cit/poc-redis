package com.example.cal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CalApplication

fun main(args: Array<String>) {
	runApplication<CalApplication>(*args)
}
