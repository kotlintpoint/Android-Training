package file_operation

import java.io.File

fun main() {
    val file = File("test.txt")
    println("Exists -> ${file.exists()}")
    println("isFile -> ${file.isFile()}")
    println("isDirectory -> ${file.isDirectory()}")
    println("canRead -> ${file.canRead()}")
    println("canWrite -> ${file.canWrite()}")
    println("canExecute -> ${file.canExecute()}")


    if(file.exists()){
        println("File already exist.")
//        if(file.delete()){
//            println("File Deleted")
//        }

        file.readLines().forEach { line ->
            println(line)
        }
    }else {
        if(file.createNewFile()){
            println("File created.")
        }

//        if(file.mkdir()){
//            println("Directory created")
//        }

    }
}