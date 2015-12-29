/*
 * Copyright (c) 2015  Ohmer.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kotlinthree.andex.widget

import android.view.Gravity
import android.view.View
import android.widget.Toast

/**
 * Created by ohmer on 12/28/15.
 */

/**
 * make custome toast with custom view
 *
 * @param customeView  custom view to show
 * @param gravity Set the location at which the notification should appear on the screen.
 * @see android.view.Gravity
 * @param xOff the X offset in pixels to apply to the gravity's location
 * @param yOff the Y offset in pixels to apply to the gravity's location
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun Toast.makeView(customeView: View, gravity: Int = Gravity.CENTER, xOff: Int = 0,
                   yOff: Int = 0, duration: Int = Toast.LENGTH_LONG): Toast {
    this.view = customeView
    setGravity(gravity, xOff, yOff)
    setDuration(duration)
    return this
}