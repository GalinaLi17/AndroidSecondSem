package ru.itis.android.uprising26.firebase

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.android.uprising26.utils.NotificationHelper
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationHelper: NotificationHelper

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val data = message.data
        if (data.isNotEmpty()) {
            val title = data["title"] ?: "Uprising Notification"
            val body = data["message"] ?: ""
            val kind = data["kind"]

            notificationHelper.showNotification(
                title = title,
                message = body,
                kind = kind
            )
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}