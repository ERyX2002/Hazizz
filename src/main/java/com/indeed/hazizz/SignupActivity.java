package com.indeed.hazizz;

import com.indeed.hazizz.Communication.HashMapBuilder;
import com.indeed.hazizz.Communication.MiddleMan;
import com.indeed.hazizz.Communication.MyCallback;
//import com.indeed.hazizz.Communication.POJO.Requests.Register;
import com.indeed.hazizz.Communication.POJO.Requests.Register;
import com.indeed.hazizz.Communication.POJO.Requests.RequestInterface;
import com.indeed.hazizz.Communication.POJO.Response.CustomResponseHandler;
import com.indeed.hazizz.Communication.Requests.Request;
import com.indeed.hazizz.Communication.ResponseBodies;
import com.indeed.hazizz.Communication.ResponseHandler;

import com.indeed.hazizz.Communication.RequestBodies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends AppCompatActivity {

	private int antMan = 0;


    private String username;
    private String email;
    private String password;

    private EditText usernameET;
    private EditText emailET;
    private EditText passwordET;
    private Button button_signup;

    private TextView textView;

    private MyCallback<ResponseBodies.Error> errorCallback;
    // private MyCallback<ResponseTypes.AuthResponse> authCallback;

    private static final String BASE_URL = "http://80.98.42.103:8080/";

    private ResponseHandler responseHandler = new ResponseHandler() {

        @Override
        public void on401() {
            textView.append("created");
        }

        @Override
        public void on400() {
            textView.append("Registration was successful");
        }

        /* @Override
         public void onInvalidData(){

         } */
        @Override
        public void onUnknowError() {
            textView.append("Unknown Error");
        }

   /*     @Override
        public void onInvalidData() {

        } */

        @Override
        public void onDatabaseError() {

        }

        @Override
        public void onPathVariableMissing() {

        }

        @Override
        public void onForbidden() {

        }

        @Override
        public void onGeneralAuthenticationError() {
            textView.append("onGeneralAuthenticationError");
        }

        @Override
        public void onInvalidPassword() {

        }

        @Override
        public void onAccountLocked() {

        }

        @Override
        public void onAccountDisabled() {

        }

        @Override
        public void onAccountExpired() {

        }

        @Override
        public void onUnkownPermission() {

        }

        @Override
        public void onBadAuthenticationRequest() {

        }

        @Override
        public void onGeneralUserError() {

        }

        @Override
        public void onUserNotFound() {

        }

        @Override
        public void onUsernameConflict() {

        }

        @Override
        public void onEmailConflict() {

        }

        @Override
        public void onGeneralGroupError() {

        }

        @Override
        public void onGroupNotFound() {

        }

        @Override
        public void onGroupNameConflict() {

        }
    };

    // urls
    //  private final String regUrl= "http://80.98.42.103:8080/register/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameET = findViewById(R.id.editText_username);
        emailET = findViewById(R.id.editText_email);
        passwordET = findViewById(R.id.editText_password);

        textView = findViewById(R.id.textView);

        button_signup = (Button) findViewById(R.id.button_signup);


        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // signUp(); //parameter: JSONObject,
                username = usernameET.getText().toString();
                email = emailET.getText().toString();
                password = passwordET.getText().toString();
              //  RequestBodies.Register user = new RequestBodies.Register(username, password, email);
                textView.append("username: " + username + "\n email: " + email + "\n password: " + password);
                // textView.append(REQUESTS.ERRORS.TIME.value());
                HashMap<String, Object> requestBody = new HashMap<>();

                requestBody.put("username", username);
                requestBody.put("password", password);
                requestBody.put("emailAddress", email);

                CustomResponseHandler responseHandler = new CustomResponseHandler(){

                    @Override
                    public void onResponse(HashMap<String, Object> response) {
                        textView.append("\n errorCode: " + response.get("errorCode"));
                        Log.e("hey", "got here");
                    }
                    @Override
                    public void onFailure() {
                        textView.append("\n your signup was successful");
                    }
                    @Override
                    public void onErrorResponse(HashMap<String, Object> errorResponse) {
                        textView.append("\n errorCode: " + errorResponse.get("errorCode"));
                        Log.e("hey", "got here");
                    }
                };

                MiddleMan sendRegisterRequest = new MiddleMan<Register>("register", requestBody, responseHandler);
                sendRegisterRequest.sendRequest();
              //  HashMapBuilder responseHmb = new HashMapBuilder(sendRegisterRequest.getResponse());
               // textView.append("\n errorCode: " + (int)responseHmb.getValue("errorCode"));
                //(Call<T>) aRequest.register(headerMap, request);
            //    String msg = (Error)sendRegisterRequest.getResponse().getMessage();
            //    textView.append((Error)sendRegisterRequest.getResponse().getMessage());
             //   textView.append(sendRegisterRequest.getResponse1());

            }
        });
    }
}

/*
            //    ResponseTypes.AuthResponse
                 HashMap<String, String> headerMap = new HashMap<String, String>();
                 headerMap.put("Content-Type", "application/json");

                Call<ResponseBodies.Error> call = userClient.register(headerMap, user);

               // errorCallback = new MyCallback<ResponseBodies.Error>(responseHandler);

               // call.enqueue(errorCallback);

                call.enqueue(new Callback<ResponseBodies.Error>() {
                    @Override
                    public void onResponse(Call<ResponseBodies.Error> call, Response<ResponseBodies.Error> response) {

                    //    response.body().getTitle();
                        responseHandler.checkErrorCode(response.body().getErrorCode());
                        responseHandler.checkHttpStatus(response.code());

                    }
                    @Override
                    public void onFailure(Call<ResponseBodies.Error> call, Throwable t) {
                       // responseHandler.checkHttpStatus(call.code());
                        Log.e("hey", "Registration was successful");
                    }
                });



              // HashMap<String, String> headerMap = new HashMap<String, String>();
              // headerMap.put("Content-Type", "application/json");

               // sendNetworkRequest(user);


                /*

                JSONObject body = new JSONObject();
                try {
                    body.put("username", username);
                    body.put("password", password);
                    body.put("emailAddress", email);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("hey", "99");
                }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://80.98.42.103:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetroFit retroFit = retrofit.create(RetroFit.class);



                HashMap<String, String> headerMap = new HashMap<String, String>();
                headerMap.put("Content-Type", "application/json");

                Call<ResponseBody> call = retroFit.register(headerMap, body);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("Server Response:", "" + response.toString());

                        try{
                            String json = response.body().toString();
                            Log.e("onResponse:", "json: " + json);
                            JSONObject data = null;
                            data = new JSONObject(json);
                        }catch(JSONException e){
                            Log.e("onResponse:", "JSONException" + e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("hey", "oh boy");
                    }
                });


              //  Rest.sendRequestPostTEST(getBaseContext(), body);
            //    Rest.sendRequestGet("https://api.myjson.com/bins/1aflq4", getBaseContext());

                // String url, ArrayList<String> k, RequestQueue mQueue
              //  Rest.sendRequestPost("http://80.98.42.103:8080/register/", getBaseContext(), body);
              //  Rest.sendRequestPost(REQUESTS.REGISTER.URL.value(), getBaseContext(), body, bodym);
            }
        }); */
