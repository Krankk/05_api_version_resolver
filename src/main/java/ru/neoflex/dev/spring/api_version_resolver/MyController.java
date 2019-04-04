package ru.neoflex.dev.spring.api_version_resolver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.dev.spring.api_version_resolver.vMapping.VersionRange;

@RestController
public class MyController {


    @RequestMapping(value = "/url_content_type",
        produces = "application/vnd.myapi.v1+json")
    public ResponseEntity<String> url1_accept() {
        return ResponseEntity.ok("url1_accept");
    }

@RequestMapping(value = "/url_content_type",
        produces = "application/vnd.myapi.v2+json")
    public ResponseEntity<String> url2_accept() {
        return ResponseEntity.ok("url2_accept");
    }




    @VersionRange(Constants.V_1)
    @RequestMapping("/url_header")
    public ResponseEntity<String> url1_header() {
        return ResponseEntity.ok("url1_header");
    }

    @VersionRange(Constants.V_2)
    @RequestMapping("/url_header")
    public ResponseEntity<String> url2_header() {
        return ResponseEntity.ok("url2_header");
    }
}
