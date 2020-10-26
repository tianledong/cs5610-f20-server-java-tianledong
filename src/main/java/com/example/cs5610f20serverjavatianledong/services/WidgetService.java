package com.example.cs5610f20serverjavatianledong.services;

import com.example.cs5610f20serverjavatianledong.models.Widget;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WidgetService {
  List<Widget> widgets = new ArrayList<>();

  public List<Widget> findAllWidgets() {
    return widgets;
  }

  public Widget findWidgetById(String widgetId) {
    for (Widget w : widgets) {
      if (w.getId().equals(widgetId))
        return w;
    }
    return null;
  }

  public Widget createWidget(String tid, Widget widget) {
    String uuid = UUID.randomUUID().toString();
    widget.setId(uuid);
    widget.setTopicId(tid);
    widgets.add(widget);
    return widget;
  }

  public Integer updateWidget(
          String widgetId,
          Widget newWidget) {
    for (Widget w : widgets) {
      if (w.getId().equals(widgetId)) {
        w.setName(newWidget.getName());
        w.setType(newWidget.getType());
        w.setCssClass(newWidget.getCssClass());
        w.setHeight(newWidget.getHeight());
        w.setWidth(newWidget.getWidth());
        w.setWidgetOrder(newWidget.getWidgetOrder());
        w.setText(newWidget.getText());
        w.setSrc(newWidget.getSrc());
        w.setSize(newWidget.getSize());
        w.setStyle(newWidget.getStyle());
        w.setValue(newWidget.getValue());
        return 1;
      }
    }
    return 0;
  }

  public List<Widget> findWidgetsForTopic(String tid) {
    List<Widget> topicWidgets = new ArrayList<>();
    for (Widget widget : widgets) {
      if (widget.getTopicId().equals(tid)) {
        topicWidgets.add(widget);
      }
    }
    return topicWidgets;
  }

  public int deleteWidget(String wid) {
    for (Widget widget : widgets) {
      if (widget.getTopicId().equals(wid)) {
        widgets.remove(widget);
        return 1;
      }
    }
    return 0;
  }
}
