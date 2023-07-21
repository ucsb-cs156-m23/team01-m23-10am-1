package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="PublicHoliday info from https://date.nager.at")
@Slf4j
@RestController
@RequestMapping("/api/v2/publicholidays")
public class PublicHolidaysController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService PublicHolidayQueryService;

    @Operation(summary = "Get the date of a publicholiday in a year", description = "JSON return format documented here: https://date.nager.at")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHolidays(
        @Parameter(name="year", description="what year", example="2023") @RequestParam String year,
        @Parameter(name="countryCode", description="the code of the country", example="us") @RequestParam String countryCode
    ) throws JsonProcessingException {
        log.info("getPublicHolidays: year={} countryCode={}", year, countryCode);
        String result = PublicHolidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }

}
