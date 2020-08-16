package com.feelsokman.androidtemplate.di

interface HasComponent<T> {
    val component: T
}

fun <T> Any.getComponent(): T = (this as HasComponent<T>).component
