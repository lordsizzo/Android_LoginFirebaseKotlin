package lou.sizzo.android_loginfirebasekotlin.session

import android.app.Activity
import android.content.*
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import lou.sizzo.android_loginfirebasekotlin.*
import lou.sizzo.android_loginfirebasekotlin.session.nav.NavigationIntents
import lou.sizzo.android_loginfirebasekotlin.databinding.ActivityMainBinding
import lou.sizzo.android_loginfirebasekotlin.utils.snackbar
import lou.sizzo.android_loginfirebasekotlin.utils.toast

open class GoogleSession: NavigationIntents() {

    fun isLoggedIn(context: Context): Boolean {
        return GoogleSignIn.getLastSignedInAccount(context) != null
    }

    fun createGoogleSingIn(context: Context): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.id_google_client))
            .requestEmail()
            .build()
    }

    fun handleGoogleSessionResult(completedTask: Task<GoogleSignInAccount>, context: Context, firebaseAuth: FirebaseAuth){
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            account?.let { account ->
                val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                firebaseAuth.signInWithCredential(credential).addOnCompleteListener {task->
                    if(task.isSuccessful) {
                        intentWithGmailSession(context)
                    }
                }
            }
        } catch (e: ApiException){
            e.let {
                context.toast("Error: $it", Toast.LENGTH_SHORT)
            }
        }
    }

    fun loginEmailFirebaseSingIn(context: Context, activity: Activity, firebaseAuth: FirebaseAuth,  loginBinding: ActivityMainBinding, it: View) {
        if (loginBinding.etCorreoLogin.text.toString() != ""){
            if (loginBinding.etPassLogin.text.toString() != ""){
                firebaseAuth.signInWithEmailAndPassword(loginBinding.etCorreoLogin.text.toString(), loginBinding.etPassLogin.text.toString())
                    .addOnCompleteListener(activity) { task ->
                        if (task.isSuccessful) {

                            context.toast("Inicia sesión con email")
                            intentWithEmailFirebaseSession(context)
                        } else {
                            context.toast("${task.exception!!.message}")
                        }
                    }
            }else{
                it.snackbar("Falta Contraseña")
            }

        }else{
            it.snackbar("Falta Correo")
        }
    }

    fun createEmailFirebaseSingIn(context: Context, activity: Activity, firebaseAuth: FirebaseAuth,  loginBinding: ActivityMainBinding, it: View) {
        if (loginBinding.etCorreo.text.toString() != ""){
            if (loginBinding.etPass.text.toString() != ""){
                if (loginBinding.etPassConfirm.text.toString() != ""){
                    if (loginBinding.etPass.text.toString() == loginBinding.etPassConfirm.text.toString()){
                        firebaseAuth.createUserWithEmailAndPassword(loginBinding.etCorreo.text.toString(), loginBinding.etPassConfirm.text.toString())
                            .addOnCompleteListener(activity) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    intentWithEmailFirebaseSession(context)
                                } else {
                                    // If sign in fails, display a message to the user.
                                    context.toast("${task.exception!!.message}")
                                    //updateUI(null)
                                }
                            }
                    }else{
                        it.snackbar("Las contraseñas no coinciden")
                    }
                }else{
                    it.snackbar("Falta confirmar contraseña")
                }
            }else{
                it.snackbar("Falta Contraseña")
            }

        }else{
            it.snackbar("Falta Correo")
        }
    }

    fun recoveryPasswordFirebase(context: Context, activity: Activity, firebaseAuth: FirebaseAuth,  loginBinding: ActivityMainBinding, it: View) {
        if (loginBinding.etCorreoLogin.text.toString() != ""){
            firebaseAuth.sendPasswordResetEmail(loginBinding.etCorreoLogin.text.toString()).addOnCompleteListener(activity){ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    context.toast("Instrucciones enviadas a tu correo con exito")
                    loginBinding.etCorreoLogin.text?.clear()
                } else {

                    context.toast("${task.exception!!.message}")
                }
            }
        }else{
            it.snackbar("Falta Correo")
        }
    }
}