package com.example.an.apptruyenonline.GiaoDienTruyen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.an.apptruyenonline.Adapter.ChapterTruyenAdapter;
import com.example.an.apptruyenonline.Object.Chapter;
import com.example.an.apptruyenonline.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ChapterTruyen extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Chapter> list = new ArrayList<>();
    ChapterTruyenAdapter adapter;
    ProgressDialog dialog;
    String IdPart,NamePart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_truyen);

        dialog = ProgressDialog.show(this, "", "Loading...", true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Intent intent = getIntent();
        IdPart = intent.getStringExtra("IdPart");
        NamePart = intent.getStringExtra("NamePart");
        getSupportActionBar().setTitle(NamePart);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AddJsonIntoList().execute("http://appdemo.pe.hu/ChapterByIdPart.php");
            }
        });
    }

    class AddJsonIntoList extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return getStoryUrl(params[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray mang = new JSONArray(s);
                for (int i = 0; i < mang.length(); i++) {
                    JSONObject chapterData = mang.getJSONObject(i);
                    list.add(new Chapter(
                            chapterData.getInt("IdChapter"),
                            chapterData.getInt("IdPart"),
                            chapterData.getString("ContentChapter"),
                            chapterData.getString("Images"),
                            chapterData.getInt("Pos"),
                            chapterData.getInt("Active")
                    ));
                }
                adapter = new ChapterTruyenAdapter(ChapterTruyen.this, list);
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();

        }
    }

    private String getStoryUrl(String theUrl) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(theUrl);
        List nameValuePair = new ArrayList(1);
        nameValuePair.add(new BasicNameValuePair("IdPart", IdPart));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String kq = "";
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            kq = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
