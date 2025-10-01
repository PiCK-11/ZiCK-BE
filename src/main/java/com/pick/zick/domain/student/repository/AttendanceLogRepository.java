package com.pick.zick.domain.student.repository;

import com.pick.zick.domain.student.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {
    List<AttendanceLog> findAllByUser_AppliedTrue();
    //언더바는 중첩된 속성을 구분하기 위해 사용됨
}