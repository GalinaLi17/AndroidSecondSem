package ru.itis.android.uprising26.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.itis.android.uprising26.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        private const val CHANNEL_PROMO = "promo_channel"
        private const val CHANNEL_AUTH = "auth_channel"
        private const val CHANNEL_DEFAULT = "default_channel"
    }

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val promoChannel = NotificationChannel(
                CHANNEL_PROMO,
                context.getString(R.string.notification_channel_promo_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = context.getString(R.string.notification_channel_promo_desc) }

            val authChannel = NotificationChannel(
                CHANNEL_AUTH,
                context.getString(R.string.notification_channel_auth_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = context.getString(R.string.notification_channel_auth_desc) }

            val defaultChannel = NotificationChannel(
                CHANNEL_DEFAULT,
                context.getString(R.string.notification_channel_default_name),
                NotificationManager.IMPORTANCE_LOW
            )

            notificationManager.createNotificationChannel(promoChannel)
            notificationManager.createNotificationChannel(authChannel)
            notificationManager.createNotificationChannel(defaultChannel)
        }
    }

    fun showNotification(title: String, message: String, kind: String?) {
        val channelId = when (kind) {
            "promo" -> CHANNEL_PROMO
            "auth" -> CHANNEL_AUTH
            else -> CHANNEL_DEFAULT
        }

        val displayTitle = title.ifEmpty {
            when (kind) {
                "promo" -> context.getString(R.string.notification_promo_title)
                "auth" -> context.getString(R.string.notification_auth_title)
                else -> context.getString(R.string.notification_default_title)
            }
        }

        val displayMessage = message.ifEmpty { context.getString(R.string.notification_default_message) }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(displayTitle)
            .setContentText(displayMessage)
            .setAutoCancel(true)
            .setPriority(if (kind == "auth") NotificationCompat.PRIORITY_HIGH else NotificationCompat.PRIORITY_DEFAULT)

        if (kind == "promo") {
            builder.setStyle(NotificationCompat.BigTextStyle().bigText(displayMessage))
        } else if (kind == "auth") {
            builder.setCategory(NotificationCompat.CATEGORY_ALARM)
        }

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}