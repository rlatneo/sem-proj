package semi.project.jsnr.jibsa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JibsaController {
	
	@GetMapping("jibsaMain.js")
	public String jibsaMain() {
		return "jibsaMain";
	}
	
	@GetMapping("jibsaManagementSchedule.js")
	public String jibsaManagementSchedule() {
		return "jibsaManagementSchedule";
	}
	
	@GetMapping("jibsaModifyInfo.js")
	public String jibsaModifyInfo() {
		return "jibsaModifyInfo";
	}
	
	@GetMapping("jibsaModifySchedule.js")
	public String jibsaModifySchedule() {
		return "jibsaModifySchedule";
	}
	
	@GetMapping("jibsaWorkTime.js")
	public String jibsaWorkTime() {
		return "jibsaWorkTime";
	}
	
	@GetMapping("personal.js")
	public String personal() {
		return "personal";
	}
	
	@GetMapping("premium.js")
	public String premium() {
		return "premium";
	}
	
	@GetMapping("review.js")
	public String review() {
		System.out.println("review.js");
		return "review";
	}
	
	@GetMapping("secession.js")
	public String secession() {
		System.out.println("secession.js");
		return "secession";
	}
}
