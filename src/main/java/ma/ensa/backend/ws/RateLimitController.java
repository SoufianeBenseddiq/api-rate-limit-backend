package ma.ensa.backend.ws;

import ma.ensa.backend.service.facade.RateLimitService;
import ma.ensa.backend.utils.enums.WindowType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rate-limit")
public class RateLimitController {

}