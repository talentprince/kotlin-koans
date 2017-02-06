package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)

operator fun MyDate.compareTo(other: MyDate): Int = when {
    this.year != other.year -> this.year - other.year
    this.month != other.month -> this.month - other.month
    else -> this.dayOfMonth - other.dayOfMonth
}
