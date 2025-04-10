package pack1
// https://kotlinlang.org/docs/inheritance.html

abstract class Person {
    abstract fun welcome()
}

open class Employee: Person() {
    internal var firstName: String = ""
    internal var lastName: String = ""

    override fun toString(): String {
        return firstName
    }

    override fun welcome() {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

class Manager : Employee() {

}