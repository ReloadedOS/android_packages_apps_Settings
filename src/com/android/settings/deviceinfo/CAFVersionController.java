/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.android.settings.deviceinfo;

import android.content.Context;
import android.os.SystemProperties;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.text.TextUtils;

import com.android.settings.R;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

public class CAFVersionController extends AbstractPreferenceController implements
        PreferenceControllerMixin {
    private static final String CAF_PROPERTY = "ro.caf.version";
    private static final String CAF_VERSION_PERF = "caf_version";

    public CAFVersionController(Context context) {
        super(context);
    }

    @Override
    public String getPreferenceKey() {
        return CAF_PROPERTY;
    }

    @Override
    public boolean isAvailable() {
        return !TextUtils.isEmpty(SystemProperties.get(CAF_PROPERTY));
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        final Preference pref = screen.findPreference(CAF_VERSION_PERF);
        if (pref != null) {
            final String summary = SystemProperties.get(CAF_PROPERTY,
                    mContext.getResources().getString(R.string.caf_version_default));
            pref.setSummary(summary);
        }
    }
}
