package com.mayburger.insanpeduli.mvp.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mayburger.insanpeduli.R
import com.mayburger.insanpeduli.model.Users
import com.mayburger.insanpeduli.mvp.logreg.LogRegActivity
import com.mayburger.insanpeduli.mvp.main.fragments.campaign.CampaignFragment
import com.mayburger.insanpeduli.mvp.main.fragments.home.HomeFragment
import com.mayburger.insanpeduli.mvp.profile.ProfileActivity
import com.mayburger.insanpeduli.mvp.user_donation.UserDonationActivity
import com.mayburger.insanpeduli.ui.MvpActivity
import com.mayburger.insanpeduli.utils.*
import com.mayburger.insanpeduli.utils.Constants.CAMPAIGN
import com.mxn.soul.flowingdrawer_core.ElasticDrawer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.flowing_menu.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*


class MainActivity : MvpActivity<MainPresenter>(), MainView {

    override fun onSuccessGetUser(users: Users) {
        Glide.with(this)
                .load(users.image)
                .apply(RequestOptions.circleCropTransform()).into(profile_image)
        with(profile_image) {
            setOnClickListener {
                startActivity<ProfileActivity>()
            }
        }
        with(email) {
            typeface = regular
            text = users.email
        }
        with(name) {
            typeface = bold
            text = users.name
        }
        with(donationText) {
            typeface = regular
        }
        with(profileText) {
            typeface = regular
        }
        with(campaignText) {
            typeface = regular
        }
        with(signOutText) {
            typeface = regular
        }
        with(profileMenu) {
            setOnClickListener {
                startActivity<ProfileActivity>()
            }
        }
        with(donationMenu) {
            setOnClickListener {
                startActivity<UserDonationActivity>()
            }
        }
        with(signOutMenu) {
            setOnClickListener {
                auth = FirebaseAuth.getInstance()
                auth?.signOut()
                startActivity<LogRegActivity>()
                finish()
            }
        }
        if (users.verified!!) {
            verified.visibility = View.VISIBLE
        } else {
            verified.visibility = View.GONE
        }
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter(this@MainActivity, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mvpPresenter?.getUser()

        with(title_text) {
            typeface = black
        }

        addFragmentOnTop(this, HomeFragment(), R.id.frame_home)
        addFragmentOnTop(this, CampaignFragment(""), R.id.frame_campaign)
        nav.defaultBackgroundColor = ContextCompat.getColor(ctx, R.color.colorWhite)



        setupBottomNavigation(ArrayList(),
                intArrayOf(R.string.none, R.string.none),
                intArrayOf(R.drawable.ic_home_grey, R.drawable.ic_campaign_grey),
                intArrayOf(R.color.colorPrimaryDark, R.color.colorPrimaryDark)
        )

        nav.setOnTabSelectedListener({ position, _ ->
            when (position) {
                0 -> {
                    frame_home.visibility = View.VISIBLE
                    frame_campaign.visibility = View.GONE
                    title_text.text = getString(R.string.home)
                }
                1 -> {
                    frame_home.visibility = View.GONE
                    frame_campaign.visibility = View.VISIBLE
                    title_text.text = getString(R.string.campaign)
                }
            }
            true
        })

    }

    override fun onBackPressed() {
        initBackPressed(this@MainActivity)
    }

    fun bas() {
        val db = FirebaseFirestore.getInstance()
        val user = HashMap<String, Any>()
        val uid: String = UUID.randomUUID().toString()
        auth = FirebaseAuth.getInstance()
        user[Constants.CATEGORY] = "orphans"
        user[Constants.DESCRIPTION] = "Campaign official ini tidak memiliki target pencapaian dan akan terus ada. Uang yang di donasi-kan ke campaign ini akan di distribusikan sesuai dengan kategori yang di pilih"
        user[Constants.PHONE] = "087778792230"
        user[Constants.TARGET] = "0"
        user[Constants.PICTURE] = "https://firebasestorage.googleapis.com/v0/b/insanpeduli-bb900.appspot.com/o/donation%2FScreen%20Shot%202018-10-13%20at%2010.13.37%20PM.png?alt=media&token=7c35458b-2e48-4fa4-8b9b-634c894f58c0"
        user[Constants.TITLE] = "Donasi SIP Anak Yatim"
        user[Constants.UUID] = auth!!.currentUser?.uid.toString()
        user[Constants.ID] = uid
        db.collection(CAMPAIGN).document(uid)
                .set(user)
                .addOnSuccessListener({ })
                .addOnFailureListener({ ctx?.toast("failure") })
    }


    internal fun setupBottomNavigation(itemList: ArrayList<AHBottomNavigationItem>, titleList: IntArray, iconlist: IntArray, colorList: IntArray) {
        nav.accentColor = ContextCompat.getColor(ctx, R.color.colorPrimaryDark)
        nav.inactiveColor = ContextCompat.getColor(ctx, R.color.colorBottomNavigationInactive)
        nav.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE

        for (i in titleList.indices) {
            itemList.add(AHBottomNavigationItem(titleList[i], iconlist[i], colorList[i]))
            nav.addItem(itemList[i])
        }


        drawerlayout.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL)
        drawerlayout.setOnDrawerStateChangeListener(object : ElasticDrawer.OnDrawerStateChangeListener {
            override fun onDrawerStateChange(oldState: Int, newState: Int) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                }
            }

            override fun onDrawerSlide(openRatio: Float, offsetPixels: Int) {
            }
        })

        drawerBurger.setOnClickListener {
            drawerlayout.openMenu()
        }

    }
}
