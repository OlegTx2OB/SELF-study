import java.time.LocalDateTime
import java.time.ZoneOffset


fun main()
{
    val dateTime = LocalDateTime.of(1990, 10, 10, 10, 10)
    println(dateTime)
    val newDateTime = dateTime.plusMinutes(1000000)
    println(newDateTime)
    val count = newDateTime.toEpochSecond(ZoneOffset.UTC) / 60
    println(count)
    val newDateTime2 = LocalDateTime.ofEpochSecond(count * 60, 0, ZoneOffset.UTC)
    println(newDateTime2)


}