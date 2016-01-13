/*
 * Copyright (c) 2016  Ohmer.
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

package com.kotlinthree.andex.component

import android.content.Context
import android.view.View
import android.widget.Toast
import com.kotlinthree.andex.widget.makeView

/**
 * Created by ohmer on 1/13/16.
 */

/**
 * toast with custom view
 * @param resId  id of string resource to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun Context.toastText(resId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, resId, duration).show()

/**
 * toast with custom view
 * @param text  text to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun Context.toastText(text: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, duration).show()

/**
 * toast with custom view
 * @param view  custom view to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun Context.toastView(view: View, duration: Int = Toast.LENGTH_LONG) = Toast(this).makeView(view,
        duration = duration).show()