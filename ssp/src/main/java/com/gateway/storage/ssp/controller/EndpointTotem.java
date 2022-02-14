package com.gateway.storage.ssp.controller;

import java.net.URI;
import java.util.Date;

import javax.validation.Valid;

import com.gateway.storage.ssp.entities.TotemPacket;
import com.gateway.storage.ssp.services.GatewayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/totem")
public class EndpointTotem {

    @Autowired
    private GatewayService gatewayStorageService;

    @GetMapping
    public String getGatewayTotem() {
        Date date = new Date();        
        return "Return from Totem Endpoint - " + date;
    }

    @PostMapping 
    public ResponseEntity<TotemPacket> toPacketStoragee(@Valid @RequestBody TotemPacket totemPacket)
    {   
        String response = gatewayStorageService.blobStorage(totemPacket.getFileName(), totemPacket.getData());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{totem}").buildAndExpand(response).toUri();
        return ResponseEntity.created(uri).build();
    }
}

