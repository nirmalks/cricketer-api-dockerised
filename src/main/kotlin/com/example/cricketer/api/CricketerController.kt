package com.example.cricketer.api

import com.example.cricketer.domain.Cricketer
import com.example.cricketer.domain.CricketerResponse
import com.example.cricketer.domain.CricketerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class CricketerController(private val cricketerService: CricketerService) {
    @GetMapping("/cricketers")
    fun getAllCricketers(
        @RequestParam(
            name = "page",
            required = false,
            defaultValue = "1"
        ) page: Int,
        @RequestParam(
            name = "query",
            required = false,
            defaultValue = ""
        ) query: String
    ): CricketerResponse {
        if (query.isEmpty()) {
            return cricketerService.getAllPlayers(page)
        }
        return cricketerService.searchPlayers(query, page)
    }

    @GetMapping("/cricketers/{id}")
    suspend fun getCricketer(@PathVariable("id") id: Long): ResponseEntity<Cricketer> {
        if(cricketerService.findById(id).isPresent()) {
            return ResponseEntity.ok(cricketerService.findById(id).get())
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/cricketers")
    suspend fun addCricketer(@RequestBody cricketer: Cricketer): ResponseEntity<Cricketer> {
        return ResponseEntity(cricketerService.save(cricketer),HttpStatus.CREATED)
    }

    @PutMapping("/cricketers/{id}")
    suspend fun updateCricketer(@PathVariable("id") id: Long, @RequestBody cricketer: Cricketer): ResponseEntity<Cricketer> {
        if(!cricketerService.findById(id).isPresent()) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val existingCricketer = cricketerService.findById(id) .get()?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val newCricketer = cricketerService.save(existingCricketer.copy(country = cricketer.country, name = cricketer.name,
            highestScore = cricketer.highestScore))
        return ResponseEntity(newCricketer, HttpStatus.OK)
    }

    @DeleteMapping("/cricketers/{id}")
    suspend fun deleteCricketer(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        if(!cricketerService.findById(id).isPresent()) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(cricketerService.deleteById(id),HttpStatus.OK)
    }
}