package com.chooloo.www.callmanager.service;

import android.content.Intent;
import android.telecom.Call;
import android.telecom.InCallService;

import com.chooloo.www.callmanager.activity.OngoingCallActivity;

public class CallService extends InCallService {

    public static Call sOngoingCall = null;

    @Override
    public void onCallAdded(Call call) {
        super.onCallAdded(call);
        startActivity(new Intent(this, OngoingCallActivity.class));
        sOngoingCall = call;
    }

    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);
        sOngoingCall = null;
    }

    Call.Callback callCallback = new Call.Callback(){
        @Override
        public void onStateChanged(Call call, int state) {
            super.onStateChanged(call, state);
            // Send updates to the activity
            CallManager.updateCall(call);
        }
    };
}
