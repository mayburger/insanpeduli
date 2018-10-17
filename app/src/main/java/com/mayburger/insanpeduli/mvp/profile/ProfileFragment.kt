package com.mayburger.insanpeduli.mvp.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.mayburger.football.ui.MvpFragment
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.model.Users
import com.mayburger.insanpeduli.utils.*
import com.mayburger.insanpeduli.utils.Constants.PICK_IMAGE_REQUEST
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.toast
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : MvpFragment<ProfileFragmentPresenter>(), OnBackPressed, ProfileFragmentView {


    var filePath: Uri? = null

    override fun onSuccessGetUser(users: Users) {
        with(image) {
            setOnClickListener {
                chooseImage()
            }
        }
        content.visibility = View.VISIBLE
        progress_profile.visibility = View.GONE
        with(name) {
            text = users.name
            typeface = bold
        }
        with(email) {
            text = users.email
            typeface = regular
        }
        with(phone) {
            text = users.phone
            typeface = light
        }
        with(edit_photo) {
            setOnClickListener { chooseImage() }
        }

        Glide.with(this)
                .load(users.image)
                .apply(RequestOptions.circleCropTransform()).into(image)
    }

    var storage: FirebaseStorage? = null
    var storageReference: StorageReference? = null
    var ref: StorageReference? = null
    var uploadTask: UploadTask? = null
    @SuppressLint("SetTextI18n")
    fun uploadImage(@NonNull filePath: Uri) {
        storage = FirebaseStorage.getInstance()
        storageReference = storage?.reference

        if (filePath != null) {
            ref = storageReference?.child("images/" + FirebaseAuth.getInstance().currentUser?.uid.toString())
            uploadTask = ref?.putFile(filePath)
            uploadTask?.continueWithTask({ task ->
                if (!task.isSuccessful) {
                    throw task.exception!!
                }
                ref!!.downloadUrl
            })?.addOnCompleteListener({ task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    val db = FirebaseFirestore.getInstance()
                    val contact = db.collection("users").document(auth.currentUser?.uid.toString())
                    contact.update(Constants.IMAGE, downloadUri.toString())
                            .addOnSuccessListener({
                                try {
                                    progress_picture.visibility = View.GONE
                                    Glide.with(this)
                                            .load(downloadUri.toString())
                                            .apply(RequestOptions.circleCropTransform()).into(image)
                                } catch (e: Exception) {

                                }
                            })

                    ctx.toast(downloadUri.toString())
                } else {
                    ctx.toast("upload failed")
                }
            })
        }
    }


    fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }


    override fun onBackPressed() {
        activity?.finish()
    }

    override fun createPresenter(): ProfileFragmentPresenter {
        return ProfileFragmentPresenter(this@ProfileFragment, activity as AppCompatActivity)
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        content.visibility = View.GONE
        progress_profile.visibility = View.VISIBLE
        mvpPresenter?.getUser()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK
                && data != null && data.data != null) {
            filePath = data.data
            try {
                progress_picture.visibility = View.VISIBLE
                uploadImage(filePath!!)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}// Required empty public constructor
