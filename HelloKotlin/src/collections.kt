
fun main() {
    // immutable
//    val cities = listOf("City1", "City2", "City3")
    val cities: List<String> = listOf("City1", "City2", "City3")
    println(cities)
    println("Citi at 0 index is ${cities[0]}")
    println(cities.first())
    println(cities.last())


    // mutable
//    val students = mutableListOf("Student1", "Student2", "Student3")
    val students: MutableList<String> = mutableListOf("Student1", "Student2", "Student3")
    println(students)
    students.add("Student4")
    println(students)
    val isRemoved = students.remove("Student1");
    println("Student1 is Removed : $isRemoved")
    println(students)
//    students.add(100)

    // parent to child not allowed
//    val newCities: MutableList<String> = cities

    val citySet: Set<String> = setOf("City1", "City2", "City3", "City1", "City1")
    println("City Set $citySet")

    val cityMutableSet: MutableSet<String> = mutableSetOf("City1", "City2", "City3", "City1", "City1")
    println("City Set $cityMutableSet")
    cityMutableSet.add("City5")
    println("City Set $cityMutableSet")
    println("Count : ${cityMutableSet.count()}")

    val numberList = listOf(10, 4,9, 6,7,9,3,20);
    val countEven = numberList.count { num -> num % 2 == 0 }
    println("Total even numbers $countEven")

    println("index of 9 is ${numberList.indexOf(9)}")
    println("index of 9 is ${numberList.lastIndexOf(9)}")


    val mapList = mapOf("apple" to 90, "kiwi" to 190, "banana" to 150)
    println(mapList)
    println("Value for Kiwi is ${mapList["kiwi"]}")

    val mutableMapList: MutableMap<String, Int> = mutableMapOf("apple" to 90, "kiwi" to 190, "banana" to 150)
    println(mutableMapList)
//    mutableMapList.put("watermelon", 300)
    mutableMapList["watermelon"] = 300
    println(mutableMapList)

    if(mutableMapList.containsKey("apple")){
        println("Our list contains apple.")
    }else{
        println("Our list not contains apple.")
    }

    println("Keys : ${mutableMapList.keys}")
    println("Values : ${mutableMapList.values}")
}