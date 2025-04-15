package companion

//https://kotlinlang.org/docs/exceptions.html

class User(val name: String) {
    companion object {
        fun create(name: String): User = User(name)
    }
}

sealed class Error(val message: String) {
    class NetworkError : Error("Network Error")
    class DatabaseError : Error("Database Error")
    class UnknownError : Error("Unknown Error")
}

class FileError : Error("File Error") {

}

class MyPrint<T>(val data: Array<T>) {
    fun printData() {
        data.forEach { println("Value is $it") }
    }
}

fun <T, T1> printData(data: Array<T>, num: T1) {
    data.forEach { println("Value is $it") }
}

fun main() {
    try {
        val user = User.create("Test")
        println("Your name is ${user.name}")


        val errorList = listOf(Error.NetworkError(), Error.DatabaseError(), Error.UnknownError(), FileError())
        errorList.forEach { println(it.message) }

        printData(arrayOf(10, 20, 30, 40, 50), 100)
        printData(arrayOf("A", "B", "C"), 100)
        printData(arrayOf(true, false, true), "100")
        printData(arrayOf(Error.NetworkError(), Error.DatabaseError(), Error.UnknownError(), FileError()), 100)

        MyPrint<Int>(arrayOf(10, 20, 30, 40, 50)).printData()
        MyPrint<String>(arrayOf("A", "B", "C")).printData()
        MyPrint(arrayOf(true, false, true)).printData()
        MyPrint(arrayOf(Error.NetworkError(), Error.DatabaseError(), Error.UnknownError(), FileError())).printData()

        println("Enter number : ")
        val num = readln().toInt()
        require(num > 0) { "Number must be positive." }
        println("Your number is $num")

    } catch (ex: Exception) {
        println(ex)
    }
    println("Thank you.")
}