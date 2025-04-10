data class Contact(private val id: Int, var email: String = "test@gmail.com") {
    var category: String = ""

    fun printContact() {
        println("id = $id")
        println("email = $email")
        println("category = $category")
    }
}

fun main() {
    val c1 = Contact(1, "abc@gmail.com")
//    c1.id = 10
    c1.category = "Category1"
//    c1.email = "test@gmail.com"

    val c2 = Contact(2, "pqr@gmail.com")
    c2.category = "Category2"

    val c3 = Contact(3)
    val c4 = Contact(3)

    c1.printContact()
    c2.printContact()
    c3.printContact()

//    println("id = ${c2.id}")
//    println("email = ${c2.email}")
//    println("category = ${c2.category}")

    println(c1)

    if(c3 == c4){
        println("Both are same")
    }else{
        println("Both are different")
    }

    val c5 = c1.copy(email = "hi@gmail.com")
    println(c5)
}