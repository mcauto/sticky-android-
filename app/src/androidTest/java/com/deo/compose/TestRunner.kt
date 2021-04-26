package com.deo.compose

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.facebook.stetho.Stetho
import dagger.hilt.android.testing.HiltTestApplication

class TestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?, name: String?, context: Context?
    ): Application {
//        Stetho.initializeWithDefaults(context);
        return super.newApplication(
            cl, HiltTestApplication::class.java.name, context
        )
    }
}