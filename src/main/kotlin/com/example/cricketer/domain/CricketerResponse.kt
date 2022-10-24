package com.example.cricketer.domain

import org.springframework.data.domain.Page

data class CricketerResponse (
    val data: List<CricketerDTO>,
    val totalItems: Long,
    val totalPages: Int,
    val currentPage: Int,
    val hasNext: Boolean,
    val hasPrevious: Boolean,
    val isFirst: Boolean,
    val isLast: Boolean
) {
    companion object{
        fun fromCricketerPage(cricketerPageResponse: Page<CricketerDTO>) =
            CricketerResponse(
                cricketerPageResponse.content,
                cricketerPageResponse.totalElements,
                cricketerPageResponse.totalPages,
                cricketerPageResponse.number + 1,
                cricketerPageResponse.hasNext(),
                cricketerPageResponse.hasPrevious(),
                cricketerPageResponse.isFirst,
                cricketerPageResponse.isLast
            )
    }
}