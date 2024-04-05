package com.ruma.moshidemo.http.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ruma.moshidemo.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LoginActivity : AppCompatActivity() {


    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // [START config_signin]
        // Configure Google Sign In
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.web_client_id)).requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        // [END config_signin]

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth


        // 模拟点击登录按钮进行登录
        val loginButton = findViewById<Button>(R.id.login)
        text = findViewById(R.id.text)
        loginButton.setOnClickListener {
            // 模拟登录成功操作

            signIn()
        }
    }

    // [START onactivityresult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    // [END onactivityresult]


    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithCredential:success")
                val user = auth.currentUser
                updateUI(user)
                LoginUtil.loginStatusLiveData.value = true
                finish()
            } else {
                // If sign in fails, display a message to the user.
                LoginUtil.loginStatusLiveData.value = false
                Log.w(TAG, "signInWithCredential:failure", task.exception)
                updateUI(null)
            }
        }
    }

    // [END auth_with_google]
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

    private fun updateUI(user: FirebaseUser?) {

        user?.getIdToken(true)?.addOnCompleteListener {
            if(it.isSuccessful){
                val token = it.result.token
                val authTimestamp = it.result.authTimestamp
                val expirationTimestamp = it.result.expirationTimestamp
                val space = expirationTimestamp - authTimestamp
                val formatMillsecondsddMMMMyy = formatMillsecondsddMMMMyy(space)
                text.text = "email = ${user?.email} \n  displayName =${user?.displayName}\n 过期时间间隔 = ${space}  \n authTimestamp =${authTimestamp} \n expirationTimestamp=$expirationTimestamp"
            }else{
                text.text = "email = ${user?.email} \n  displayName =${user?.displayName} "
            }
        }

    }

    private fun formatMillsecondsddMMMMyy(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.US)

        val date = Date(timestamp)

        val formattedDate: String = sdf.format(date)
        return formattedDate
    }


}
