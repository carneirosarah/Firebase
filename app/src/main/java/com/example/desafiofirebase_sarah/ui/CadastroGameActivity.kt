package com.example.desafiofirebase_sarah.ui

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.desafiofirebase_sarah.R
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_cadastro_game.*
import java.io.ByteArrayOutputStream


class CadastroGameActivity : AppCompatActivity() {

    private lateinit var storageRef: StorageReference
    private lateinit var newData: ByteArray
    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var db: FirebaseFirestore
    private lateinit var cr: CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_game)
        config()

        addPictureButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        saveGameButton.setOnClickListener {
            uploadData()
        }
    }

    private fun dispatchTakePictureIntent() {

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            addPictureButton.setImageBitmap(imageBitmap)
            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            newData = baos.toByteArray()

        }
    }

    fun getData(url: String): MutableMap<String, Any> {

        val game: MutableMap<String, Any> = HashMap()

        game["name"] = game_name.text.toString()
        game["create_at"] = game_created_at.text.toString()
        game["desc"] = game_desc.text.toString()
        game["img"] = url

        return game
    }

    fun uploadData () {

        val name = game_name.text.toString()
        val imgRef = storageRef.child("images/" + name + ".jpg")
        val uploadTask = imgRef.putBytes(newData)
        val task = uploadTask.continueWithTask { task ->

            if (task.isSuccessful) {
                Toast.makeText(this, "Upload da imagem em progresso", Toast.LENGTH_SHORT).show()
            }

            imgRef!!.downloadUrl
        }.addOnCompleteListener { task ->

            if (task.isSuccessful) {
                val downloadUri = task.result
                val url = downloadUri.toString()
                        .substring(0, downloadUri.toString().indexOf("&token"))
                Log.i("Link Direto ", url)

                val data = getData(url)

                cr.document(name).set(data).addOnSuccessListener {

                    Toast.makeText(this, "Upload dos dados realizado com sucesso", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {

                    Log.i("Cadastro Game", it.toString())
                }
            }
        }
    }

    fun config() {

        storageRef = FirebaseStorage.getInstance().reference
        db = FirebaseFirestore.getInstance()
        cr = db.collection("games")
    }
}