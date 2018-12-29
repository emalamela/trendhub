package com.chimichanga.trendhub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chimichanga.trendhub.repository.list.RepositoryListFragment

class MainActivity : AppCompatActivity() {

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
