package com.hmf.rest;

import com.hmf.service.Document;
import com.hmf.service.DocumentService;
import com.hmf.service.ResponseMetadata;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.io.File;

@Controller
@RequestMapping(value = "/doc")
public class DocumentController {

    private static final Logger LOG = Logger.getLogger(DocumentController.class);   
    @Autowired
    DocumentService documentService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody ResponseMetadata handleFileUpload(@RequestParam(value="file") MultipartFile file) throws IOException {
        return documentService.save(file);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    // Disable service during development
    public void maintainencemode(@PathVariable String id) {
    }
    public HttpEntity<byte[]> getDocument(@PathVariable String id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(documentService.getDocumentFile(id), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Document> getDocument() {
        return documentService.findAll();
    }
}

