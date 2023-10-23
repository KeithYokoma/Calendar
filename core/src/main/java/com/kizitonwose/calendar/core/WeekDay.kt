package com.kizitonwose.calendar.core

import androidx.compose.runtime.Immutable
import org.threeten.bp.LocalDate
import java.io.Serializable

/**
 * Represents a day on the week calendar.
 *
 * @param date the date for this day.
 * @param position the [WeekDayPosition] for this day.
 */
@Immutable
data class WeekDay(val date: LocalDate, val position: WeekDayPosition) : Serializable
