package org.example.app.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerDTOrequest(Long id, String name, String phone, String address) {
}
