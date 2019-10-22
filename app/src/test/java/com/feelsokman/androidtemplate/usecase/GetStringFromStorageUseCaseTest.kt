package com.feelsokman.androidtemplate.usecase

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.feelsokman.storage.LocalStorage
import com.feelsokman.storage.Storage
import io.reactivex.android.schedulers.AndroidSchedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetStringFromStorageUseCaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var getStringFromStorageUseCase: GetStringFromStorageUseCase
    private lateinit var storage: Storage

    lateinit var appContext: Context

    @Before
    fun setUp() {
        appContext = ApplicationProvider.getApplicationContext()

        storage = LocalStorage(appContext)
        getStringFromStorageUseCase = GetStringFromStorageUseCase(AndroidSchedulers.mainThread(), storage)
    }

    @Test
    fun getStringFromStorage() {
    }
}