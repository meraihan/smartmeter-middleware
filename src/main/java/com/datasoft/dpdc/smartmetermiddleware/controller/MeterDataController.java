package com.datasoft.dpdc.smartmetermiddleware.controller;

import com.datasoft.dpdc.smartmetermiddleware.model.Meter;
import com.datasoft.dpdc.smartmetermiddleware.service.MeterDataService;
import com.datasoft.dpdc.smartmetermiddleware.service.UserBillSummaryService;
import com.datasoft.dpdc.smartmetermiddleware.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/meter")
public class MeterDataController {

    @Autowired
    private UserBillSummaryService billSummaryService;
    @Autowired
    private MeterDataService meterDataService;
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String getMeterList(Model model) {

        List<Meter> meterList = meterDataService.findAllMeterList();
        model.addAttribute("meterList", meterList);
        return "meter/meter_list";
    }

    @GetMapping("/search")
    public String welcomePage(Model model) {
        model.addAttribute("title", "Search Meter");
        model.addAttribute("meterList", meterDataService.findMeterName());
        return "meter/search_meter";
    }

    @PostMapping("/search")
    public String meterData(HttpServletRequest request, Model model) {
        String meterNumber = request.getParameter("meterNumber");
        if(meterNumber!=null){
            Meter meter = meterDataService.findMeterData(meterNumber);
            model.addAttribute("meterData", meter);
            model.addAttribute("billSummary", billSummaryService.getBillSummary(meter.getId()));
        }
        return "meter/ajax_meter_data";
    }


    @RequestMapping(value = "/add")
    public String add(@ModelAttribute("add") Meter meter, HttpServletRequest request, Model model) {
        if (request.getMethod().equals(RequestMethod.POST.toString())) {
            if (meterDataService.addMeter(meter)) {
                model.addAttribute("success", "Meter Added Successfully");
            }  else {
                model.addAttribute("error", "Meter Add Failed");
            }
        }
        model.addAttribute("userList", meterDataService.findAllUser());
        return "meter/add_meter_data";
    }

    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, Model model, @ModelAttribute("meter") Meter meter) {
        if (request.getMethod().equals((RequestMethod.POST).toString())) {

        }
        Meter fetchMeterData = meterDataService.findMeterById(meter.getId());
        model.addAttribute("meterData", fetchMeterData);
        String name = userService.findUserById(fetchMeterData.getUser().getId()).getName();
        model.addAttribute("userName", name);
        return "meter/meter_edit";
    }
}
