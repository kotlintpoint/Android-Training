package pack1

private fun foo() {  } // visible inside example.kt

var bar: Int = 5 // property is visible everywhere
    private set         // setter is visible only in example.kt

internal val baz = 6    // visible inside the same module