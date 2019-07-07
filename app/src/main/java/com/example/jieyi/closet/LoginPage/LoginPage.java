package com.example.jieyi.closet.LoginPage;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jieyi.closet.MainActivity;
import com.example.jieyi.closet.R;
import com.example.jieyi.closet.Util.HttpUtil;


public class LoginPage extends AppCompatActivity {

    private EditText acconnt_Edit;
    private EditText password_Edit;
    private HttpUtil httpUtill;
    private TextView textView;

    private final int LOGIN_SUCESS = 0;
    public Handler handler = new Handler(){
        public  void handleMessage(Message message){
            switch (message.what){
                case LOGIN_SUCESS:
                    Intent intent = new Intent(LoginPage.this,MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //控件定义
        acconnt_Edit = findViewById(R.id.login_account);
        password_Edit = findViewById(R.id.login_password);
        textView = findViewById(R.id.responseTest);
        Button login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = acconnt_Edit.getText().toString();
                String password = password_Edit.getText().toString();
                String HttpUrl = "http://192.168.1.104:8080/ClosetService/servlet/LoginDataServlet";
                //get后缀 账号密码
                String message = String.format("?%s=%s&%s=%s","account",account,"password",password);
                httpUtill = new HttpUtil();
                HttpUtil.CallBack callBack = new HttpUtil.CallBack() {
                    @Override
                    public void onRequestComplete(String result) {
                        if(result.equals("login_sucess")){
                            Message resMessage = new Message();
                            resMessage.what = LOGIN_SUCESS;
                            handler.sendMessage(resMessage);
                        }

                    }
                };
                //get 请求http,
                HttpUtil.doGetAsyn(HttpUrl + message,callBack);//HttpUrl + message,callBack
                //post
            }
        });
    }


}
