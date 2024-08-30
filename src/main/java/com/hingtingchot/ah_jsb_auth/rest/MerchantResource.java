package com.hingtingchot.ah_jsb_auth.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hingtingchot.ah_jsb_auth.model.MerchantDTO;
import com.hingtingchot.ah_jsb_auth.service.MerchantService;


@RestController
@RequestMapping(value = "/api/merchants", produces = MediaType.APPLICATION_JSON_VALUE)
public class MerchantResource {

    private final MerchantService merchantService;

    public MerchantResource(final MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> getAllMerchants() {
        return ResponseEntity.ok(merchantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> getMerchant(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(merchantService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createMerchant(@RequestBody @Valid final MerchantDTO merchantDTO) {
        final Long createdId = merchantService.create(merchantDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateMerchant(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final MerchantDTO merchantDTO) {
        merchantService.update(id, merchantDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteMerchant(@PathVariable(name = "id") final Long id) {
        merchantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
