@file:Suppress("NOTHING_TO_INLINE")

package com.feelsokman.androidtemplate.extensions

import timber.log.Timber

object Timber {
    /** Log a verbose exception and a message that will be evaluated lazily when the message is printed */
    @JvmStatic
    inline fun v(t: Throwable? = null, message: () -> String) = log { Timber.v(t, message()) }

    @JvmStatic
    inline fun v(t: Throwable?) = Timber.v(t)

    /** Log a debug exception and a message that will be evaluated lazily when the message is printed */
    @JvmStatic
    inline fun d(t: Throwable? = null, message: () -> String) = log { Timber.d(t, message()) }

    @JvmStatic
    inline fun d(t: Throwable?) = Timber.d(t)

    /** Log an info exception and a message that will be evaluated lazily when the message is printed */
    @JvmStatic
    inline fun i(t: Throwable? = null, message: () -> String) = log { Timber.i(t, message()) }

    @JvmStatic
    inline fun i(t: Throwable?) = Timber.i(t)

    /** Log a warning exception and a message that will be evaluated lazily when the message is printed */
    @JvmStatic
    inline fun w(t: Throwable? = null, message: () -> String) = log { Timber.w(t, message()) }

    @JvmStatic
    inline fun w(t: Throwable?) = Timber.w(t)

    /** Log an error exception and a message that will be evaluated lazily when the message is printed */
    @JvmStatic
    inline fun e(t: Throwable? = null, message: () -> String) = log { Timber.e(t, message()) }

    @JvmStatic
    inline fun e(t: Throwable?) = Timber.e(t)

    /** Log an assert exception and a message that will be evaluated lazily when the message is printed */
    @JvmStatic
    inline fun wtf(t: Throwable? = null, message: () -> String) = log { Timber.wtf(t, message()) }

    @JvmStatic
    inline fun wtf(t: Throwable?) = Timber.wtf(t)

    // These functions just forward to the real timber. They aren't necessary, but they allow method
    // chaining like the normal Timber interface.

    /** A view into Timber's planted trees as a tree itself. */
    @JvmStatic
    inline fun asTree(): Timber.Tree = Timber.asTree()

    /** Add a new logging tree. */
    @JvmStatic
    inline fun plant(tree: Timber.Tree) = Timber.plant(tree)

    /** Set a one-time tag for use on the next logging call. */
    @JvmStatic
    inline fun tag(tag: String): Timber.Tree = Timber.tag(tag)

    /** A view into Timber's planted trees as a tree itself. */
    @JvmStatic
    inline fun uproot(tree: Timber.Tree) = Timber.uproot(tree)

    /** Set a one-time tag for use on the next logging call. */
    @JvmStatic
    inline fun uprootAll() = Timber.uprootAll()

    /** A [Timber.Tree] for debug builds. Automatically infers the tag from the calling class. */
    @JvmStatic
    inline fun DebugTree() = Timber.DebugTree()
}

//
// Extensions on trees
//

/** Log a verbose exception and a message that will be evaluated lazily when the message is printed */
inline fun Timber.Tree.logVerbose(t: Throwable? = null, message: () -> String) = log { v(t, message()) }

/** Log a debug exception and a message that will be evaluated lazily when the message is printed */
inline fun Timber.Tree.logDebug(t: Throwable? = null, message: () -> String) = log { d(t, message()) }

/** Log an info exception and a message that will be evaluated lazily when the message is printed */
inline fun Timber.Tree.logInfo(t: Throwable? = null, message: () -> String) = log { i(t, message()) }

/** Log a warning exception and a message that will be evaluated lazily when the message is printed */
inline fun Timber.Tree.logWarning(t: Throwable? = null, message: () -> String) = log { w(t, message()) }

/** Log an error exception and a message that will be evaluated lazily when the message is printed */
inline fun Timber.Tree.logError(t: Throwable? = null, message: () -> String) = log { e(t, message()) }

/** Log an assert exception and a message that will be evaluated lazily when the message is printed */
inline fun Timber.Tree.logWhatTheFuck(t: Throwable? = null, message: () -> String) = log { wtf(t, message()) }

//
// Plain functions
//

/** Log a verbose exception and a message that will be evaluated lazily when the message is printed */
inline fun logVerbose(t: Throwable? = null, message: () -> String) = log { Timber.v(t, message()) }

inline fun logVerbose(t: Throwable?) = Timber.v(t)

/** Log a debug exception and a message that will be evaluated lazily when the message is printed */
inline fun logDebug(t: Throwable? = null, message: () -> String) = log { Timber.d(t, message()) }

inline fun logDebug(t: Throwable?) = Timber.d(t)

/** Log an info exception and a message that will be evaluated lazily when the message is printed */
inline fun logInfo(t: Throwable? = null, message: () -> String) = log { Timber.i(t, message()) }

inline fun logInfo(t: Throwable?) = Timber.i(t)

/** Log a warning exception and a message that will be evaluated lazily when the message is printed */
inline fun logWarning(t: Throwable? = null, message: () -> String) = log { Timber.w(t, message()) }

inline fun logWarning(t: Throwable?) = Timber.w(t)

/** Log an error exception and a message that will be evaluated lazily when the message is printed */
inline fun logError(t: Throwable? = null, message: () -> String) = log { Timber.e(t, message()) }

inline fun logError(t: Throwable?) = Timber.e(t)

/** Log an assert exception and a message that will be evaluated lazily when the message is printed */
inline fun logWhatTheFuck(t: Throwable? = null, message: () -> String) = log { Timber.wtf(t, message()) }

inline fun logWhatTheFuck(t: Throwable?) = Timber.wtf(t)

/** @suppress */
@PublishedApi
internal inline fun log(block: () -> Unit) {
    if (Timber.treeCount() > 0) block()
}
