package com.example.cricketer.domain

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class CricketerService(@Autowired val cricketerRepository: CricketerRepository) {
    val logger = LoggerFactory.getLogger(CricketerService::class.java)

    suspend fun save(cricketer: Cricketer): Cricketer {
        return cricketerRepository.save(cricketer)
    }

    suspend fun findById(id: Long): Optional<Cricketer> {
        return cricketerRepository.findById(id)
    }

    fun getAllPlayers(page: Int = 0): CricketerResponse {
        val pageNumber = if (page < 1) 0 else page - 1
        val pageable = PageRequest.of(pageNumber, 5, Sort.Direction.DESC, "name")

        return CricketerResponse.fromCricketerPage(cricketerRepository.findCricketers(pageable))
    }

    fun searchPlayers(query: String, page: Int = 0): CricketerResponse {
        val pageNumber = if (page < 1) 0 else page - 1
        val pageable = PageRequest.of(pageNumber, 5, Sort.Direction.DESC, "name")
        return CricketerResponse.fromCricketerPage(cricketerRepository.findByNameContainsIgnoreCase(query, pageable))
    }

    suspend fun deleteById(id: Long) {
        return cricketerRepository.deleteById(id)
    }
}