package com.datasoft.dpdc.smartmetermiddleware.controller;

import com.datasoft.dpdc.smartmetermiddleware.model.Meter;
import com.datasoft.dpdc.smartmetermiddleware.model.PaymentHistory;
import com.datasoft.dpdc.smartmetermiddleware.model.User;
import com.datasoft.dpdc.smartmetermiddleware.service.MeterDataService;
import com.datasoft.dpdc.smartmetermiddleware.service.PaymentHistoryService;
import com.datasoft.dpdc.smartmetermiddleware.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by rayhan on 9/20/18.
 */
@Controller
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    PaymentHistoryService paymentHistoryService;
    @Autowired
    MeterDataService meterDataService;
    @Autowired
    UserService userService;

    @GetMapping("payBill")
    public String getPaymentPage() {
        return "payment/pay_monthly";
    }

    @GetMapping("payForm")
    public String payFormPage() {
        return "payment/pay_card";
    }

    @GetMapping("paymentMethod")
    public String paymentMethod() {
        return "payment/payment_method";
    }

    @GetMapping("/searchBill")
    public String billData() {
        return "payment/ajax_bill_data";
    }

    @GetMapping("/mobileBanking")
    public String mobileBanking() {
        return "payment/pay_mobile_banking";
    }

    @GetMapping("/paySuccess")
    public String paySuccess() {
        return "payment/pay_success";
    }

    @GetMapping("/paymentInfo")
    public String paymentInfo(Model model, @ModelAttribute("meter") Meter meter){
        List<PaymentHistory> meterWisePaymentHistoryList;
        meterWisePaymentHistoryList = paymentHistoryService.findPaymentHistory(meter.getId());
        model.addAttribute("paymentHistoryList", meterWisePaymentHistoryList);

        Meter meterInfo = meterDataService.findMeterById(meter.getId());
        User user = userService.findUserById(meterInfo.getUser().getId());
        model.addAttribute("UserInfo", user);
        String address = user.getAddress().getHouse()+", "+ user.getAddress().getBlock() +", "+
                user.getAddress().getRoad()+", "+user.getAddress().getPostOffice() +", "+
                user.getAddress().getPostOffice()+", "+ user.getAddress().getZipCode()+", "+user.getAddress().getDistrict()
                +", "+ "Zone: " + user.getAddress().getZone();
        model.addAttribute("address", address);
        return "payment/pay_info";
    }
}