package com.feelsokman.androidtemplate.extensions

import android.os.Binder
import android.os.Bundle
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import android.view.View
import androidx.core.view.doOnAttach
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import java.io.Serializable
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun FragmentActivity.savedState(
    key: String = SavedState.Key,
    defaultBundle: () -> Bundle? = { intent?.extras }
): Lazy<SavedState> = lazy {
    SavedState({ this }, key, defaultBundle).also { savedState ->
        savedState.registerSavedStateProvider()
    }
}

fun Fragment.savedState(
    key: String = SavedState.Key,
    defaultBundle: () -> Bundle? = { arguments }
): Lazy<SavedState> = lazy {
    SavedState({ this }, key, defaultBundle).also { savedState ->
        savedState.registerSavedStateProvider()
    }
}

fun View.savedState(
    key: String = SavedState.Key,
    defaultBundle: () -> Bundle? = { Bundle() }
): Lazy<SavedState> = lazy {
    SavedState({ findViewTreeSavedStateRegistryOwner()!! }, "$key@$id", defaultBundle).also { savedState ->
        doOnAttach {
            savedState.registerSavedStateProvider()
        }
    }
}

class SavedState constructor(
    private val owner: () -> SavedStateRegistryOwner,
    private val key: String,
    defaultBundle: () -> Bundle?
) {
    companion object {
        internal const val Key = "com.feelsokman.androidtemplate.extensions.key"
    }

    private val savedState by lazy {
        owner().savedStateRegistry.consumeRestoredStateForKey(key) ?: defaultBundle() ?: Bundle()
    }

    internal fun registerSavedStateProvider() {
        owner().savedStateRegistry.registerSavedStateProvider(key) { savedState }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> property(): ReadWriteProperty<Any, T> = object : ReadWriteProperty<Any, T> {
        override operator fun getValue(thisRef: Any, property: KProperty<*>): T = savedState.get(property.name) as T
        override operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) = setValue(property, value)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> property(defaultValue: T): ReadWriteProperty<Any, T> = object : ReadWriteProperty<Any, T> {
        override operator fun getValue(thisRef: Any, property: KProperty<*>): T =
            savedState.get(property.name)?.let { it as T } ?: defaultValue

        override operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) = setValue(property, value)
    }

    private fun <T> setValue(property: KProperty<*>, value: T) {
        when (value) {
            is Boolean -> savedState.putBoolean(property.name, value)
            is BooleanArray -> savedState.putBooleanArray(property.name, value)
            is Double -> savedState.putDouble(property.name, value)
            is DoubleArray -> savedState.putDoubleArray(property.name, value)
            is Int -> savedState.putInt(property.name, value)
            is IntArray -> savedState.putIntArray(property.name, value)
            is Long -> savedState.putLong(property.name, value)
            is LongArray -> savedState.putLongArray(property.name, value)
            is String -> savedState.putString(property.name, value)
            is Binder -> savedState.putBinder(property.name, value)
            is Bundle -> savedState.putBundle(property.name, value)
            is Byte -> savedState.putByte(property.name, value)
            is ByteArray -> savedState.putByteArray(property.name, value)
            is Char -> savedState.putChar(property.name, value)
            is CharArray -> savedState.putCharArray(property.name, value)
            is CharSequence -> savedState.putCharSequence(property.name, value)
            is Float -> savedState.putFloat(property.name, value)
            is FloatArray -> savedState.putFloatArray(property.name, value)
            is Parcelable -> savedState.putParcelable(property.name, value)
            is Serializable -> savedState.putSerializable(property.name, value)
            is Short -> savedState.putShort(property.name, value)
            is ShortArray -> savedState.putShortArray(property.name, value)
            is Size -> savedState.putSize(property.name, value)
            is SizeF -> savedState.putSizeF(property.name, value)
            else -> throw IllegalArgumentException(
                "Can't set the property(${property.name})'s value($value). Use property(getValue: Bundle.(key: String) -> T, setValue: Bundle.(key: String, value: T) -> Unit) method."
            )
        }
    }

    fun <T> property(
        getValue: Bundle.(String) -> T
    ): ReadOnlyProperty<Any, T> = object : ReadOnlyProperty<Any, T> {
        override operator fun getValue(thisRef: Any, property: KProperty<*>): T = savedState.getValue(property.name)
    }

    fun <T> property(
        getValue: Bundle.(String) -> T,
        setValue: Bundle.(String, T) -> Unit
    ): ReadWriteProperty<Any, T> = object : ReadWriteProperty<Any, T> {
        override operator fun getValue(thisRef: Any, property: KProperty<*>): T = savedState.getValue(property.name)
        override operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
            savedState.setValue(property.name, value)
    }
}
