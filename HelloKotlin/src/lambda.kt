

fun toSeconds(time: String): (Int) -> Int = when (time) {
    "hour" -> { value -> value * 60 * 60 }
    "minute" -> { value -> value * 60 }
    "second" -> { value -> value }
    else -> { value -> value }
}


fun main() {
//    val data = listOf<Int>(1,5,6,3,2,8,9)
//
//    val filterData = data.filter { value -> value > 5 }
//    println(filterData)

    val timesInMinutes = listOf(2, 10, 15, 1)
    val timesInHours = listOf(2, 3, 4)
    val timesInSeconds = listOf(55, 45, 35)

    val totalTimeInSeconds = timesInMinutes.map{value -> value * 60}.sum()
//    val totalTimeInSeconds = timesInMinutes.map(toSeconds("minute")).sum()
    val totalTimeInSeconds1 = timesInHours.map(toSeconds("hour")).sum()
    val totalTimeInSeconds2 = timesInHours.map(toSeconds("second")).sum()
    println("Total Time in seconds $totalTimeInSeconds")

//    val sum = timesInMinutes.fold(0, { result, item -> result + item})
    val sum = timesInMinutes.fold(0){ result, item -> result + item}
    print("Sum = $sum")


}
