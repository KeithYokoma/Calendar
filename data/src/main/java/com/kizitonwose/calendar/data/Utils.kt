package com.kizitonwose.calendar.data

import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

fun checkDateRange(startMonth: YearMonth, endMonth: YearMonth) {
    check(endMonth >= startMonth) {
        "startMonth: $startMonth is greater than endMonth: $endMonth"
    }
}

fun checkDateRange(startDate: LocalDate, endDate: LocalDate) {
    check(endDate >= startDate) {
        "startDate: $startDate is greater than endDate: $endDate"
    }
}
