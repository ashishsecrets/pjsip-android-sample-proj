package com.example.freeswitchandroid;

import static androidx.core.widget.TextViewKt.addTextChangedListener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.gotev.sipservice.CodecPriority;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipAccountTransport;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjmedia_srtp_use;

import java.util.ArrayList;
import java.util.List;

public class CallsActivity extends AppCompatActivity {

    String uri;
    String value;
    String value2;
    SipAccountData sipAccountData;
    Context context;

    String hostname = "david380.fs1.pressone.co";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);

        SipServiceCommand.enableSipDebugLogging(true);


    }

    public void call(View view){

        sipAccountData = new SipAccountData();
        EditText editText = (EditText) findViewById(R.id.number);
        value = editText.getText().toString();

        sipAccountData.setUsername(value);

        sipAccountData.setRealm(hostname);

        EditText editText2 = (EditText) findViewById(R.id.password);

        value2 = editText2.getText().toString();

        sipAccountData.setPassword(value2);
        sipAccountData.setHost(hostname);
        sipAccountData.setSrtpUse(pjmedia_srtp_use.PJMEDIA_SRTP_DISABLED);
        sipAccountData.setSrtpSecureSignalling(0);
        sipAccountData.setTransport(SipAccountTransport.TCP);

        context = this;

        uri = SipServiceCommand.setAccount(context, sipAccountData);
        SipServiceCommand.start(context);

        Toast.makeText(this, "Making a call !",
                Toast.LENGTH_LONG).show();

        String numberToCall = null;

        if(value.equals("5294241166")){
            numberToCall = "1911899877";
        }
        else if(value.equals("1911899877")){
            numberToCall = "5294241166";
        }


        SipServiceCommand.makeCall(context, uri, "sip:" + numberToCall + "@" + hostname, false, false, false);
        System.out.println(sipAccountData.getCallId() +  sipAccountData.getRealm() + sipAccountData.getHost() + sipAccountData.getUsername() + sipAccountData.getPassword());
    }

    public void answer(View view){

        sipAccountData = new SipAccountData();
        EditText editText = (EditText) findViewById(R.id.number);
        value = editText.getText().toString();

        sipAccountData.setUsername(value);

        sipAccountData.setRealm(hostname);


        EditText editText2 = (EditText) findViewById(R.id.password);

        value2 = editText2.getText().toString();

        sipAccountData.setPassword(value2);
        sipAccountData.setHost(hostname);
        sipAccountData.setSrtpUse(pjmedia_srtp_use.PJMEDIA_SRTP_DISABLED);
        sipAccountData.setSrtpSecureSignalling(0);
        sipAccountData.setTransport(SipAccountTransport.TCP);


        context = this;

        uri = SipServiceCommand.setAccount(context, sipAccountData);
        SipServiceCommand.start(context);

        Toast.makeText(this, "Receiving a call !",
                Toast.LENGTH_LONG).show();

        SipServiceCommand.acceptIncomingCall(context, uri, sipAccountData.getCallId(), false);

    }
}