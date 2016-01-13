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
import android.graphics.drawable.Drawable
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
fun Fragment.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) = getContext().toast(resId, duration)

/**
 * toast with custom view
 * @param text  text to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) = getContext().toast(text, duration)

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
 * Return a drawable object associated with a particular resource ID and
 * styled for the specified theme. Various types of objects will be
 * returned depending on the underlying resource -- for example, a solid
 * color, PNG image, scalable image, etc.
 *
 * @param resId The desired resource identifier, as generated by the aapt
 *           tool. This integer encodes the package, type, and resource
 *           entry. The value 0 is an invalid identifier.
 * @return Drawable An object that can be used to draw this resource.
 * @throws NotFoundException Throws NotFoundException if the given ID does
 *         not exist.
 */
fun Fragment.getDrawable(resId: Int): Drawable = getResources().getDrawable(resId, null)


/**
 * Returns a themed color integer associated with a particular resource ID.
 * If the resource holds a complex {@link ColorStateList}, then the default
 * color from the set is returned.
 *
 * @param resId The desired resource identifier, as generated by the aapt
 *           tool. This integer encodes the package, type, and resource
 *           entry. The value 0 is an invalid identifier.
 *
 * @throws NotFoundException Throws NotFoundException if the given ID does
 *         not exist.
 *
 * @return A single color value in the form 0xAARRGGBB.
 */
fun Fragment.getColor(resId: Int): Int = getResources().getColor(resId, null)

/**
 * toast with custom view in support v4 fragment
 * @param resId  id of string resource to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun android.support.v4.app.Fragment.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) = getContext()
        .toast(resId, duration)

/**
 * toast with custom view in support v4 fragment
 * @param text  text to show
 * @param duration Set how long to show the view for.
 * @see android.widget.Toast.LENGTH_SHORT
 * @see android.widget.Toast.LENGTH_LONG
 */
fun android.support.v4.app.Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) = getContext()
        .toast(text, duration)

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

/**
 * Return a drawable object associated with a particular resource ID and
 * styled for the specified theme. Various types of objects will be
 * returned depending on the underlying resource -- for example, a solid
 * color, PNG image, scalable image, etc.
 *
 * @param resId The desired resource identifier, as generated by the aapt
 *           tool. This integer encodes the package, type, and resource
 *           entry. The value 0 is an invalid identifier.
 * @return Drawable An object that can be used to draw this resource.
 * @throws NotFoundException Throws NotFoundException if the given ID does
 *         not exist.
 */
fun android.support.v4.app.Fragment.getDrawable(resId: Int): Drawable = getResources().getDrawable(resId, null)


/**
 * Returns a themed color integer associated with a particular resource ID.
 * If the resource holds a complex {@link ColorStateList}, then the default
 * color from the set is returned.
 *
 * @param resId The desired resource identifier, as generated by the aapt
 *           tool. This integer encodes the package, type, and resource
 *           entry. The value 0 is an invalid identifier.
 *
 * @throws NotFoundException Throws NotFoundException if the given ID does
 *         not exist.
 *
 * @return A single color value in the form 0xAARRGGBB.
 */
fun android.support.v4.app.Fragment.getColor(resId: Int): Int = getResources().getColor(resId, null)