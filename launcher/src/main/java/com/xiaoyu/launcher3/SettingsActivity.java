/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xiaoyu.launcher3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.lmy.launcher3.R;

/**
 * Settings activity for Launcher. Currently implements the following setting: Allow rotation
 */
public class SettingsActivity extends Activity {

    private static final String ROTATION = "Rotation";
    private static final String ALL_APP = "AllApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        // Display the fragment as the main content.
//        getFragmentManager().beginTransaction()
//                .replace(android.R.id.content, new LauncherSettingsFragment())
//                .commit();
        LauncherAppState.setApplicationContext(this);
        initView();
    }

    private void initView() {
        Switch allow = (Switch) findViewById(R.id.setting_allow_rotation);
        Switch allApp = (Switch) findViewById(R.id.settting_have_allapp);
        allApp.setChecked(LauncherAppState.isDisableAllApps());
        allow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Bundle extras = new Bundle();
                extras.putBoolean(LauncherSettings.Settings.EXTRA_VALUE, b);
                getContentResolver().call(
                        LauncherSettings.Settings.CONTENT_URI,
                        LauncherSettings.Settings.METHOD_SET_BOOLEAN,
                        "", extras);
            }
        });
        allApp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                getApplicationContext().getSharedPreferences(Utilities.SP_NAME, 0).
                        edit().putBoolean(Utilities.ALL_APP_KEY, b).commit();
            }
        });
    }


    /**
     * This fragment shows the launcher preferences.
     */
//    public static class LauncherSettingsFragment extends PreferenceFragment
//            implements OnPreferenceChangeListener {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.launcher_preferences);
//
//            LauncherAppState.setApplicationContext(getActivity());
//            //旋转
//            SwitchPreference allowRotation = (SwitchPreference) findPreference(
//                    Utilities.ALLOW_ROTATION_PREFERENCE_KEY);
//            allowRotation.setPersistent(false);
//
//            Bundle extras = new Bundle();
//            extras.putBoolean(LauncherSettings.Settings.EXTRA_DEFAULT_VALUE, false);
//            Bundle value = getActivity().getContentResolver().call(
//                    LauncherSettings.Settings.CONTENT_URI,
//                    LauncherSettings.Settings.METHOD_GET_BOOLEAN,
//                    Utilities.ALLOW_ROTATION_PREFERENCE_KEY, extras);
//            allowRotation.setChecked(value.getBoolean(LauncherSettings.Settings.EXTRA_VALUE));
//
//            allowRotation.setOnPreferenceChangeListener(this);
//            allowRotation.setKey(ROTATION);
//
//            //抽屉
//            final SwitchPreference allApp = (SwitchPreference) findPreference(
//                    Utilities.IS_HAVE_ALLAPP);
//            allApp.setPersistent(LauncherAppState.isDisableAllApps());
//            Log.d("lmy--",LauncherAppState.isDisableAllApps()+"");
//            allApp.setOnPreferenceChangeListener(this);
//            allApp.setKey(ALL_APP);
//        }
//
//        @Override
//        public boolean onPreferenceChange(Preference preference, Object newValue) {
//            String key = preference.getKey();
//            Log.d("lmy--","set_activity key="+key);
//            if (TextUtils.isEmpty(key)) {
//                return false;
//            }
//            if (key.equals(ROTATION)) {
//                Bundle extras = new Bundle();
//                extras.putBoolean(LauncherSettings.Settings.EXTRA_VALUE, (Boolean) newValue);
//                getActivity().getContentResolver().call(
//                        LauncherSettings.Settings.CONTENT_URI,
//                        LauncherSettings.Settings.METHOD_SET_BOOLEAN,
//                        preference.getKey(), extras);
//            } else if (key.equals(ALL_APP)) {
//                getActivity().getApplicationContext().getSharedPreferences(Utilities.SP_NAME, 0).
//                        edit().putBoolean(Utilities.ALL_APP_KEY, (boolean) newValue);
//            }
//            return true;
//        }
//    }
}
