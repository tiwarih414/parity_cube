package com.paritycube.paritycube_assignmernt.home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigInteger


class DealsModel {

    @SerializedName("seo_setting")
    @Expose
    var seoSetting: SeoSetting? = null
    @SerializedName("deals")
    @Expose
    var deals: Deals? = null

    class Datum {
        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("fpd_flag")
        @Expose
        var fpdFlag: Boolean? = null
        @SerializedName("off_percent")
        @Expose
        var offPercent: String? = null
        @SerializedName("current_price")
        @Expose
        var currentPrice: Int? = null
        @SerializedName("original_price")
        @Expose
        var originalPrice: Int? = null
        @SerializedName("image")
        @Expose
        var image: String? = null
        @SerializedName("comments_count")
        @Expose
        var commentsCount: Int? = null
        @SerializedName("all_posts_count")
        @Expose
        var allPostsCount: Int? = null
        @SerializedName("created_at")
        @Expose
        var createdAt: Long? = null
        @SerializedName("score")
        @Expose
        var score: Int? = null
        @SerializedName("vote_value")
        @Expose
        var voteValue: Int? = null
        @SerializedName("state")
        @Expose
        var state: String? = null
        @SerializedName("description")
        @Expose
        var description: Any? = null
        @SerializedName("share_url")
        @Expose
        var shareUrl: String? = null
        @SerializedName("deal_url")
        @Expose
        var dealUrl: String? = null
        @SerializedName("view_count")
        @Expose
        var viewCount: Int? = null
        @SerializedName("vote_down_reason")
        @Expose
        var voteDownReason: VoteDownReason? = null
        @SerializedName("vote_count")
        @Expose
        var voteCount: Int? = null
        @SerializedName("fpd_suggestted")
        @Expose
        var fpdSuggestted: Boolean? = null
        @SerializedName("merchant")
        @Expose
        var merchant: Merchant? = null
        @SerializedName("front_page_suggestions_count")
        @Expose
        var frontPageSuggestionsCount: Int? = null
        @SerializedName("user")
        @Expose
        var user: User? = null
    }

    class Deals {
        @SerializedName("data")
        @Expose
        var data: ArrayList<Datum>? = null

        @SerializedName("total_count")
        @Expose
        val totalCount: Int? = null
    }

    class Merchant {

        @SerializedName("id")
        @Expose
        private val id: Int? = null
        @SerializedName("name")
        @Expose
        private val name: String? = null
        @SerializedName("image")
        @Expose
        private val image: String? = null
        @SerializedName("permalink")
        @Expose
        private val permalink: String? = null
        @SerializedName("recommendation")
        @Expose
        private val recommendation: Int? = null
        @SerializedName("recommendation_flag")
        @Expose
        private val recommendationFlag: Boolean? = null
        @SerializedName("average_rating")
        @Expose
        private val averageRating: String? = null
    }

    class SeoSetting {
        @SerializedName("title")
        @Expose
        private val title: String? = null
        @SerializedName("description")
        @Expose
        private val description: String? = null
        @SerializedName("web_url")
        @Expose
        private val webUrl: String? = null
    }

    class User {
        private val id: Int? = null
        private val name: String? = null
        private val image: String? = null
        private val rank: String? = null
        private val current_dimes: Int? = null
        private val karma: Int? = null
        private val fpd_count: Int? = null
    }

    class VoteDownReason {
        @SerializedName("Referral link")
        @Expose
        private val referralLink: Int? = null
        @SerializedName("Better price elsewhere")
        @Expose
        private val betterPriceElsewhere: Int? = null
        @SerializedName("Product not good/not worth the price")
        @Expose
        private val productNotGoodNotWorthThePrice: Int? = null
        @SerializedName("Other reasons")
        @Expose
        private val otherReasons: Int? = null
        @SerializedName("Invalid/User Specific Coupon/Deal")
        @Expose
        private val invalidUserSpecificCouponDeal: Int? = null
        @SerializedName("Repost")
        @Expose
        private val repost: Int? = null
        @SerializedName("Self Promotion")
        @Expose
        private val selfPromotion: Int? = null
    }
}