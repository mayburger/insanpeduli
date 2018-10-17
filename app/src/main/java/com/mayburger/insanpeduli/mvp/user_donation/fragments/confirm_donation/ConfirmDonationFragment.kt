package com.mayburger.insanpeduli.mvp.user_donation.fragments.confirm_donation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.mayburger.football.ui.MvpFragment
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.model.Donation
import com.mayburger.insanpeduli.utils.Constants
import com.mayburger.insanpeduli.utils.Constants.USER_DONATIONS
import com.mayburger.insanpeduli.utils.OnBackPressed
import com.mayburger.insanpeduli.utils.regular
import com.mayburger.insanpeduli.utils.bold
import kotlinx.android.synthetic.main.fragment_confirm_donation.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.io.IOException


@SuppressLint("ValidFragment")
/**
 * A simple [Fragment] subclass.
 */
class ConfirmDonationFragment @SuppressLint("ValidFragment") constructor
(var donation: Donation) : MvpFragment<ConfirmDonationPresenter>(), OnBackPressed, ConfirmDonationView {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(choose_card) {
            setOnClickListener { chooseImage() }
        }
        with(choose_text) {
            typeface = bold
        }
        with(fileName) {
            typeface = regular
        }

        with(title){
            typeface = bold
        }

        with(subtitle){
            typeface = regular
        }

        with(verify_card) {
            setOnClickListener {
                uploadImage(filePath!!)
            }
        }
        with(verify_text) {
            typeface = bold
        }
    }

    var storage: FirebaseStorage? = null
    var storageReference: StorageReference? = null
    var ref: StorageReference? = null
    var uploadTask: UploadTask? = null
    @SuppressLint("SetTextI18n")
    fun uploadImage(@NonNull filePath: Uri) {
        progress.visibility = View.VISIBLE
        storage = FirebaseStorage.getInstance()
        storageReference = storage?.reference

        if (filePath != null) {
            ref = storageReference?.child("evidence/" + FirebaseAuth.getInstance().currentUser?.uid.toString() + donation.id)
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
                    val contact = db.collection(USER_DONATIONS).document(donation.id)
                    contact.update(Constants.TRANSFER_PROOF, downloadUri.toString())
                            .addOnSuccessListener({
                                try {
                                    activity?.finish()
                                    toast("Request Success! Please wait for the admin to verify your evidence")
                                } catch (e: Exception) {

                                }
                            })

                } else {
                    ctx.toast("upload failed")
                }
            })
        }
    }

    var filePath: Uri? = null
    fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), Constants.PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK
                && data != null && data.data != null) {
            filePath = data.data
            fileName.visibility = View.VISIBLE
            val path = filePath!!.path
            fileName.text = path.substring(path.lastIndexOf("/")+1);
            try {
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onBackPressed() {
        fragmentManager!!.beginTransaction().remove(this).commit()
    }

    override fun createPresenter(): ConfirmDonationPresenter {
        return ConfirmDonationPresenter(this@ConfirmDonationFragment, activity as AppCompatActivity)
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_confirm_donation
    }
}// Required empty public constructor
