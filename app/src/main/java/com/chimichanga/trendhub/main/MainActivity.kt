package com.chimichanga.trendhub.main

import android.os.Bundle
import com.chimichanga.trendhub.R
import com.chimichanga.trendhub.repository.list.RepositoryListFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, RepositoryListFragment.newInstance())
                .commitNow()
        }
    }

}
