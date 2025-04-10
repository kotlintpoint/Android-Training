import kotlin.math.PI


fun main() {
    printHello()
    printHello()
    printHello()
    sum(10, 20)
    println("8 + 9 = ${addition(8, 9)}")

    sum(y = 5, x = 6)
    sum(y = 19)

    println(areaOfCircle(2.0))
}

/*fun printHello() {
    println("Hello from Kotlin")
}*/
val printHello = { println("Hello from Kotlin") }

fun sum(x: Int = 78, y: Int){
    if(x<0 || y<0){
        return
    }
    println("$x + $y = ${x+y}")
}

fun addition(x: Int, y: Int) : Int {

    if(x<0 || y<0){
        return 0
    }

    return x + y
}

//fun areaOfCircle (radius: Double) = PI * radius * radius
val areaOfCircle = { radius: Double -> PI * radius * radius }