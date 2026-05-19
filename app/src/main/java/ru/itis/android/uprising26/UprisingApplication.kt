package ru.itis.android.uprising26

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import ru.itis.android.uprising26.utils.UserIdProvider
import javax.inject.Inject

@HiltAndroidApp
class UprisingApplication : Application() {

    @Inject
    lateinit var userIdProvider: UserIdProvider

    override fun onCreate() {
        super.onCreate()
        
        val userId = userIdProvider.getUserId()
        FirebaseCrashlytics.getInstance().setUserId(userId)
        
        FirebaseCrashlytics.getInstance().setCustomKey("internal_user_id", userId)
    }
}