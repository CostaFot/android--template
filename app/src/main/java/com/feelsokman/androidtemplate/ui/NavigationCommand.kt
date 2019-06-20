package com.feelsokman.chatkeyboard.ui

sealed class NavigationCommand {
    object ShowPromptEnableKeyboard : NavigationCommand()
    object ShowEditTextAndHintsView : NavigationCommand()
}