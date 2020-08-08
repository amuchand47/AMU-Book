package com.herokuapp.amulibraray.amulibrary.Notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.herokuapp.amulibraray.amulibrary.Adapter.Adapter;
import com.herokuapp.amulibraray.amulibrary.Model.Song;
import com.herokuapp.amulibraray.amulibrary.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Song> songs = new ArrayList<>();
    Adapter adapter;
    String [] data ;
    ProgressBar progressBar;
    private SearchView mSearchView;
    private ArrayList <Song> songArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        recyclerView = findViewById(R.id.songsList);
        ScrapSite();

        mSearchView = (SearchView)findViewById(R.id.searchView);
        setupSearchView();

    }
    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void ScrapSite() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest("https://www.amu.ac.in/shownoticeall.jsp", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Elements word;

                songArrayList = new ArrayList<>();

                Document doc = Jsoup.parse(response);

                word = doc.getElementsByTag("p");

                data = new String[word.size()];

                for(int i=0;i<100;i++){

                    String name =word.eq(i).text();
                    String image_url = word.eq(i).select("a").attr("href");
                    String tf = word.eq(i).select("a").attr("href");
                    Log.i("mytag","Items found: "+tf);
                    songArrayList.add(new Song(image_url, name));
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(NoticeActivity.this));
                adapter= new Adapter(NoticeActivity.this, songArrayList);
                recyclerView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

}