package controller;


import Service.SpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringMVCController {
    private final SpringService service;

    @Autowired
    public SpringMVCController(SpringService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String requestHandler() {
        return service.serviceMethod("/test");
    }
}
