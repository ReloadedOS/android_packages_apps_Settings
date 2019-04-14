/*
 * Copyright (C) 2019 The Reloaded Project
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
package com.android.settings.reloaded;

import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.support.v7.preference.Preference;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class SystemUpdatePreferenceController extends BasePreferenceController {

    private static final String KEY_SYSTEM_UPDATE_SETTINGS = "reloaded_update";

    public SystemUpdatePreferenceController(Context context) {
        super(context, KEY_SYSTEM_UPDATE_SETTINGS);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (KEY_SYSTEM_UPDATE_SETTINGS.equals(preference.getKey())) {
            try {
                String activityName = "org.reloaded.updater";
                String className = "org.reloaded.updater.MainActivity";
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(activityName, className));
                mContext.startActivity(intent);
            } catch (Exception e){
            }
        }
        return false;
    }

}
