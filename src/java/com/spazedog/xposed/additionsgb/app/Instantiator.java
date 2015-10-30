package com.spazedog.xposed.additionsgb.app;


import android.app.Service;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.spazedog.xposed.additionsgb.R;
import com.spazedog.xposed.additionsgb.app.service.PreferenceService;

public class Instantiator<T> {

    public static final Instantiator<Fragment> Fragments = new Instantiator<Fragment>();
    public static final Instantiator<DialogFragment> Dialogs = new Instantiator<DialogFragment>();
    public static final Instantiator<Service> Services = new Instantiator<Service>();

    protected Class<?> get(int id) {
        switch (id) {
            // Services
            case R.id.service_preferences: return PreferenceService.class;

            // Nothing as default
            default: return null;
        }
    }

    protected Instantiator() {}

    public Class<? extends T> getClass(int id) {
        Class<?> clazz = get(id);

        if (clazz != null) {
            try {
                return (Class<? extends T>) clazz;

            } catch (ClassCastException e) {}
        }

        return null;
    }

    public T getInstance(int id) {
        Class<? extends T> clazz = getClass(id);

        if (clazz != null) {
            try {
                return clazz.getDeclaredConstructor().newInstance();

            } catch (Throwable e) {}
        }

        return null;
    }
}
