package com.example.signicattest;

import android.content.Context;
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

import com.example.signicattest.databinding.FragmentSecondBinding;

public class LoginFragment extends Fragment {

    private FragmentSecondBinding binding;
    SharedPreferences sharedPreferences;
    EditText pin;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pin = getView().findViewById(R.id.editTextPinLogin);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userPin = String.valueOf(pin.getText());
                sharedPreferences = getActivity().getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
                // Sjekk at pin feltet har en verdi
                if (!(userPin.equals(""))) {
                    String savedPin = sharedPreferences.getString(getString(R.string.pin_key),"");
                    if(savedPin.equals(userPin)) {
                        Toast.makeText(getContext(), R.string.login_success, Toast.LENGTH_SHORT).show();
                        NavHostFragment.findNavController(LoginFragment.this)
                                .navigate(R.id.action_Login);
                    }
                    else {
                        Toast.makeText(getContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();
                    }
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