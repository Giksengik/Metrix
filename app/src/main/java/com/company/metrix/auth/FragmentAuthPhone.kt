package com.company.metrix.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.metrix.R
import com.company.metrix.databinding.FragmentPhoneAuthBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class FragmentAuthPhone() : Fragment() {

    companion object {
        fun newInstance(authHandler: AuthHandler): Fragment {
            return FragmentAuthPhone(authHandler)
        }
    }

    private constructor(authHandler: AuthHandler) : this() {
        this.authHandler = authHandler
    }

    private var authHandler: AuthHandler? = null
    private var binding: FragmentPhoneAuthBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneAuthBinding.inflate(inflater)
        binding?.buttonConfirmPhoneNumber?.setOnClickListener {
            binding?.phoneNumberBlock?.isEnabled = false
            binding?.authProgressBar?.visibility = View.VISIBLE
            binding?.buttonConfirmPhoneNumber?.visibility = View.INVISIBLE
            sendVerificationCode()
        }
        binding?.buttonConfirmVerificationCode?.setOnClickListener {
            binding?.authProgressBar?.visibility = View.VISIBLE
            binding?.buttonConfirmVerificationCode?.visibility = View.INVISIBLE
            binding?.verificationCodeBlock?.isEnabled = false
            signInWithPhoneAuthCredential()
        }
        auth = Firebase.auth
        return binding?.root
    }

    private fun sendVerificationCode() {
        val phoneNumber = binding?.phoneNumberField?.text.toString()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    binding?.phoneNumberBlock?.isEnabled = true
                    binding?.phoneNumberBlock?.error = getString(R.string.number_error)
                    binding?.authProgressBar?.visibility = View.INVISIBLE
                    binding?.buttonConfirmPhoneNumber?.visibility = View.VISIBLE
                }

                override fun onCodeSent(id: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    verificationId = id
                    binding?.phoneNumberBlock?.visibility = View.INVISIBLE
                    binding?.buttonConfirmPhoneNumber?.visibility = View.INVISIBLE
                    binding?.authProgressBar?.visibility = View.INVISIBLE
                    binding?.verificationCodeBlock?.visibility = View.VISIBLE
                    binding?.buttonConfirmVerificationCode?.visibility = View.VISIBLE
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential() {
        val code = binding?.verificationCodeField?.text.toString()
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    onSignedIn(task.result.user!!)
                } else {
                    binding?.verificationCodeBlock?.error = getString(R.string.code_error)
                    binding?.authProgressBar?.visibility = View.INVISIBLE
                    binding?.buttonConfirmVerificationCode?.visibility = View.VISIBLE
                    binding?.verificationCodeBlock?.isEnabled = true
                }
            }
    }

    private fun onSignedIn(user: FirebaseUser) {
        //val phoneNumber = user.phoneNumber
        authHandler?.handleSuccessAuth()
        binding?.authProgressBar?.visibility = View.INVISIBLE
        binding?.buttonConfirmVerificationCode?.visibility = View.VISIBLE
    }

}