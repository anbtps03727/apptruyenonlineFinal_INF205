package com.example.an.apptruyenonline.Login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.an.apptruyenonline.GiaoDienTruyen.TabMainTruyen;
import com.example.an.apptruyenonline.Object.User;
import com.example.an.apptruyenonline.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by An on 12/9/2016.
 */

public class LoginFragment extends Fragment {
    public static String filename1 = "user_detail";
    ProgressDialog dialog;
    ArrayList<User> listuser = new ArrayList<User>();
    EditText username, password;
    TextView signup;
    String fullname, avatar, phone;
    int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_login, container, false);
        username = (EditText) v.findViewById(R.id.username);
        password = (EditText) v.findViewById(R.id.password);
        ImageView img = (ImageView)v.findViewById(R.id.imageView);
        Picasso.with(getActivity())
                .load("http://covenantomaha.org/wp-content/uploads/2013/08/Graphic_Logo_The_Story.jpg")
                .fit().centerCrop().into(img);
        Button btnLogin = (Button) v.findViewById(R.id.loginbtn);
        signup = (TextView) v.findViewById(R.id.tvSignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.view_pager, signUpFragment);
                fragmentTransaction.commit();
            }
        });

        dialog = ProgressDialog.show(LoginFragment.this.getActivity(), "", "Loading...", true);
        SharedPreferences pre1 = getActivity().getSharedPreferences(filename1, getActivity().MODE_PRIVATE);
        id = pre1.getInt("id", 0);
        if(id!=0){
            Intent intent = new Intent(LoginFragment.this.getActivity(), TabMainTruyen.class);
            startActivity(intent);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() == 0) {
                    username.setError("Tài khoản không được trống");
                } else if (password.getText().toString().length() == 0) {
                    password.setError("Mật khẩu không được trống");
                } else {
                    Boolean resultlog = checkLogin(username.getText().toString(), password.getText().toString());
                    if (resultlog) {
                        Toast.makeText(getActivity(), "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                        savePreferencesUser();
                    Intent intent = new Intent(LoginFragment.this.getActivity(), TabMainTruyen.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "Tài khoản hoặc mật khẩu không chính xác !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new docJSON1().execute("http://appdemo.pe.hu/UserSelecte.php");
            }
        });
        return v;
    }

    class docJSON1 extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {

            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPreExecute() {
            dialog.show();

        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray mang = new JSONArray(s);
                for (int i = 0; i < mang.length(); i++) {
                    JSONObject userData = mang.getJSONObject(i);
                         listuser.add(new User(
                                 userData.getInt("UserId"),
                                 userData.getString("UserName"),
                                 userData.getString("PassWord"),
                                 userData.getString("FullName"),
                                 userData.getString("PhoneNumber"),
                                 userData.getString("FacebookId"),
                                 userData.getString("Avatar"),
                                 userData.getInt("Pos"),
                                 userData.getInt("Active")
                         ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }
    }

    private static String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public boolean checkLogin(String user, String pass) {

        for (int i = 0; i < listuser.size(); i++) {
            String username = listuser.get(i).Username;
            String password = listuser.get(i).Password;
            if (username.equals(user) && password.equals(pass)) {
                id = listuser.get(i).UserId;
                fullname = listuser.get(i).Fullname;
                avatar = listuser.get(i).Avatar;
                phone = listuser.get(i).Phone;
                return true;
            }
        }

        return false;
    }

    public void savePreferencesUser() {
        SharedPreferences pre1 = getActivity().getSharedPreferences(filename1, getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor1 = pre1.edit();
        String user = username.getText().toString();
        editor1.putInt("id", id);
        editor1.putString("fullname", fullname);
        editor1.putString("avata", avatar);
        editor1.putString("phone", phone);
        editor1.putString("user", user);
        editor1.putString("FbId", "");
        editor1.putBoolean("exist", true);
        editor1.commit();

    }
}
