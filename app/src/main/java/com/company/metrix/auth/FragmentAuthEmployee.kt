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
import com.company.metrix.R
import com.company.metrix.databinding.FragmentEmployeeAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FragmentAuthEmployee() : Fragment() {

    private lateinit var database: DatabaseReference

    companion object{
        fun newInstance(authHandler: AuthHandler): Fragment {
            return FragmentAuthEmployee(authHandler)
        }
    }

    private constructor(authHandler: AuthHandler) : this(){
        this.authHandler = authHandler
    }

    private var authHandler : AuthHandler? = null
    private lateinit var binding : FragmentEmployeeAuthBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeeAuthBinding.inflate(inflater)
        binding.buttonEmployeeAuth.setOnClickListener {
            binding.authProgressBar.visibility = View.VISIBLE
            binding.buttonEmployeeAuth.visibility = View.INVISIBLE
            authorizationHandler.launch(getSignInIntent())
        }

        database = Firebase.database.reference.child("users")
        auth = Firebase.auth
        return binding.root
    }

    private val authorizationHandler =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            if (result?.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val account = task.result
                firebaseAuthWithGoogle(account.idToken!!)
            } else {
                showError()
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
                    checkInDatabase()
                } else {
                    showError()
                }
            }
    }

    private fun checkInDatabase() {
        val user = Firebase.auth.currentUser!!
        database.orderByChild("id").equalTo(user.email).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.children.iterator().hasNext()) {
                    addToDatabase(user.uid, user.email!!)
                } else {
                    onSignedIn()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun addToDatabase(uid: String, email: String) {
        val userDatabase = database.child(uid)
        userDatabase.child("id").setValue(email)
        userDatabase.child("team").setValue(0)
        onSignedIn()
    }


    private fun onSignedIn() {
        authHandler?.handleSuccessAuth()
        binding.authProgressBar.visibility = View.VISIBLE
        binding.buttonEmployeeAuth.visibility = View.INVISIBLE
    }

    private fun showError() {
        Toast.makeText(requireContext(), getString(R.string.auth_error), Toast.LENGTH_SHORT).show()
        binding.authProgressBar.visibility = View.VISIBLE
        binding.buttonEmployeeAuth.visibility = View.INVISIBLE
    }

}