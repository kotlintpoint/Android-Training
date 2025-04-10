// https://kotlinlang.org/docs/classes.html#constructors
// Class header primary constructor
class Person (val id: Int, val firstName:String, val lastName: String) {
    init {
        println("Person is Created")
    }

    constructor():this(1, "Hello","Hi"){
        // secondary constructor
    }

    constructor(firstName: String): this(2, firstName, "test") {
        // secondary constructor
    }
}

fun main() {
    val p1 = Person(1, "Test", "test")

    val p2 = Person()

    val p3 = Person("Raju")
}