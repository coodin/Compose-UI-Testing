package com.example.compose.rally


import org.junit.Rule
import org.junit.Test
import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar

import org.junit.runners.Parameterized

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun rallyTopAppBarTest() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { /*TODO*/ },
                currentScreen = RallyScreen.Accounts
            )
        }
        composeTestRule.onRoot(useUnmergedTree = true)
            .printToLog("currentLabelExists")
        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()) and hasParent(
                    hasContentDescription(
                        RallyScreen.Accounts.name
                    )
                ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun TabSelectionTopAppBar() {

        composeTestRule.setContent {
            var currentScreen by  remember {
                mutableStateOf(RallyScreen.Bills)
            }
            val allScreens = RallyScreen.values().toList()

            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { screen -> currentScreen = screen },
                currentScreen = currentScreen
            )
        }
        composeTestRule.onRoot(useUnmergedTree = true)
            .printToLog("currentLabelExists")
        val node = composeTestRule.onNode(
            hasTestTag("tab") and
                    hasContentDescription(RallyScreen.Accounts.name),
            useUnmergedTree = true
        ).performClick()
        node.assertIsSelected()
//        node.assertHasClickAction().performClick()
//            .printToLog("AfterClickedAccounts")


//        val nodeAfter = composeTestRule.onNode(
//            hasTestTag("tab") and
//                    hasContentDescription(RallyScreen.Accounts.name),
//            useUnmergedTree = true
//        )
        //nodeAfter.printToLog("AfterClickedAccounts")
        //node.assertIsSelected()
        //node.assertIsDisplayed()
        //node.performClick()
        composeTestRule.onNode(isSelected()).printToLog("isSelected")

    }
}