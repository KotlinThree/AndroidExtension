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

package com.kotlinthree.andex.thread

/**
 * Created by ohmer on 1/23/16.
 */

fun <T> T.post(runnable: () -> Unit){
    WeakHandler().post(runnable)
}

fun <T> T.postDelay(delayMillis: Long, runnable: () -> Unit){
    WeakHandler().postDelayed(delayMillis,runnable)
}