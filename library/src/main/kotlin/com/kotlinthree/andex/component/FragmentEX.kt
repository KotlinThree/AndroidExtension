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

import android.app.Fragment
import android.view.View
import android.widget.Toast

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
fun Fragment.toastText(resId: Int, duration: Int = Toast.LENGTH_SHORT) = getContext().toastText(resId, duration)

/**
 * toast with custom view
 * @param text  text to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun Fragment.toastText(text: String, duration: Int = Toast.LENGTH_SHORT) = getContext().toastText(text, duration)

/**
 * toast with custom view
 * @param view  custom view to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun Fragment.toastView(view: View, duration: Int = Toast.LENGTH_LONG) = getContext().toastView(view,duration)

/**
 * find child view by id, you should call this method after {@link #onCreateView}
 * @param id
 * @return The view that has the given id in the hierarchy or null
 */
fun <T : View> Fragment.findView(id: Int): T = getView().findViewById(id) as T

/**
 * toast with custom view in support v4 fragment
 * @param resId  id of string resource to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun android.support.v4.app.Fragment.toastText(resId: Int, duration: Int = Toast.LENGTH_SHORT) = getContext()
        .toastText(resId, duration)

/**
 * toast with custom view in support v4 fragment
 * @param text  text to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun android.support.v4.app.Fragment.toastText(text: String, duration: Int = Toast.LENGTH_SHORT) = getContext()
        .toastText(text, duration)

/**
 * toast with custom view in support v4 fragment
 * @param view  custom view to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun android.support.v4.app.Fragment.toastView(view: View, duration: Int = Toast.LENGTH_LONG) = getContext()
        .toastView(view,duration)

/**
 * find child view by id in support v4 fragment, you should call this method after {@link #onCreateView}
 * @param id
 * @return The view that has the given id in the hierarchy or null
 */
fun <T : View> android.support.v4.app.Fragment.findView(id: Int): T = getView().findViewById(id) as T