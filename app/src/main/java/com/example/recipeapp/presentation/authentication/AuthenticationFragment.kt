package com.example.recipeapp.presentation.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import kotlinx.android.synthetic.main.fragment_authentication.*


class AuthenticationFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authentication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Login.setOnClickListener {
            val action = AuthenticationFragmentDirections.actionAuthenticationFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        SignUp.setOnClickListener {
            val action1 = AuthenticationFragmentDirections.actionAuthenticationFragmentToSignUpFragment()
            findNavController().navigate(action1)
        }
    }
}