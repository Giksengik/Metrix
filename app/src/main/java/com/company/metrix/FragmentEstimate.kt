package com.company.metrix

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.company.metrix.databinding.FragmentEstimateBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class FragmentEstimate : Fragment() {
    private var binding : FragmentEstimateBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEstimateBinding.inflate(inflater)
        binding?.buttonConfirmPhoneNumber?.setOnClickListener { sendVerificationCode() }
        binding?.buttonConfirmVerificationCode?.setOnClickListener { signInWithPhoneAuthCredential() }
        auth = Firebase.auth
        return binding?.root
    }

    private fun sendVerificationCode() {
        val phoneNumber = binding?.phoneNumberField?.text.toString()
        if (PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        showCodeToast()
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        showErrorToast()
                        p0.printStackTrace()
                    }

                    override fun onCodeSent(id: String, p1: PhoneAuthProvider.ForceResendingToken) {
                        verificationId = id
                    }
                })
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    private fun signInWithPhoneAuthCredential() {
        val code = binding?.verificationCodeField?.text.toString()
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    onSignedIn(task.result.user!!)
                } else {
                    showErrorToast()
                }
            }
    }

    private fun onSignedIn(user: FirebaseUser) {
        val phoneNumber = user.phoneNumber
        Toast.makeText(requireContext(), "Success, your number is $phoneNumber", Toast.LENGTH_SHORT).show()
    }

    private fun showErrorToast() {
        Toast.makeText(requireContext(), "Sign In Error", Toast.LENGTH_SHORT).show()
    }

    private fun showCodeToast() {
        Toast.makeText(requireContext(), "Введите код подтверждения", Toast.LENGTH_SHORT).show()
    }

}