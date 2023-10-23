package com.kizitonwose.calendar.core

import androidx.compose.runtime.Immutable
import org.threeten.bp.YearMonth
import java.io.Serializable

/**
 * Represents a month on the calendar.
 *
 * @param yearMonth the calendar month value.
 * @param weekDays the weeks in this month.
 */
@Immutable
data class CalendarMonth(
    val yearMonth: YearMonth,
    val weekDays: List<List<CalendarDay>>,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CalendarMonth

        if (yearMonth != other.yearMonth) return false
        if (weekDays.first().first() != other.weekDays.first().first()) return false
        if (weekDays.last().last() != other.weekDays.last().last()) return false

        return true
    }

    override fun hashCode(): Int {
        var result = yearMonth.hashCode()
        result = 31 * result + weekDays.first().first().hashCode()
        result = 31 * result + weekDays.last().last().hashCode()
        return result
    }

    override fun toString(): String {
        return "CalendarMonth { " +
            "first = ${weekDays.first().first()}, " +
            "last = ${weekDays.last().last()} " +
            "} "
    }
}
