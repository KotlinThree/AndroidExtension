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
package com.kotlinthree.andex.handler

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import java.lang.ref.WeakReference
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * This code is deeply refer to {@link https://github.com/badoo/android-weak-handler}
 * Memory safer implementation of android.os.Handler
 *
 * Original implementation of Handlers always keeps hard reference to handler in queue of execution.
 * If you create anonymous handler and post delayed message into it, it will keep all parent class
 * for that time in memory even if it could be cleaned.
 *
 *
 * This implementation is trickier, it will keep WeakReferences to runnables and messages,
 * and GC could collect them once WeakHandler instance is not referenced any more
 *
 */

private class WeakHandler(val callback: Handler.Callback? = null, looper: Looper = Looper.myLooper()) {
    private lateinit var mExec: ExecHandler
    private val mLock = ReentrantLock()
    private val mRunnables = ChainedRef(mLock, Runnable { })

    /**
     * Default constructor associates this handler with the [Looper] for the
     * current thread.

     * If this thread does not have a looper, this handler won't be able to receive messages
     * so an exception is thrown.
     */
    init {
        if (callback == null) {
            mExec = ExecHandler(looper = looper)
        } else {
            mExec = ExecHandler(WeakReference(callback), looper = looper)
        }

    }

    /**
     * Causes the Runnable r to be added to the message queue.
     * The runnable will be run on the thread to which this handler is
     * attached.

     * @param r The Runnable that will be executed.
     * *
     * *
     * @return Returns true if the Runnable was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.
     */
    fun post(runnable: () -> Unit): Boolean {
        val r = Runnable { runnable() }
        return mExec.post(wrapRunnable(r))
    }

    /**
     * Causes the Runnable r to be added to the message queue, to be run
     * at a specific time given by uptimeMillis.
     * **The time-base is [android.os.SystemClock.uptimeMillis].**
     * The runnable will be run on the thread to which this handler is attached.

     * @param runnable The function that will be executed.
     * *
     * @param uptimeMillis The absolute time at which the callback should run,
     * *         using the [android.os.SystemClock.uptimeMillis] time-base.
     * *
     * *
     * @return Returns true if the Runnable was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.  Note that a
     * *         result of true does not mean the Runnable will be processed -- if
     * *         the looper is quit before the delivery time of the message
     * *         occurs then the message will be dropped.
     */
    fun postAtTime(uptimeMillis: Long, runnable: () -> Unit): Boolean {
        val r = Runnable { runnable() }
        return mExec.postAtTime(wrapRunnable(r), uptimeMillis)
    }

    /**
     * Causes the Runnable r to be added to the message queue, to be run
     * at a specific time given by uptimeMillis.
     * **The time-base is [android.os.SystemClock.uptimeMillis].**
     * The runnable will be run on the thread to which this handler is attached.

     * @param r The Runnable that will be executed.
     * *
     * @param uptimeMillis The absolute time at which the callback should run,
     * *         using the [android.os.SystemClock.uptimeMillis] time-base.
     * *
     * *
     * @return Returns true if the Runnable was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.  Note that a
     * *         result of true does not mean the Runnable will be processed -- if
     * *         the looper is quit before the delivery time of the message
     * *         occurs then the message will be dropped.
     * *
     * *
     * @see android.os.SystemClock.uptimeMillis
     */
    fun postAtTime(r: Runnable, token: Any, uptimeMillis: Long): Boolean {
        return mExec.postAtTime(wrapRunnable(r), token, uptimeMillis)
    }

    /**
     * Causes the Runnable r to be added to the message queue, to be run
     * after the specified amount of time elapses.
     * The runnable will be run on the thread to which this handler
     * is attached.

     * @param r The Runnable that will be executed.
     * *
     * @param delayMillis The delay (in milliseconds) until the Runnable
     * *        will be executed.
     * *
     * *
     * @return Returns true if the Runnable was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.  Note that a
     * *         result of true does not mean the Runnable will be processed --
     * *         if the looper is quit before the delivery time of the message
     * *         occurs then the message will be dropped.
     */
    fun postDelayed(delayMillis: Long, runnable: () -> Unit): Boolean {
        val r = Runnable { runnable() }
        return mExec.postDelayed(wrapRunnable(r), delayMillis)
    }

    fun postDelayed(delayMillis: Long, runnable: Runnable): Boolean {
        return mExec.postDelayed(wrapRunnable(runnable), delayMillis)
    }

    /**
     * Posts a message to an object that implements Runnable.
     * Causes the Runnable r to executed on the next iteration through the
     * message queue. The runnable will be run on the thread to which this
     * handler is attached.
     * **This method is only for use in very special circumstances -- it
     * can easily starve the message queue, cause ordering problems, or have
     * other unexpected side-effects.**

     * @param r The Runnable that will be executed.
     * *
     * *
     * @return Returns true if the message was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.
     */
    fun postAtFrontOfQueue(r: () -> Unit): Boolean {
        val r = Runnable { r() }
        return mExec.postAtFrontOfQueue(wrapRunnable(r))
    }

    /**
     * Remove any pending posts of Runnable r that are in the message queue.
     */
    fun removeCallbacks(r: Runnable) {
        val runnable = mRunnables.remove(r)
        if (runnable != null) {
            mExec.removeCallbacks(runnable)
        }
    }

    /**
     * Remove any pending posts of Runnable r with Object
     * token that are in the message queue.  If token is null,
     * all callbacks will be removed.
     */
    fun removeCallbacks(r: Runnable, token: Any) {
        val runnable = mRunnables.remove(r)
        if (runnable != null) {
            mExec.removeCallbacks(runnable, token)
        }
    }

    /**
     * Pushes a message onto the end of the message queue after all pending messages
     * before the current time. It will be received in callback,
     * in the thread attached to this handler.

     * @return Returns true if the message was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.
     */
    fun sendMessage(msg: Message): Boolean {
        return mExec.sendMessage(msg)
    }

    /**
     * Sends a Message containing only the what value.

     * @return Returns true if the message was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.
     */
    fun sendEmptyMessage(what: Int): Boolean {
        return mExec.sendEmptyMessage(what)
    }

    /**
     * Sends a Message containing only the what value, to be delivered
     * after the specified amount of time elapses.
     * @see .sendMessageDelayed
     * @return Returns true if the message was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.
     */
    fun sendEmptyMessageDelayed(what: Int, delayMillis: Long): Boolean {
        return mExec.sendEmptyMessageDelayed(what, delayMillis)
    }

    /**
     * Sends a Message containing only the what value, to be delivered
     * at a specific time.
     * @see .sendMessageAtTime
     * @return Returns true if the message was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.
     */
    fun sendEmptyMessageAtTime(what: Int, uptimeMillis: Long): Boolean {
        return mExec.sendEmptyMessageAtTime(what, uptimeMillis)
    }

    /**
     * Enqueue a message into the message queue after all pending messages
     * before (current time + delayMillis). You will receive it in
     * callback, in the thread attached to this handler.

     * @return Returns true if the message was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.  Note that a
     * *         result of true does not mean the message will be processed -- if
     * *         the looper is quit before the delivery time of the message
     * *         occurs then the message will be dropped.
     */
    fun sendMessageDelayed(msg: Message, delayMillis: Long): Boolean {
        return mExec.sendMessageDelayed(msg, delayMillis)
    }

    /**
     * Enqueue a message into the message queue after all pending messages
     * before the absolute time (in milliseconds) uptimeMillis.
     * **The time-base is [android.os.SystemClock.uptimeMillis].**
     * You will receive it in callback, in the thread attached
     * to this handler.

     * @param uptimeMillis The absolute time at which the message should be
     * *         delivered, using the
     * *         [android.os.SystemClock.uptimeMillis] time-base.
     * *
     * *
     * @return Returns true if the message was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.  Note that a
     * *         result of true does not mean the message will be processed -- if
     * *         the looper is quit before the delivery time of the message
     * *         occurs then the message will be dropped.
     */
    fun sendMessageAtTime(msg: Message, uptimeMillis: Long): Boolean {
        return mExec.sendMessageAtTime(msg, uptimeMillis)
    }

    /**
     * Enqueue a message at the front of the message queue, to be processed on
     * the next iteration of the message loop.  You will receive it in
     * callback, in the thread attached to this handler.
     * **This method is only for use in very special circumstances -- it
     * can easily starve the message queue, cause ordering problems, or have
     * other unexpected side-effects.**

     * @return Returns true if the message was successfully placed in to the
     * *         message queue.  Returns false on failure, usually because the
     * *         looper processing the message queue is exiting.
     */
    fun sendMessageAtFrontOfQueue(msg: Message): Boolean {
        return mExec.sendMessageAtFrontOfQueue(msg)
    }

    /**
     * Remove any pending posts of messages with code 'what' that are in the
     * message queue.
     */
    fun removeMessages(what: Int) {
        mExec.removeMessages(what)
    }

    /**
     * Remove any pending posts of messages with code 'what' and whose obj is
     * 'object' that are in the message queue.  If object is null,
     * all messages will be removed.
     */
    fun removeMessages(what: Int, `object`: Any) {
        mExec.removeMessages(what, `object`)
    }

    /**
     * Remove any pending posts of callbacks and sent messages whose
     * obj is token.  If token is null,
     * all callbacks and messages will be removed.
     */
    fun removeCallbacksAndMessages(token: Any) {
        mExec.removeCallbacksAndMessages(token)
    }

    /**
     * Check if there are any pending posts of messages with code 'what' in
     * the message queue.
     */
    fun hasMessages(what: Int): Boolean {
        return mExec.hasMessages(what)
    }

    /**
     * Check if there are any pending posts of messages with code 'what' and
     * whose obj is 'object' in the message queue.
     */
    fun hasMessages(what: Int, `object`: Any): Boolean {
        return mExec.hasMessages(what, `object`)
    }

    private fun wrapRunnable(r: Runnable): WeakRunnable {
        //noinspection ConstantConditions
        if (r == null) {
            throw NullPointerException("Runnable can't be null")
        }
        val hardRef = ChainedRef(mLock, r)
        mRunnables.insertAfter(hardRef)
        return hardRef.wrapper
    }
}

private class ExecHandler(val weakCallback: WeakReference<Handler.Callback>? = null
                          , looper: Looper = Looper.myLooper()) : Handler(looper) {
    override fun dispatchMessage(msg: Message?) {
        super.dispatchMessage(msg)
    }
    override fun handleMessage(msg: Message) {
        val callback = weakCallback?.get() ?: return // Already disposed
        callback.handleMessage(msg)
    }
}

private class WeakRunnable(private val delegate: WeakReference<Runnable>
                           , private val reference: WeakReference<ChainedRef>) : Runnable {

    override fun run() {
        Log.d("WeakRunnable", "run")
        val delegate = delegate.get()
        val reference = reference.get()
        reference?.remove()
        delegate?.run()
    }
}

private class ChainedRef(var lock: Lock,runnable: Runnable) {
    var next: ChainedRef? = null
    var prev: ChainedRef? = null
    val wrapper: WeakRunnable
    val runnableWeakRef: WeakReference<Runnable>

    init {
        runnableWeakRef = WeakReference(runnable)
        this.wrapper = WeakRunnable(runnableWeakRef, WeakReference(this))
    }

    fun remove(): WeakRunnable {
        lock.lock()
        try {
            prev?.next = next
            next?.prev = prev
            prev = null
            next = null
        } finally {
            lock.unlock()
        }
        return wrapper
    }

    fun insertAfter(candidate: ChainedRef) {
        lock.lock()
        try {
            this.next?.prev = candidate

            candidate.next = this.next
            this.next = candidate
            candidate.prev = this
        } finally {
            lock.unlock()
        }
    }

    fun remove(runnable: Runnable): WeakRunnable? {
        lock.lock()
        try {
            var curr = this.next // Skipping head
            while (curr != null) {
                if (curr.runnableWeakRef.get() == null) {
                    val nullChainedRef = curr
                    curr = curr.next
                    nullChainedRef.remove()
                    continue
                } else if (curr.runnableWeakRef.get() === runnable) {
                    // We do comparison exactly how Handler does inside
                    return curr.remove()
                }
                curr = curr.next
            }
        } finally {
            lock.unlock()
        }
        return null
    }
}

private val defaultWeakHandler = WeakHandler()
