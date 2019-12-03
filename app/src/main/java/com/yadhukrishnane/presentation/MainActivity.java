package com.yadhukrishnane.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yadhukrishnane.presentation.models.ChatbotResponse;
import com.yadhukrishnane.presentation.network.NetworkManager;
import com.yadhukrishnane.presentation.network.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkManager.OnNetworkResponseListener, View.OnClickListener {

    private RecyclerView rvMessages;
    private AppCompatEditText edtMessage;
    private AppCompatImageView ivSend;

    private List<Message> messages;

    private ChatAdapter mAdapter;

    private ConstraintSet showSendSet = new ConstraintSet();
    private ConstraintSet dontShowSendSet = new ConstraintSet();

    private ConstraintLayout mRootLayout;

    private boolean isShown = false;

    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_init);

        rvMessages = findViewById(R.id.rv_message);
        edtMessage = findViewById(R.id.edt_presentation_msg);
        ivSend = findViewById(R.id.iv_presentation_send);

        mRootLayout = findViewById(R.id.main_root);
        showSendSet.clone(this, R.layout.activity_main);
        dontShowSendSet.clone(this, R.layout.activity_main_init);

        edtMessage.addTextChangedListener(watcher);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvMessages.setLayoutManager(manager);

        messages = new ArrayList<>();
        mAdapter = new ChatAdapter(messages);
        rvMessages.setAdapter(mAdapter);

        ivSend.setOnClickListener(this);

        networkManager = NetworkManager.getInstance();
        networkManager.setNetworkListener(this);
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0) {
                if (!isShown) {
                    isShown = true;
                    TransitionManager.beginDelayedTransition(mRootLayout);
                    showSendSet.applyTo(mRootLayout);
                }
            } else {
                isShown = false;
                TransitionManager.beginDelayedTransition(mRootLayout);
                dontShowSendSet.applyTo(mRootLayout);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void successResponse(final Response response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ChatbotResponse chatbotResponse = (ChatbotResponse) response.body;

                Message message = new Message();
                message.setSide(Message.LEFT_SIDE);
                message.setMessage(chatbotResponse.getResult().getFulfillment().getSpeech());
                messages.add(message);
                mAdapter.notifyItemInserted(messages.size() - 1);
                rvMessages.scrollToPosition(messages.size() - 1);
            }
        });

    }

    @Override
    public void failureResponse(Response response) {
        Log.d("MainActivity", "failureResponse -------" + response.body);
    }

    @Override
    public void noInternet() {

    }

    @Override
    public void onClick(View view) {
        String text = edtMessage.getText().toString();
        if (text.length() == 0) return;

        Message message = new Message();
        message.setSide(Message.RIGHT_SIDE);
        message.setMessage(text);
        messages.add(message);
        mAdapter.notifyItemInserted(messages.size() - 1);
        rvMessages.scrollToPosition(messages.size() - 1);
        clearMessageField();

        networkManager.callAPIForMessage(text);
    }

    private void clearMessageField() {
        edtMessage.setText("");
    }
}
