package com.example.cs5610f20serverjavatianledong.repositories;

import com.example.cs5610f20serverjavatianledong.models.Widget;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
  @Query(value = "SELECT * FROM widgets WHERE topicId=:tid", nativeQuery = true)
  List<Widget> findWidgetsForTopic(@Param("tid") String tid);
}