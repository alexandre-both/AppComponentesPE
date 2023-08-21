package br.dev.alexandreboth.mobile.componentespe.ui.componente;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.dev.alexandreboth.mobile.componentespe.R;
import br.dev.alexandreboth.mobile.componentespe.model.Componente;

public class CadComponenteFragment extends Fragment implements View.OnClickListener {

   private View view = null;

    private EditText etCompNome;
    private Spinner spCompTipo;
    private Spinner spCompTensao;
    private Spinner spCompGaveta;
    private Spinner spCompEspaco;
    private Button btSalvar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_cad_componente, container, false);

        this.etCompNome = (EditText) view.findViewById(R.id.etNomeComponente);
        this.spCompTipo = (Spinner) view.findViewById(R.id.spTipoComponente);
        this.spCompTensao = (Spinner) view.findViewById(R.id.spTensaoOperacao);
        this.spCompGaveta = (Spinner) view.findViewById(R.id.spGaveta);
        this.spCompEspaco = (Spinner) view.findViewById(R.id.spEspaco);
        this.btSalvar = (Button) view.findViewById(R.id.btSalvarComp);

        return this.view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        //verificando se é o botão salvar
            case R.id.btSalvarComp:
                //instanciando o objeto de negocio
                Componente componente = new Componente();
                componente.setNome(this.etCompNome.getText().toString());
                componente.setTipo(this.spCompTipo.getSelectedItemPosition());


                break;
        }

    }
}