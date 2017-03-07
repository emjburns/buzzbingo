package buzzbingo;

import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/v1")
public abstract class ApiBaseController {
  public static final String API_VERSION = "v1";
}
