package com.flicker.searchimage

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.flicker.searchimage.components.PhotoItemView
import com.flicker.searchimage.network.PhotoX
import org.junit.Rule
import org.junit.Test

class PhotoItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun photoItemView_DisplayedCorrectly() {
        // Create a mock PhotoX object
        val mockPhoto = PhotoX(farm=66, id="53222104367", isfamily=0, isfriend=0, ispublic=1, owner="190477311@N05", secret="3f3ac7afaa", server="65535", title="Broadstone, Poole")

        // Set up the UI
        composeTestRule.setContent {
            PhotoItemView(photo = mockPhoto, onItemClick = {})
        }

        // Check that the Image and Text are displayed
        composeTestRule.onNodeWithContentDescription("Image").assertIsDisplayed()
        composeTestRule.onNodeWithText(mockPhoto.title).assertIsDisplayed()
    }
}
