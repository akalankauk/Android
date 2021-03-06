/*
 * Copyright (c) 2018 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.app.privacy.renderer

import android.support.test.InstrumentationRegistry
import com.duckduckgo.app.browser.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TrackersRendererTest {

    private val context = InstrumentationRegistry.getTargetContext()
    private val testee = TrackersRenderer()

    @Test
    fun whenNoTrackersThenTrackersTextShowsZeroBlocked() {
        val text = testee.trackersText(context, 0, true)
        assertEquals("0 Trackers Blocked", text)
    }

    @Test
    fun whenTenTrackersAndAllBlockedThenTrackerTextShowsTenBlocked() {
        val text = testee.trackersText(context, 10, true)
        assertEquals("10 Trackers Blocked", text)
    }

    @Test
    fun whenTenTrackersAndNotAllBlockedThenTrackerTextShowsTenFound() {
        val text = testee.trackersText(context, 10, false)
        assertEquals("10 Trackers Found", text)
    }

    @Test
    fun whenNoMajorNetworksThenNetworksTextShowsZeroBlocked() {
        val text = testee.majorNetworksText(context, 0, true)
        assertEquals("0 Major Tracker Networks Blocked", text)
    }

    @Test
    fun whenTenMajorNetworksAndAllBlockedThenNetworksTextShowsTenBlocked() {
        val text = testee.majorNetworksText(context, 10, true)
        assertEquals("10 Major Tracker Networks Blocked", text)
    }

    @Test
    fun whenTenMajorNetworksAndNotAllBlockedThenNetworksTextShowsTenFound() {
        val text = testee.majorNetworksText(context, 10, false)
        assertEquals("10 Major Tracker Networks Found", text)
    }

    @Test
    fun whenPercentNullPercentageIsBlank() {
        val text = testee.percentage(null)
        assertEquals("", text)
    }

    @Test
    fun whenPercentIsRecurringFractionPercentageIsRoundNumber() {
        val text = testee.percentage(0.333333333f)
        assertEquals("33%", text)
    }

    @Test
    fun whenPercentIsHalfPercentageIs50Percent() {
        val text = testee.percentage(0.5f)
        assertEquals("50%", text)
    }

    @Test
    fun whenNoTrackerNetworksThenNetworksTextShowsZeroBlocked() {
        val text = testee.networksText(context, 0, true)
        assertEquals("0 Tracker Networks Blocked", text)
    }

    @Test
    fun whenTenTrackerNetworksAndAllBlockedThenNetworksTextShowsTenBlocked() {
        val text = testee.networksText(context, 10, true)
        assertEquals("10 Tracker Networks Blocked", text)
    }

    @Test
    fun whenTenTrackersNetworksAndNotAllBlockedThenNetworksTextShowsTenFound() {
        val text = testee.networksText(context, 10, false)
        assertEquals("10 Tracker Networks Found", text)
    }

    @Test
    fun whenAllTrackersBlockedThenNetworksBannerIsGood() {
        val resource = testee.networksBanner(true)
        assertEquals(R.drawable.networks_banner_good, resource)
    }

    @Test
    fun whenNotAllTrackersBlockedThenNetworksBannerIsBad() {
        val resource = testee.networksBanner(false)
        assertEquals(R.drawable.networks_banner_bad, resource)
    }

    @Test
    fun whenAllTrackersBlockedThenNetworksIconIsGood() {
        val resource = testee.networksIcon(true)
        assertEquals(R.drawable.networks_icon_good, resource)
    }

    @Test
    fun whenNotAllTrackersBlockedThenNetworksIconIsBad() {
        val resource = testee.networksIcon(false)
        assertEquals(R.drawable.networks_icon_bad, resource)
    }

    @Test
    fun whenNetworkNameMatchesPillIconThenResourceIsReturned() {
        val resource = testee.networkPillIcon(context, "facebook")
        assertEquals(R.drawable.network_pill_facebook, resource)
    }

    @Test
    fun whenNetworkNameMatchesLogoIconThenResourceIsReturned() {
        val resource = testee.networkLogoIcon(context, "facebook")
        assertEquals(R.drawable.network_logo_facebook, resource)
    }

    @Test
    fun whenNetworkNameSansDotsMatchesLogoIconThenResourceIsReturned() {
        val resource = testee.networkLogoIcon(context, "amazon.com")
        assertEquals(R.drawable.network_logo_amazoncom, resource)
    }

    @Test
    fun whenNetworkNameSansSpacesMatchesPillIconThenResourceIsReturned() {
        val resource = testee.networkLogoIcon(context, "fox one stop media")
        assertEquals(R.drawable.network_logo_foxonestopmedia, resource)
    }

    @Test
    fun whenNetworkNameDoesNotMatchLogoIconThenNull() {
        val resource = testee.networkLogoIcon(context, "unknownnetwork")
        assertNull(resource)
    }

    @Test
    fun whenCountIsZeroThenIconIsSuccess() {
        assertEquals(R.drawable.icon_success, testee.successFailureIcon(0))
    }

    @Test
    fun whenCountIsNotZeroThenIconIsFailure() {
        assertEquals(R.drawable.icon_fail, testee.successFailureIcon(1))
    }
}