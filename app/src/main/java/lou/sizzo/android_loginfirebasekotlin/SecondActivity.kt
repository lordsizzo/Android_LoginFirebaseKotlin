package lou.sizzo.android_loginfirebasekotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import lou.sizzo.android_loginfirebasekotlin.session.GoogleSession
import lou.sizzo.android_loginfirebasekotlin.databinding.SecondActivityBinding
import lou.sizzo.android_loginfirebasekotlin.dialogs.Dialogs

class SecondActivity : AppCompatActivity() {

    val googleIn = GoogleSession()
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    lateinit var mGoogleSignInClient: GoogleSignInClient

    lateinit var binding: SecondActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mGoogleSignInClient= GoogleSignIn.getClient(this, googleIn.createGoogleSingIn(this))

        //Check if exists a logged user
        val user = firebaseAuth.currentUser
        user?.let { info ->
            binding.tv.text = info.email
            info.photoUrl?.let {
                Glide.with(this)
                    .load(it)
                    .into(binding.img)
            }
        }

        binding.btnCerrarSesion.setOnClickListener{
            Dialogs().bottomDialogCerrarSesion(this@SecondActivity, mGoogleSignInClient)
        }
    }
}