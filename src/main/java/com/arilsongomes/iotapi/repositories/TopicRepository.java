package com.arilsongomes.iotapi.repositories;

import com.arilsongomes.iotapi.entity.Device;
import com.arilsongomes.iotapi.entity.Topic;
import com.arilsongomes.iotapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Override
    Optional<Topic> findById(Long topicId);

    @Query("SELECT t FROM Topic t WHERE t.user = :user")
    List<Topic> findByUser(@Param("user") User user);
}
