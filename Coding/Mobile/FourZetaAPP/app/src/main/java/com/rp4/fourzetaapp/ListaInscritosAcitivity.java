package com.rp4.fourzetaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.rp4.fourzetaapp.Connection.HttpService;
import com.rp4.fourzetaapp.model.CategoriaParcelable;
import com.rp4.fourzetaapp.model.Circuito;
import com.rp4.fourzetaapp.model.Dupla;
import com.rp4.fourzetaapp.model.Torneio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListaInscritosAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        if(extras != null) {
            Circuito circuito = intent.getExtras().getParcelable("circuito");
            System.out.println("CIRCUITO: " + circuito.getNome() + " " + circuito.getDescricao());

            Torneio torneio = intent.getExtras().getParcelable("torneio");
            System.out.println("TORNEIO: " + torneio.getNome() + " " + torneio.getDescricao());
//            CategoriaParcelable cp = intent.getExtras().getParcelable("categoria");
//            System.out.println("CP: " + cp.getNome());
//        }

        getSupportActionBar().setTitle(circuito.getNome());
        getSupportActionBar().setSubtitle(torneio.getNome());
        setContentView(R.layout.activity_lista_inscritos_acitivity);

        final List<Torneio> torneios = new ArrayList<>();
        final List<Dupla> duplas = new ArrayList<>();

        try {
            List<Circuito> retorno = new HttpService().execute().get();
            int cont= 0;
            while(cont < retorno.size()){
                if(retorno.get(cont).getId() == circuito.getId()){
                    circuito = retorno.get(cont);
                }
                cont++;
            }
            int cont2=0;
            while(cont2 < circuito.getTorneios().size()){
                if(circuito.getTorneios().get(cont2).getId() == torneio.getId()){
                    torneio = circuito.getTorneios().get(cont2);
                }
                cont2++;
            }
            duplas.addAll(torneio.getDuplas());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Lista Inscritos
        ListView lista = (ListView) findViewById(R.id.list_inscritos);
        ArrayList<String> equipes = preencherDados(duplas);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, equipes);
        lista.setAdapter(arrayAdapter);

        Button btnPesquisar = findViewById(R.id.btnVoltar);
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaInscritosAcitivity.this, InscritosActivity.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<String> preencherDados(List<Dupla> duplas){
        ArrayList<String> dados = new ArrayList<String>();
        if(duplas != null) {
            int cont=0;
            while(cont < duplas.size()){
                dados.add("Dupla: " + duplas.get(cont).getAtleta1().getNome() + " & " + duplas.get(cont).getAtleta2().getNome() + "\nCategoria: " + duplas.get(cont).getCategoria());
                cont++;
            }
        }else{
            dados.add("Nenhum Registro Encontrado");
        }
        return dados;
    }

}
