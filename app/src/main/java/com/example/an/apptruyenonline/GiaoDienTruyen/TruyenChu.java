package com.example.an.apptruyenonline.GiaoDienTruyen;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.an.apptruyenonline.Adapter.TruyenChuAdapter;
import com.example.an.apptruyenonline.Object.Story;
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

public class TruyenChu extends Fragment {
    RecyclerView recyclerView;
    ProgressDialog dialog;
    TruyenChuAdapter adapter;
    ArrayList<Story> list1 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.truyenchu_fragment, container, false);
        dialog = ProgressDialog.show(getActivity(), "", "Loading...", true);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AddJsonIntoList().execute("http://appdemo.pe.hu/StoryByTypeStory.php");
            }
        });

        return v;
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
                    JSONObject storyData = mang.getJSONObject(i);
                    list1.add(new Story(
                            storyData.getInt("IdStory"),
                            storyData.getString("NameStory"),
                            storyData.getString("TypeStory"),
                            storyData.getString("Images"),
                            storyData.getString("Description"),
                            storyData.getString("ReleaseDate"),
                            storyData.getString("Pos"),
                            storyData.getString("Active")
                    ));
                }
                adapter = new TruyenChuAdapter(getActivity(), list1);
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();

        }
    }

    private static String getStoryUrl(String theUrl) {
        String TypeStory = "StoryWord";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(theUrl);
        List nameValuePair = new ArrayList(1);
        nameValuePair.add(new BasicNameValuePair("TypeStory", TypeStory));
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
}
