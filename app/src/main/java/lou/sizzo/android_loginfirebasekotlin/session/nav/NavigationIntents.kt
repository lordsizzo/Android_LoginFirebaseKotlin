package lou.sizzo.android_loginfirebasekotlin.session.nav

import android.app.Activity
import android.content.Context
import android.content.Intent
import lou.sizzo.android_loginfirebasekotlin.LoginActivity
import lou.sizzo.android_loginfirebasekotlin.SecondActivity

abstract class NavigationIntents {

    fun intentWithEmailFirebaseSession(context: Context){
        val intent = Intent(context, SecondActivity::class.java)
        context.startActivity(intent)
        (context as Activity).finish()
    }

    fun intentWithGmailSession(context: Context){
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        (context as Activity).finish()
    }
}