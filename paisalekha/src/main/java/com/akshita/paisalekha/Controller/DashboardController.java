package com.akshita.paisalekha.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.akshita.paisalekha.dto.DashboardResponse;
import com.akshita.paisalekha.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard(@RequestParam String username) {
        return dashboardService.getDashboard(username);
    }
}