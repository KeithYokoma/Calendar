package com.kizitonwose.calendar.core

import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.temporal.WeekFields
import java.util.*

/**
 * Returns the days of week values such that the desired
 * [firstDayOfWeek] property is at the start position.
 *
 * @see [firstDayOfWeekFromLocale]
 */
@JvmOverloads
fun daysOfWeek(firstDayOfWeek: DayOfWeek = firstDayOfWeekFromLocale()): List<DayOfWeek> {
    val pivot = 7 - firstDayOfWeek.ordinal
    val daysOfWeek = DayOfWeek.values()
    // Order `daysOfWeek` array so that firstDayOfWeek is at the start position.
    return (daysOfWeek.takeLast(pivot) + daysOfWeek.dropLast(pivot))
}

/**
 * Returns the first day of the week from the default locale.
 */
fun firstDayOfWeekFromLocale(): DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek

/**
 * Returns a [LocalDate] at the start of the month.
 *
 * Complements [YearMonth.atEndOfMonth].
 */
fun YearMonth.atStartOfMonth(): LocalDate = this.atDay(1)

val LocalDate.yearMonth: YearMonth
    get() = YearMonth.of(year, month)

val YearMonth.nextMonth: YearMonth
    get() = this.plusMonths(1)

val YearMonth.previousMonth: YearMonth
    get() = this.minusMonths(1)
