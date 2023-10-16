package br.dev.alexandreboth.mobile.componentespe.ui.componente;

import android.content.Context;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import br.dev.alexandreboth.mobile.componentespe.R;
import br.dev.alexandreboth.mobile.componentespe.model.Componente;

public class CadComponenteFragment extends Fragment implements View.OnClickListener, Response.ErrorListener, Response.Listener {

   private View view = null;

    private EditText etCompNome;
    private Spinner spCompTipo;
    private Spinner spCompTensao;
    private Spinner spCompGaveta;
    private Spinner spCompEspaco;
    private Button btSalvar;

    //volley
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReq;

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
        this.btSalvar.setOnClickListener(this);

        //instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());

        //inicializando a fila de requests do SO
        this.requestQueue.start();


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
                componente.setTensao(this.spCompTensao.getSelectedItemPosition());
                componente.setGaveta(this.spCompGaveta.getSelectedItemPosition());
                componente.setEspaco(this.spCompEspaco.getSelectedItemPosition());

                //chamar o webservice
                jsonObjectReq = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://10.0.2.2/cadcomp3.php",
                        componente.toJsonObject(), this, this);
                requestQueue.add(jsonObjectReq);

                break;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.make(view,
                "Ops! Houve um problema ao realizar o cadastro: " +
                        error.toString(),Snackbar.LENGTH_LONG);
        mensagem.show();


    }

    @Override
    public void onResponse(Object response) {

        try {
            //instanciando objeto para manejar o JSON que recebemos
            JSONObject jason = new JSONObject(response.toString());
            //context e text são para a mensagem na tela o Toast
            Context context = view.getContext();
            //pegando mensagem que veio do json
            CharSequence text = jason.getString( "message");
            //duracao da mesnagem na tela
            int duration = Toast.LENGTH_SHORT;

            //verificando se salvou sem erro ara limpar campos na tela
            if (jason.getBoolean("success")){
                //campos texto
                this.etCompNome.setText("");
                //selecionando primeiro item dos spinners
                this.spCompTipo.setSelection(0);
                this.spCompTensao.setSelection(0);
                this.spCompGaveta.setSelection(0);
                this.spCompEspaco.setSelection(0);


            }
            Toast toast = Toast.makeText (context, text, duration);
            toast.show();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



    }
}