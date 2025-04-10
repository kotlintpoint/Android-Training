fun main() {
//    println("Enter no1 : ")
//    val a = readln().toInt()
//    println("Enter no2 : ")
//    val b = readln().toInt()
//
//    val max = if(a > b) a else b
//    println("Max = $max")


//    println("Enter age : ")
//    val age = readln().toInt()
//
//    println(
//        when {
//            age <= 0 -> "Invalid age"
//            age <= 18 -> "Child"
//            age <=65 -> "Adult"
//            else -> "Senior"
//        }
//    )

    var sum = 0
    for(number in 1..5){
        sum+=number
        println("Number is $number")
    }
    println("Sum is $sum")

    val list = listOf("apple", "banana", "watermelon")
    for(fruit in list){
        println("$fruit length is ${fruit.length} ")
    }

    val para = """
     val list = listOf("apple", "banana", "watermelon")
    for(fruit in list){
        println("\$ fruit length is \$ {fruit.length}")
    }   
    """.trimIndent()
    println(para)

}