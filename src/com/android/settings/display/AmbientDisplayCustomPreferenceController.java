/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.android.settings.display;

import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.provider.Settings;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;

import com.android.settings.R;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

import java.util.List;

import static android.provider.Settings.Secure.DOZE_ENABLED;
import static com.android.internal.logging.nano.MetricsProto.MetricsEvent.ACTION_AMBIENT_DISPLAY;

public class AmbientDisplayCustomPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {

    static final String KEY_AMBIENT_CUSTOM = "ambient_display_custom";

    private final MetricsFeatureProvider mMetricsFeatureProvider;
    
    private Context mContext;

    public AmbientDisplayCustomPreferenceController(Context context) {
        super(context);
        mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
        mContext = context;
    }

    @Override
    public boolean isAvailable() {
        return !mContext.getResources().getString(R.string.config_customDozePackage).equals("");
    }

    @Override
    public String getPreferenceKey() {
        return KEY_AMBIENT_CUSTOM;
    }
    
    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        if(isAvailable()){
            Preference toRemove = screen.findPreference("ambient_display");
            screen.removePreference(toRemove);
        }
    }

    @Override
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (KEY_AMBIENT_CUSTOM.equals(preference.getKey())) {
            mMetricsFeatureProvider.action(mContext, ACTION_AMBIENT_DISPLAY);
            try {
                String[] customDozePackage = mContext.getResources().getString(R.string.config_customDozePackage).split("/");
                String activityName = customDozePackage[0];
                String className = customDozePackage[1];
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(activityName, className));
                mContext.startActivity(intent);
            } catch (Exception e){
            }
        }
        return false;
    }

    @Override
    public void updateNonIndexableKeys(List<String> keys) {
        keys.add(getPreferenceKey());
    }

    @Override
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (Settings.Secure.getInt(mContext.getContentResolver(), DOZE_ENABLED, 1) != 0) {
            preference.setSummary(R.string.switch_on_text);
        } else {
            preference.setSummary(R.string.switch_off_text);
        }
    }

}
