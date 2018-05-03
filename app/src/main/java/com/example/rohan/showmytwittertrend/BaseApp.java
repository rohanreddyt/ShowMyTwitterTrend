package com.example.rohan.showmytwittertrend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.rohan.showmytwittertrend.deps.DaggerDeps;
import com.example.rohan.showmytwittertrend.deps.Deps;
import com.example.rohan.showmytwittertrend.networking.NetworkModule;

import java.io.File;

/**
 * Created by ennur on 6/28/16.
 */
public class BaseApp extends AppCompatActivity {
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        File cacheFile = new File(getCacheDir(), "responses");
//        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
        deps = DaggerDeps.builder().networkModule(new NetworkModule()).build();

    }

    public Deps getDeps() {
        return deps;
    }
}
