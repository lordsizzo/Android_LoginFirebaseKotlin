package lou.sizzo.android_loginfirebasekotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import lou.sizzo.android_loginfirebasekotlin.session.GoogleSession
import lou.sizzo.android_loginfirebasekotlin.databinding.ActivityMainBinding
import lou.sizzo.android_loginfirebasekotlin.utils.click
import lou.sizzo.android_loginfirebasekotlin.utils.fadeVisibility
import lou.sizzo.android_loginfirebasekotlin.utils.toast


class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding: ActivityMainBinding

    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val googleIn = GoogleSession()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        FirebaseApp.initializeApp(this)

        mGoogleSignInClient= GoogleSignIn.getClient(this, googleIn.createGoogleSingIn(this))

        initViewListeners()

        //Revisa si hay una sesión iniciada
        val user = firebaseAuth.currentUser
        toast(user.toString())
        user?.let {
            if (googleIn.isLoggedIn(this)){

                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    fun initViewListeners(){

        //Login with Gmail
        loginBinding.btnGoogle.click{
            signInGoogle()
        }

        //Create user with Email
        loginBinding.btnCorreo.click{
            GoogleSession().createEmailFirebaseSingIn(this, this@LoginActivity, firebaseAuth,  loginBinding, it)
        }

        //Login with Email
        loginBinding.btnLogin.click{
            GoogleSession().loginEmailFirebaseSingIn(this, this@LoginActivity, firebaseAuth,  loginBinding, it)
        }

        //Recovery password with email
        loginBinding.btnRecuperarPass.click{
            GoogleSession().recoveryPasswordFirebase(this, this@LoginActivity, firebaseAuth,  loginBinding, it)
        }

        loginBinding.btnRegistrar.click{

            loginBinding.campoLogin.fadeVisibility(View.GONE)
            loginBinding.campoRegistro.fadeVisibility(View.VISIBLE)

            loginBinding.btnRegistrar.fadeVisibility(View.GONE)
            loginBinding.btnYaTengoCuenta.fadeVisibility(View.VISIBLE)
        }

        loginBinding.btnYaTengoCuenta.click{
            loginBinding.campoLogin.fadeVisibility(View.VISIBLE)
            loginBinding.campoRegistro.fadeVisibility(View.GONE)

            loginBinding.btnRegistrar.fadeVisibility(View.VISIBLE)
            loginBinding.btnYaTengoCuenta.fadeVisibility(View.GONE)
        }
    }

    private fun signInGoogle(){
        toast("Logging In", Toast.LENGTH_SHORT)
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            GoogleSession().handleGoogleSessionResult(task, this, firebaseAuth)
        }
    }
}