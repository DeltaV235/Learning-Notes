package Service;

import org.springframework.stereotype.Service;

@Service
public class SpringService {
    public String serviceMethod(String uri) {
        return "Hello World!" + " " + uri;
    }
}
