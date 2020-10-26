package com.example.cs5610f20serverjavatianledong.controllers;

import com.example.cs5610f20serverjavatianledong.models.Widget;
import com.example.cs5610f20serverjavatianledong.services.WidgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {
  @Autowired
  WidgetService service;

  @PostMapping("/api/topics/{tid}/widgets")
  public Widget createWidget(@PathVariable("tid") String tid, Widget widget) {
    return service.createWidget(tid, widget);
  }

  @GetMapping("/api/topics/{tid}/widgets")
  public List<Widget> findWidgetsForTopic(@PathVariable("tid") String tid) {
    return service.findWidgetsForTopic(tid);
  }

  @PutMapping("/api/widgets/{wid}")
  public int updateWidget(@PathVariable("wid") String wid, Widget widget) {
    return service.updateWidget(wid, widget);
  }

  @DeleteMapping("/api/widgets/{wid}")
  public int deleteWidget(@PathVariable("wid") String wid) {
    return service.deleteWidget(wid);
  }

  @GetMapping("/api/widgets")
  public List<Widget> findAllWidgets() {
    return service.findAllWidgets();
  }

  @GetMapping("/api/widgets/{wid}")
  public Widget findWidgetById(@PathVariable("wid") String wid) {
    return service.findWidgetById(wid);
  }
}
