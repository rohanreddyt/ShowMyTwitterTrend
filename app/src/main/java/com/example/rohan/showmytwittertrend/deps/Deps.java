package com.example.rohan.showmytwittertrend.deps;




import com.example.rohan.showmytwittertrend.HomeActivity;
import com.example.rohan.showmytwittertrend.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
}
