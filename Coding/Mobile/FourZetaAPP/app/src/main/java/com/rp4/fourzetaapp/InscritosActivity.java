package com.rp4.fourzetaapp;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.rp4.fourzetaapp.Connection.HttpService;
import com.rp4.fourzetaapp.model.Categoria;
import com.rp4.fourzetaapp.model.CategoriaParcelable;
import com.rp4.fourzetaapp.model.Circuito;
import com.rp4.fourzetaapp.model.Dupla;
import com.rp4.fourzetaapp.model.Torneio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.widget.AdapterView.*;

public class InscritosActivity extends AppCompatActivity implements OnItemSelectedListener{

    private Spinner spinnerCircuito;
    ArrayAdapter<Circuito> adapterCircuito;
    private Spinner spinnerTorneio;
    ArrayAdapter<Torneio> adapterTorneio;
    private Spinner spinnerCategoria;
    ArrayAdapter<Categoria> adapterCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Inscritos"); //Titulo para ser exibido na sua Action Bar em frente à seta
        setContentView(R.layout.activity_inscritos);


        List<Circuito> circuitos = new ArrayList<>();
        final List<Torneio> torneios = new ArrayList<>();
        final List<Dupla> duplas = new ArrayList<>();

        try {
            List<Circuito> retorno = new HttpService().execute().get();
            circuitos = retorno;
            //resposta.setText(retorno.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Spinner Circuito
        spinnerCircuito = findViewById(R.id.spinnerCircuito);
        adapterCircuito = new ArrayAdapter<Circuito>(this, android.R.layout.simple_spinner_item, circuitos);
        adapterCircuito.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinnerCircuito.setAdapter(adapterCircuito);
        spinnerCircuito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Circuito circuito = (Circuito) parent.getSelectedItem();
                torneios.clear();
                torneios.addAll(circuito.getTorneios());
                adapterTorneio.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner Torneio
        spinnerTorneio = findViewById(R.id.spinnerTorneio);
        adapterTorneio = new ArrayAdapter<Torneio>(this, android.R.layout.simple_spinner_item, torneios);
        adapterTorneio.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinnerTorneio.setAdapter(adapterTorneio);
        spinnerTorneio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Torneio torneio = (Torneio) parent.getSelectedItem();
                for(int i = 0; i < torneio.getDuplas().size(); i++){
                   duplas.add(torneio.getDuplas().get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner Categoria
//        spinnerCategoria = findViewById(R.id.spinnerCategoria);
//        adapterCategoria = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item, Categoria.values());
//        adapterCategoria.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
//        adapterCategoria.notifyDataSetChanged();
//        spinnerCategoria.setAdapter(adapterCategoria);
//        spinnerCategoria.setOnItemSelectedListener(this);

        Button btnPesquisar = findViewById(R.id.btnPesquisar);
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InscritosActivity.this, ListaInscritosAcitivity.class);
                Circuito circuito = (Circuito) spinnerCircuito.getSelectedItem();
                Torneio torneio = (Torneio) spinnerTorneio.getSelectedItem();
//                Categoria categoria = (Categoria) spinnerCategoria.getSelectedItem();
//                CategoriaParcelable cp = new CategoriaParcelable(categoria.toString());

                System.out.println("CIRCUITO -> " + circuito.getNome());
                System.out.println("TORNEIO -> " + torneio.getNome());

                intent.putExtra("circuito", circuito);
                intent.putExtra("torneio", torneio);
//                intent.putExtra("categoria", cp);
//                startActivity(intent);
                startActivity(intent);
            }
        });

    }

    public void getSelectCircuito(View v){
        Circuito circuito = (Circuito) spinnerCircuito.getSelectedItem();
    }

    public void displayCircuitoData(Circuito circuito){
        String nome = circuito.getNome();
        String descricao = circuito.getDescricao();

    }

    public void getSelectTorneio(View v){
        Torneio torneio = (Torneio) spinnerTorneio.getSelectedItem();
    }

    public void displayTorneioData(Torneio torneio){
        String nome = torneio.getNome();
        String descricao = torneio.getDescricao();
    }

    public void getSelectCategoria(View v){
        Categoria categoria = (Categoria) spinnerCategoria.getSelectedItem();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

    @Override
    public void onBackPressed(){ //Botão BACK padrão do android
        startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        return;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
