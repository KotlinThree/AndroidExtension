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

package com.kotlinthree.andex.view

import android.support.annotation.IdRes
import android.util.SparseArray
import android.view.View

/**
 * Created by ohmer on 1/13/16.
 */

/**
 * find child view by id
 * @param id
 * @return The view that has the given id in the hierarchy or null
 */
inline fun <reified T : View> View.findView(@IdRes id: Int): T = findViewById(id) as T

/**
 * use to find child view by id frequently, such as in adapter
 * @param id
 * @return The view that has the given id in the hierarchy or null
 */
inline fun <reified T : View> View.findViewOften(@IdRes viewId: Int): T {
    val viewHolder: SparseArray<View> = tag as? SparseArray<View> ?: SparseArray()
    tag = viewHolder
    var childView: View? = viewHolder.get(viewId)
    if (null == childView) {
        childView = findViewById(viewId)
        viewHolder.put(viewId, childView)
    }
    return childView as T
}
