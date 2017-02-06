package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

operator fun MyDate.rangeTo(other: MyDate): DateRange =  DateRange(this, other)

operator fun MyDate.plus(time: TimeInterval): MyDate {
    return this.addTimeIntervals(time, 1)
}

operator fun MyDate.plus(time: RepeatedTimeInterval): MyDate {
    return this.addTimeIntervals(time.timeInterval, time.times)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(i: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, i)

class RepeatedTimeInterval(var timeInterval: TimeInterval, var times : Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object :Iterator<MyDate> {
            var current = start
            override fun hasNext(): Boolean {
                return  current <= endInclusive
            }

            override fun next(): MyDate {
                var result = current
                current = current.nextDay()
                return result
            }
        }
    }
}

operator fun MyDate.compareTo(other: MyDate): Int = when {
    this.year != other.year -> this.year - other.year
    this.month != other.month -> this.month - other.month
    else -> this.dayOfMonth - other.dayOfMonth
}

operator fun DateRange.contains(date: MyDate): Boolean = date >= this.start && date <= this.endInclusive