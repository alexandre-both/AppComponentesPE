package br.dev.alexandreboth.mobile.componentespe.ui.componente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.dev.alexandreboth.mobile.componentespe.R;

public class CadComponenteFragment extends Fragment {

   private View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_cad_componente, container, false);
        return this.view;
    }
}