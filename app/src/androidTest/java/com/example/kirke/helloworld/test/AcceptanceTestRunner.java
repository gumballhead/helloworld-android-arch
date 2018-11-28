package com.example.kirke.helloworld.test;

import android.os.Bundle;
import androidx.test.runner.AndroidJUnitRunner;
import cucumber.api.android.CucumberInstrumentationCore;

public class AcceptanceTestRunner extends AndroidJUnitRunner {
    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);
    @Override
    public void onCreate(final Bundle bundle) {
        instrumentationCore.create(bundle);
        super.onCreate(bundle);
    }
    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }
}
