package com.example.an.apptruyenonline.Login;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.an.apptruyenonline.R;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by tibui on 04/07/2016.
 */
public class SignUpFragment extends Fragment {
    EditText fullname, password,re_password,phone,username;
    ProgressDialog dialog;
    String id,name,avata,fbemail;
    private int networkId;
    Switch sw;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.login_sign_up, container, false);
        //dialog = ProgressDialog.show(SignUpFragment.this.getActivity(), "", "Loading...", true);
        fullname = (EditText) v.findViewById(R.id.fullname);
        username = (EditText)v.findViewById(R.id.username);
        password = (EditText) v.findViewById(R.id.password);
        re_password = (EditText) v.findViewById(R.id.re_password);
        phone  = (EditText) v.findViewById(R.id.phone);
        ImageView img = (ImageView)v.findViewById(R.id.imageView2);
        Picasso.with(getActivity())
                .load("https://bullsender.com/templates/images/ole-signup.png")
                .fit().centerCrop().into(img);
        Button btnSign = (Button) v.findViewById(R.id.btnSign);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullname.getText().toString().length() == 0 ||fullname.getText().toString().equals(" ")) {
                    fullname.setError("Tên không được trống");
                }else if (phone.getText().toString().length() == 0 ||phone.getText().toString().equals(" ")) {
                    phone.setError("Số điện thoại không được trống");
                }else if (username.getText().toString().length() == 0||username.getText().toString().equals(" ")) {
                    username.setError("Tài khoản không được trống");
                }  else if (password.getText().toString().trim().length() < 5||password.getText().toString().equals(" ")) {
                    password.setError("Mật khẩu không được trống hoặc nhỏ hơn 5 ký tự");
                }  else if(!re_password.getText().toString().equals(password.getText().toString())){
                    re_password.setError("Mật khẩu không trùng");
                }
                 else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new goiWebServices().execute("http://appdemo.pe.hu/UserInsert.php");
                            Toast.makeText(SignUpFragment.this.getActivity(),"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });
        return v;
    }

    class goiWebServices extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... params) {
            return makePostRequest(params[0]);
        }
        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(SignUpFragment.this.getActivity(), "", "Loading...", true);

        }
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(SignUpFragment.this.getActivity(), s, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    }
    private String makePostRequest(String u) {
        HttpClient httpClient = new DefaultHttpClient();
        // URL của trang web nhận request
        HttpPost httpPost = new HttpPost(u);
        // Các tham số truyền
        List nameValuePair = new ArrayList(9);
        nameValuePair.add(new BasicNameValuePair("action", "insert"));
        nameValuePair.add(new BasicNameValuePair("UserName", username.getText().toString()));
        nameValuePair.add(new BasicNameValuePair("PassWord", password.getText().toString()));
        nameValuePair.add(new BasicNameValuePair("FullName", fullname.getText().toString()));
        nameValuePair.add(new BasicNameValuePair("PhoneNumber", phone.getText().toString()));
        nameValuePair.add(new BasicNameValuePair("FaceBookId", "1"));
        nameValuePair.add(new BasicNameValuePair("Avatar", "null"));
        nameValuePair.add(new BasicNameValuePair("Pos", "1"));
        nameValuePair.add(new BasicNameValuePair("Active", "1"));

        //Encoding POST data
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
