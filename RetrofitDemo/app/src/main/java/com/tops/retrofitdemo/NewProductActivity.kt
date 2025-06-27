package com.tops.retrofitdemo

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.retrofitdemo.databinding.ActivityNewProductBinding
import com.tops.retrofitdemo.model.NewProduct
import com.tops.retrofitdemo.model.NewProductResponse
import com.tops.retrofitdemo.model.Product
import com.tops.retrofitdemo.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val TAG = "NewProductActivity"
class NewProductActivity : AppCompatActivity() {
    private var product: Product? = null
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    private val REQUEST_IMAGE_CAPTURE: Int = 100
    private lateinit var binding: ActivityNewProductBinding
    lateinit var currentPhotoPath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityNewProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras
        product = bundle!!.getParcelable("product", Product::class.java)
        if(product != null){
            binding.etTitle.setText(product!!.title)
            binding.etDescription.setText(product!!.description)
            binding.btnSubmit.setText("Update")
        }

        setSupportActionBar(binding.toolbar)
        binding.btnSubmit.setOnClickListener {
            if(product != null){
                // update
                updateProduct()
            }else{
                submitNewProduct()
            }

        }

        binding.btnBrowse.setOnClickListener {
            showOptionsToChooseImage()
        }

        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                binding.imageView.setImageURI(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    }

    private fun updateProduct() {
        val newProduct = NewProduct( title = binding.etTitle.text.toString(), description = binding.etDescription.text.toString())
        val call = RetrofitClient.getInstance().updateProduct(product!!.id, newProduct)
        call.enqueue(object: Callback<NewProductResponse>{
            override fun onResponse(
                call: Call<NewProductResponse>,
                response: Response<NewProductResponse>
            ) {
                if(response.isSuccessful && response.raw().code == 200){
                    Log.i(TAG, response.body()!!.toString())
                    val resultIntent = Intent().apply {
                        putExtra("product", response.body()!!)
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }

            override fun onFailure(call: Call<NewProductResponse>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

        })
    }

    private fun showOptionsToChooseImage() {
        AlertDialog.Builder(this)
            .setTitle("Choose Image")
            .setPositiveButton("Camera", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dispatchTakePictureIntent()
                }
            })
            .setNegativeButton("Gallery", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    pickImageFromGallery()
                }
            })
            .setNeutralButton("Cancel", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            }).create().show()
    }

    private fun pickImageFromGallery() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun submitNewProduct() {
        val product = NewProduct(
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString()
        )
        val call:Call<NewProductResponse> = RetrofitClient.getInstance().saveProduct(product)
        call.enqueue(object: Callback<NewProductResponse>{
            override fun onResponse(call: Call<NewProductResponse>, response: Response<NewProductResponse>) {
                Log.i(TAG, response.raw().toString())
                if(response.isSuccessful && response.raw().code == 201){
                    Log.i(TAG, response.body()!!.toString())
                    val resultIntent = Intent().apply {
                        putExtra("product", response.body()!!)
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }

            override fun onFailure(call: Call<NewProductResponse>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.imageView.setImageBitmap(imageBitmap)
        }
    }

    private fun dispatchTakePictureIntent() {

        val takePictureIntent =Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)

//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            // Ensure that there's a camera activity to handle the intent
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                // Create the File where the photo should go
//                val photoFile: File? = try {
//                    createImageFile()
//                } catch (ex: IOException) {
//                    // Error occurred while creating the File
//                    null
//                }
//                // Continue only if the File was successfully created
//                photoFile?.also {
//                    val photoURI: Uri = FileProvider.getUriForFile(
//                        this,
//                        "com.tops.retrofitdemo",
//                        it
//                    )
//                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//                }
//            }
//        }
    }


//    private fun createImageFile(): File {
//        // Create an image file name
//        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
//        return File.createTempFile(
//            "JPEG_${timeStamp}_", /* prefix */
//            ".jpg", /* suffix */
//            storageDir /* directory */
//        ).apply {
//            // Save a file: path for use with ACTION_VIEW intents
//            currentPhotoPath = absolutePath
//        }
//    }
}