package com.example.cs5610f20serverjavatianledong.services;

import com.example.cs5610f20serverjavatianledong.models.Widget;
import com.example.cs5610f20serverjavatianledong.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WidgetService {
  @Autowired
  WidgetRepository widgetRepository;

  public List<Widget> findAllWidgets() {
    return (List<Widget>) widgetRepository.findAll();
  }

  public Widget findWidgetById(Integer widgetId) {
    return widgetRepository.findById(widgetId).orElse(null);
  }

  public int updateAllWidgets(String tid, List<Widget> newWidgets) {
    int order = 0;
    for (Widget widget : newWidgets) {
      widget.setWidgetOrder(order++);
    }
    widgetRepository.saveAll(newWidgets);
    return 1;
  }

  private int maxWidgetOrderForTopic(String tid) {
    List<Widget> tWidgets = widgetRepository.findWidgetsForTopic(tid);
    int maxOrder = -1;
    for (Widget widget : tWidgets) {
      if (widget.getWidgetOrder() > maxOrder) {
        maxOrder = widget.getWidgetOrder();
      }
    }
    return maxOrder;
  }

  public Widget createWidget(String tid, Widget widget) {
    widget.setTopicId(tid);
    widget.setWidgetOrder(maxWidgetOrderForTopic(tid) + 1);
    return widgetRepository.save(widget);
  }

  public Integer updateWidget(Integer widgetId, Widget newWidget) {
    Widget w = findWidgetById(widgetId);
    if (w != null) {
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
      widgetRepository.save(w);
      return 1;
    }
    return 0;
  }

  public List<Widget> findWidgetsForTopic(String tid) {
    return widgetRepository.findWidgetsForTopic(tid);
  }

  public int deleteWidget(Integer wid) {
    widgetRepository.deleteById(wid);
    return 1;
  }
}
