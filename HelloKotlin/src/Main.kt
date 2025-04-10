//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

var total = 3

fun main() {
    println("Hello World!!!")
    welcomeMessage();

    var num1 = 10       // var is mutable, can change
    val num2 = 20       // val is immutable , not changeable

    num1 += 5
//    num2 += 5

    val name = "Ankit Sodha"
    println("Good Morning, $name. How are you?")
    println("$num1 + $num2 = ${num1+num2}")

    var age:Int = 25
}

fun welcomeMessage() {
    println("Welcome to Kotlin!!!")
}