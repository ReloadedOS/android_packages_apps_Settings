/*
 * Copyright (C) 2018 The Android Open Source Project
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
 * limitations under the License
 */

package com.android.settings.reloaded;

import android.content.Context;
import android.os.SystemProperties;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;

import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;


public class PerfModes extends BasePreferenceController
        implements Preference.OnPreferenceChangeListener {

    private ListPreference mPerfModes;

    public PerfModes(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return mContext.getResources().getBoolean(
                com.android.internal.R.bool.config_hasPerformanceProfiles) ? AVAILABLE : CONDITIONALLY_UNAVAILABLE ;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        mPerfModes = (ListPreference) screen.findPreference(getPreferenceKey());
        int value = SystemProperties.getInt("persist.reloaded.perfmode", 1);
        mPerfModes.setValue(Integer.toString(value));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        SystemProperties.set("persist.reloaded.perfmode", (String) newValue);
        refreshSummary(preference);
        return true;
    }

    @Override
    public CharSequence getSummary() {
        int value = SystemProperties.getInt("persist.reloaded.perfmode", 1);
        int index = mPerfModes.findIndexOfValue(Integer.toString(value));
        return mPerfModes.getEntries()[index];
    }
}
