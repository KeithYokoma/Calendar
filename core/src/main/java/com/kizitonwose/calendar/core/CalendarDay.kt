package com.kizitonwose.calendar.core

import androidx.compose.runtime.Immutable
import org.threeten.bp.LocalDate
import java.io.Serializable

/**
 * Represents a day on the calendar.
 *
 * @param date the date for this day.
 * @param position the [DayPosition] for this day.
 */
@Immutable
data class CalendarDay(val date: LocalDate, val position: DayPosition) : Serializable
