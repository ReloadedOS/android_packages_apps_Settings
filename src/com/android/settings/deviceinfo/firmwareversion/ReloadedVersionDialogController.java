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

package com.android.settings.deviceinfo.firmwareversion;

import com.android.settings.R;
import com.android.settingslib.DeviceInfoUtils;
import android.content.Context;
import android.os.SystemProperties;

public class ReloadedVersionDialogController {
    private static final String RELOADED_PROPERTY = "ro.reloaded.version";
    private static final int RELOADED_VERSION_VALUE_ID = R.id.reloaded_version_value;
    private static final int RELOADED_VERSION_LABEL_ID = R.id.reloaded_version_label;

    private final FirmwareVersionDialogFragment mDialog;
    private final Context mContext;

    public ReloadedVersionDialogController(FirmwareVersionDialogFragment dialog) {
        mDialog = dialog;
        mContext = dialog.getContext();
    }

    /**
     * Updates Reloaded version to the dialog.
     */
    public void initialize() {
        mDialog.setText(RELOADED_VERSION_VALUE_ID, SystemProperties.get(RELOADED_PROPERTY,
                mContext.getResources().getString(R.string.reloaded_version_default)));
    }
}
