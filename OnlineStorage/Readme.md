Restful API

https://reqres.in/
Easy or Simple APIs
https://jsonplaceholder.typicode.com/  
https://restful-api.dev/


Okhttp
https://square.github.io/okhttp/

Gson
https://github.com/google/gson


# Android Custom Activity Result using `registerForActivityResult`

This project demonstrates how to launch your **own custom activity** using Android’s modern `registerForActivityResult` API and receive a result back in the calling activity.

---

## 🔧 Features

- Uses `ActivityResultContracts.StartActivityForResult`
- Handles result via lambda callback
- Simple communication between two activities

---

## 📁 Project Structure
MainActivity.kt # First screen, launches SecondActivity
SecondActivity.kt # Second screen, sends result back
activity_main.xml # UI with Button
activity_second.xml # UI with Button/Text

---

## 🚀 How It Works

### 1. Define Launcher in `MainActivity.kt`

```kotlin
class MainActivity : AppCompatActivity() {

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val message = result.data?.getStringExtra("result_key")
                Toast.makeText(this, "Received: $message", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}
```


📝 Sample SecondActivity.kt
```
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.btnSendResult).setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("result_key", "Hello from SecondActivity!")
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
```