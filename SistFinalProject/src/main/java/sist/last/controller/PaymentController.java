package sist.last.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {

	@GetMapping("/payment/pay-success")
    public ModelAndView paymentSuccess() {
        // payment.jsp로 이동
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/payment/payment");
        return modelAndView;
    }
}
