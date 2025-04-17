package pack2

import companion.Error
import companion.FileError
import pack1.Employee


//class TestError : Error("Test Error")
//{
//
//}

fun main() {
    val emp = Employee()
    emp.firstName = "Test"
    emp.lastName = "test.txt"

//    val p1 = Person()

    val errorList = listOf(Error.NetworkError(), Error.DatabaseError(), Error.UnknownError(), FileError())
    errorList.forEach { println(it.message) }

}