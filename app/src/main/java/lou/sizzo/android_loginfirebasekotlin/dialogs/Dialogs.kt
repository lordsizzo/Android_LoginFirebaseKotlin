package lou.sizzo.android_loginfirebasekotlin.dialogs

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.app.Activity
import lou.sizzo.android_loginfirebasekotlin.LoginActivity
import lou.sizzo.android_loginfirebasekotlin.databinding.BottomSheetLogoutBinding
import lou.sizzo.android_loginfirebasekotlin.utils.toast

class Dialogs {

    fun bottomDialogCerrarSesion(context: Context, mGoogleSignInClient: GoogleSignInClient){

        var bindingLogout: BottomSheetLogoutBinding = BottomSheetLogoutBinding.inflate(
            LayoutInflater.from(context))
        val dialogBSDLogout = BottomSheetDialog(context)
        dialogBSDLogout.setCancelable(false)

        bindingLogout.btnCancelar.setOnClickListener {
            dialogBSDLogout.dismiss()
        }
        bindingLogout.btnAceptarCerrarSession .setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {

                context.toast("Cerrando sesión")
                val intent= Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
                (context as Activity).finish()
            }
            dialogBSDLogout.dismiss()
        }
        dialogBSDLogout.setContentView(bindingLogout.root)
        dialogBSDLogout.show()
    }
}