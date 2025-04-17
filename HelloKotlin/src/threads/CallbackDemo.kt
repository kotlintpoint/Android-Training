package threads

fun main() {
    addTwoNumbersAsync { result ->
        run {
            println("Callback Function")
            println("Result : $result")
        }
    }
}
fun addTwoNumbersAsync(callback: (Int) -> Unit) {
    val num1 = 10
    val num2 = 30
    val sum = num1 + num2
    callback(sum)
}
