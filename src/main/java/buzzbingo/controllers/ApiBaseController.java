package buzzbingo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/v1")
public abstract class ApiBaseController {
  public static final String API_VERSION = "v1";

}
