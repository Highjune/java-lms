package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private final List<Session> sessions = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Session getSession(int generation) {
        validateGeneration(generation);
        return this.sessions.get(generation - 1);
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    private void validateGeneration(int generation) {
        validateNegative(generation);
        validateRange(generation);
    }

    private void validateNegative(int generation) {
        if (generation <= 0) {
            throw new IllegalArgumentException("기수는 1 기수 이상부터 시작합니다. 조회한 기수 = " + generation);
        }
    }

    private void validateRange(int generation) {
        if (sessions.size() < generation) {
            throw new IllegalArgumentException("해당 기수의 강의는 존재하지 않습니다. 조회한 기수 = " + generation);
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", sessions=" + sessions +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
