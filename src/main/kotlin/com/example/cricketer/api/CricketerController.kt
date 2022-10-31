package com.example.cricketer.api

import com.example.cricketer.domain.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    fun getCricketer(@PathVariable("id") id: Long): ResponseEntity<CricketerDTO> {
        if(cricketerService.findById(id).isPresent()) {
            return ResponseEntity.ok(CricketerDTO.toDto(cricketerService.findById(id).get()))
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/cricketers")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCricketer(@RequestBody @Valid cricketer: CricketerRequest): CricketerDTO {
        return cricketerService.createCricketer(cricketer)
    }

    @PutMapping("/cricketers/{id}")
    fun updateCricketer(@PathVariable("id") id: Long, @RequestBody cricketer: Cricketer): ResponseEntity<Cricketer> {
        if(!cricketerService.findById(id).isPresent()) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val existingCricketer = cricketerService.findById(id) .get()?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val newCricketer = cricketerService.save(existingCricketer.copy(country = cricketer.country, name = cricketer.name,
            highestScore = cricketer.highestScore))
        return ResponseEntity(newCricketer, HttpStatus.OK)
    }

    @DeleteMapping("/cricketers/{id}")
    fun deleteCricketer(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        if(!cricketerService.findById(id).isPresent()) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(cricketerService.deleteById(id),HttpStatus.OK)
    }
}