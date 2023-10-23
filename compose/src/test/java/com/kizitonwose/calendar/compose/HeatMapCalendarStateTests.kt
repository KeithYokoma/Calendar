package com.kizitonwose.calendar.compose

import com.kizitonwose.calendar.compose.heatmapcalendar.HeatMapCalendarState
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.zone.TzdbZoneRulesProvider
import org.threeten.bp.zone.ZoneRulesProvider

class HeatMapCalendarStateTests {

    @Before
    fun setup() {
        if (ZoneRulesProvider.getAvailableZoneIds().isEmpty()) {
            val stream = this.javaClass.classLoader!!.getResourceAsStream("TZDB.dat")
            stream.use(::TzdbZoneRulesProvider).apply {
                ZoneRulesProvider.registerProvider(this)
            }
        }
    }

    @After
    fun tearDown() {
        ZoneRulesProvider.refresh()
    }

    @Test
    fun `start month update is reflected in the state`() {
        val now = YearMonth.now()
        val updatedStartMonth = now.minusMonths(4)
        val state = createState(
            startMonth = now,
            endMonth = now,
        )

        assertEquals(state.store[0].yearMonth, now)

        state.startMonth = updatedStartMonth

        assertEquals(state.store[0].yearMonth, updatedStartMonth)
    }

    @Test
    fun `end month update is reflected in the state`() {
        val now = YearMonth.now()
        val updatedEndMonth = now.plusMonths(4)
        val state = createState(
            startMonth = now,
            endMonth = now,
        )

        assertEquals(state.store[0].yearMonth, now)

        state.endMonth = updatedEndMonth

        assertEquals(state.store[4].yearMonth, updatedEndMonth)
    }

    @Test
    fun `first day of the week update is reflected in the state`() {
        val firstDayOfWeek = LocalDate.now().dayOfWeek

        val state = createState(firstDayOfWeek = firstDayOfWeek)

        state.store[0].weekDays.forEach { week ->
            assertEquals(week.first().date.dayOfWeek, firstDayOfWeek)
        }

        do {
            val updatedFirstDayOfWeek = state.firstDayOfWeek.plus(1)
            state.firstDayOfWeek = updatedFirstDayOfWeek

            state.store[0].weekDays.forEach { week ->
                assertEquals(week.first().date.dayOfWeek, updatedFirstDayOfWeek)
            }
        } while (firstDayOfWeek != state.firstDayOfWeek)
    }

    private fun createState(
        startMonth: YearMonth = YearMonth.now(),
        endMonth: YearMonth = startMonth,
        firstVisibleMonth: YearMonth = startMonth,
        firstDayOfWeek: DayOfWeek = firstDayOfWeekFromLocale(),
        visibleItemState: VisibleItemState = VisibleItemState(),
    ) = HeatMapCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = firstVisibleMonth,
        firstDayOfWeek = firstDayOfWeek,
        visibleItemState = visibleItemState,
    )
}
