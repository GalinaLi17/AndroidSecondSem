package ru.itis.android.uprising26.firebase

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticsLogger @Inject constructor(
    private val analytics: FirebaseAnalytics,
    private val crashlytics: FirebaseCrashlytics
) {
    fun logScreenView(screenName: String) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenName)
        }
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        crashlytics.log("Screen view: $screenName")
    }

    fun logEvent(name: String, params: Bundle? = null) {
        analytics.logEvent(name, params)
    }

    fun logInfoScreenShown() {
        analytics.logEvent("info_screen_shown", null)
    }

    fun logInfoScreenClosed() {
        analytics.logEvent("info_screen_closed", null)
    }

    fun logException(throwable: Throwable) {
        crashlytics.recordException(throwable)
    }
}