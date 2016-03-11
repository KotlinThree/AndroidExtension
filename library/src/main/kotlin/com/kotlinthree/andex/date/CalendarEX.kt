package com.hujiang.normandy.extension

import java.util.*

/**
 * Created by ohmer on 1/7/16.
 */


/**
 * 判断是否是今天
 */
fun Calendar.isToday() = this.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE)

fun Calendar.second() = get(Calendar.SECOND)

fun Calendar.hourOfDay() = get(Calendar.HOUR_OF_DAY) // 24小时制的时间

fun Calendar.hour() = get(Calendar.HOUR)

fun Calendar.minute() = get(Calendar.MINUTE)

