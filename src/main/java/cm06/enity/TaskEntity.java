package cm06.enity;

import java.sql.Timestamp;

public class TaskEntity {
    private int id;
    private UserEntity user;      // ID của người dùng
    private ProjectEntity project;   // ID của dự án
    private StatusEntity status;    // ID của trạng thái
    private String name;     // Tên nhiệm vụ
    private Timestamp startDate;  // Ngày bắt đầu
    private Timestamp endDate;    // Ngày kết thúc

    // Getter và setter cho thuộc tính id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public ProjectEntity getProject() {
		return project;
	}
	public void setProject(ProjectEntity project) {
		this.project = project;
	}
	public StatusEntity getStatus() {
		return status;
	}
	public void setStatus(StatusEntity status) {
		this.status = status;
	}
	// Getter và setter cho thuộc tính name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter và setter cho thuộc tính startDate
    public Timestamp getStartDate() {
        return startDate;
    }
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    // Getter và setter cho thuộc tính endDate
    public Timestamp getEndDate() {
        return endDate;
    }
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
