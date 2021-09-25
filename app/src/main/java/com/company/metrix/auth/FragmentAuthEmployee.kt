package com.company.metrix.auth

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.company.metrix.databinding.FragmentEmployeeAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FragmentAuthEmployee() : Fragment() {

    companion object{
        fun newInstance(authHandler: AuthHandler): Fragment {
            return FragmentAuthEmployee(authHandler)
        }
    }

    private constructor(authHandler: AuthHandler) : this(){
        this.authHandler = authHandler
    }

    private var authHandler : AuthHandler? = null
    private var binding : FragmentEmployeeAuthBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmployeeAuthBinding.inflate(inflater)
        binding?.buttonEmployeeAuth?.setOnClickListener { authorizationHandler.launch(getSignInIntent()) }
        auth = Firebase.auth
        return binding?.root
    }

    private val authorizationHandler =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            if (result?.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val account = task.result
                firebaseAuthWithGoogle(account.idToken!!)
            } else {
                showErrorToast()
            }
        }

    private fun getSignInIntent(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("628067409317-2oru4c54664ac946q9re305fe5i46f1m.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        return mGoogleSignInClient.signInIntent
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    onSignedIn(auth.currentUser!!)
                } else {
                    showErrorToast()
                }
            }
    }

    private fun onSignedIn(user: FirebaseUser) {
        val name = user.displayName
        authHandler?.handleSuccessAuth()
    }

    private fun showErrorToast() {
        Toast.makeText(requireContext(), "Sign In Error", Toast.LENGTH_SHORT).show()
    }

}