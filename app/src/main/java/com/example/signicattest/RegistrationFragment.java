package com.example.signicattest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.signicattest.databinding.FragmentFirstBinding;

public class RegistrationFragment extends Fragment {

    private FragmentFirstBinding binding;
    SharedPreferences sharedPreferences;
    EditText user;
    EditText pin;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = getView().findViewById(R.id.editTextUser);
        pin = getView().findViewById(R.id.editTextNumberPassword);
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = String.valueOf(user.getText());
                String userPin = String.valueOf(pin.getText());
                sharedPreferences = getActivity().getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
                // Sjekk at begge felter har en verdi
                if (!(userName.equals("")) && !(userPin.equals(""))) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.user_key),userName);
                    editor.putInt(getString(R.string.pin_key),Integer.valueOf(userPin));
                    editor.apply();
                    Toast.makeText(getContext(), R.string.registration_success, Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(RegistrationFragment.this)
                            .navigate(R.id.action_Register);
                }
                else if ((userName.equals("")) && (userPin.equals(""))) {
                    Toast.makeText(getContext(), R.string.both_missing, Toast.LENGTH_SHORT).show();
                }
                else if ((userName.equals(""))) {
                    Toast.makeText(getContext(), R.string.user_missing, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), R.string.pin_missing, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}