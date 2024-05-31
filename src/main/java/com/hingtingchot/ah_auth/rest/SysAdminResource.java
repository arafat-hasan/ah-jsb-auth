package com.hingtingchot.ah_auth.rest;

import com.hingtingchot.ah_auth.model.SysAdminDTO;
import com.hingtingchot.ah_auth.service.SysAdminService;
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


@RestController
@RequestMapping(value = "/api/sysAdmins", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysAdminResource {

    private final SysAdminService sysAdminService;

    public SysAdminResource(final SysAdminService sysAdminService) {
        this.sysAdminService = sysAdminService;
    }

    @GetMapping
    public ResponseEntity<List<SysAdminDTO>> getAllSysAdmins() {
        return ResponseEntity.ok(sysAdminService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SysAdminDTO> getSysAdmin(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(sysAdminService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSysAdmin(@RequestBody @Valid final SysAdminDTO sysAdminDTO) {
        final Long createdId = sysAdminService.create(sysAdminDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSysAdmin(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final SysAdminDTO sysAdminDTO) {
        sysAdminService.update(id, sysAdminDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSysAdmin(@PathVariable(name = "id") final Long id) {
        sysAdminService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
